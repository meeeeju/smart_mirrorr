package com.example.yanadu.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserData implements Serializable {
    @SerializedName("nickname")
    String nickname;
    @SerializedName("id")
    String id;
    @SerializedName("passwd")
    String passwd;
    @SerializedName("email")
    String email;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
