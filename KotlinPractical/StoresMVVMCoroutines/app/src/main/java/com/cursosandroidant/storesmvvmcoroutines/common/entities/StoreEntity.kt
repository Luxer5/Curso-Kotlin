package com.cursosandroidant.storesmvvmcoroutines.common.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

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
@Entity(tableName = "StoreEntity", indices = [Index(value = ["name"], unique = true)])
data class StoreEntity(@PrimaryKey(autoGenerate = true) var id: Long = 0,
                       var name: String,
                       var phone: String,
                       var website: String = "",
                       var photoUrl: String,
                       var isFavorite: Boolean = false){

    constructor() : this(name = "", phone = "", photoUrl = "")
}
