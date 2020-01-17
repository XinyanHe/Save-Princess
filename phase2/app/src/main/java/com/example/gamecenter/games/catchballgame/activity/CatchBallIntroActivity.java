package com.example.gamecenter.games.catchballgame.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.gamecenter.R;
import com.example.gamecenter.strategy.BackGroundSetter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.Button;


import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Activity class of the first game's introduction.
 * */
public class CatchBallIntroActivity extends AppCompatActivity {

    //Initialize Class
    private Handler handler = new Handler();
    private Activity current;
  /**
   * Create items when starting this activity.
   *
   * @param savedInstanceState */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_ball_intro);


      current = this;
      this.handler = new Handler();
      this.runnable.run();
    }

  /**
   * A new interface Runnable.
   * */
  private final Runnable runnable =
      new Runnable() {
        /**
         * Use Runnable to start a thread.
         * */
        @Override
        public void run() {
          ConstraintLayout layout = findViewById(R.id.catchballintroPage);
          BackGroundSetter.setWallPaper(
              new TextView[] {findViewById(R.id.points1), findViewById(R.id.points2),
              findViewById(R.id.points3),findViewById(R.id.points4),findViewById(R.id.intro1),
              findViewById(R.id.intro2),findViewById(R.id.intro3),findViewById(R.id.title)}, current, layout);
          handler.postDelayed(runnable, 50);
        }
      };
}