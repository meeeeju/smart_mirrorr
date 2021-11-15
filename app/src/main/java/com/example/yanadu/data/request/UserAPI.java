package com.example.yanadu.data.request;

import com.example.yanadu.data.model.SignInForm;
import com.example.yanadu.data.model.SignUpForm;
import com.example.yanadu.data.model.UserData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


//사용자와 관련된 API입니다.
public interface UserAPI {

//    @GET("/user")
//    Call<UserData> getUserData(@Body( id String ) String id, String pw))
//    @FormUrlEncoded
    @POST("/user")
    Call<UserData> signIn(@Body SignInForm signInForm );

    @POST("/addUser")
    Call<UserData> signUp(@Body UserData user);
}
