package com.example.gamecenter.login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.gamecenter.R;
import com.example.gamecenter.games.catchballgame.activity.CatchBallMenu;
import com.example.gamecenter.games.math24game.activity.Math24Menu;
import com.example.gamecenter.games.slidinggame.activity.SlidingMenu;
import com.example.gamecenter.setting.SettingsActivity;
import com.example.gamecenter.strategy.BackGroundSetter;
import com.example.gamecenter.strategy.BaseActivity;

public class MainMenuActivity extends BaseActivity {

    private ImageView setting;
    private Handler handler;
    private Activity current;
    private TextView chooseGame;
    // catchballgame button
    private Button catchballGame;
    // catchballgame button
    private Button slidingGame;
    // catchballgame button
    private Button math24Game;

    /**
     * Create items when starting the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        //Set the runnable and handler

        current = this;
        this.handler = new Handler();
        this.runnable.run();

        SharedPreferences preferences = getSharedPreferences("USER", MODE_PRIVATE);
        String display = preferences.getString("MainMenuActivity", "");

        setting = findViewById(R.id.setting_btn_mainMenu);
        catchballGame = findViewById(R.id.btn_catchBall);
        slidingGame = findViewById(R.id.btn_slidingGame);
        math24Game = findViewById(R.id.btn_math24);

        setting.setOnClickListener(v -> {
            switchToPage(SettingsActivity.class);
        });

        catchballGame.setOnClickListener(v -> {
            switchToPage(CatchBallMenu.class);
        });

        slidingGame.setOnClickListener(v -> {
            switchToPage(SlidingMenu.class);
        });

        math24Game.setOnClickListener(v -> {
            switchToPage(Math24Menu.class);
        });
    }

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            ConstraintLayout layout = findViewById(R.id.mainmenu);
            BackGroundSetter.setWallPaper(new TextView[]{findViewById(R.id.chooseGame1)}, current, layout);
            handler.postDelayed(runnable, 50);
        }
    };
}
