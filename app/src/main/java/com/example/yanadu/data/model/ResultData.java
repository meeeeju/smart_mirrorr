package com.example.yanadu.data.model;

import com.google.gson.annotations.SerializedName;

public class ResultData {

    @SerializedName("pulse")
    double pulse;
    @SerializedName("o2")
    double o2;
    @SerializedName("blood")
    double blood;
    @SerializedName("date")
    String date;
    @SerializedName("name")
    String name;

}
