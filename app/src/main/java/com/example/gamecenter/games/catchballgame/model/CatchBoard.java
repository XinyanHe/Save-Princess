package com.example.gamecenter.games.catchballgame.model;

import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.Observable;

/**
 * A CatchBoard is the Board for the CatchBall Game.
 */
public class CatchBoard extends Observable implements Serializable {
    /** screenWidth is the screenWidth.*/
    private int screenWidth;
    /** frameHeight is the frame height.*/
    private int frameHeight;
    /** orange is an OrangeBall.*/
    private OrangeBall orange;
    /** black is a BlackBall.*/
    private BlackBall black;
    /** pink is a PinkBall.*/
    private Ball pink;
    /** balls is a 2D array of Balls.*/
    private Ball[] balls;
    /** playerPrince is a PlayerPrince.*/
    private PlayerPrince playerPrince;

    /**
     * Create a CatchBoard
     * @param window windowManager
     * @param x coefficient for position change
     * @param y coefficient for position change
     * @param baseSpeed base speed for the ball
     * @param views views of the ball
     */
    public CatchBoard(WindowManager window, int x, int y, int baseSpeed, ImageView[] views){

        Display display = window.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        screenWidth = size.x;
        playerPrince = new PlayerPrince(views[3]);
        orange = new OrangeBall(x,y,views[0],baseSpeed);
        pink = new PinkBall(x,y,views[2],baseSpeed+8);
        black = new BlackBall(x,y,views[1],baseSpeed+4);

        balls = new Ball[] {orange,black,pink};

    }

    /**
     * A setter for the baseSpeed.
     * @param baseSpeed the baseSpeed
     */
     void setBaseSpeed(int baseSpeed){
         for (int i = 0; i < 3; i ++){
             balls[i].setSpeed(baseSpeed + i * 4);
         }
     }


    /**
     * A getter for the balls.
     * @return the balls
     */
     Ball[] getBalls(){
        return balls;

    }

    /**
     * A getter for the screeWidth.
     * @return screenWidth.
     */
    int getScreenWidth() {
        return screenWidth;
    }

    /**
     * A setter for the frameHeight
     * @param frameHeight the frame Height.
     */
    void setFrameHeight(int frameHeight) {
        this.frameHeight = frameHeight;
    }

    /**
     * A getter for the frameHeight.
     * @return the frameHeight.
     */
    int getFrameHeight() {
        return frameHeight;
    }

    /**
     * A getter for the playerPrince.
     * @return playerPrince.
     */
    PlayerPrince getPlayerPrince(){
        return playerPrince;
    }

}