package com.example.yanadu.data.request;


import com.example.yanadu.data.model.CheckReturn;
import com.example.yanadu.data.model.Note;

import com.example.yanadu.data.model.ObjectData;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

//스케줄 관련 API입니다. 사용자가 기록한 TODOLIST, 날짜
public interface ToDoAPI {

    //todolist에 글 적은거 서버로 보내서 등록해주기
    @POST("/todo ")  //지정해줘야행
    Call<CheckReturn> writeToDO(@Body Note note);


    //todolist에 적은거 삭제하고싶은 경우
    @POST("/todoDelete")
    Call<CheckReturn> deleteToDo(@Body String _id); //Note의 PK만 넘겨주고싶음


}
