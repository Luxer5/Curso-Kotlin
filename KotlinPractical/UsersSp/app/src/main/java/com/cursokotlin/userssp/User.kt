package com.cursokotlin.userssp

data class User(val id: Long, var name: String, var lastName: String, var url: String) {

    fun getfullName(): String= "$name $lastName"
}