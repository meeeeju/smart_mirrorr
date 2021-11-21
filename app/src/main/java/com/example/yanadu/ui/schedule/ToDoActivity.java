package com.example.yanadu.ui.schedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import com.example.yanadu.R;

import java.text.BreakIterator;

public class ToDoActivity extends AppCompatActivity {

    private static final String TAG = "ToDoActivity";

    Fragment mainFragment;
//    Button saveButton=(Button) findViewById(R.id.btn_saveButton);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.yanadu.R.layout.activity_to_do);

        mainFragment = new MainFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container,mainFragment).commit();

        Button saveButton=(Button) findViewById(R.id.btn_saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText inputToDo=(EditText) findViewById(R.id.et_inputToDo);
                String todo = inputToDo.getText().toString();

                Toast.makeText(getApplicationContext(),"추가되었습니다.",Toast.LENGTH_SHORT).show();

            }


        });
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