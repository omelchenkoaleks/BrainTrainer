package com.omelchenkoaleks.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView mTimerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTimerTextView = findViewById(R.id.timer_text_view);

        CountDownTimer timer = new CountDownTimer(6000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                mTimerTextView.setText(Integer.toString(seconds));
            }

            /*
                метод вызывается при завершении отчета
             */
            @Override
            public void onFinish() {
                Toast.makeText(getApplicationContext(), "Таймер завершен",
                        Toast.LENGTH_SHORT).show();
                mTimerTextView.setText(Integer.toString(0));
            }
        };

        timer.start();
    }
}
