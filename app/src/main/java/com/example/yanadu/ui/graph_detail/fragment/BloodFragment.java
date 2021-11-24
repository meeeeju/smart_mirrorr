package com.example.yanadu.ui.graph_detail.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yanadu.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;


import java.util.ArrayList;


public class BloodFragment extends Fragment {

    private ArrayList<Double> valueMinList;
    private ArrayList<Double> valueMaxList;
    private ArrayList<String> labelList = new ArrayList<>(); // ArrayList 선언

    BarChart barChart;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = (ViewGroup) inflater.inflate(R.layout.fragment_blood, container, false);


        barChart = (BarChart) rootView.findViewById(R.id.fragment_blood);
        showBarChart();

        // Inflate the layout for this fragment
        return rootView;

    }

    public void setList(ArrayList<Double> l1,ArrayList<Double>  l2){
        valueMinList=l1;
        valueMaxList=l2;


    }


    public void showBarChart(){
        ArrayList<BarEntry> entries = new ArrayList<>();
        String title = "MaxBlood";


        //fit the data into a bar
        for (int i = 0; i < valueMaxList.size(); i++) {
           // Log.d("max",valueMaxList.toString());
            BarEntry barEntry = new BarEntry(i, valueMaxList.get(i).floatValue());
            entries.add(barEntry);
        }

        BarDataSet barDataSet = new BarDataSet(entries, title);

        BarData data = new BarData(barDataSet);
        barChart.setData(data);
        barChart.invalidate();
    }

}

//참고자료
//https://medium.com/@clyeung0714/using-mpandroidchart-for-android-application-barchart-540a55b4b9ef
//https://onepinetwopine.tistory.com/987
