package com.example.collagehelper.activity.welcome;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.collagehelper.R;
import com.example.collagehelper.activity.login.view.LoginActivity;

/**
 * Create by liang on 2019/03/06
 */
public class WelcomeActivity extends AppCompatActivity {

    private Button btnSkip;
    private MyCountDownTimer myCountDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        btnSkip = findViewById(R.id.btn_skip);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this,LoginActivity.class);
                myCountDownTimer.cancel();
                startActivity(intent);
            }
        });
        myCountDownTimer = new MyCountDownTimer(4 * 1000, 1000);
        myCountDownTimer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myCountDownTimer.cancel();
    }

    class MyCountDownTimer extends CountDownTimer{

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture,countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            btnSkip.setText("跳过" + millisUntilFinished/1000 + "秒");
        }

        @Override
        public void onFinish() {
            Intent intent = new Intent(WelcomeActivity.this,LoginActivity.class);
            startActivity(intent);
        }
    }
}
