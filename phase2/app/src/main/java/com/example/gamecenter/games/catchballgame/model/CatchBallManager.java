package com.example.gamecenter.games.catchballgame.model;


import com.example.gamecenter.gameinterface.GameManager;
import com.example.gamecenter.scoreboard.Scoreboard;

import java.io.Serializable;

public class CatchBallManager implements GameManager, Serializable {

    private CatchBoard board;
    private PlayerPrince player;
    private boolean gameOver;
    private int score;


    public CatchBallManager(CatchBoard catchBoard) {
        board = catchBoard;
        player = board.getPlayerPrince();
        gameOver = false;

    }


    public void changePos(boolean actionFlag) {
        //Call hitcheck() before changePos
        int frameHeight = board.getFrameHeight();
        for (Ball ball : board.getBalls()) {
            ball.move(board.getScreenWidth(), frameHeight, 0);
        }
        player.move(actionFlag, frameHeight);

    }

    public void setBoardHeight(int frameHeight){
        board.setFrameHeight(frameHeight);

    }


     public void hitCheck() {
        //if the center of the Ball is in the box,it counts as a hit
         for (Ball ball : board.getBalls()) {
            if (validate(ball.getCenterX(), ball.getCenterY(), player.getY(), player.getSize())) {
                if(ball instanceof BlackBall){
                    gameOver = true;
                    break;
                }
                else{
                    score += ball.getPoint();
                ball.setX(-10);
                }
            }
        }

    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public boolean isGameOver(){
        return gameOver;
    }


    public void updatePlayerSize(){
        player.setY((int)player.getView().getY());
        player.setSize(player.getView().getHeight());
    }


    private boolean validate(int X, int Y, int itemY, int itemSize) {

        return 0 <= X && X <= itemSize && itemY <= Y && Y <= itemY + itemSize;

    }

    public boolean checkNextLevel() {
        if (score >= 50) {
            board.setBaseSpeed(14);
            return true;

        }
        return false;

    }

    /**
     * @param scoreboard the scoreboard of the game
     * @param user the user of the game
     */
    public void checkToAddScore(Scoreboard scoreboard, String user, int time, String level) {
        if(isGameOver())
        {
            scoreboard.addScore(user,this.getScore(),time,level);
        }
    }



}
