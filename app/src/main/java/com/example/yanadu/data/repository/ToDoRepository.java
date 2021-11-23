package com.example.yanadu.data.repository;

import com.example.yanadu.data.model.CheckReturn;
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
        scheduleService.writeToDO(note).enqueue(new Callback<CheckReturn>() {
            @Override
            public void onResponse(Call<CheckReturn> call, Response<CheckReturn> response) {
                if (response.isSuccessful()) {


                }
            }

            @Override
            public void onFailure(Call<CheckReturn> call, Throwable t) {

            }

        });
    }





}


