package com.example.yanadu.data.request;

import com.example.yanadu.data.model.SignInForm;
import com.example.yanadu.data.model.SignUpForm;
import com.example.yanadu.data.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


//사용자와 관련된 API입니다.
public interface UserAPI {

//    @GET("/user")
//    Call<UserData> getUserData(@Body( id String ) String id, String pw))

//    @FormUrlEncoded

    @POST("/user")
    Call<SignUpForm> signIn(@Body SignInForm signIn );

    @POST("/addUser")
    Call<SignUpForm> signUp(@Body SignUpForm signUp);

    @POST("/user")
    Call<SignUpForm> userInfo(@Body User user);
}
