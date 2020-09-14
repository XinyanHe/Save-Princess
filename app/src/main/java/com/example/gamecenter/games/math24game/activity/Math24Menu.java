package com.example.gamecenter.games.math24game.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gamecenter.games.slidinggame.activity.SlidingScoreboardActivity;
import com.example.gamecenter.scoreboard.ScoreboardFileSaver;
import com.example.gamecenter.strategy.BaseActivity;
import com.example.gamecenter.gameinterface.GameMenu;
import com.example.gamecenter.R;
import com.example.gamecenter.strategy.BackGroundSetter;
import com.example.gamecenter.login.MainMenuActivity;
import com.example.gamecenter.scoreboard.Scoreboard;
import com.example.gamecenter.user.User;
import com.example.gamecenter.user.UserManager;


public class Math24Menu extends BaseActivity implements GameMenu {
    private Handler handler;
    private Activity current;

    /**
     * A ScoreBoard.
     */
    public static Scoreboard scoreboard;

    private static final String fileName = "Math24Scores.ser";

    private User currentPlayer = UserManager.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        scoreboard = new Scoreboard();
        ScoreboardFileSaver scoreboardFileSaver = new ScoreboardFileSaver(this, fileName);
        scoreboard.register(scoreboardFileSaver);
        scoreboard.setGlobalScore(scoreboardFileSaver.getGlobalScores());

        setContentView(R.layout.activity_math24menu);

        current = this;
        setButtons();
        this.handler = new Handler();
        this.runnable.run();
    }


    private final Runnable runnable = new Runnable() {
        /**
         * Set the color of the text that can adapt the different background colour
         */
        @Override
        public void run() {
            RelativeLayout layout = findViewById(R.id.math24Menu);
            BackGroundSetter.setWallPaper(new TextView[]{findViewById(R.id.savePrincess)}, current, layout);
            handler.postDelayed(runnable, 50);
        }
    };

    /**
     * Set buttons quit, intro, new game, and scoreboard
     */
    private void setButtons() {
        setQuitBtn();
        setIntroBtn();
        setNewGameBtn();
        setScoreboardBtn();
        onClickSettingBtn(findViewById(R.id.setting_btn_math));
    }

    /**
     * Activate the scoreboard button.
     */
    @Override
    public void setScoreboardBtn() {
        findViewById(R.id.mathScoreBoardbtn).setOnClickListener(v ->{
            Intent i = new Intent(this, Math24ScoreboardActivity.class);
            i.putExtra("saveChoice", true);
            startActivity(i);

        });
    }

    /**
     * Activate the quit button
     */
    @Override
    public void setQuitBtn() {
        findViewById(R.id.QuitmathButton).setOnClickListener(v -> switchToPage(MainMenuActivity.class));
    }

    /**
     * Activate the new game button
     */
    @Override
    public void setNewGameBtn() {
        findViewById(R.id.newmathGame).setOnClickListener(v -> switchToPage(Math24Activity.class));
    }

    /**
     * Activate the intro button
     */
    @Override
    public void setIntroBtn() {
        findViewById(R.id.intro_mathBtn).setOnClickListener(v -> switchToPage(Math24IntroActivity.class));
    }

    /**
     * Activate Resume button
     */
    @Override
    public void onResume() {
        super.onResume();
    }

}
