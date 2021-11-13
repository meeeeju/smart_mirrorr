package com.example.yanadu.data.repository;

import com.example.yanadu.data.request.ApiRequestFactory;
import com.example.yanadu.data.request.ResultDataAPI;
import com.example.yanadu.data.request.UserAPI;

import retrofit2.Retrofit;

public class ResultRepository {

    private static ResultDataAPI ResultDataService;

    public ResultRepository(){
        Retrofit a1= ApiRequestFactory.getInstance();
        ResultDataService= a1.create(ResultDataAPI.class);
    }



}
