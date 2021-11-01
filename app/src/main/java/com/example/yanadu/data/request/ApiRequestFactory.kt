package com.example.yanadu.data.request

import com.example.yanadu.data.model.SignupForm
import com.example.yanadu.data.request.UserService


import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager
import okhttp3.JavaNetCookieJar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ApiRequestFactory {
    private const val baseUrl = ""   //baseUrl 적어주기!

//    val headerInterceptor = Interceptor {
//        val request = it.request()
//            .newBuilder()
//            .addHeader("Content-Type", "application/json")
//            .addHeader("Content-Type","multipart/form-data")   //무엇인 찾아보기
//            .build()
//        return@Interceptor it.proceed(request)
//    }

    val retrofit = Retrofit.Builder()   //레트로핏 빌드
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
//        .client(OkHttpClient.Builder()
//            .addInterceptor(HttpLoggingInterceptor().apply { this.level = HttpLoggingInterceptor.Level.BODY })
//            .addInterceptor(headerInterceptor)
//            .cookieJar(JavaNetCookieJar(CookieManager()))
//            .build())
        .build()

    val userService : UserService by lazy{
        retrofit.create(UserService::class.java)
    }

    var loginService: UserService = retrofit.create(UserService::class.java)






}