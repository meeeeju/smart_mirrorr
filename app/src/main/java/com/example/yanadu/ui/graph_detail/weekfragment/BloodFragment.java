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
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.MPPointF;


import java.util.ArrayList;


public class BloodFragment extends Fragment {

    BarChart barChart;
    XAxis xAxis ;
    YAxis yAxis;
    BarDataSet barDataSet;
    BarDataSet barDataSet1;
    private ArrayList<Double> valueMinList;
    private ArrayList<Double> valueMaxList;

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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = (ViewGroup) inflater.inflate(R.layout.fragment_blood, container, false);


        barChart = (BarChart) rootView.findViewById(R.id.fragment_blood);
        xAxis = barChart.getXAxis();
        showBarChart();
        initBarDataSet(barDataSet,barDataSet1);

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
        ArrayList<BarEntry> entries1 = new ArrayList<>();
        String title = "MaxBlood";
        String title2="MinBlood";


        //fit the data into a bar
        for (int i = 0; i < valueMaxList.size(); i++) {
           // Log.d("max",valueMaxList.toString());
            BarEntry barEntry = new BarEntry(i, valueMaxList.get(i).floatValue());
            BarEntry barEntry1=new BarEntry(i+0.3f,valueMinList.get(i).floatValue());
            entries.add(barEntry);
            entries1.add(barEntry1);
        }

        barDataSet = new BarDataSet(entries, title);
        barDataSet1 = new BarDataSet(entries1, title2);

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(barDataSet);
        dataSets.add(barDataSet1);
        BarData data = new BarData(dataSets);


        float groupSpace = 0.4f;
        float barSpace = 0f;
        float barWidth = 0.4f;
        // (barSpace + barWidth) * 2 + groupSpace = 1
        data.setBarWidth(barWidth);
        // so that the entire chart is shown when scrolled from right to left
        xAxis.setValueFormatter(new IndexAxisValueFormatter(weekdays));
        xAxis.setAxisMaximum(weekdays.size() );
        barChart.setData(data);
        barChart.setScaleEnabled(false);
       // barChart.setVisibleXRangeMaximum(6f);
       barChart.groupBars(0f, groupSpace, barSpace);

        barChart.invalidate();


        //click event 부여
        barChart.setOnChartValueSelectedListener(new barChartOnChartValueSelectedListener());
    }


    private void initBarDataSet(BarDataSet barDataSet,BarDataSet barDataSet1){
        //Changing the color of the bar
        barDataSet.setColor(Color.parseColor("#F7BFA8"));
        barDataSet1.setColor(Color.parseColor("#1FBCD3"));
        //Setting the size of the form in the legend
        barDataSet.setFormSize(15f);
        barDataSet1.setFormSize(15f);
        //showing the value of the bar, default true if not set
        barDataSet.setDrawValues(true);
        barDataSet1.setDrawValues(true);
        //setting the text size of the value of the bar
        barDataSet.setValueTextSize(8f);
        barDataSet1.setValueTextSize(8f);

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

//참고자료
//https://medium.com/@clyeung0714/using-mpandroidchart-for-android-application-barchart-540a55b4b9ef
//https://onepinetwopine.tistory.com/987
//https://jeongupark-study-house.tistory.com/159

//서로 다른 2개의 dataset사용법
//https://chjune0205.tistory.com/81