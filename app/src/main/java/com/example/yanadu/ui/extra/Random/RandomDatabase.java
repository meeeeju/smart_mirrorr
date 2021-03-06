package com.example.yanadu.ui.extra.Random;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class RandomDatabase {
    private static final String TAG = "RandomDatabase";

    private static RandomDatabase database;
    public static String DATABASE_NAME = "random.db";
    public static String TABLE_NOTE = "RANDOM_NOTE";
    public static int DATABASE_VERSION = 1;

    private Context context;
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;


    private RandomDatabase(Context context){
        this.context = context;
    }

    public static RandomDatabase getInstance(Context context){
        if(database == null){
            database = new RandomDatabase(context);
        }

        return database;
    }

    //이후 리스트를 표시할 때 현지위치를 나타내는 커서역할을 함
    public Cursor rawQuery(String SQL){

        Cursor c1 = null;
        try{
            c1 = db.rawQuery(SQL,null);
        } catch (Exception ex){
            Log.e(TAG,"Exception in rawQuery",ex);
        }

        return c1;
    }

    //sql문을 실행시키는 역할을 함
    public boolean execSQL(String SQL) {

        try {
            Log.d(TAG, "SQL : " + SQL);
            db.execSQL(SQL);
        } catch(Exception ex) {
            Log.e(TAG, "Exception in execSQL", ex);
            return false;
        }
        return true;
    }

    private class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
            super(context, name,factory,version);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {


            //테이블초기화
            String DROP_SQL = "drop table if exists " +TABLE_NOTE;

            try {
                db.execSQL(DROP_SQL);

            } catch (Exception ex){
                Log.e(TAG, "Exception in DROP_SQL", ex);
            }

            //테이블 생섣
            String CREATE_SQL = "create table " + TABLE_NOTE + "("
                    + " _id integer NOT NULL PRIMARY KEY AUTOINCREMENT, "
                    + "  TODO TEXT DEFAULT '' "
                    + ")";
            try{
                db.execSQL(CREATE_SQL);
            } catch (Exception ex){
                Log.e(TAG,"Exception in CREATE_SQL", ex);
            }

            //테이블의 인덱스 붙이기
            String CREATE_INDEX_SQL = "create index " + TABLE_NOTE + "_IDX ON " + TABLE_NOTE + "("
                    + "_id"
                    + ")";
            try{
                db.execSQL(CREATE_INDEX_SQL);
            } catch (Exception ex){
                Log.e(TAG, "Exception in CREATE_INDEX_SQL",ex);
            }

//
            ArrayList<String> dummyRandomData = new ArrayList<String>(){{
                add("하루 1시간 걷기");
                add("하루 물 1L 마시기");
                add("택시 탈 거리 걸어가기");
                add("하루 3번웃기 ");

            };}; // ArrayList 선언

            for (int i=0;i<dummyRandomData.size();i++)
            {
                //테이블에 값을 추가하는 sql구문 insert...
                String sqlSave = "insert into " + RandomDatabase.TABLE_NOTE + " (TODO) values (" +
                        "'" + dummyRandomData.get(i) + "')";
                db.execSQL(sqlSave);
            }
        }





        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

    }

    //데이터베이스 열기
    public boolean open(){

        dbHelper = new DatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = dbHelper.getWritableDatabase();

        return true;
    }

    //데이터베이스 닫기
    public void close(){
        db.close();
        database = null;
    }
}
