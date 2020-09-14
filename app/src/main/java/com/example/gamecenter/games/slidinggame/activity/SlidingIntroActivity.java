package com.example.gamecenter.games.slidinggame.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.example.gamecenter.R;

import android.content.Intent;
import android.view.View;
import android.widget.Button;


import android.os.Bundle;
import android.widget.TextView;

import static android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;

/**
 * The SlidingIntroActivity class.
 */
public class SlidingIntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_intro);

    }

}
