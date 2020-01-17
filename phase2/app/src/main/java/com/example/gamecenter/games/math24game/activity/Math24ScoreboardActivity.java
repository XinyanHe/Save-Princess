package com.example.gamecenter.games.math24game.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gamecenter.user.User;
import com.example.gamecenter.login.LoginActivity;
import com.example.gamecenter.R;
import com.example.gamecenter.user.UserManager;


public class Math24ScoreboardActivity extends AppCompatActivity {

    /**
     * The quick reference for the currently logged in player.
     */
    private User currentPlayer = UserManager.getCurrentUser();

    /**
     *The controller of this view
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        addReturnButtonListener();

        //Change to appropriate game title and score description
        TextView gameTitle = findViewById(R.id.GameTitle);
        TextView scoreDescription = findViewById(R.id.ScoreDescription);
        gameTitle.setText("Math24");
        scoreDescription.setText("Score of answers correct");

        boolean displayName = getIntent().getExtras().getBoolean("saveChoice");
        TextView globalScoresText = findViewById(R.id.GlobalScores);
        String globalScoreValues = Math24Menu.scoreboard.getScoreValues(displayName,false, currentPlayer);
        globalScoresText.setText(globalScoreValues);

        TextView userScoresText = findViewById(R.id.UserScores);
        String userScoreValues = Math24Menu.scoreboard.getScoreValues(displayName,true, currentPlayer);
        userScoresText.setText(userScoreValues);
    }

    /**
     * Active the button to return to the main game screen.
     */
    private void addReturnButtonListener() {
        Button ReturnButton = findViewById(R.id.ReturnButton);
        ReturnButton.setOnClickListener(v -> switchToStarting());
    }
    private void switchToStarting(){
        Intent tmp = new Intent(this, Math24Menu.class);
        startActivity(tmp);
    }
}
