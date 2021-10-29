package com.example.restapitutorial.retrofit

import android.util.Log
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.stream.DoubleStream.builder
import kotlin.math.log

//싱글턴이 적용 (메모리를 하나만씀)
 object RetrofitClient {
     //레트로핏 클라이언트 선언
     private var retrofitClient: Retrofit?=null



    //레트로핏 클라이언트 가져오기
    fun getClient(baseUrl:String):Retrofit?{
        Log.d("TAG","retrofitClient-getClient() called")

        if (retrofitClient==null){

            //레트로핏 빌드
            retrofitClient=Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofitClient;
    }

}