package com.example.yanadu.ui.graph_detail.weekfragment;

import android.content.Context;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yanadu.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.*;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class O2MonthFragment extends Fragment {

    LineChart lineChart;

    ArrayList<Double> valueList;  //값 넣어지는 곳
    private ArrayList<String> monthLabel = new ArrayList<String>();
    View rootView;
    private Context c;

    public O2MonthFragment(Context c) {
        this.c = c;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.activity_sample_line, container, false);

        lineChart = (LineChart) rootView.findViewById(R.id.linechart_op);

        //sampledata 31개
        ArrayList<Double> value31List=new ArrayList<Double>(){
            {
                add(1.0);add(2.0);add(3.0);add(4.0);add(5.0);
                add(1.0);add(2.0);add(7.0);add(4.0);add(5.0);
                add(6.0);add(2.0);add(3.0);add(4.0);add(5.0);
                add(1.0);add(12.0);add(3.0);add(4.0);add(5.0);
                add(1.0);add(18.0);add(24.0);add(4.0);add(5.0);
                add(1.0);add(2.0);add(3.0);add(4.0);add(5.0);
                add(7.0);

            }
        };

        //setValueList(value31List);
        showLineChart();
        return rootView;
    }

    public void setValueList(ArrayList<Double> dataList)
    {
        valueList=dataList;
    }

    public void showLineChart() {

        String labeltitle = "o2";
        ArrayList<Entry> values = new ArrayList<>();

        //fit the data into entry
        for (int i = 0; i < valueList.size(); i++) {
            // Log.d("max",valueMaxList.toString());
           values.add(new Entry(i,(valueList.get(i)).floatValue()));
        }

        //make line with value and labeltitle
        LineDataSet set1 = new LineDataSet(values, labeltitle);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1); // add the data sets

        // create a data object with the data sets
        LineData data = new LineData(dataSets);

        // set data
        lineChart.setData(data);
        initLineDataSet(set1);

        //원하는 지점시 선 그어주는 건데 왜 주석 처리되도 반응하는지 모르겠음,,,
//        lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
//            @Override
//            public void onValueSelected(Entry e, Highlight h) {
//
//                Highlight highlight[] = new Highlight[lineChart.getData().getDataSets().size()];
//                for (int j = 0; j < lineChart.getData().getDataSets().size(); j++) {
//
//                    IDataSet iDataSet = lineChart.getData().getDataSets().get(j);
//
//                    for (int i = 0; i < ((LineDataSet) iDataSet).getValues().size(); i++) {
//                        if (((LineDataSet) iDataSet).getValues().get(i).getX() == e.getX()) {
//                            highlight[j] = new Highlight(e.getX(), e.getY(), j);
//                        }
//                    }
//
//                }
//                lineChart.highlightValues(highlight);
//            }
//
//            @Override
//            public void onNothingSelected() {
//            }
//        });


        MyMarkerViews mv = new MyMarkerViews(c, R.layout.custom_marker_view);

        // Set the marker to the chart
        mv.setChartView(lineChart);
        lineChart.setMarker(mv);
    }


    public void initLineDataSet(LineDataSet set1) {
        lineChart.setBackgroundColor(Color.WHITE); // 그래프 배경 색 설정

        //x축관련
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(makeDayLabel(valueList.size())));
        //xAxis.enableGridDashedLine(8, 10, 0);
        // xAxis.setLabelCount(12, true); //X축의 데이터를 최대 몇개 까지 나타낼지에 대한 설정 5개 force가 true 이면 반드시 보여줌

        set1.setLineWidth(2);
        set1.setCircleRadius(6);
        set1.setCircleColor(Color.parseColor("#6B8E23"));
        set1.setCircleColorHole(Color.YELLOW);
        set1.setColor(Color.parseColor("#6B8E23"));
        set1.setColor(Color.parseColor("#6B8E23")); // 차트의 선 색 설정


        //Setting the size of the form in the legend
        set1.setFormSize(15f);
        //showing the value of the bar, default true if not set
        set1.setDrawValues(false);
        //setting the text size of the value of the bar
        set1.setValueTextSize(15f);
        set1.setDrawFilled(false); // 차트 아래 fill(채우기) 설정


        //remove label description
        Description description = new Description();
        description.setEnabled(false);
        lineChart.setDescription(description);


        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //set the horizontal distance of the grid line
        xAxis.setGranularity(1f);
        //hiding the x-axis line, default true if not set
        xAxis.setDrawAxisLine(false);
        //hiding the vertical grid lines, default true if not set
        xAxis.setDrawGridLines(false);


        YAxis leftAxis = lineChart.getAxisLeft();
        //hiding the left y-axis line, default true if not set
        leftAxis.setDrawAxisLine(false);

        YAxis rightAxis = lineChart.getAxisRight();
        //hiding the right y-axis line, default true if not set
        rightAxis.setDrawAxisLine(false);


    }




    public ArrayList<String> makeDayLabel(int monthSize)
    {
        monthLabel.clear();
        for (int i=1;i<monthSize;i++)
        {
            monthLabel.add(String.valueOf(i)+"th");
        }
        return monthLabel;
    }


}

