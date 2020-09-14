package com.example.gamecenter.games.math24game;

import android.widget.Button;

import com.example.gamecenter.gameinterface.GameController;
import com.example.gamecenter.gameinterface.MyObserver;
import com.example.gamecenter.gameinterface.MySubject;
import com.example.gamecenter.games.math24game.activity.Math24Activity;
import com.example.gamecenter.games.math24game.model.Math24Manager;

import java.util.ArrayList;
import java.util.List;

public class Math24Presenter implements GameController, MySubject {

    private Math24Manager mathManager;
    private Math24Activity mathView;
    private static List<MyObserver> observers;


    public Math24Presenter(Math24Manager mathManager, Math24Activity mathView) {
        this.mathManager = mathManager;
        this.mathView = mathView;
        observers = new ArrayList<>();
    }

    public void onStart() {
        mathView.setMessage("");
        mathView.getNextBtn().setEnabled(false);
        int[] questions = mathManager.getQuestion();
        Button[] nums = mathView.getNums();
        for (int i = 0; i < 4; i++) {
            mathView.setNumText(nums[i],questions[i]);
        }
        if (mathManager.checkNextLevel()){
            mathView.setLevel("Level2");
            mathView.setLives(1);
        }
    }


    /**
     * @param mathExpression
     * show result of the equation after call this method
     * check whether it's correct.
     */
    public void calculateResult(String mathExpression){
        int result = mathManager.calculate(mathExpression);
        mathView.showResult(result);
        checkCurrentResult();

    }

    private void checkCurrentResult() {
        //if the result is correct, the message "congratulations! click Next to proceed will appear."
        // the "next" will be enabled for the player to get to next question
        // score will get updated
        if(mathManager.isCorrectAnswer()){
            mathView.setMessage("Congratulations!\n Click Next to proceed");
            mathView.getNextBtn().setEnabled(true);
            mathView.updateScore(mathManager.getScore());
        }
        else{
            //if the result if wrong, the message "It's Wrong" will appear
            // lives get updated
            // if the game is over after getting the current question wrong, a page will pop up
            //asking whether the player want to save the score
            mathView.setMessage("It's Wrong!!!");
            mathView.setLives(mathManager.getLives());
            if(mathManager.isGameOver()) {
                mathView.showFailure();
                mathView.showPrompt();
            }
        }
        //disable some buttons
        mathView.disableAll();
        mathView.getClear().setEnabled(true);
    }

    public void onDestroy() {
        mathView = null;
    }


    @Override
    public Math24Manager getGameManager() {
        return this.mathManager;
    }


    /**
     * Register the MyObserver object to observe
     *
     * @param obj to register
     */
    @Override
    public void register(MyObserver obj) {
        if(!observers.contains(obj))
        {observers.add(obj);
            obj.setSubject(this);}
    }

    /**
     * A method to notifyObservers to change
     */
    @Override
    public void notifyObservers() {
        for (MyObserver obj:observers){
            obj.update();
        }
    }
}

