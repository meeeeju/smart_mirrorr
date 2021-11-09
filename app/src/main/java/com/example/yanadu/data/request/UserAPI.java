package com.example.yanadu.data.request;

import com.example.yanadu.data.model.UserData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserAPI {
    @GET("/user")
    Call<UserData> getData(@Query("id")String query);
}
