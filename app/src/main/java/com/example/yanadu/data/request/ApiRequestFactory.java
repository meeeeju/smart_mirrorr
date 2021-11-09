package com.example.yanadu.data.request;

import android.util.Log;

import com.example.yanadu.data.model.UserData;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRequestFactory {
    String url = "http://125.6.37.219:16002";
    UserAPI api;

    public ApiRequestFactory() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .build();
        //dbCallback = listener;
        api = retrofit.create(UserAPI.class);
    }

    public void getData(){
        api.getData("id").enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                if(response.isSuccessful()){
                    Log.d("id", response.body().getId());
                    Log.d("name", response.body().getNickname());
                    Log.d("email", response.body().getEmail());
                }
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                Log.d("mTag", t.toString());
            }
        });
    }
}
