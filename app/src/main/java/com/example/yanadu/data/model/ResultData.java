package com.example.yanadu.data.model;

import com.google.gson.annotations.SerializedName;

public class ResultData extends ObjectData {

    @SerializedName("pulse")
    double pulse;
    @SerializedName("o2")
    double o2;
    @SerializedName("bloodMin")
    double bloodMin;
    @SerializedName("bloodMax")
    double bloodMax;
    @SerializedName("date")
    String date;
    @SerializedName("name")
    String name;

    public double getPulse() {
        return pulse;
    }

    public double getO2() {
        return o2;
    }

    public double getBloodMin() {
        return bloodMin;
    }

    public double getBloodMax() {
        return bloodMax;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }
}
