package com.example.yanadu.retrofit

import com.example.restapitutorial.utils.API
import com.example.yanadu.data.Sample

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IRetrofit {

    @GET(API.SEARCH_USER)
    fun getdata(@Query("query") searchTerm:String) : Call<Sample>



    @GET(API.SEARCH_USER)
    fun getdatas() : Call<Sample>

}