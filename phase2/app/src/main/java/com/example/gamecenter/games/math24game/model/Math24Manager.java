package com.example.gamecenter.games.math24game.model;

import com.example.gamecenter.gameinterface.GameManager;
import com.example.gamecenter.games.math24game.strategy.Calculator;
import com.example.gamecenter.scoreboard.Scoreboard;

public class Math24Manager implements GameManager {
    //create int variable score
    private int score;
    //create boolean gameOver to tell whether the game is over or not
    private boolean gameOver;
    //create QuestionBank type variable questionBank
    private QuestionBank questionBank;
    //create boolean correctAnswer to tell whether the player get 24 by their equation
    private boolean correctAnswer;
    //create int variable lives to count lives available
    private int lives;

    //constructor of Math24Manager
    public Math24Manager(){
        this.score = 0;
        gameOver =false;
        lives = 3;
        questionBank = new QuestionBank();
    }

    /**
     * @return the random question selected from the corresponding question bank
     */
    public int[] getQuestion(){
        return questionBank.getRandomQ(checkNextLevel());

    }

    //test if the result equals 24
    public boolean isGameOver(){
        return score >= 450 || lives <= 0 || checkNextLevel() && !isCorrectAnswer();
    }

    /**
     * @return return true or false according to the boolean variable correctAnswer
     */
    public boolean isCorrectAnswer(){
        return correctAnswer;
    }


    /**
     * @return return true if the score is equal or larger than 150
     */
    @Override
    public boolean checkNextLevel() {
        return score >= 150;
    }


    /**
     * @return return the current number of lives
     */
    public int getLives() {
        return lives;
    }

    //determine if the player's answer can make it to 24
    public int calculate(String equation){
        int result = (int)Math.round(Calculator.getResult(equation));
        if (result == 24) {
            correctAnswer = true;
            addScore();

        }
        else{
            lives -= 1;
            correctAnswer = false;

        }
        return result;
    }


    private void addScore(){
        if (score< 150) {
            score += 50;
        } else {
            score += 100;
        }
    }

    public int getScore() {
        return score;
    }

    /**
     * @param scoreboard the scoreboard of the game
     * @param user the user of the game
     */

    public void checkToAddScore(Scoreboard scoreboard, String user, int time, String level) {
        if(isGameOver())
        {
            scoreboard.addScore(user,score,time,level);
        }
    }


}

