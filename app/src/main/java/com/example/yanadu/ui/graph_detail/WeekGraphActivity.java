package com.example.yanadu.ui.graph_detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.yanadu.R;
import com.example.yanadu.data.model.ObjectData;
import com.example.yanadu.data.model.ResultData;
import com.example.yanadu.data.repository.ResultRepository;
import com.example.yanadu.data.request.OnGetData;
import com.example.yanadu.ui.graph_detail.fragment.BloodFragment;
import com.example.yanadu.ui.graph_detail.fragment.O2Fragment;
import com.example.yanadu.ui.graph_detail.fragment.PulseFragment;
import com.github.mikephil.charting.charts.BarChart;

import java.util.ArrayList;
import java.util.List;

public class WeekGraphActivity extends Fragment implements OnGetData {

    ArrayList<Double> pulseValueList = new ArrayList<Double>();
    ArrayList<Double> bloodMaxValueList = new ArrayList<Double>();
    ArrayList<Double> bloodMinValueList = new ArrayList<Double>();
    ArrayList<Double> o2ValueList = new ArrayList<Double>();

    BloodFragment bloodFrag = new BloodFragment();
    O2Fragment o2Frag = new O2Fragment();
    PulseFragment pulseFrag = new PulseFragment();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.activity_week_graph, container, false);

        ResultRepository resultService=new ResultRepository(this);
        resultService.requestHealthdata("dkdkd");  //


        Button btn_fragmentA= v.findViewById(R.id.btn_fragmentA);
        btn_fragmentA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, bloodFrag).commit();
            }
        });

        Button btn_fragmentB = v.findViewById(R.id.btn_fragmentB);
        btn_fragmentB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, o2Frag).commit();
            }
        });
        Button btn_fragmentC = v.findViewById(R.id.btn_fragmentC);
        btn_fragmentC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, pulseFrag).commit();
            }
        });


        return v;
    }




    @Override
    public void onGetData(ObjectData objectData) {

    }

    @Override
    public void onSendDate(ObjectData objectData) {

    }

    @Override
    public void onGetDataList(List<ObjectData> objectDataList) {
        for(ObjectData od : objectDataList){
            ResultData rd = (ResultData)od;

            pulseValueList.add(rd.getPulse());
            bloodMaxValueList.add(rd.getBloodMax());
            bloodMinValueList.add(rd.getBloodMin());
            o2ValueList.add(rd.getO2());
        }

        o2Frag.setList(o2ValueList);
        bloodFrag.setList(bloodMinValueList,bloodMaxValueList);
        pulseFrag.setList(pulseValueList);


    }
}