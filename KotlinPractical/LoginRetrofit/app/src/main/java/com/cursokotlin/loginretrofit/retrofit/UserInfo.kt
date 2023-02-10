package com.cursokotlin.loginretrofit.retrofit

import com.cursokotlin.loginretrofit.Constants
import com.google.gson.annotations.SerializedName

class UserInfo (
    @SerializedName(Constants.EMAIL_PARAM) val email: String,
    @SerializedName(Constants.PASSWORD_PARAM) val pass: String
        )