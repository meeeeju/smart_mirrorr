package com.example.yanadu.data.repository;

import com.example.yanadu.data.request.ApiRequestFactory;
import com.example.yanadu.data.request.ScheduleAPI;
import com.example.yanadu.data.request.UserAPI;

import retrofit2.Retrofit;



public class ScheduleRepository {
    private static ScheduleAPI scheduleService;

    public ScheduleRepository(){
        Retrofit a1= ApiRequestFactory.getInstance();
        scheduleService= a1.create(ScheduleAPI.class);
    }
}
