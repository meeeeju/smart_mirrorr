package com.example.yanadu.ui;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.yanadu.R;
import com.example.yanadu.data.model.ObjectData;
import com.example.yanadu.data.model.ResultData;
import com.example.yanadu.data.model.UserData;
import com.example.yanadu.data.repository.ResultRepository;
import com.example.yanadu.data.request.OnGetData;
import com.example.yanadu.ui.graph_detail.WeekGraphActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.animation.Animator;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;


public class RealMainActivity extends Fragment implements View.OnClickListener, OnGetData {

    public CardView maincard1;
    public ImageButton shufflebutton;
    public BarChart mainBarCard;
    public Button mircon;

    TextView bloodpressure,pulse,oxygen;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.activity_real_main, container, false);
        UserData u1=(UserData) getArguments().getSerializable("User");

        maincard1=(CardView) v.findViewById(R.id.maincard1);
        mainBarCard=(BarChart) v.findViewById(R.id.mainDataGraph);

        bloodpressure=v.findViewById(R.id.bloddpressure);
        pulse=v.findViewById(R.id.pulse);
        oxygen=v.findViewById(R.id.oxygen);

        ResultRepository resultService=new ResultRepository(this);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        resultService.requesthealthdailydata(u1.getId(),sdf.format(Calendar.getInstance().getTime()));

        maincard1.setOnClickListener(this);
        TextView username=v.findViewById(R.id.username);
        username.setText(u1.getNickname());

        shufflebutton= (ImageButton) v.findViewById(R.id.mainshuffle);
        shufflebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int randomnum=(int) (Math.random()*10);
                DigitTextView digitTextView = (DigitTextView) v.findViewById(R.id.digitTextView);
                digitTextView.setValue(randomnum);

            }
        });

        mircon=(Button) v.findViewById(R.id.mirrorconnect);
        mircon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),MirrorConnection.class);
                startActivity(intent);

            }
        });


        return v;



    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId())
        {
            case R.id.maincard1:
                i=new Intent(getActivity(), WeekGraphActivity.class);
                startActivity(i);
                break;

        }

    }


    @Override
    public void onGetData(ObjectData objectData) {
        ResultData rd = (ResultData) objectData;

        bloodpressure.setText(rd.getBloodMin()+"   "+rd.getBloodMax());
        pulse.setText(rd.getPulse()+"");
        oxygen.setText(rd.getO2()+"");

        ArrayList<Double> dailyValueList= new ArrayList<Double>();
        dailyValueList.add(rd.getBloodMin());
        dailyValueList.add(rd.getBloodMax());
        dailyValueList.add(rd.getPulse());
        dailyValueList.add(rd.getO2());

        showBarChart(dailyValueList);


        



    }

    @Override
    public void onSendDate(ObjectData objectData) {

    }

    @Override
    public void onGetDataList(List<ObjectData> objectDataList) {

    }


    public void showBarChart(ArrayList<Double> dailyDataList){


        ArrayList<String> dataName = new ArrayList<String>(){{
            add("Max 혈압");
            add("Min 혈압");
            add("맥박 ");
            add("산소포화도");

        };}; // ArrayList 선언
        ArrayList<BarEntry> entries = new ArrayList<>();
        String title = "Daily Data";


        //fit the data into a bar
        for (int i = 0; i < dailyDataList.size(); i++) {
            // Log.d("max",valueMaxList.toString());
            BarEntry barEntry = new BarEntry(i, dailyDataList.get(i).floatValue());
            entries.add(barEntry);
        }

        BarDataSet barDataSet = new BarDataSet(entries, title);

        BarData data = new BarData(barDataSet);
        mainBarCard.setData(data);
        mainBarCard.invalidate();
        mainBarCard.setScaleEnabled(false);

        XAxis xAxis=mainBarCard.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(dataName));


        initBarDataSet(barDataSet);  //barchar 꾸며주기
        //click event 부여
        //barChart.setOnChartValueSelectedListener(new barChartOnChartValueSelectedListener());
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
        mainBarCard.setDescription(description);


        XAxis xAxis=mainBarCard.getXAxis();

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //set the horizontal distance of the grid line
        xAxis.setGranularity(1f);
        //hiding the x-axis line, default true if not set
        xAxis.setDrawAxisLine(false);
        //hiding the vertical grid lines, default true if not set
        xAxis.setDrawGridLines(false);

        YAxis leftAxis = mainBarCard.getAxisLeft();
        //hiding the left y-axis line, default true if not set
        leftAxis.setDrawAxisLine(false);

        YAxis rightAxis = mainBarCard.getAxisRight();
        //hiding the right y-axis line, default true if not set
        rightAxis.setDrawAxisLine(false);

    }

}