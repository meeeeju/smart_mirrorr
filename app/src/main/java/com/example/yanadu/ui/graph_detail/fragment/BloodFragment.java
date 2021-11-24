package com.example.yanadu.ui.graph_detail.fragment;

import android.graphics.RectF;
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
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;


import java.util.ArrayList;


public class BloodFragment extends Fragment {

    BarChart barChart;
    private ArrayList<Double> valueMinList;
    private ArrayList<Double> valueMaxList;

    private ArrayList<String> labelList = new ArrayList<String>(){{
        add("월");
        add("화");
        add("수");
        add("목");
        add("금");
        add("토");
        add("일");
    };}; // ArrayList 선언



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

    //데이터 값 넣어주기
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
        barDataSet.setColor(ColorTemplate.rgb("#ff7b22"));

        BarData data = new BarData(barDataSet);
        barChart.setData(data);
        barChart.invalidate();
//        barChart.getXAxis().getValueFormatter()=
//        barChart.xAxis.valueFormatter = object: ValueFormatter() {
//            override fun getFormattedValue(value: Float): String {
//                return labels[value.toInt()]
//            }
//        }
        barChart.setOnChartValueSelectedListener(new barChartOnChartValueSelectedListener());
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

//참고자료
//https://medium.com/@clyeung0714/using-mpandroidchart-for-android-application-barchart-540a55b4b9ef
//https://onepinetwopine.tistory.com/987
//https://jeongupark-study-house.tistory.com/159

//서로 다른 2개의 dataset사용법
//https://chjune0205.tistory.com/81