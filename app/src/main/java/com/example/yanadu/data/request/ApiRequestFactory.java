package com.example.yanadu.data.request;


import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class  ApiRequestFactory {


    private static final String BASE_URL = "http://125.6.37.219:16002";
    private static Retrofit retrofit = null;

    public  ApiRequestFactory(){
    }

    public static Retrofit getInstance()
    {


        if (retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                    .build();
        }
        return retrofit;
    }







}
