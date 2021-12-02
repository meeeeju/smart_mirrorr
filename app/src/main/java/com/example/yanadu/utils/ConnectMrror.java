package com.example.yanadu.utils;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.example.yanadu.data.model.UserData;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ConnectMrror extends Thread {
    Context c;
    String code;
    UserData u;
    Handler handler;
    public ConnectMrror(Context c, String code, UserData u) {
        this.c = c;
        this.code = code;
        this.u = u;

    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket("125.6.37.219", 16005);
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            byte[] byteArr= null;
            String s = "mobile\n" + code + "\n" + u.getId() + "\n" + u.getNickname() + "\n";
            Log.d("asdf", s);
            byteArr = s.getBytes("UTF-8");

            os.write(byteArr);
            os.flush();

            byteArr = new byte[512];
            int readByteCount = is.read(byteArr);

            if(readByteCount == -1)
                throw new IOException();

            s = new String(byteArr, 0, readByteCount, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}