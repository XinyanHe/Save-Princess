package com.example.gamecenter.games.catchballgame.model;

import android.widget.ImageView;

/**
 * An OrangeBall
 */
public class OrangeBall extends Ball {
    /** point of the OrangeBall*/
    private int point;

    /**
     * Create an OrangeBall
     * @param x coordinate
     * @param y coordinate
     * @param view the view of the OrangeBall
     * @param speed the speed of the OrangeBall
     */
    OrangeBall(int x, int y, ImageView view, int speed){
        super(x,y, view, speed);
        point = 10;


    }

    /**
     * A getter for the point of the OrangeBall.
     * @return point
     */
    @Override
    public int getPoint() {
        return point;
    }


    /**
     * The move method for the OrangeBall.
     * @param screenWidth the screenwidth
     * @param  frameHeight the frameHeight of the screen
     * @param width the width of the item
     */
    @Override
    public void move(int screenWidth, int frameHeight, int width) {
        super.move(screenWidth,frameHeight, 20);
    }


}
