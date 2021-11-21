package com.example.yanadu.data.repository;

import com.example.yanadu.data.request.ApiRequestFactory;
import com.example.yanadu.data.request.ToDoAPI;

import retrofit2.Retrofit;



public class ToDoRepository {
    private static ToDoAPI scheduleService;

    public ToDoRepository(){
        Retrofit a1= ApiRequestFactory.getInstance();
        scheduleService= a1.create(ToDoAPI.class);
    }
}
