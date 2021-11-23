package com.example.yanadu.data.repository;

import android.util.Log;
import android.widget.Toast;
import com.example.yanadu.data.model.CheckReturn;
import com.example.yanadu.data.model.Note;
import com.example.yanadu.data.model.ObjectData;
import com.example.yanadu.data.model.ResultData;
import com.example.yanadu.data.request.ApiRequestFactory;
import com.example.yanadu.data.request.OnGetData;
import com.example.yanadu.data.request.ToDoAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;


public class ToDoRepository {
    private static ToDoAPI scheduleService=null;
    OnGetData onget;

    public ToDoRepository(OnGetData onget){
        this.onget=onget;
        if (scheduleService==null)
            scheduleService= ApiRequestFactory.getInstance().create(ToDoAPI.class);
    }


    public void setToDO(Note note){
        scheduleService.writeToDO(note).enqueue(new Callback<CheckReturn>() {
            @Override
            public void onResponse(Call<CheckReturn> call, Response<CheckReturn> response) {
                if (response.isSuccessful()) {
                    onget.onGetData(response.body());

                }
            }

            @Override
            public void onFailure(Call<CheckReturn> call, Throwable t) {

            }

        });
    }








}


