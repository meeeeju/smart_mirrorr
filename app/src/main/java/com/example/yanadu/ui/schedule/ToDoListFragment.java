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
import com.example.yanadu.data.model.Note;
import com.example.yanadu.data.model.ObjectData;
import com.example.yanadu.data.model.UserData;
import com.example.yanadu.data.repository.ToDoRepository;
import com.example.yanadu.data.request.OnGetData;

import java.util.ArrayList;
import java.util.List;

public class ToDoListFragment extends Fragment implements OnGetData {

    private static final String TAG = "MainFragment";

    RecyclerView recyclerView;
    public NoteAdapter adapter;
    ToDoRepository ToDoservice;
    public ArrayList<Note> ToDOList = new ArrayList<Note>();
    UserData u1;


    //인플레이션: 화면을 생성이 아닌 구성할 때, 즉 생성된 이후에 호출되는 역할
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_todo, container, false);
         ToDoservice=new ToDoRepository(this);

         u1=(UserData) getArguments().getSerializable("User");

         ToDoservice.getToDO(u1.getId());
        // ToDoservice.getToDO("dmsrn135");

        initUI(rootView);


        return rootView;
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        ToDoservice.getToDO("dmsrn135");
//    }

    private void initUI(ViewGroup rootView){
        Log.d("test","initUI 실행됨!!");

        //fragment_main.xml에 만들었던 RecyclerView을 연결
        recyclerView = rootView.findViewById(R.id.recyclerViewToDo);

        //LinearLayoutManager을 이용하여 LinearLayout에 recyclerView을 붙입니다. 이후 이것은 todo_item들이 세로로 하나하나 정렬하는 역할
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        //어댑터들을 연결하는 역할
        adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);

    }


    public int loadNoteListData(){

            adapter.setItems(ToDOList);
            adapter.notifyDataSetChanged();

        return ToDOList.size();
    }


    @Override
    public void onGetData(ObjectData objectData) {

    }

    @Override
    public void onSendDate(ObjectData objectData) {

    }

    @Override
    public void onGetDataList(List<ObjectData> objectDataList) {
        ToDOList=(ArrayList)objectDataList;
        for (ObjectData od : objectDataList) {
            Note n1=(Note)od;
            Log.d("id",n1.getId());
            Log.d("todo",n1.getTodo());
        }

        loadNoteListData();



    }
}


