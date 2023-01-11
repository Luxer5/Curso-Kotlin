package com.cursosandroidant.storesmvvmcoroutines.common.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cursosandroidant.storesmvvmcoroutines.common.entities.StoreEntity

/****
 * Project: Stores Coroutines
 * From: com.cursosandroidant.storesmvvmcoroutines
 * Created by Alain Nicolás Tello on 09/02/22 at 12:34 PM
 * Course: Android Practical with Kotlin from zero.
 * Only on: https://www.udemy.com/course/kotlin-intensivo/
 * All rights reserved 2021.
 * My website: www.alainnicolastello.com
 * All my Courses(Only on Udemy):
 * https://www.udemy.com/user/alain-nicolas-tello/
 ***/
@Dao
interface StoreDao {
    @Query("SELECT * FROM StoreEntity")
    fun getAllStores() : LiveData<MutableList<StoreEntity>>

    @Query("SELECT * FROM StoreEntity where id = :id")
    fun getStoreById(id: Long): LiveData<StoreEntity>

    @Insert
    suspend fun addStore(storeEntity: StoreEntity): Long

    @Update
    suspend fun updateStore(storeEntity: StoreEntity): Int

    @Delete
    suspend fun deleteStore(storeEntity: StoreEntity): Int
}