package com.example.yanadu.ui.graph_detail.weekfragment;

import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.yanadu.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class SampleMutipleLines extends AppCompatActivity {

    LineChart lineChart;

     ArrayList<Double> valueMinList;  //값 넣어지는 곳
     ArrayList<Double> valueMaxList;  //값 넣어지는 곳



    private ArrayList<String> monthLabel = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_line);


        lineChart = (LineChart) findViewById(R.id.linechart_op);

        //sampledata 30개
        ArrayList<Double> value30List=new ArrayList<Double>(){
            {
                add(1.0);add(2.0);add(3.0);add(4.0);add(5.0);
                add(1.0);add(2.0);add(7.0);add(4.0);add(5.0);
                add(6.0);add(2.0);add(3.0);add(4.0);add(5.0);
                add(1.0);add(12.0);add(3.0);add(4.0);add(5.0);
                add(1.0);add(18.0);add(24.0);add(4.0);add(5.0);
                add(1.0);add(2.0);add(3.0);add(4.0);add(5.0);

            }
        };

        //sampledata 31개
        ArrayList<Double> value31MaxList=new ArrayList<Double>(){
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
        //sampledata 31개
        ArrayList<Double> value31MinList=new ArrayList<Double>(){
            {
                add(2.0);add(2.0);add(3.0);add(4.0);add(5.0);
                add(1.0);add(8.0);add(7.0);add(4.0);add(8.0);
                add(2.0);add(2.0);add(39.0);add(8.0);add(5.0);
                add(18.0);add(12.0);add(3.0);add(4.0);add(5.0);
                add(3.0);add(23.0);add(24.0);add(4.0);add(9.0);
                add(1.0);add(7.0);add(3.0);add(9.0);add(5.0);
                add(15.0);

            }
        };

        //sampledata28개
        ArrayList<Double> value28List=new ArrayList<Double>(){
            {
                add(1.0);add(2.0);add(3.0);add(4.0);add(5.0);
                add(1.0);add(2.0);add(7.0);add(4.0);add(5.0);
                add(6.0);add(2.0);add(3.0);add(4.0);add(5.0);
                add(1.0);add(12.0);add(3.0);add(4.0);add(5.0);
                add(1.0);add(18.0);add(24.0);add(4.0);add(5.0);
                add(1.0);add(2.0);add(3.0);
            }
        };
        setValueList(value31MinList,value31MaxList);
        showLineChart();


    }

    public void setValueList(ArrayList<Double> dataList1,ArrayList<Double> dataList2) //1이 작은것
    {
        valueMinList=dataList1;
        valueMaxList=dataList2;
    }



    public void showLineChart() {

        String labeltitleMin= "MinBlood";
        String labeltitleMax="MaxBlood";
        ArrayList<Entry> minvalues = new ArrayList<>();
        ArrayList<Entry> maxvalues = new ArrayList<>();

        //fit the data into entry
        for (int i = 0; i < valueMinList.size(); i++) {
            // Log.d("max",valueMaxList.toString());
            minvalues.add(new Entry(i,(valueMinList.get(i)).floatValue()));
        }

        //fit the data into entry
        for (int i = 0; i < valueMaxList.size(); i++) {
            // Log.d("max",valueMaxList.toString());
            maxvalues.add(new Entry(i,(valueMaxList.get(i)).floatValue()));
        }


        //make line with value and labeltitle
        LineDataSet setMin = new LineDataSet(minvalues, labeltitleMin);
        LineDataSet setMax = new LineDataSet(maxvalues, labeltitleMax);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();

        dataSets.add(setMin); // add the data sets
        dataSets.add(setMax); // add the data sets

        // create a data object with the data sets
       // LineData data = new LineData(dataSets);
        LineData data=new LineData(dataSets);

        // set data
        lineChart.setData(data);
        lineChart.invalidate();
        initLineDataSet(setMin,setMax);



        MyMarkerViews mv = new MyMarkerViews(this, R.layout.custom_marker_view);
        // Set the marker to the chart
        mv.setChartView(lineChart);
        lineChart.setMarker(mv);
    }


    public void initLineDataSet(LineDataSet setMin,LineDataSet setMax) {
        lineChart.setBackgroundColor(Color.WHITE); // 그래프 배경 색 설정

        //x축관련
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(makeDayLabel(valueMinList.size())));
        //xAxis.enableGridDashedLine(8, 10, 0);
        // xAxis.setLabelCount(12, true); //X축의 데이터를 최대 몇개 까지 나타낼지에 대한 설정 5개 force가 true 이면 반드시 보여줌

        setMin.setLineWidth(2);
        setMax.setLineWidth(2);
        setMin.setCircleRadius(6);
        setMax.setCircleRadius(6);
        setMin.setCircleColor(Color.parseColor("#F8C77F"));
        setMax.setCircleColor(Color.parseColor("#FFA1B4DC"));
        setMin.setCircleColorHole(Color.BLUE);
        setMax.setCircleColorHole(Color.BLUE);
        setMin.setColor(Color.parseColor("#F8C77F"));
        setMax.setColor(Color.parseColor("#FFA1B4DC"));
        setMin.setColor(Color.parseColor("#F8C77F")); // 차트의 선 색 설정
        setMax.setColor(Color.BLUE); // 차트의 선 색 설정


        //Setting the size of the form in the legend
        setMin.setFormSize(15f);
        setMax.setFormSize(15f);
        //showing the value of the bar, default true if not set
        setMin.setDrawValues(false);
        setMax.setDrawValues(false);
        //setting the text size of the value of the bar
        setMin.setValueTextSize(15f);
        setMax.setValueTextSize(15f);
        setMin.setDrawFilled(false); // 차트 아래 fill(채우기) 설정
        setMax.setDrawFilled(false); // 차트 아래 fill(채우기) 설정


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

