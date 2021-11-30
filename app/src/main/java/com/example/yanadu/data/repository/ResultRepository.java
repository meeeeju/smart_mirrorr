package com.example.yanadu.data.repository;

import android.util.Log;

import com.example.yanadu.data.model.ObjectData;
import com.example.yanadu.data.model.ResultData;
import com.example.yanadu.data.request.ApiRequestFactory;
import com.example.yanadu.data.request.OnGetData;
import com.example.yanadu.data.request.ResultDataAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultRepository {

    private static ResultDataAPI ResultDataService=null;

    OnGetData onget;
    public ResultRepository(OnGetData onget){
        if (ResultDataService==null)
            ResultDataService= ApiRequestFactory.getInstance().create(ResultDataAPI.class);
        this.onget = onget;
    }

    public void requestHealthdata(String id){
        ResultDataService.getHealthdata(id).enqueue(new Callback<List<ResultData>>() {
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

    public void requestHealthWeeklydata(String id,String date){
        ResultDataService.getHealthWeeklydata(id,date).enqueue(new Callback<List<ResultData>>() {
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

    public void requesthealthdailydata(String id,String date){
        ResultDataService.getHealthDaydata(id,date).enqueue(new Callback<ResultData>() {
            @Override
            public void onResponse(Call<ResultData> call, Response<ResultData> response) {
                if (response.isSuccessful())
                {
                    ResultData rd = response.body();
                    onget.onGetData(rd);
                    Log.d("on","실행됨");
                }
                else{
                    Log.d("","실행안됨");
                }

            }
            @Override
            public void onFailure(Call<ResultData> call, Throwable t) {
                Log.d("mTag", t.toString());
            }
        });
    }
}
