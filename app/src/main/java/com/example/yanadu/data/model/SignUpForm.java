package com.example.yanadu.data.model;

import com.google.gson.annotations.SerializedName;

public class SignUpForm {

    @SerializedName("nickname")
    String nickname;
    @SerializedName("id")
    String id;
    @SerializedName("passwd")
    String passwd;
    @SerializedName("email")
    String email;
    @SerializedName("sex")
    String sex;
    @SerializedName("smoking")
    String smoking;
    @SerializedName("birth")
    String birth;


}
