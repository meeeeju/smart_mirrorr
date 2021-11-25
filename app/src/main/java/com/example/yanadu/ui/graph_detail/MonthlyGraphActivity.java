package com.example.yanadu.ui.graph_detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.yanadu.R;
import com.example.yanadu.data.model.ObjectData;
import com.example.yanadu.data.model.UserData;
import com.example.yanadu.data.repository.ResultRepository;
import com.example.yanadu.data.request.OnGetData;
import com.example.yanadu.ui.graph_detail.monthfragment.MBloodFragment;
import com.example.yanadu.ui.graph_detail.weekfragment.BloodFragment;
import com.example.yanadu.ui.graph_detail.weekfragment.O2Fragment;
import com.example.yanadu.ui.graph_detail.weekfragment.PulseFragment;

import java.util.List;

public class MonthlyGraphActivity extends Fragment implements OnGetData {


    MBloodFragment MbloodFrag = new MBloodFragment();
   // O2Fragment o2Frag = new O2Fragment();
   // PulseFragment pulseFrag = new PulseFragment();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.activity_monthly_graph, container, false);

//        ResultRepository resultService=new ResultRepository(this);
//        UserData u1=(UserData) getArguments().getSerializable("User");

        //  Log.d("useranddate",u1.getId()+":"+getCurrentDate());
        //   resultService.requestHealthWeeklydata(u1.getId(),getCurrentDate());  //
 //       resultService.requestHealthdata(u1.getId());


        Button btn_fragmentA= v.findViewById(R.id.btn_fragmentblood);
        btn_fragmentA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,MbloodFrag ).commit();
            }
        });
//
//        Button btn_fragmentB = v.findViewById(R.id.btn_fragmento2);
//        btn_fragmentB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, o2Frag).commit();
//            }
//        });
//        Button btn_fragmentC = v.findViewById(R.id.btn_fragmentpulse);
//        btn_fragmentC.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, pulseFrag).commit();
//            }
//        });



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

    }
}
