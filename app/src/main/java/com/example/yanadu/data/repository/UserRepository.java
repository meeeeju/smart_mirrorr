package com.example.yanadu.data.repository;

import android.util.Log;

import com.example.yanadu.data.model.SignInForm;
import com.example.yanadu.data.model.SignUpForm;
import com.example.yanadu.data.model.UserData;
import com.example.yanadu.data.request.ApiRequestFactory;
import com.example.yanadu.data.request.OnGetData;
import com.example.yanadu.data.request.UserAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserRepository {

    private static UserAPI userService;
    OnGetData onget;
    public UserRepository(OnGetData onget){
        this.onget = onget;
        userService=ApiRequestFactory.getInstance().create(UserAPI.class);
    }


    public void requestSignIn(SignInForm s1){
        userService.signIn(s1).enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                if (response.isSuccessful()) {
//                    Log.d("id", response.body().getId());
//                    Log.d("pw",response.body().getPasswd());
//                    Log.d("name", response.body().getNickname());
//                    Log.d("email", response.body().getEmail());
//                    Log.d("sex",response.body().getSex());
//                    Log.d("smoking",response.body().getSmoking());
//                    Log.d("birth",response.body().getBirth());
//                    Log.d("birth",response.body().getResult()+"");
                    onget.onGetData(response.body());
                }
            }
            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                Log.d("mTag", t.toString());
            }
        });
    }

    public static void requestSignUp(UserData u1){
        userService.signUp(u1).enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                if (response.isSuccessful()) {
                    Log.d("id", response.body().getId());
                    Log.d("pw",response.body().getPasswd());
                    Log.d("name", response.body().getNickname());
                    Log.d("email", response.body().getEmail());
                    Log.d("sex",response.body().getSex());
                    Log.d("smoking",response.body().getSmoking());
                    Log.d("birth",response.body().getBirth());
                }
            }
            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                Log.d("mTag", t.toString());
            }
        });
    }




}
