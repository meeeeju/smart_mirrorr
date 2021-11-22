package com.example.yanadu.data.request;

import com.example.yanadu.data.model.CheckReturn;
import com.example.yanadu.data.model.UserData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


//사용자와 관련된 API입니다.
public interface UserAPI {

//    @GET("/user")
//    Call<UserData> getUserData(@Body( id String ) String id, String pw))

//    @FormUrlEncoded

    @POST("/user")
    Call<UserData> signIn(@Body UserData signIn );

    @POST("/addUser")
    Call<CheckReturn> signUp(@Body UserData signUp);


}
