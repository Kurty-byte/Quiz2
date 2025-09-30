package com.example.quiz2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button to_bmi;
    Button to_dr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        to_bmi = (Button) findViewById(R.id.to_bmi_btn);
        to_dr = (Button) findViewById(R.id.to_dr_btn);

        to_bmi.setOnClickListener(this::navigateToBMI);
        to_dr.setOnClickListener(this::navigateToDiceRoller);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void navigateToBMI(View v) {
        Intent intent = new Intent(MainActivity.this, BMIActivity.class);
        startActivity(intent);
    }

    private void navigateToDiceRoller(View v) {
        Intent intent = new Intent(MainActivity.this, DiceRollerActivity.class);
        startActivity(intent);
    }
}