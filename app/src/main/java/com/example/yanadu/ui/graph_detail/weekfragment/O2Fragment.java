package com.example.yanadu.ui.graph_detail.weekfragment;

import android.graphics.Color;
import android.graphics.RectF;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yanadu.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;


public class O2Fragment extends Fragment{

    BarChart barChart;
    XAxis xAxis ;
    BarDataSet barDataSet;

    ArrayList<Double> valueList;
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


        barChart = (BarChart) rootView.findViewById(R.id.barchart_O2);
        xAxis=barChart.getXAxis();
        showBarChart();
        initBarDataSet(barDataSet);

        // Inflate the layout for this fragment
        return rootView;

    }



    public void showBarChart(){
        ArrayList<BarEntry> entries = new ArrayList<>();
        String title = "산소포화도";


        //fit the data into a bar
        for (int i = 0; i < valueList.size(); i++) {
            // Log.d("max",valueMaxList.toString());
            BarEntry barEntry = new BarEntry(i, valueList.get(i).floatValue());
            entries.add(barEntry);
        }

        barDataSet = new BarDataSet(entries, title);

        BarData data = new BarData(barDataSet);
        barChart.setData(data);
        barChart.invalidate();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(weekdays));


        //click event 부여
        barChart.setOnChartValueSelectedListener(new barChartOnChartValueSelectedListener());
    }


    private void initBarDataSet(BarDataSet barDataSet){
        //Changing the color of the bar
        barDataSet.setColor(Color.parseColor("#F7BFA8"));
        //Setting the size of the form in the legend
        barDataSet.setFormSize(15f);
        //showing the value of the bar, default true if not set
        barDataSet.setDrawValues(true);
        //setting the text size of the value of the bar
        barDataSet.setValueTextSize(8f);

        //remove label description
        Description description = new Description();
        description.setEnabled(false);
        barChart.setDescription(description);

        //막대그래 aniamation
        //      barChart.animateY(500);

        //xㅌ
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //set the horizontal distance of the grid line
        xAxis.setGranularity(1f);
        //hiding the x-axis line, default true if not set
        xAxis.setDrawAxisLine(false);
        //hiding the vertical grid lines, default true if not set
        xAxis.setDrawGridLines(false);

        YAxis leftAxis = barChart.getAxisLeft();
        //hiding the left y-axis line, default true if not set
        leftAxis.setDrawAxisLine(false);

        YAxis rightAxis = barChart.getAxisRight();
        //hiding the right y-axis line, default true if not set
        rightAxis.setDrawAxisLine(false);

    }

    private final RectF onValueSelectedRectF = new RectF();
    private class barChartOnChartValueSelectedListener implements OnChartValueSelectedListener {

        @Override
        public void onValueSelected(Entry e, Highlight h) {
            //trigger activity when the bar value is selected

            if (e == null)
                return;

            RectF bounds = onValueSelectedRectF;
            barChart.getBarBounds((BarEntry) e, bounds);
            MPPointF position = barChart.getPosition(e, YAxis.AxisDependency.LEFT);

            Log.i("bounds", bounds.toString());
            Log.i("position", position.toString());

            Log.i("x-index",
                    "low: " + barChart.getLowestVisibleX() + ", high: "
                            + barChart.getHighestVisibleX());

            MPPointF.recycleInstance(position);
        }
        @Override
        public void onNothingSelected() {

        }
    }



}