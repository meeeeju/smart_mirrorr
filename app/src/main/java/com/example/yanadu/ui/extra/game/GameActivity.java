package com.example.yanadu.ui.extra.game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.yanadu.R;
import com.example.yanadu.ui.extra.Random.RandomDatabase;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    Fragment mainFragment;
    EditText inputToDo;
    Context context;

    public static com.example.yanadu.ui.extra.game.NoteDatabase noteDatabase = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mainFragment = new GameFragment();

        //getSupportFragmentManager 을 이용하여 이전에 만들었던 **FrameLayout**에 `fragment_main.xml`이 추가
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mainFragment).commit();

        Button saveButton = findViewById(R.id.saveBtnG);
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                saveToDo();



            }
        });
        openDatabase();
    }
    private void saveToDo(){
        inputToDo = findViewById(R.id.inputToDo);

        //EditText에 적힌 글을 가져오기
        String todo = inputToDo.getText().toString();
        if (!(todo.equals("")))
        {
            //테이블에 값을 추가하는 sql구문 insert...
            String sqlSave = "insert into " + com.example.yanadu.ui.extra.game.NoteDatabase.TABLE_NOTE + " (TODO) values (" +
                    "'" + todo + "')";

            //sql문 실행
            com.example.yanadu.ui.extra.game.NoteDatabase database = com.example.yanadu.ui.extra.game.NoteDatabase.getInstance(context);
            database.execSQL(sqlSave);
            Toast.makeText(getApplicationContext(),"추가되었습니다.",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"입력칸을 채워주세요.",Toast.LENGTH_SHORT).show();
        }

//        //테이블에 값을 추가하는 sql구문 insert...
//        String sqlSave = "insert into " + NoteDatabase.TABLE_NOTE + " (TODO) values (" +
//                "'" + todo + "')";
//
//        //sql문 실행
//        NoteDatabase database = NoteDatabase.getInstance(context);
//        database.execSQL(sqlSave);

        //저장과 동시에 EditText 안의 글 초기화
        inputToDo.setText("");
    }

    private void MakeLieData(){

        Log.d("dkdkd","나 실행됨");

        ArrayList<String> dummyLieData = new ArrayList<String>(){{
            add("거짓말 1");
            add("거짓말 2");
            add("거짓말 2");


        };}; // ArrayList 선언

        for (int i=0;i<dummyLieData.size();i++)
        {
            //테이블에 값을 추가하는 sql구문 insert...
            String sqlSave = "insert into " + NoteDatabase.TABLE_NOTE + " (TODO) values (" +
                    "'" + dummyLieData.get(i) + "')";
            //sql문 실행
            NoteDatabase database = NoteDatabase.getInstance(context);
            database.execSQL(sqlSave);
        }

    }


    public void openDatabase() {
        // open database
        if (noteDatabase != null) {
            noteDatabase.close();
            noteDatabase = null;
        }

        noteDatabase = com.example.yanadu.ui.extra.game.NoteDatabase.getInstance(this);
        boolean isOpen = noteDatabase.open();
        if (isOpen) {
            Log.d(TAG, "Note database is open.");
        } else {
            Log.d(TAG, "Note database is not open.");
        }

        MakeLieData();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (noteDatabase != null) {
            noteDatabase.close();
            noteDatabase = null;
        }
    }


}