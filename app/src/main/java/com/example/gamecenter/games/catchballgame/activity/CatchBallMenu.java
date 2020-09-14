package com.example.gamecenter.games.catchballgame.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gamecenter.gameinterface.GameMenu;
import com.example.gamecenter.R;
import com.example.gamecenter.games.catchballgame.presenter.CatchBallPresenter;
import com.example.gamecenter.games.math24game.activity.Math24ScoreboardActivity;
import com.example.gamecenter.login.MainMenuActivity;
import com.example.gamecenter.scoreboard.Scoreboard;
import com.example.gamecenter.scoreboard.ScoreboardFileSaver;
import com.example.gamecenter.strategy.BackGroundSetter;
import com.example.gamecenter.strategy.BaseActivity;
import com.example.gamecenter.user.User;
import com.example.gamecenter.user.UserManager;

public class CatchBallMenu extends BaseActivity implements GameMenu {
    //CatchBallMenu has four buttons: New Game, Scoreboard, Introduction and Quit.

    /**
     * The Scoreboard
     */
    public static Scoreboard scoreboard;

    /**
     * The CatchBallPresenter
     */
    public static CatchBallPresenter controller;

    /**
     * The file of CatchBallScores.
     */
    private static final String fileName = "CatchBallScores.ser";

    private Handler handler;
    private Activity current;



    private User currentPlayer = UserManager.getCurrentUser();


    /**
     * Create items when starting this activity.
     *
     * @param savedInstanceState */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        scoreboard = new Scoreboard();
        ScoreboardFileSaver scoreboardFileSaver = new ScoreboardFileSaver(this, fileName);
        scoreboard.register(scoreboardFileSaver);
        scoreboard.setGlobalScore(scoreboardFileSaver.getGlobalScores());


        setContentView(R.layout.activity_catchballmenu);

        current = this;
        setButtons();
         this.handler = new Handler();
         this.runnable.run();


    }

    /**
     * A new interface Runnable.
     * */
    private final Runnable runnable = new Runnable() {
        /**
         * Use Runnable to start a thread.
         * */
        @Override
        public void run() {
            RelativeLayout layout = findViewById(R.id.catchBallmenu);
            BackGroundSetter.setWallPaper(new TextView[]{findViewById(R.id.catchBall_text)},current,layout);
            handler.postDelayed(runnable, 50);
        }
    };

  /**
   * Set the buttons.
   * */
  private void setButtons() {
        setQuitBtn();
        setIntroBtn();
        setNewGameBtn();
        setScoreboardBtn();
        onClickSettingBtn(findViewById(R.id.setting_btn_ball));
    }

    /**
     * Activate the quit button.
     */
    @Override
    public void setQuitBtn() {
        findViewById(R.id.quitBallBtn).setOnClickListener(v-> switchToPage(MainMenuActivity.class));
    }

  /**
   * Set scoreboard button.
   * */
  @Override
  public void setScoreboardBtn() {
        findViewById(R.id.ballScoreBoardbtn).setOnClickListener(v-> {

            Intent i = new Intent(this, CatchBallScoreboardActivity.class);
            i.putExtra("saveChoice", true);
            startActivity(i);
        });
    }

  /**
   * Set new game button.
   * */
  @Override
  public void setNewGameBtn() {
        findViewById(R.id.newballGame).setOnClickListener(v -> switchToPage(CatchBallActivity.class));

    }

  /**
   *  Set help button.
   * */
  @Override
  public void setIntroBtn() {
        findViewById(R.id.help_ball).setOnClickListener(v -> switchToPage(CatchBallIntroActivity.class));

    }
}
