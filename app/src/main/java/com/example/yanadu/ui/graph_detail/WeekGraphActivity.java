package com.example.yanadu.ui.graph_detail;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.yanadu.R;
import com.example.yanadu.data.model.ObjectData;
import com.example.yanadu.data.model.ResultData;
import com.example.yanadu.data.model.UserData;
import com.example.yanadu.data.repository.ResultRepository;
import com.example.yanadu.data.request.OnGetData;
import com.example.yanadu.ui.graph_detail.weekfragment.BloodFragment;
import com.example.yanadu.ui.graph_detail.weekfragment.O2Fragment;
import com.example.yanadu.ui.graph_detail.weekfragment.PulseFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WeekGraphActivity extends Fragment implements OnGetData {

    ArrayList<Double> pulseValueList = new ArrayList<Double>();
    ArrayList<Double> bloodMaxValueList = new ArrayList<Double>();
    ArrayList<Double> bloodMinValueList = new ArrayList<Double>();
    ArrayList<Double> o2ValueList = new ArrayList<Double>();

    BloodFragment bloodFrag = new BloodFragment();
    O2Fragment o2Frag = new O2Fragment();
    PulseFragment pulseFrag = new PulseFragment();

    Button btn_fragmentA;
    Button btn_fragmentB ;
    Button btn_fragmentC ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.activity_week_graph, container, false);

        ResultRepository resultService=new ResultRepository(this);
        UserData u1=(UserData) getArguments().getSerializable("User");

      //  Log.d("useranddate",u1.getId()+":"+getCurrentDate());
     //   resultService.requestHealthWeeklydata(u1.getId(),getCurrentDate());  //
        resultService.requestHealthdata(u1.getId());


         btn_fragmentA= v.findViewById(R.id.btn_fragmentblood);
        btn_fragmentB = v.findViewById(R.id.btn_fragmento2);
        btn_fragmentC = v.findViewById(R.id.btn_fragmentpulse);


        btn_fragmentA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_fragmentB.setBackgroundResource(R.color.white);
                btn_fragmentC.setBackgroundResource(R.color.white);
                btn_fragmentA.setBackgroundResource(R.drawable.rectangle_background_pink);

               getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, bloodFrag).commit();
            }
        });


        btn_fragmentB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_fragmentA.setBackgroundResource(R.color.white);
                btn_fragmentC.setBackgroundResource(R.color.white);
                btn_fragmentB.setBackgroundResource(R.drawable.rectangle_background_pink);


                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, o2Frag).commit();
            }
        });

        btn_fragmentC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_fragmentA.setBackgroundResource(R.color.white);
                btn_fragmentB.setBackgroundResource(R.color.white);
                btn_fragmentC.setBackgroundResource(R.drawable.rectangle_background_pink);


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

//        for(Double o :o2ValueList)
//        {
//            Log.d("o2",o+"");
//        }

        //기본 설정
        btn_fragmentB.setBackgroundResource(R.color.white);
        btn_fragmentC.setBackgroundResource(R.color.white);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, bloodFrag).commit();

    }

    private String getCurrentDate() {
        //System.out.println(now); 현재 시간 출력
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String formatedNow = formatter.format(now); // 포맷팅 적용
        return formatedNow;

    }


}