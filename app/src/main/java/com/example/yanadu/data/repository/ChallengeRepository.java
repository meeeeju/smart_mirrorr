package com.example.yanadu.data.repository;

import com.example.yanadu.data.request.ApiRequestFactory;
import com.example.yanadu.data.request.ChallengeAPI;
import com.example.yanadu.data.request.ResultDataAPI;

import retrofit2.Retrofit;

public class ChallengeRepository {


    private static ChallengeAPI ChallengeService;

    public ChallengeRepository(){
        Retrofit a1= ApiRequestFactory.getInstance();
        ChallengeService= a1.create(ChallengeAPI.class);
    }
}
