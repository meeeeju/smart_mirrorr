package com.example.yanadu.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResultData extends ObjectData implements Serializable {

    @SerializedName("pulse")
    double pulse;
    @SerializedName("spo")
    double o2;
    @SerializedName("bloodMin")
    double bloodMin;
    @SerializedName("bloodMax")
    double bloodMax;
    @SerializedName("date")
    String date;
    @SerializedName("id")
    String id;

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

    public String getId() {
        return id;
    }

}
