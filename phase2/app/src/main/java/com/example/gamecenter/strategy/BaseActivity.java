package com.example.gamecenter.strategy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gamecenter.setting.SettingsActivity;
import com.example.gamecenter.user.User;

import static android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;

/**
 * The BaseActivity class contains some basic methods can be used by all Game activities.
 */
public class BaseActivity extends AppCompatActivity {

    /**
     * The switchToPage method to switch to another class.
     * @param targetPage the target class.
     */
    public void switchToPage(Class targetPage){
            Intent i = new Intent(this, targetPage);
            i.addFlags(FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(i);

    }

    /**
     * The onClickSettingBtn
     * @param settingBtn a button
     */

    public void onClickSettingBtn(ImageView settingBtn){
        settingBtn.setOnClickListener(v -> {
            //wait to implement loading
            Intent i = new Intent(this, SettingsActivity.class);
            i.addFlags(FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(i);

        });
    }

    /**
     * The goToResult method is used to switch to the scoreboard page and pass in the player's
     * score.
     * @param targetPage gameResult class
     */
    public void goToResult(Class targetPage, boolean displayName){
        Intent intent = new Intent(this, targetPage);
        intent.putExtra("saveChoice",displayName);
        startActivity(intent);
    }


    /**
     * The setPauseButton is used to set the pause button in different game.
     * @param pauseBtn the button used to pause a game
     * @param gameTimer a timer that displays the time
     */
    @SuppressLint("SetTextI18n")
    public void setPauseButton(Button pauseBtn, GameTimer gameTimer){
            if ((int)pauseBtn.getTag()==0){
                pauseBtn.setTag(1);
                gameTimer.stop();
                //Change Button Text;
                pauseBtn.setText("RESUME");
            }
            else{
                pauseBtn.setTag(0);
                gameTimer.restart();
                //Change Button Text;
                pauseBtn.setText("PAUSE");
            }


    }





}
