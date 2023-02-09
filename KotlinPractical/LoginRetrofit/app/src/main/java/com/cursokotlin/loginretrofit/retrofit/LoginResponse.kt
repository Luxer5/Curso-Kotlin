package com.cursokotlin.loginretrofit.retrofit


data class LoginResponse(var token: String) : SuccessResponse(token)
