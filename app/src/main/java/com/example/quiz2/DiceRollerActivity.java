package com.example.quiz2;

import android.content.Intent;
import android.graphics.Color;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.quiz2.fragments.OneDiceFragment;
import com.example.quiz2.fragments.TwoDiceFragment;
import com.example.quiz2.fragments.ThreeDiceFragment;
import com.example.quiz2.fragments.FourDiceFragment;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DiceRollerActivity extends AppCompatActivity {

    // UI components
    Button back_btn;
    TextView num_of_dice;
    FrameLayout dice_container;
    Spinner die_count_options;

    // Die count
    String[] opt;
    // Mutable
    int dice_count;

    // Fragment manager for handling fragments
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dice_roller);

        // Initialize fragment manager
        fragmentManager = getSupportFragmentManager();

        // Adding options to the spinner
        opt = getResources().getStringArray(R.array.num_rolls);

        dice_container = (FrameLayout) findViewById(R.id.die_frame);
        die_count_options = (Spinner) findViewById(R.id.die_options);
        back_btn = (Button) findViewById(R.id.back_btn);
        num_of_dice = (TextView) findViewById(R.id.die_count);

        setupSpinner();

        // Load the default fragment (1 die)
        loadFragment(new OneDiceFragment());

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        die_count_options.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected_value = opt[position];
                num_of_dice.setText(selected_value);
                dice_count = Integer.parseInt(selected_value);

                // Switch fragments based on selection
                switchFragment(dice_count);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                num_of_dice.setText("1");
                dice_count = 1;
                loadFragment(new OneDiceFragment());
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void setupSpinner() {
        die_count_options = (Spinner) findViewById(R.id.die_options);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opt);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        die_count_options.setAdapter(adapter);
    }

    private void switchFragment(int numberOfDice) {
        Fragment fragment;

        switch (numberOfDice) {
            case 1:
                fragment = OneDiceFragment.newInstance();
                break;
            case 2:
                fragment = TwoDiceFragment.newInstance();
                break;
            case 3:
                fragment = ThreeDiceFragment.newInstance();
                break;
            case 4:
                fragment = FourDiceFragment.newInstance();
                break;
            default:
                fragment = OneDiceFragment.newInstance();
                break;
        }

        loadFragment(fragment);
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.die_frame, fragment);
        transaction.commit();
    }
}