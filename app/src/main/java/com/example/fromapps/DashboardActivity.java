package com.example.fromapps;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> resultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        TextView dName = (TextView) findViewById(R.id.dName);
        TextView dEmail = (TextView) findViewById(R.id.dEmail);
        TextView dPhone = (TextView) findViewById(R.id.dPhone);

        dName.setText(getIntent().getStringExtra(MainActivity.EXTRA_NAME));
        dEmail.setText(getIntent().getStringExtra(MainActivity.EXTRA_EMAIL));
        dPhone.setText(getIntent().getStringExtra(MainActivity.EXTRA_PHONE));

        Button bCall = (Button) findViewById(R.id.bCall);
        bCall.setOnClickListener(v -> {
            String number = "*123#";
            Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+number));
            startActivity(callIntent);
        });

        Button bQuiz = (Button) findViewById(R.id.bQuiz);
        bQuiz.setOnClickListener(v -> {
            Intent quizIntent = new Intent(DashboardActivity.this, QuizActivity.class);
            resultLauncher.launch(quizIntent);
        });

        TextView tvResult = (TextView) findViewById(R.id.dNilai);
        resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == QuizActivity.RESULT_CODE && result.getData() != null) {
                        int answer = result.getData().getIntExtra(QuizActivity.EXTRA_ANSWER, 0);
                        if(answer == 1) {
                            tvResult.setText("BENAR");
                        } else {
                            tvResult.setText("SALAH");
                        }
                    }
                });
    }
}