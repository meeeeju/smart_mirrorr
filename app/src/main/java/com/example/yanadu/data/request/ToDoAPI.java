package com.example.yanadu.data.request;


import com.example.yanadu.data.model.Note;
import com.example.yanadu.data.model.SignInForm;
import com.example.yanadu.data.model.SignUpForm;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

//스케줄 관련 API입니다. 사용자가 기록한 TODOLIST, 날짜
public interface ToDoAPI {

    @POST("/ ")  //지정해줘야행
    Call<Boolean> signIn(@Body Note note);


}
