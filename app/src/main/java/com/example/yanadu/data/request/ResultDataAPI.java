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
    Call<List<ResultData>> getHealthWeeklydata(@Query("id") String id,@Query("date") String date);  //주

    @GET("/healthmonthlydata")
    Call<List<ResultData>> getMonthlydata(@Query("id") String id,@Query("date") String date);  //달


    @GET("/healyhdailydata")
    Call<ResultData> getHealthDaydata(@Query("id") String id,@Query("date") String date);  //메인에 띄울 데이터



}
