package com.example.yanadu.ui.graph_detail.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yanadu.R;

import java.util.ArrayList;


public class O2Fragment extends Fragment {

    ArrayList<Double> valueList;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void setList(ArrayList<Double> l1){
        valueList=l1;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        return inflater.inflate(R.layout.fragment_o2, container, false);
    }
}