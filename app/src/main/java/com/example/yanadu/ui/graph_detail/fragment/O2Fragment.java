package com.example.yanadu.ui.graph_detail.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yanadu.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;


public class O2Fragment extends Fragment {

    ArrayList<Double> valueList;
    BarChart barChart;
    private ArrayList<String> labelList = new ArrayList<>(); // ArrayList 선언




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


        View rootView = (ViewGroup) inflater.inflate(R.layout.fragment_o2, container, false);


        barChart = (BarChart) rootView.findViewById(R.id.fragment_bluetooth_chat_barchart);
        showBarChart();

        // Inflate the layout for this fragment
        return rootView;

    }




    public void showBarChart(){
        ArrayList<BarEntry> entries = new ArrayList<>();
        String title = "Title";


        //fit the data into a bar
        for (int i = 0; i < valueList.size(); i++) {
            // Log.d("max",valueMaxList.toString());
            BarEntry barEntry = new BarEntry(i, valueList.get(i).floatValue());
            entries.add(barEntry);
        }

        BarDataSet barDataSet = new BarDataSet(entries, title);

        BarData data = new BarData(barDataSet);
        barChart.setData(data);
        barChart.invalidate();
    }


}