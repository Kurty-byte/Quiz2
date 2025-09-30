package com.example.quiz2.models;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import com.example.quiz2.R;

import java.util.ArrayList;
import java.util.Random;

public class Dice {
    Random random = new Random();
    private ImageView dice_face;
    private int val;
    private static ArrayList<Integer> colors;

    private static void setupColors(Context context) {
        colors = new ArrayList<>();
        colors.add(ContextCompat.getColor(context, R.color.red));
        colors.add(ContextCompat.getColor(context, R.color.blue));
        colors.add(ContextCompat.getColor(context, R.color.green));
        colors.add(ContextCompat.getColor(context, R.color.yellow));
        colors.add(ContextCompat.getColor(context, R.color.magenta));
        colors.add(ContextCompat.getColor(context, R.color.cyan));
        colors.add(ContextCompat.getColor(context, R.color.orange));
        colors.add(ContextCompat.getColor(context, R.color.purple));
    }

    public Dice() {
    }

    public void setDice_face(ImageView dice_face) {
        this.dice_face = dice_face;
        if (colors == null) {
            setupColors(dice_face.getContext());
        }
    }

    public int getVal() {
        return val;
    }

    public void roll() {
        dice_face.animate()
                .scaleX(0.8f)
                .scaleY(0.8f)
                .rotationBy(360)
                .setDuration(500)
                .withEndAction(() -> dice_face.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(500)
                        .start())
                .start();

        int roll = random.nextInt(6) + 1;
        this.val = roll;
        updateDice(roll);
    }

    // For backward compatibility if needed
    public void roll(ArrayList<Integer> c) {
        roll();
    }

    private void changeColor() {
        int rand_color = colors.get(random.nextInt(colors.size()));
        dice_face.setColorFilter(rand_color, PorterDuff.Mode.MULTIPLY);
    }

    private void updateDice(int value) {
        switch (value) {
            case 1:
                dice_face.setImageResource(R.drawable.dice_1);
                break;
            case 2:
                dice_face.setImageResource(R.drawable.dice_2);
                break;
            case 3:
                dice_face.setImageResource(R.drawable.dice_3);
                break;
            case 4:
                dice_face.setImageResource(R.drawable.dice_4);
                break;
            case 5:
                dice_face.setImageResource(R.drawable.dice_5);
                break;
            case 6:
                dice_face.setImageResource(R.drawable.dice_6);
                break;
        }
        changeColor();
    }
}