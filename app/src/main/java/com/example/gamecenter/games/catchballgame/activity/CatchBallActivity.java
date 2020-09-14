package com.example.gamecenter.games.catchballgame.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gamecenter.R;
import com.example.gamecenter.gameinterface.GameView;
import com.example.gamecenter.games.catchballgame.model.CatchBallManager;
import com.example.gamecenter.games.catchballgame.model.CatchBoard;
import com.example.gamecenter.games.catchballgame.presenter.CatchBallPresenter;
import com.example.gamecenter.scoreboard.ScoreboardFileSaver;
import com.example.gamecenter.strategy.BaseActivity;
import com.example.gamecenter.strategy.GameTimer;
import com.example.gamecenter.strategy.prompts.GamePrompts;
import com.example.gamecenter.strategy.prompts.Prompts;
import com.example.gamecenter.user.User;
import com.example.gamecenter.user.UserManager;

import java.io.Serializable;
import java.util.TimerTask;

/*BASED ON: https://youtu.be/ojD6ZDi2ep8
ALL CREDIT FOR THE ORIGINAL IMPLEMENTATION OF A SIMILAR SINGLETON GOES TO THE ORIGINAL AUTHOR OF
    THE CODE.*/

/**
 * Activity class of the first game: catch ball.
 * */
public class CatchBallActivity extends BaseActivity implements GameView, Serializable {

    private TextView scoreLabel;
    private TextView startLabel;

    //Score for the game
    private int score = 0;

    //Presenter
    private CatchBallPresenter presenter;

    //Initialize Class
    private Handler handler = new Handler();
    public GameTimer gameTimer;

    //Status check
    private boolean actionFlag = false;
    private boolean startFlag = false;

    /**
     * Level of the game.
     * */
    private TextView level;


    /**
     * File of CatchBall game score.
     * */
    private static final String fileName = "CatchBallScores.ser";

    /**
     * The current user.
     * */
    private User currentPlayer = UserManager.getCurrentUser();

    /**
     * Create items when starting this activity.
     */
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_ball);
        setBackButton();

        ImageView[] imgs = new ImageView[]{findViewById(R.id.orange), findViewById(R.id.black),
                findViewById(R.id.pink), findViewById(R.id.box)};
        presenter = new CatchBallPresenter(this, new CatchBallManager(new CatchBoard(getWindowManager(), -80, -80, 8, imgs)));

        level = findViewById(R.id.catchBallLevel);
        level.setText("LEVEL1");



//         Set GameTimer
        Chronometer chrono = findViewById(R.id.chronometerBall);
        gameTimer = new GameTimer(chrono);

        Button pauseButton = findViewById(R.id.catchBallPause);
        scoreLabel = findViewById(R.id.scoreLabel);
        startLabel = findViewById(R.id.startLabel);

        scoreLabel.setText("Score: 0");
        pauseButton.setTag(0);
        setPauseButton(pauseButton, gameTimer);
    }

    /**
     * Go to the result of the Game.
     */

    public void goToResult(boolean displayName) {
        presenter.getGameManager().checkToAddScore(CatchBallMenu.scoreboard, currentPlayer.getUsername(),gameTimer.getTime(),getLevel());
        ScoreboardFileSaver scoreboardFileSaver = new ScoreboardFileSaver(this, fileName);
        scoreboardFileSaver.saveToFile(fileName);
        finish();
        super.goToResult(CatchBallScoreboardActivity.class, displayName);
    }

    /**
     * Show prompts on the screen.
     * */
    public void showPrompt() {
        ViewGroup layout = findViewById(R.id.catchBall);
        Prompts prompts = new GamePrompts();
        AlertDialog dialog = prompts.createPrompt(getLayoutInflater(), layout, this);
        prompts.getBackToMainBtn().setOnClickListener(v -> {backToMain();
        });
        prompts.getDisplayBothBtn().setOnClickListener(v -> {
            goToResult(true);
        });
        prompts.getOnlyScoreBtn().setOnClickListener(v -> goToResult(false));
        dialog.show();

    }


    /**
     * Update the GameTimer.
     */

    @SuppressLint("SetTextI18n")
    public void updateTimer() {
        gameTimer.getTimer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(() -> {
                    presenter.hitCheck(actionFlag);
                    scoreLabel.setText("Score: " + score);
                });
            }
        }, 0, 30);

    }

    /**
     * Set the value of level
     * @param levels */
    public void setLevel(String levels) {
        level.setText(levels);
    }

    /**
     * Create events on touching.
     */
    @Override
    public boolean onTouchEvent(MotionEvent action) {

        FrameLayout frame = findViewById(R.id.frame);

        presenter.onStart(action, startFlag, frame.getHeight() - 130);

        return true;
    }

    /**
     * Enable or disable the action.
     *
     * @param action */
    public void makeAction(MotionEvent action) {
        if (action.getAction() == MotionEvent.ACTION_DOWN) {
            actionFlag = true;
        } else if (action.getAction() == MotionEvent.ACTION_UP) {
            actionFlag = false;
        }

    }

    /**
     * Hide the start label from users.
     * */
    public void hideStartLabel() {
        startLabel.setVisibility(View.GONE);
    }

    /**
     * Update the score of the player.
     *
     * @param score the score of the player */
    public void updateScore(int score) {
        this.score = score;
    }

    /**
     * Get the timer for the game.
     *
     * @return */
    public GameTimer getGameTimer() {
        return gameTimer;
    }

    /**
     * Get the score of the player.
     *
     * @return */
    public int getScore() {
        return score;
    }

    /**
     * Set the start flag to a boolean value.
     *
     * @param startFlag */
    public void setStartFlag(boolean startFlag) {
        this.startFlag = startFlag;
    }

    /**
     * Set the text of the Pause button, whether pause or resume.
     *
     * @param pauseBtn the button used to pause a game
     * @param gameTimer a timer that displays the time
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void setPauseButton(Button pauseBtn, GameTimer gameTimer) {
        pauseBtn.setOnClickListener(v -> {
            if (( int ) pauseBtn.getTag() == 0 && startFlag) {
                pauseBtn.setTag(1);

                gameTimer.stop();
                //Change Button Text;
                pauseBtn.setText("RESUME");

            } else {
                pauseBtn.setTag(0);
                gameTimer.restart();
                updateTimer();
                //Change Button Text;
                pauseBtn.setText("PAUSE");
            }

        });
    }

    /**
     * Set the back-to-main button.
     * */
    public void setBackButton() {
        findViewById(R.id.catchBallBack).setOnClickListener(v -> {
            backToMain();
        });
    }

    /**
     * Create the event of the back button.
     * */
    private void backToMain() {
        switchToPage(CatchBallMenu.class);
        presenter.onDestroy();
        finish();
    }

    /**
     * Get the current Level of User
     * */
    private String getLevel() {
        return level.getText().toString();
    }
}
