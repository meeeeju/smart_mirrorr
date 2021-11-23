package com.example.yanadu.ui.schedule;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import com.example.yanadu.R;
import com.example.yanadu.data.model.CheckReturn;
import com.example.yanadu.data.model.Note;
import com.example.yanadu.data.model.ObjectData;
import com.example.yanadu.data.model.UserData;
import com.example.yanadu.data.repository.ToDoRepository;
import com.example.yanadu.data.repository.UserRepository;
import com.example.yanadu.data.request.OnGetData;

import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ToDoActivity extends Fragment implements OnGetData{

    private static final String TAG = "ToDoActivity";


    Fragment mainFragment;
    ToDoRepository TodoService;
    UserData u1;

//    Button saveButton=(Button) findViewById(R.id.btn_saveButton);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.activity_to_do, container, false);
        mainFragment = new MainFragment();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,mainFragment).commit();

//
//        TodoService=new ToDoRepository(this);  //this는 OnGetData
//        u1=(UserData) getArguments().getSerializable("User");
//

        Button saveButton=(Button) v.findViewById(R.id.btn_saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText inputToDo=(EditText) v.findViewById(R.id.et_inputToDo);
                String todo = inputToDo.getText().toString();
//                Log.d("check","tesing"+u1.getId()+"");
//
//                //서버로 전송
//                TodoService.setToDO(new Note(1,u1.getId(),todo,getCurrentDate()));
                inputToDo.setText("");
                Toast.makeText(getActivity().getApplicationContext(),"추가되었습니다.",Toast.LENGTH_SHORT).show();
            }


        });

        return inflater.inflate(R.layout.activity_to_do, container, false);
    }


    private String getCurrentDate() {
        //System.out.println(now); 현재 시간 출력
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String formatedNow = formatter.format(now); // 포맷팅 적용
        return formatedNow;

    }

    @Override
    public void onGetData(ObjectData objectData) {

        CheckReturn flag=(CheckReturn) objectData;

        if (flag.getCheck())
        {
            Log.d("check setToDO funct",flag.getCheck()+"");
        }


    }

    @Override
    public void onSendDate(ObjectData objectData) {

    }

    @Override
    public void onGetDataList(List<ObjectData> objectDataList) {

    }
}