package com.example.gamecenter.games.catchballgame.model;

import android.widget.ImageView;

/**
 * A PinkBall.
 */
public class PinkBall extends Ball {
    /**
     * The point of a PinkBall.
     */
    private int point;

    /**
     * Create a PinkBall
     * @param x coordinate
     * @param y coordinate
     * @param view the view for a PinkBall
     * @param speed the speed for a PinkBall
     */
    PinkBall(int x, int y, ImageView view, int speed){
        super(x,y, view, speed);
        point = 30;
    }

    /**
     * A getter for the point of PinkBall
     * @return point
     */
    @Override
    public int getPoint() {
        return point;
    }


    /**
     * The move method for the PinkBall
     * @param screenWidth the screenwidth
     * @param  frameHeight the frameHeight of the screen
     * @param width the width of the item
     */
    @Override
    public void move(int screenWidth, int frameHeight, int width) {
        super.move(screenWidth,frameHeight, 20);
    }


}