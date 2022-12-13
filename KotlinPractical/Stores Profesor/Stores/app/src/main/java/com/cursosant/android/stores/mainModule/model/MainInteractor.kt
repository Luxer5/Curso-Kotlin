package com.cursosant.android.stores.mainModule.model

import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.cursosant.android.stores.StoreApplication
import com.cursosant.android.stores.common.entities.StoreEntity
import com.cursosant.android.stores.common.utils.Constants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainInteractor {

    fun getStores(callback: (MutableList<StoreEntity>) -> Unit){

        val url = Constants.STORES_URL + Constants.GET_ALL_PATH

        var storeList = mutableListOf<StoreEntity>()

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null, { response ->
            Log.i("Response", response.toString())

            val status = response.optInt(Constants.STATUS_PROPERTY, Constants.ERROR)
            //val status = response.getInt(Constants.STATUS_PROPERTY)

            if (status == Constants.SUCCESS){

                Log.i ("status", status.toString())

                val jsonList = response.optJSONArray(Constants.STORES_PROPERTY)?.toString()
                 if (jsonList != null) {
                     val mutableListType = object : TypeToken<MutableList<StoreEntity>>() {}.type
                     storeList = Gson().fromJson(jsonList, mutableListType)

                     callback(storeList)
                     return@JsonObjectRequest
                 }
            }
            callback(storeList)
        },{
            it.printStackTrace()
            callback(storeList)
        })

        StoreApplication.storeAPI.addToRequestQueue(jsonObjectRequest)
    }

    fun getStoresRoom(callback: (MutableList<StoreEntity>)-> Unit){
        doAsync {
            val storesList = StoreApplication.database.storeDao().getAllStores()
            uiThread {
                val json = Gson().toJson(storesList)
                Log.i("Gson", json)
                callback(storesList)
            }
        }
    }

    fun deleteStore(storeEntity: StoreEntity, callback: (StoreEntity) -> Unit){
        doAsync {
            StoreApplication.database.storeDao().deleteStore(storeEntity)
            uiThread {
                callback(storeEntity)
            }
        }
    }

    fun updateStore(storeEntity: StoreEntity, callback: (StoreEntity) -> Unit){
        doAsync {
            StoreApplication.database.storeDao().updateStore(storeEntity)
            uiThread {
                callback(storeEntity)
            }
        }
    }
}