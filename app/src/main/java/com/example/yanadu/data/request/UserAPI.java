package com.example.yanadu.data.request;

import com.example.yanadu.data.model.SignInForm;
import com.example.yanadu.data.model.SignUpForm;
import com.example.yanadu.data.model.UserData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserAPI {
//    @GET("/user")
//    Call<UserData> getData(@Query("id")String query);

//    @GET("/user")
//    Call<UserData> getUserData(@Body( id String ) String id, String pw))
    @FormUrlEncoded
    @POST("/user")
    Call<UserData> signIn(@Field("userid") String userid,
                          @Field("userpw") String userpw);
  //  Call<UserData> signIn(@Body SignInForm signInForm );
}
