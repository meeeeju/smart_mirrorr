package com.example.yanadu.ui.schedule;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import com.example.yanadu.R;
import com.example.yanadu.data.model.*;
import com.example.yanadu.data.repository.ToDoRepository;
import com.example.yanadu.data.request.OnGetData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ToDoActivity extends AppCompatActivity implements OnGetData {

    private static final String TAG = "ToDoActivity";


    ToDoListFragment toDoListFragment;
    ToDoRepository ToDoservice;
    UserData u1;
    int size;
//    Button saveButton=(Button) findViewById(R.id.btn_saveButton);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.yanadu.R.layout.activity_to_do);

        u1=(UserData) getIntent().getExtras().getSerializable("User");
        toDoListFragment = new ToDoListFragment();

        //값 보내기
        Bundle bundle = new Bundle();
        bundle.putSerializable("User", u1);
        toDoListFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,toDoListFragment).commit();

        ToDoservice=new ToDoRepository(this);
        ToDoservice.getToDO(u1.getId());

        Button saveButton=(Button) findViewById(R.id.btn_saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText inputToDo=(EditText) findViewById(R.id.et_inputToDo);
                 String  todo = inputToDo.getText().toString();
                Note temp = new Note(size+1,todo,getCurrentDate(),u1.getId());
                 //데이터 컬럼 수 알아오는 함
                ToDoservice.getToDO(u1.getId());

                toDoListFragment.ToDOList.add(temp);
                ToDoservice.setToDO(temp);
                inputToDo.setText("");
                toDoListFragment.adapter.notifyDataSetChanged();
            }

            //ㅎgetTODO를 지우고 먼저 서버에 보낸후 _id값(사이즈임)이 오면 그 사이즈를 NOte temp에 넣어주고 Todolist.add() + 데이터 모델 만들기

        });
    }



    @Override
    public void onGetData(ObjectData objectData) {
        CheckReturn cr=(CheckReturn) objectData;
        Log.d("check",cr.getCheck()+"");
        Toast.makeText(getApplicationContext(),"추가되었습니다.",Toast.LENGTH_SHORT).show();

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