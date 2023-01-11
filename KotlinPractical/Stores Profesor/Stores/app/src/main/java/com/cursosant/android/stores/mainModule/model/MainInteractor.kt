package com.cursosant.android.stores.mainModule.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
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

    /*fun getStores (callback: (MutableList<StoreEntity>) -> Unit){
        //isLocal nos ayuda a cambiar entre la fuente donde consultemos los datos
        val isLocal = true
        if(isLocal) {
            getStoresRoom { storeList -> callback(storeList) }
        }else {
            getStoresAPI { storesList -> callback(storesList)}
        }

    }*/

    /*fun getStoresAPI(callback: (MutableList<StoreEntity>) -> Unit){

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
    }*/

    /*fun getStoresRoom(callback: (MutableList<StoreEntity>)-> Unit){
        doAsync {
            val storeList = StoreApplication.database.storeDao().getAllStores()
            uiThread {
                val json = Gson().toJson(storeList)
                Log.i("Gson", json)
                callback(storeList)
            }
        }
    }*/

    val stores: LiveData<MutableList<StoreEntity >> = liveData {
        kotlinx.coroutines.delay(1_000) //temporal para pruebas
        val storesLiveData = StoreApplication.database.storeDao().getAllStores()
        emitSource(storesLiveData.map { stores ->
            stores.sortedBy { it.name }.toMutableList()
        })

    }

    fun deleteStore(storeEntity: StoreEntity, callback: (StoreEntity) -> Unit){
        doAsync {
            StoreApplication.database.storeDao().deleteStore(storeEntity)
            uiThread {
                callback(storeEntity)
            }
        }
    }

    suspend fun updateStore(storeEntity: StoreEntity){
        StoreApplication.database.storeDao().updateStore(storeEntity)

    }
}