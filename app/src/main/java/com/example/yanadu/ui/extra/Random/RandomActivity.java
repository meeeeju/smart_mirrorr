package com.example.yanadu.ui.extra.Random;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.yanadu.R;

import java.util.ArrayList;

public class RandomActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    Fragment mainFragment;
    EditText intputRandom;
    Context context;

    RecyclerView recyclerView;
    RandomAdapter adapter;

    SwipeRefreshLayout swipeRefreshLayout;

    public static RandomDatabase randomDatabase = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        Button saveButton = findViewById(R.id.saveBtnR);
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                saveToDo();

                Toast.makeText(getApplicationContext(),"추가되었습니다.",Toast.LENGTH_SHORT).show();
                loadNoteListData();
            }
        });
        initUI();

        openDatabase();
        loadNoteListData();
    }

    private void initUI(){

        //recyclerView연결
        recyclerView = findViewById(R.id.recyclerViewRandom);

        //LinearLayoutManager을 이용하여 recyclerView설정
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //어댑터 연결결
        adapter = new RandomAdapter();
        recyclerView.setAdapter(adapter);

    }

    public int loadNoteListData(){

        //데이터를 가져오는 sql문 select... (id의 역순으로 정렬)
        String loadSql = "select _id, TODO from " + RandomDatabase.TABLE_NOTE + " order by _id desc";

        int recordCount = -1;
        RandomDatabase database = RandomDatabase.getInstance(context);

        if(database != null){
            //cursor를 객체화하여 rawQuery문 저장
            Cursor outCursor = database.rawQuery(loadSql);

            recordCount = outCursor.getCount();

            //_id, TODO가 담겨질 배열 생성
            ArrayList<Random> items = new ArrayList<>();

            //for문을 통해 하나하나 추가
            for(int i = 0; i < recordCount; i++){
                outCursor.moveToNext();

                int _id = outCursor.getInt(0);
                String todo = outCursor.getString(1);
                items.add(new Random(_id,todo));
            }
            outCursor.close();

            //어댑터에 연결 및 데이터셋 변경
            adapter.setItems(items);
            adapter.notifyDataSetChanged();
        }

        return recordCount;
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