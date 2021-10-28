package com.example.fromapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class QuizActivity extends AppCompatActivity {

    public static final String EXTRA_ANSWER = "extra_answer";
    public static final Integer RESULT_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        RadioGroup rgJwb = (RadioGroup) findViewById(R.id.rgjwb);

        Button bJwb = (Button) findViewById(R.id.bJwb);
        bJwb.setOnClickListener(v -> {
            int answer = 0;
            if(rgJwb.getCheckedRadioButtonId() != 0){
                int id = rgJwb.getCheckedRadioButtonId();
                if(id == R.id.rb13){
                    answer = 1;
                }
            }
            Intent resultIntent = new Intent();
            resultIntent.putExtra(EXTRA_ANSWER, answer);
            setResult(RESULT_CODE, resultIntent);
            finish();
        });

    }
}