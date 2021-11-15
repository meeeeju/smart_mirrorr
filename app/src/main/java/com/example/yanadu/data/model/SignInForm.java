package com.example.yanadu.data.model;

import com.google.gson.annotations.SerializedName;

public class SignInForm {

    @SerializedName("id")
    String id;
    @SerializedName("pwd")
    String pwd;

    public SignInForm(String toString, String toString1) {
        id=toString;
        pwd=toString1;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setPwd(String passwd) {
        this.pwd = passwd;
    }
}


