package com.omelchenkoaleks.braintrainer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScoreActivity extends AppCompatActivity {
    private TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        mResultTextView = findViewById(R.id.result_text_view);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("result")) {
            int result = intent.getIntExtra("result", 0);

            /*
                получаем данные из SharedPreferences
             */
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            int max = preferences.getInt("max", 0);

            String score = String.format("Ваш результат: %s\nМаксимальный результат: %s", result, max);
            mResultTextView.setText(score);
        }
    }

    public void onClickStartNewGame(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
