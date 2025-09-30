package com.example.quiz2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.quiz2.R;
import com.example.quiz2.models.Dice;

public class TwoDiceFragment extends Fragment {

    private ImageView diceImage1, diceImage2;
    private TextView resultText;
    private Button rollButton;
    private Dice dice1, dice2;

    public TwoDiceFragment() {
    }

    public static TwoDiceFragment newInstance() {
        return new TwoDiceFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two_dice, container, false);

        // Initialize views
        diceImage1 = view.findViewById(R.id.dice_image_1);
        diceImage2 = view.findViewById(R.id.dice_image_2);
        resultText = view.findViewById(R.id.result_text);
        rollButton = view.findViewById(R.id.roll_button);

        // Initialize dice objects
        dice1 = new Dice();
        dice1.setDice_face(diceImage1);

        dice2 = new Dice();
        dice2.setDice_face(diceImage2);

        // Set click listener for roll button
        rollButton.setOnClickListener(v -> rollDice());

        return view;
    }

    private void rollDice() {
        rollButton.setEnabled(false);
        dice1.roll();
        dice2.roll();

        // Update result text after animation completes
        rollButton.postDelayed(() -> {
            int dice1Value = dice1.getVal();
            int dice2Value = dice2.getVal();
            int total = dice1Value + dice2Value;
            resultText.setText("Result: " + total + " (" + dice1Value + "+" + dice2Value + ")");
            rollButton.setEnabled(true);
        }, 1000); // Wait for animation to complete
    }
}
