package com.example.yanadu.ui.graph_detail.weekfragment;

import android.graphics.Color;
import android.graphics.RectF;
import android.os.Bundle;

import android.widget.TextView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.yanadu.R;
import com.example.yanadu.data.model.ResultData;
import com.example.yanadu.data.model.UserData;
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


public class PulseFragment extends Fragment{

    BarChart barChart;
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


        UserData u1=(UserData) getArguments().getSerializable("User");

        //  Log.d("useranddate",u1.getId()+":"+getCurrentDate());
        //   resultService.requestHealthWeeklydata(u1.getId(),getCurrentDate());  //



        barChart = (BarChart) rootView.findViewById(R.id.barchart_op);
        showBarChart();





        // Inflate the layout for this fragment
        return rootView;

    }



    public void showBarChart(){
        ArrayList<BarEntry> entries = new ArrayList<>();
        BarDataSet barDataSet;
        String title = "맥박";


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
        barChart.setScaleEnabled(false);

        XAxis xAxis=barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(weekdays));


        initBarDataSet(barDataSet);  //barchar 꾸며주기

    }


    private void initBarDataSet(BarDataSet barDataSet){
        //Changing the color of the bar
        barDataSet.setColor(Color.parseColor("#FF0036"));
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

        XAxis xAxis=barChart.getXAxis();

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

//    public void setDate(ResultData first, ResultData second) {
//        weekDate.add(first.getDate());
//        weekDate.add(second.getDate());
//
//
//    }

//    private final RectF onValueSelectedRectF = new RectF();
//    private class barChartOnChartValueSelectedListener implements OnChartValueSelectedListener {
//
//        @Override
//        public void onValueSelected(Entry e, Highlight h) {
//            //trigger activity when the bar value is selected
//
//            if (e == null)
//                return;
//
//            RectF bounds = onValueSelectedRectF;
//            barChart.getBarBounds((BarEntry) e, bounds);
//            MPPointF position = barChart.getPosition(e, YAxis.AxisDependency.LEFT);
//
//            Log.i("bounds", bounds.toString());
//            Log.i("position", position.toString());
//
//            Log.i("x-index",
//                    "low: " + barChart.getLowestVisibleX() + ", high: "
//                            + barChart.getHighestVisibleX());
//
//            MPPointF.recycleInstance(position);
//        }
//        @Override
//        public void onNothingSelected() {
//
//        }
//    }


}