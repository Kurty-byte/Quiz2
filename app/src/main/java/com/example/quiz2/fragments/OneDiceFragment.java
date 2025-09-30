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

public class OneDiceFragment extends Fragment {

    private ImageView diceImage;
    private TextView resultText;
    private Button rollButton;
    private Dice dice;

    public OneDiceFragment() {
    }

    public static OneDiceFragment newInstance() {
        return new OneDiceFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one_dice, container, false);

        // Initialize views
        diceImage = view.findViewById(R.id.dice_image_1);
        resultText = view.findViewById(R.id.result_text);
        rollButton = view.findViewById(R.id.roll_button);

        // Initialize dice object
        dice = new Dice();
        dice.setDice_face(diceImage);

        // Set click listener for roll button
        rollButton.setOnClickListener(v -> rollDice());

        return view;
    }

    private void rollDice() {
        dice.roll();

        // Update result text after animation completes
        rollButton.postDelayed(() -> {
            int result = dice.getVal();
            resultText.setText("Result: " + result);
        }, 1000); // Wait for animation to complete
    }
}
