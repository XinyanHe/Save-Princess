package com.example.gamecenter.login;

import android.accounts.AccountsException;
import android.accounts.AuthenticatorException;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.gamecenter.R;
import com.example.gamecenter.strategy.BackGroundSetter;
import com.example.gamecenter.user.User;
import com.example.gamecenter.user.UserManager;

public class LoginActivity extends AppCompatActivity{


    /**
     * A UserManager.
     */
    private UserManager userManager;

    /**
     * Create items when starting the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userManager = MainEntryActivity.userManager;
        setupSignInListener();

        current = this;

        this.mHandler = new Handler();
        this.mRunnable.run();

        /**
         * Determine whether it is runnalbe
         */

    }
    private final Runnable mRunnable = new Runnable()
    {
        public void run()
        {   ConstraintLayout layout = findViewById(R.id.loginPage);
            BackGroundSetter.setWallPaper(new TextView[0], current, layout);
            LoginActivity.this.mHandler.postDelayed(mRunnable, 50);
        }

    };//runnable

    private Handler mHandler;
    private Activity current;


    /**
     *  Activate the signIn button.
     */
    @SuppressLint("SetTextI18n")
    public void setupSignInListener() {
        Button signIn = findViewById(R.id.btnLogin);
        signIn.setOnClickListener((v) -> {
            String userName = ((EditText)findViewById(R.id.etName)).getText().toString();
            String passWord = ((EditText)findViewById(R.id.etPassword)).getText().toString();
            TextView textBox = findViewById(R.id.etTextBox);
            try {
                userManager.signIn(userName, passWord);
            }
            catch (AuthenticatorException e){
                textBox.setText("Incorrect Password.");
                return;
            }
            catch (AccountsException e) {
                textBox.setText("Username is not found.");
                return;
            }
            textBox.setText("Sign In Successfully!");
            Intent tmp = new Intent(this, MainMenuActivity.class);
            startActivity(tmp);
        });
    }

}
