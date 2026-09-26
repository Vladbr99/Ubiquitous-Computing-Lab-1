package com.example.lab1part2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private int secretNumber;
    private int guessCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        EditText editGuess = findViewById(R.id.editGuess);
        TextView textFeedback = findViewById(R.id.textFeedback);
        TextView textGuessCount = findViewById(R.id.textGuessCount);
        Button buttonGuess = findViewById(R.id.buttonGuess);
        Button buttonPlayAgain = findViewById(R.id.buttonPlayAgain);

        startNewGame(textFeedback, textGuessCount, editGuess, buttonGuess, buttonPlayAgain);

        buttonGuess.setOnClickListener(v -> {
            String guessText = editGuess.getText().toString();

            if (guessText.isEmpty()) {
                textFeedback.setText("Please enter a number.");
                return;
            }

            int guess = Integer.parseInt(guessText);
            guessCount++;
            textGuessCount.setText("Guesses: " + guessCount);

            if (guess == secretNumber) {
                textFeedback.setText("Correct! The number was " + secretNumber + ".");
                buttonGuess.setEnabled(false);
                buttonPlayAgain.setEnabled(true);
            } else if (guess < secretNumber) {
                textFeedback.setText("Too low! Try higher.");
            } else {
                textFeedback.setText("Too high! Try lower.");
            }
        });

        buttonPlayAgain.setOnClickListener(v -> {
            startNewGame(textFeedback, textGuessCount, editGuess, buttonGuess, buttonPlayAgain);
        });
    }
    private void startNewGame(TextView feedback, TextView count, EditText input,
                              Button guessBtn, Button playAgainBtn) {

        secretNumber = (int)(Math.random() * 30) + 1;
        guessCount = 0;

        feedback.setText("");
        count.setText("Guesses: 0");
        input.setText("");

        guessBtn.setEnabled(true);
        playAgainBtn.setEnabled(false);
    }

}