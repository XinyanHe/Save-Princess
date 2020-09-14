package com.example.gamecenter.games.math24game.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.example.gamecenter.R;

import android.view.View;
import android.widget.Button;


import android.os.Bundle;
import android.widget.TextView;

public class Math24IntroActivity extends AppCompatActivity implements View.OnClickListener {
    private Button back;
    private TextView introTitle, introBody;

    /** onCreate method for Math24 Introduction page
     * @param savedInstanceState
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math24_intro);

        introTitle = findViewById(R.id.math24intro);
        introBody = findViewById(R.id.introBody);
        back = findViewById(R.id.btn_back2);
        back.setOnClickListener(this);
    }


    /** Set the back button
     * @param view
     *
     */
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back2:
                finish();
                break;
        }
    }
}