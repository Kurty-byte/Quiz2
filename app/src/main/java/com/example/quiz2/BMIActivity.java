package com.example.quiz2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.ParseException;

public class BMIActivity extends AppCompatActivity {

    // UI components
    Button back_btn, calc_bmi_btn;
    EditText height_inp, weight_inp;
    TextView bmi_class, bmi_res, values;

    // Holders
    double temp_h, temp_w, total_bmi;
    String cls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bmiactivity);

        // Mutables
        height_inp = (EditText) findViewById(R.id.height_input);
        weight_inp = (EditText) findViewById(R.id.weight_input);
        calc_bmi_btn = (Button) findViewById(R.id.calculate_btn);
        bmi_class = (TextView) findViewById(R.id.bmi_class);
        bmi_res = (TextView) findViewById(R.id.bmi_result);
        values = (TextView) findViewById(R.id.inp_fields);

        // Back button
        back_btn = (Button) findViewById(R.id.back_btn);

        // Activity switching
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        calc_bmi_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFieldEmpty()) {
                    Toast.makeText(BMIActivity.this, "Please fill in both height and weight fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!setTotalBMI()) {
                    return;
                }

                setBMIClass();
                updateView();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void updateView() {
        height_inp.getText().clear();
        weight_inp.getText().clear();

        bmi_class.setText(cls);
        bmi_res.setText(String.format("BMI: %.2f", total_bmi));
        values.setText(String.format("Height: %.2f(m) Weight: %.2f(kg)", temp_h, temp_w));
    }

    private boolean setTotalBMI() {

        try {
            double h = Double.parseDouble(String.valueOf(height_inp.getText()));
            double w = Double.parseDouble(String.valueOf(weight_inp.getText()));
            if (h <= 0 || w <= 0) {
                Toast.makeText(this, "Height and weight must be greater than zero", Toast.LENGTH_LONG).show();
                return false;
            }
            total_bmi = w / (h * h);

            temp_h = h;
            temp_w = w;
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void setBMIClass() {
        if (temp_h < 1 || temp_w < 1) {
            cls = "Invalid Input.";
        } else if (total_bmi < 15) {
            cls = "Very severely underweight";
        } else if (total_bmi < 16) {
            cls = "Severely underweight";
        } else if (total_bmi < 18.5) {
            cls = "Underweight";
        } else if (total_bmi < 25) {
            cls = "Normal";
        } else if (total_bmi < 30) {
            cls = "Overweight";
        } else if (total_bmi < 35) {
            cls = "Obese Class 1 - Moderately Obese";
        } else if (total_bmi < 40) {
            cls = "Obese Class 2 - Severely Obese";
        } else {
            cls = "Obese Class 3 - Very Severely Obese";
        }
    }

    private boolean isFieldEmpty() {
        return height_inp.getText().length() == 0 || weight_inp.getText().length() == 0;
    }
}