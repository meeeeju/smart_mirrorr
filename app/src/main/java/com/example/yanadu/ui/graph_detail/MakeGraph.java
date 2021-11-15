package com.example.yanadu.ui.graph_detail;

import android.content.Context;
import android.graphics.Color;

import androidx.core.content.ContextCompat;

import com.example.yanadu.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import static android.os.Build.VERSION_CODES.R;

public class MakeGraph {



    public void showBarChart(ArrayList<Double> valueList){

        ArrayList<BarEntry> entries = new ArrayList<>();
        String title = "Title";

        //input data
        for(int i = 0; i < 7; i++){
            valueList.add(i * 100.1);
        }

        //fit the data into a bar
        for (int i = 0; i < valueList.size(); i++) {
            BarEntry barEntry = new BarEntry(i, valueList.get(i).floatValue());
            entries.add(barEntry);
        }

        BarDataSet barDataSet = new BarDataSet(entries, title);

        BarData data = new BarData(barDataSet);

    }

    public void initBarDataSet(BarDataSet barDataSet, Context context){
        //Changing the color of the bar  // barDataSet.setColor(Color.parseColor("#304567"));
        barDataSet.setColor( ContextCompat.getColor(context, com.example.yanadu.R.color.main_pink));
        //Setting the size of the form in the legend
        barDataSet.setFormSize(15f);
        //showing the value of the bar, default true if not set
        barDataSet.setDrawValues(true);
        //setting the text size of the value of the bar
        barDataSet.setValueTextSize(12f);
    }








}
