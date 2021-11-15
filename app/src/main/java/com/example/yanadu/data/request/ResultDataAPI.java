package com.example.yanadu.data.request;

import com.example.yanadu.data.model.ResultData;
import com.example.yanadu.data.model.SignInForm;
import com.example.yanadu.data.model.UserData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

//맥박,산소포화도,혈압 관련 데이터 API입니다.
public interface ResultDataAPI {
    @GET("/healthdata")
    Call<List<ResultData>> getHealthdata(@Query("name") String name);

}
