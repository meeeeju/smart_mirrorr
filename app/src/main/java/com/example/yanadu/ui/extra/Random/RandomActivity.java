package com.example.yanadu.ui.extra.Random;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.yanadu.R;

import java.util.ArrayList;

public class RandomActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    Fragment mainFragment;
    EditText intputRandom;
    Context context;

    public static RandomDatabase randomDatabase = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        mainFragment = new RandomFragment();

        //getSupportFragmentManager 을 이용하여 이전에 만들었던 **FrameLayout**에 `fragment_main.xml`이 추가
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mainFragment).commit();

        Button saveButton = findViewById(R.id.saveBtnR);
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                saveToDo();

                Toast.makeText(getApplicationContext(),"추가되었습니다.",Toast.LENGTH_SHORT).show();

            }
        });
        openDatabase();
    }

    private void saveToDo(){
        intputRandom = findViewById(R.id.inputR);

        //EditText에 적힌 글을 가져오기
        String randomHabit = intputRandom.getText().toString();

        //테이블에 값을 추가하는 sql구문 insert...
        String sqlSave = "insert into " + RandomDatabase.TABLE_NOTE + " (TODO) values (" +
                "'" + randomHabit + "')";

        //sql문 실행
        RandomDatabase database = RandomDatabase.getInstance(context);
        database.execSQL(sqlSave);

        //저장과 동시에 EditText 안의 글 초기화
        intputRandom.setText("");
    }


    public void openDatabase() {
        // open database
        if (randomDatabase != null) {
            randomDatabase.close();
            randomDatabase = null;
        }

        randomDatabase = RandomDatabase.getInstance(this);
        boolean isOpen = randomDatabase.open();
        if (isOpen) {
            Log.d(TAG, "Random database is open.");
        } else {
            Log.d(TAG, "Random database is not open.");
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (randomDatabase != null) {
            randomDatabase.close();
            randomDatabase = null;
        }
    }


}