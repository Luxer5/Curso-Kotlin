package com.cursokotlin.loginretrofit.retrofit

data class RegisterResponse(var token: String, var id: String) : SuccessResponse(token)