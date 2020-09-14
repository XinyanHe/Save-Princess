package com.example.gamecenter.games.catchballgame.model;

import android.widget.ImageView;


/**
 * A BlackBall.
 */
class BlackBall extends Ball {
    /** point for the BlackBall*/
    private int point;

    /**
     * Create the BlackBall.
     * @param x coordinate
     * @param y coordinate
     * @param view view of BlackBall
     * @param speed speed of BlackBall
     */
    BlackBall(int x, int y, ImageView view, int speed){
        super(x,y, view, speed);
        point = 0;

    }

    /**
     * A getter for the point of BlackBall.
     * @return
     */
    @Override
    public int getPoint() {
        return point;
    }

    /**
     * Move method of the BlackBall.
     * @param screenWidth the screenwidth
     * @param  frameHeight the frameHeight of the screen
     * @param width the width of the item
     */
    @Override
    public void move(int screenWidth, int frameHeight, int width) {
        super.move(screenWidth,frameHeight, 10);
    }


}

