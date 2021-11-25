package com.example.yanadu.data.repository;

import android.util.Log;
import android.widget.Toast;
import com.example.yanadu.data.model.CheckReturn;
import com.example.yanadu.data.model.Note;
import com.example.yanadu.data.model.NoteId;
import com.example.yanadu.data.model.ObjectData;
import com.example.yanadu.data.model.ResultData;
import com.example.yanadu.data.request.ApiRequestFactory;
import com.example.yanadu.data.request.OnGetData;
import com.example.yanadu.data.request.ToDoAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.ArrayList;
import java.util.List;

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
        scheduleService.writeToDO(note).enqueue(new Callback<NoteId>() {
            @Override
            public void onResponse(Call<NoteId> call, Response<NoteId> response) {
                if (response.isSuccessful()) {
                    onget.onGetData(response.body());

                }
            }

            @Override
            public void onFailure(Call<NoteId> call, Throwable t) {

            }

        });
    }


    //user id 보낼시 서버에서 user와 관련된 투두 리스트 가져와
    public void getToDO(String id){
        scheduleService.getToDOList(id).enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
                List<ObjectData> od = new ArrayList<ObjectData>();
                if (response.isSuccessful()) {
                    for (Note note : response.body()) {

                        od.add(note);
                    }
                    onget.onGetDataList(od);


                }
            }

            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {

            }

        });
    }

    //해당 todolit _id 서버에 보낼시 삭제
    public void deleteToDO(String _id){
        scheduleService.deleteToDo(_id).enqueue(new Callback<CheckReturn>() {
            @Override
            public void onResponse(Call<CheckReturn> call, Response<CheckReturn> response) {
                if (response.isSuccessful()) {
//                    Log.d("삭제응답응답",response.body().getCheck()+"");
                    onget.onGetData(response.body());

                }
            }

            @Override
            public void onFailure(Call<CheckReturn> call, Throwable t) {

            }

        });
    }
}