package com.example.yanadu.ui.graph_detail.weekfragment;

import android.graphics.Color;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.yanadu.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class SampleLine extends AppCompatActivity {

    LineChart lineChart;

    ArrayList<Double> valueList;  //값 넣어지는 곳
    public int isWeek = 1;

    private ArrayList<String> weekdays = new ArrayList<String>(){{
        add("MON");
        add("TUE");
        add("WED");
        add("THU");
        add("FRI");
        add("SAT");
        add("SUN");
    };}; // ArrayList 선언




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_line);


        lineChart=(LineChart) findViewById(R.id.linechart_op);
        showLineChart();

    }


    public void showLineChart(){

        String labeltitle="02";


        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            float val = (float) (Math.random() * 30);
            values.add(new Entry(i, val));
        }

        LineDataSet set1;
        set1 = new LineDataSet(values, labeltitle);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1); // add the data sets

        // create a data object with the data sets
        LineData data = new LineData(dataSets);

        // black lines and points
        set1.setColor(Color.BLACK);
        set1.setCircleColor(Color.BLACK);

        // set data
        lineChart.setData(data);
        initLineDataSet(set1);
    }



    public void initLineDataSet(LineDataSet set1)
    {
        lineChart.setBackgroundColor(Color.RED); // 그래프 배경 색 설정
        set1.setColor(Color.BLACK); // 차트의 선 색 설정
        set1.setCircleColor(Color.BLACK); // 차트의 points 점 색 설정

        set1.setDrawFilled(true); // 차트 아래 fill(채우기) 설정
        set1.setFillColor(Color.BLACK); // 차트 아래 채우기 색 설정


    }

}

