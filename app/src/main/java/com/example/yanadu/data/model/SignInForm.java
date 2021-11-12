package com.example.yanadu.data.model;

import com.google.gson.annotations.SerializedName;

public class SignInForm {

    @SerializedName("id")
    String id;
    @SerializedName("passwd")
    String passwd;

    public SignInForm(String toString, String toString1) {
        id=toString;
        passwd=toString1;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setPwd(String passwd) {
        this.passwd = passwd;
    }
}


