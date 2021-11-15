package com.example.yanadu.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserData extends ObjectData implements Serializable {
    @SerializedName("name")
    String name;
    @SerializedName("id")
    String id;
    @SerializedName("pwd")
    String pwd;
    @SerializedName("email")
    String email;
    @SerializedName("sex")
    String sex;
    @SerializedName("smoking")
    String smoking;
    @SerializedName("birthday")
    String birthday;
    @SerializedName("result")
    Boolean result;


    public String getNickname() {
        return name;
    }

    public void setNickname(String nickname) {
        this.name = nickname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPasswd() {
        return pwd;
    }

    public void setPasswd(String passwd) {
        this.pwd = passwd;
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
        return birthday;
    }

    public void setBirth(String birth){this.birthday=birth;}

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }


}
