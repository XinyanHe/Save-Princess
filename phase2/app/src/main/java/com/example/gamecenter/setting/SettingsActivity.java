package com.example.gamecenter.setting;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.example.gamecenter.strategy.BackGroundSetter;

/**
 * The Setting Activity that manages all the customization.
 */
public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    /** Create mediaPlayer variable with type MediaPlayer */
    private MediaPlayer mediaPlayer;
    /** Create buttons: back, restart, play, pause, day, and night */
    private Switch setBackground;

    /**
     * Create items when starting the activity
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.gamecenter.R.layout.activity_settings);

        // create a mediaplayer variable
        mediaPlayer = MediaPlayer.create(this, com.example.gamecenter.R.raw.alone);

        //recognize the bottoms and assign them to the corresponding variables
        Button[] buttons = new Button[]{findViewById(com.example.gamecenter.R.id.btn_back1),
                findViewById(com.example.gamecenter.R.id.btn_pause),
                findViewById(com.example.gamecenter.R.id.btn_play)};

        //create a background switch
        setBackground = findViewById(com.example.gamecenter.R.id.Setbackground);
        setBackground.setOnClickListener(this);

        updateSwitch();

        for (Button bb : buttons) {
            bb.setOnClickListener(this);
        }

    }

    /**
     * Have different respond when click different button
     * @param v
     */

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //If we press back button, we will finish
            case com.example.gamecenter.R.id.btn_back1:
                finish();
                break;
            //if we press pause, the music will stop playing
            case com.example.gamecenter.R.id.btn_pause:
                mediaPlayer.pause();
                break;
            //if we press restart, the music will restart playing
            case com.example.gamecenter.R.id.btn_play:
                mediaPlayer.start();
                break;
            case com.example.gamecenter.R.id.Setbackground:
                //Enable set background button
                setBackGround();
            default:
                break;
        }
    }

    /**
     * Update the switch
     */
    private void updateSwitch(){
        if (BackGroundSetter.isSwitchStatus()){
            setBackground.setChecked(true);

        }
        else{
            setBackground.setChecked(false);
        }
        setBackGround();

    }

    /**
     * Enable the Background button and set different text color and background.
     */
    private void setBackGround(){
        //Enable set background button
        boolean on = (setBackground).isChecked();
        TextView[] texts = new TextView[]{findViewById(com.example.gamecenter.R.id.character_text),
                findViewById(com.example.gamecenter.R.id.background_text), findViewById(com.example.gamecenter.R.id.points3), findViewById(com.example.gamecenter.R.id.settings)};

        ConstraintLayout layout = findViewById(com.example.gamecenter.R.id.setting);

        if (on) {
            layout.setBackground(ContextCompat.getDrawable(this, com.example.gamecenter.R.drawable.deernight));
            BackGroundSetter.setSwitchStatus(true );

            for (TextView k : texts) {
                k.setTextColor(Color.parseColor("#FFFFFF"));}
        } else {
            layout.setBackground(ContextCompat.getDrawable(this, com.example.gamecenter.R.drawable.whitebackground));
            BackGroundSetter.setSwitchStatus(false );
            for (TextView t : texts) {
                t.setTextColor(Color.parseColor("#FF777070"));
            }

        }

    }
}
