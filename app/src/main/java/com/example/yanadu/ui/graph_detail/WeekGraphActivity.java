package com.example.yanadu.ui.graph_detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.yanadu.R;
import com.github.mikephil.charting.charts.BarChart;

import java.util.ArrayList;

public class WeekGraphActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_graph);

        BarChart barChart = (BarChart) findViewById(R.id.barchart_view);

        //weekGraph객체 선언
        MakeGraph maebakWeekGraph =new MakeGraph(barChart);
        MakeGraph bloodWeekGraph =new MakeGraph(barChart);
        MakeGraph o2WeekGraph =new MakeGraph(barChart);

        ArrayList<Double> maebakValueList = new ArrayList<Double>();
        ArrayList<Double> bloodValueList = new ArrayList<Double>();
        ArrayList<Double> o2ValueList = new ArrayList<Double>();



    }





}