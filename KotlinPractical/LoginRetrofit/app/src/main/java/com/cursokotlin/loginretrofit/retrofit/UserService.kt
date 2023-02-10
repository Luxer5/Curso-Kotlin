package com.cursokotlin.loginretrofit.retrofit

import com.cursokotlin.loginretrofit.Constants
import retrofit2.http.GET

interface UserService {
    @GET(Constants.API_PATH + Constants.USERS_PATH + Constants.TWO_PATH)
    suspend fun getSingleUser(): SingleUserResponse
}