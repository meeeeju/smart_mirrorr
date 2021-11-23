package com.example.yanadu.ui.schedule;

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

import java.text.BreakIterator;

public class ToDoActivity extends Fragment {

    private static final String TAG = "ToDoActivity";


    Fragment mainFragment;
//    Button saveButton=(Button) findViewById(R.id.btn_saveButton);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.activity_to_do, container, false);
        mainFragment = new MainFragment();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,mainFragment).commit();

        Button saveButton=(Button) v.findViewById(R.id.btn_saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText inputToDo=(EditText) v.findViewById(R.id.et_inputToDo);
                String todo = inputToDo.getText().toString();

                Toast.makeText(getActivity().getApplicationContext(),"추가되었습니다.",Toast.LENGTH_SHORT).show();

            }


        });


        return inflater.inflate(R.layout.activity_to_do, container, false);
    }



    private void saveToDO(String todo) {


//        String todo = inputToDo.getText().toString();
//
//        //서버로 보내서 저장하기 요청
//
//
//        inputToDo.setText("");
    }
}