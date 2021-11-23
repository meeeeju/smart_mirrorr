package com.example.yanadu.ui.schedule;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yanadu.R;

public class MainFragment extends Fragment {

    private static final String TAG = "MainFragment";

    RecyclerView recyclerView;
    NoteAdapter adapter;
    static int count ;

    //인플레이션: 화면을 생성이 아닌 구성할 때, 즉 생성된 이후에 호출되는 역할
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_todomain, container, false);

        initUI(rootView);

        return rootView;
    }

    private void initUI(ViewGroup rootView){

        //fragment_todomain.xml에 만들었던 RecyclerView을 연결
        recyclerView = rootView.findViewById(R.id.recyclerView);

        //LinearLayoutManager을 이용하여 LinearLayout에 recyclerView을 붙입니다. 이후 이것은 todo_item들이 세로로 하나하나 정렬하는 역할
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        //어댑터들을 연결하는 역할
        adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);
        count=adapter.getItemCount();
        Log.d("itemcount",count+"");

    }
}
