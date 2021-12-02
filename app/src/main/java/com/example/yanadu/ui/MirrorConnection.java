package com.example.yanadu.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.yanadu.R;
import com.example.yanadu.data.model.UserData;
import com.example.yanadu.ui.extra.game.GameActivity;
import com.example.yanadu.ui.login.SignupActivity;
import com.example.yanadu.utils.ConnectMrror;
import com.mukesh.OnOtpCompletionListener;
import com.mukesh.OtpView;

public class MirrorConnection extends AppCompatActivity implements View.OnClickListener, OnOtpCompletionListener {
    private Button validateButton;
    private OtpView otpView;
    UserData u;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mirror_connection);
        u = (UserData) getIntent().getSerializableExtra("User");
        initializeUi();
        setListeners();
    }

    @Override public void onClick(View v) {


        Toast.makeText(getApplicationContext(),"거울 연결 요청을 보냈습니다.", Toast.LENGTH_SHORT).show();
    }

    private void initializeUi() {
        otpView = findViewById(R.id.otp_view);
        validateButton = findViewById(R.id.validate_button);
    }

    private void setListeners() {
        validateButton.setOnClickListener(this);
        otpView.setOtpCompletionListener(this);
    }

    @Override public void onOtpCompleted(String otp) {
        // do Stuff
        ConnectMrror sc=new ConnectMrror(MirrorConnection.this,otp,u);
        sc.start();
        finish();
        Toast.makeText(this, "OnOtpCompletionListener called", Toast.LENGTH_SHORT).show();

    }
}