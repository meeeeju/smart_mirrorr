package com.example.yanadu.data.request;

import com.example.yanadu.data.model.ResultData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

//맥박,산소포화도,혈압 관련 데이터 API입니다.
public interface ResultDataAPI {

    @GET("/healthdata")
    Call<List<ResultData>> getHealthdata(@Query("name") String name);

    @GET("/healthweeklydata")
    Call<List<ResultData>> getWeeklydata(@Query("id") String id);  //주

    @GET("/healthmonthlydata")
    Call<List<ResultData>> getMonthlydata(@Query("id") String id);  //달


    @GET("/healyhdailydata")
    Call<ResultData> getDailyData(@Query("id") String id);  //메인에 띄울 데이터



}
