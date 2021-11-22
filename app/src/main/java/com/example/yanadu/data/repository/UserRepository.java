package com.example.yanadu.data.repository;

import android.util.Log;

import com.example.yanadu.data.model.SignInForm;
import com.example.yanadu.data.model.SignUpForm;
import com.example.yanadu.data.model.User;
import com.example.yanadu.data.request.ApiRequestFactory;
import com.example.yanadu.data.request.OnGetData;
import com.example.yanadu.data.request.UserAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private static UserAPI userService;
    OnGetData onget;
    public UserRepository(OnGetData onget){
        this.onget = onget;
        userService=ApiRequestFactory.getInstance().create(UserAPI.class);
    }


    public void requestSignIn(SignInForm s1){
        userService.signIn(s1).enqueue(new Callback<SignUpForm>() {
            @Override
            public void onResponse(Call<SignUpForm> call, Response<SignUpForm> response) {
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
            public void onFailure(Call<SignUpForm> call, Throwable t) {
                Log.d("mTag", t.toString());
            }
        });
    }

    public  void requestSignUp(SignUpForm u1){
        userService.signUp(u1).enqueue(new Callback<SignUpForm>() {
            @Override
            public void onResponse(Call<SignUpForm> call, Response<SignUpForm> response) {
                if (response.isSuccessful()) {
//                    Log.d("id", response.body().getId());
//                    Log.d("pw",response.body().getPasswd());
//                    Log.d("name", response.body().getNickname());
//                    Log.d("email", response.body().getEmail());
//                    Log.d("sex",response.body().getSex());
//                    Log.d("smoking",response.body().getSmoking());
//                    Log.d("birth",response.body().getBirth());
                    onget.onSendDate(response.body());
                }
            }
            @Override
            public void onFailure(Call<SignUpForm> call, Throwable t) {
                Log.d("mTag", t.toString());
            }
        });
    }

    //SignUpForm=user상세 정보
    public  void requestUserData(User u1){
        userService.userInfo(u1).enqueue(new Callback<SignUpForm>() {
            @Override
            public void onResponse(Call<SignUpForm> call, Response<SignUpForm> response) {
                if (response.isSuccessful()) {
//                    Log.d("id", response.body().getId());
//                    Log.d("pw",response.body().getPasswd());
//                    Log.d("name", response.body().getNickname());
//                    Log.d("email", response.body().getEmail());
//                    Log.d("sex",response.body().getSex());
//                    Log.d("smoking",response.body().getSmoking());
//                    Log.d("birth",response.body().getBirth());
                    onget.onGetData(response.body());

                }
            }
            @Override
            public void onFailure(Call<SignUpForm> call, Throwable t) {
                Log.d("mTag", t.toString());
            }
        });
    }




}
