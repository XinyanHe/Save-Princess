package com.example.gamecenter.games.slidinggame.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import com.example.gamecenter.R;
import com.example.gamecenter.gameinterface.GameView;
import com.example.gamecenter.games.slidinggame.SlidingPresenter;
import com.example.gamecenter.strategy.BaseActivity;
import com.example.gamecenter.strategy.GameTimer;
import com.example.gamecenter.strategy.prompts.GamePrompts;
import com.example.gamecenter.strategy.prompts.Prompts;

import static android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;


@SuppressLint("Registered")
public class SlidingActivity extends BaseActivity implements GameView {

    private static int score = 0;
    private TextView textScore;
    private TextView level;
    //The number of columns and rows.
    public static int num;
    @SuppressLint("StaticFieldLeak")
    private static GameTimer gameTimer;
    private Chronometer chronometer;
    private static boolean isLevel1 = true;
    private SlidingPresenter presenter = SlidingGrid.getPresenter();


    //private TextView level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setNum();
        setContentView(R.layout.activity_sliding);
        textScore = findViewById(R.id.slidingScore);
        level = findViewById(R.id.slidingLevel);
        chronometer = findViewById(R.id.slidingTimer);

        SlidingGrid.getPresenter().setSlidingView(this);
        initViewByLevel();
        setBackButton();
        Button pauseBtn = findViewById(R.id.pause2048);
        pauseBtn.setTag(0);
        setPauseButton(pauseBtn, gameTimer);
        gameTimer.restart();
    }

    @Override
    public void setPauseButton(Button pauseBtn, GameTimer gameTimer) {
       SlidingPresenter presenter = SlidingGrid.getPresenter();
        pauseBtn.setOnClickListener(v -> {
            super.setPauseButton(pauseBtn, gameTimer);
            if (pauseBtn.getText().equals("RESUME")){
                presenter.onPause();
            }
            else{
                presenter.onResume();
            }
        });
    }

    public void initViewByLevel() {

        if (isLevel1) {
            gameTimer = new GameTimer(chronometer);
            System.out.println(gameTimer);
            level.setText("LEVEL1");}

         else {
            gameTimer.setResume(chronometer);
            isLevel1 = false;
            level.setText("LEVEL2");

    }}

    private void setNum() {
        if (isLevel1) {
            num = 3;
        } else {
            num = 4;
        }
    }


    private void backToMain(){
        finish();
        gameTimer = null;
        SlidingGrid.onDestory();
        switchToPage(SlidingMenu.class);
    }

    public static int getNum() {
        return num;
    }

    public static boolean getIsLevel1() {
        return isLevel1;
    }

    public static void changeLevel() {
        isLevel1 = false;
    }

    @Override
    @SuppressLint("SetTextI18n")
    public void updateScore(int s) {
        score = s;
        textScore.setText(score + "");
    }

    public static void setIsLevel1(boolean isLevel1) {
        SlidingActivity.isLevel1 = isLevel1;
    }


    public void goToResult(boolean displayName) {
        finish();
        super.goToResult(SlidingScoreboardActivity.class, displayName);

    }


    public void setBackButton() {
        findViewById(R.id.backtoMain).setOnClickListener(v -> {
            SlidingGrid.getPresenter().onDestory();
            switchToPage(SlidingMenu.class);
        });
    }

    public static void setGameTimer(GameTimer gameTimer) {
        SlidingActivity.gameTimer = gameTimer;
    }


    public void startLevel2() {
        gameTimer.stop();
        finish();
        Intent intent = new Intent(SlidingActivity.this, SlidingActivity.class);
        intent.putExtra("score", score);
        startActivity(intent);
    }
    public void showPrompt() {
        ViewGroup layout = findViewById(R.id.weaponPage);
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

    public static GameTimer getGameTimer(){
        return gameTimer;
    }


    public int getTime(){
        return gameTimer.getTime();
    }


    public String getLevel() {
        return level.getText().toString();
    }
}


