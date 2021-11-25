package com.example.yanadu.ui.schedule;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import com.example.yanadu.R;

import com.example.yanadu.data.model.CheckReturn;
import com.example.yanadu.data.model.Note;
import com.example.yanadu.data.model.NoteId;
import com.example.yanadu.data.model.ObjectData;
import com.example.yanadu.data.model.UserData;
import com.example.yanadu.data.repository.ToDoRepository;
import com.example.yanadu.data.repository.UserRepository;
import com.example.yanadu.data.request.OnGetData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ToDoActivity extends Fragment implements OnGetData{


    private static final String TAG = "ToDoActivity";

    ToDoListFragment toDoListFragment;
    ToDoRepository ToDoservice;
    UserData u1;
    int size;
    Note temp;

//    Button saveButton=(Button) findViewById(R.id.btn_saveButton);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.activity_to_do, container, false);

        Bundle bundle=new Bundle();   //bundle 생성해서 보내주기
        u1=(UserData) getArguments().getSerializable("User");
        toDoListFragment = new ToDoListFragment();

        //값 보내기
        bundle.putSerializable("User", u1);
        toDoListFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,toDoListFragment).commit();


        ToDoservice=new ToDoRepository(this);
        ToDoservice.getToDO(u1.getId());


//
//        TodoService=new ToDoRepository(this);  //this는 OnGetData
//        u1=(UserData) getArguments().getSerializable("User");
//

        Button saveButton=(Button) view.findViewById(R.id.btn_saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText inputToDo=(EditText) view.findViewById(R.id.et_inputToDo);
                 String  todo = inputToDo.getText().toString();
                 if (todo==null || todo.equals(""))
                 {
                     Toast.makeText(getContext(),"다시 입력해주세요", Toast.LENGTH_SHORT).show();
                     return;

                 }
                 temp = new Note(todo,getCurrentDate(),u1.getId());
                ToDoservice.setToDO(temp);

                inputToDo.setText("");


            }



        });

        return view;
    }




    @Override
    public void onGetData(ObjectData objectData) {
        NoteId _id=(NoteId) objectData;
        Log.d("check",_id.get_id()+"");
        Toast.makeText(getContext(),"추가되었습니다.",Toast.LENGTH_SHORT).show();

        //get_id로 _id 가져오기
        temp.set_id(_id.get_id());
        toDoListFragment.ToDOList.add(temp);
        toDoListFragment.adapter.notifyDataSetChanged();


    }

    @Override
    public void onSendDate(ObjectData objectData) {

    }

    @Override
    public void onGetDataList(List<ObjectData> objectDataList) {


        size=objectDataList.size();
        Log.d("current todolist size", objectDataList.size() + "");

    }


    private String getCurrentDate() {
        //System.out.println(now); 현재 시간 출력
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String formatedNow = formatter.format(now); // 포맷팅 적용
        return formatedNow;


    }
}