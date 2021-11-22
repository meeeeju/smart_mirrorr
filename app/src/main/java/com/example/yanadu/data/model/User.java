package com.example.yanadu.data.model;

import com.google.gson.annotations.SerializedName;

public class User extends ObjectData{
    @SerializedName("name")
    String name;
    @SerializedName("id")
    String id;

    public User(String name, String id){
        this.name=name;
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
