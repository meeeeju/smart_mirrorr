package com.example.yanadu.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NoteId extends ObjectData implements Serializable {

    @SerializedName("_id")
    int _id;  //todo number

    public NoteId(int _id) {
        this._id = _id;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}

