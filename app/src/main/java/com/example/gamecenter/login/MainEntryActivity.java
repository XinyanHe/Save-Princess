package com.example.gamecenter.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.gamecenter.R;
import com.example.gamecenter.strategy.BackGroundSetter;
import com.example.gamecenter.user.UserFileSaver;
import com.example.gamecenter.user.UserManager;

public class MainEntryActivity extends AppCompatActivity{
    public static UserManager userManager;

    private Handler mHandler;
    private Activity current;

    /*
     * BASED ON: hhttps://www.youtube.com/watch?v=fI9UTA-NaO4ALL
     * CREDIT FOR THE ORIGINAL IMPLEMENTATION OF A SIMILAR SINGLETON GOES TO THE ORIGINAL AUTHOR OF
    THE CODE.*/

    /**
     * Create items when starting the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrymain);
        setupSignInListener();
        setupSignUpListener();
        userManager = new UserManager();
        UserFileSaver fileSaver = new UserFileSaver(this);
        userManager.register(fileSaver);
        userManager.setAllUsers(fileSaver.getAllUsers());

        //Set the runnable and handler
        current = this;
        this.mHandler = new Handler();
        this.mRunnable.run();


    }

    private final Runnable mRunnable = new Runnable()
    {
        public void run()

        {   ConstraintLayout layout = findViewById(R.id.entryMainPage);
            BackGroundSetter.setWallPaper(new TextView[0], current, layout);
            MainEntryActivity.this.mHandler.postDelayed(mRunnable, 50);
        }

    };//runnable

    /**
     * Activate the signIn button.
     */
    public void setupSignInListener(){
        Button signIn = findViewById(R.id.SignIn);
        signIn.setOnClickListener((v) -> {
            Intent tmp = new Intent(this, LoginActivity.class);
            startActivity(tmp);
        });

    }

    /**
     * Activate the signUp button.
     */
    public void setupSignUpListener(){
        Button signUp = findViewById(R.id.SignUp);
        signUp.setOnClickListener((v) -> {
            Intent tmp = new Intent(this, RegisterActivity.class);
            startActivity(tmp);
        });

    }


}
