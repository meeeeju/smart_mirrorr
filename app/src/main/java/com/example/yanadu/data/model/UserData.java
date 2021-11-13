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
    @SerializedName("sex")
    String sex;
    @SerializedName("smoking")
    String smoking;
    @SerializedName("birth")
    String birth;

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex){this.sex=sex;}

    public String getSmoking() {
        return smoking;
    }

    public void setSmoking(String smoking){this.smoking=smoking;}

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth){this.birth=birth;}
}
