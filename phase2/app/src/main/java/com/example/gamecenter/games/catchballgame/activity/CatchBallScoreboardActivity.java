package com.example.gamecenter.games.catchballgame.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.gamecenter.R;
import com.example.gamecenter.user.User;
import com.example.gamecenter.user.UserManager;

/**
 * The CatchBallScoreboardActivity can display the Scoreboard fot the CatchBall Game.
 */
public class CatchBallScoreboardActivity extends AppCompatActivity {

    /**
     * The current user.
     * */
    private User currentPlayer = UserManager.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        addReturnButtonListener();

        boolean displayName = getIntent().getExtras().getBoolean("saveChoice");
        //Change to appropriate game title and score description
        TextView gameTitle = findViewById(R.id.GameTitle);
        TextView scoreDescription = findViewById(R.id.ScoreDescription);
        gameTitle.setText("CatchBall");
        scoreDescription.setText("Highest Points of ball collected");

        TextView globalScoresText = findViewById(R.id.GlobalScores);

        String globalScoreValues = CatchBallMenu.scoreboard.getScoreValues(displayName,false, currentPlayer);
        globalScoresText.setText(globalScoreValues);

        TextView userScoresText = findViewById(R.id.UserScores);
        String userScoreValues = CatchBallMenu.scoreboard.getScoreValues(displayName,true, currentPlayer);
        userScoresText.setText(userScoreValues);
    }

    /**
     * Active the button to return to the main game screen.
     */
    private void addReturnButtonListener() {
        Button ReturnButton = findViewById(R.id.ReturnButton);
        ReturnButton.setOnClickListener(v -> switchToStarting());
    }

    /**
     * Switch to the starting menu activity of CatchBall.
     * */
    private void switchToStarting() {
        Intent tmp = new Intent(this, CatchBallMenu.class);
        startActivity(tmp);
    }
}
