package com.example.yanadu.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Note extends ObjectData implements Serializable {

    @SerializedName("_id")
    int _id;  //todo number
    @SerializedName("todo")
    String todo;
    @SerializedName("date")
    String date;
    @SerializedName("id")
    String id;   //user id

    public Note(int _id, String todo, String date, String id) {
        this._id = _id;
        this.todo = todo;
        this.date = date;
        this.id = id;
    }

    public Note(String todo, String date, String id) {  //setToDo시

        this.todo = todo;
        this.date = date;
        this.id = id;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}