package com.example.yanadu.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.yanadu.R;
import com.example.yanadu.ui.extra.game.GameActivity;
import com.mukesh.OnOtpCompletionListener;
import com.mukesh.OtpView;

public class MirrorConnection extends AppCompatActivity implements View.OnClickListener, OnOtpCompletionListener {
    private Button validateButton;
    private OtpView otpView;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mirror_connection);
        initializeUi();
        setListeners();
    }

    @Override public void onClick(View v) {
        if (v.getId() == R.id.validate_button) {
            Toast.makeText(this, otpView.getText(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(),RealMainActivity.class);
            startActivity(intent);
        }
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
        Toast.makeText(this, "OnOtpCompletionListener called", Toast.LENGTH_SHORT).show();
    }
}