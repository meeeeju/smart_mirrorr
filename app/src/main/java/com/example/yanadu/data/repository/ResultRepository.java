package com.example.yanadu.data.repository;

import android.util.Log;

import com.example.yanadu.data.model.ObjectData;
import com.example.yanadu.data.model.ResultData;
import com.example.yanadu.data.model.SignInForm;
import com.example.yanadu.data.model.UserData;
import com.example.yanadu.data.request.ApiRequestFactory;
import com.example.yanadu.data.request.OnGetData;
import com.example.yanadu.data.request.ResultDataAPI;
import com.example.yanadu.data.request.UserAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ResultRepository {

    private static ResultDataAPI ResultDataService;

    OnGetData onget;
    public ResultRepository(OnGetData onget){
        ResultDataService= ApiRequestFactory.getInstance().create(ResultDataAPI.class);
        this.onget = onget;
    }

    public void requestHealthdata(String name){
        ResultDataService.getHealthdata(name).enqueue(new Callback<List<ResultData>>() {
            @Override
            public void onResponse(Call<List<ResultData>> call, Response<List<ResultData>> response) {
                List<ObjectData> od = new ArrayList<ObjectData>();
                if (response.isSuccessful()) {

                    for (ResultData rd : response.body()){
                        Log.d("pulse", rd.getPulse() + "");
                        Log.d("bloodmax",rd.getBloodMax()+"");
                        Log.d("bloodmin", rd.getBloodMin()+"");
                        Log.d("o2", rd.getO2()+"");
                        Log.d("date",rd.getDate()+"");
                        od.add(rd);
                    }
                    onget.onGetDataList(od);
                }
            }
            @Override
            public void onFailure(Call<List<ResultData>> call, Throwable t) {
                Log.d("mTag", t.toString());
            }
        });
    }



}