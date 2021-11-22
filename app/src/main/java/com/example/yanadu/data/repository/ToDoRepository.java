package com.example.yanadu.data.repository;

import com.example.yanadu.data.model.Note;
import com.example.yanadu.data.request.ApiRequestFactory;
import com.example.yanadu.data.request.OnGetData;
import com.example.yanadu.data.request.ToDoAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ToDoRepository {
    private static ToDoAPI scheduleService;
    OnGetData onget;

    public ToDoRepository(OnGetData onget){
        this.onget=onget;
        scheduleService= ApiRequestFactory.getInstance().create(ToDoAPI.class);
    }


    public void setToDO(Note note){
        scheduleService.signIn(note).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    if (response.body())
                    {
                        //등록되었다고 알려주기.
                    }

                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }

        });
    }

    //코드가 중복되니까 그냥 0 1 로 바꾸면 안되나?
    public void deleteToDO(Note note){
        scheduleService.signIn(note).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    if (response.body())
                    {
                        //삭제되었다고 알려주기
                    }

                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }

        });
    }




}


