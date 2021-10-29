package com.example.yanadu.data.request

import retrofit2.Call
import com.example.yanadu.data.model.SignupForm
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface UserService {

    @FormUrlEncoded
    @POST("/app_login/")
    fun requestLogin(
        @Field("nickname") nickname:String,
        @Field("userid") userid:String,
        @Field("password") password:String,
        @Field("email") email:String
    ) : Call<SignupForm>
}