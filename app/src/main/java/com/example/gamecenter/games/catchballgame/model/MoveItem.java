package com.example.gamecenter.games.catchballgame.model;

import android.widget.ImageView;

interface MoveItem {

    /**
     * A getter for the x coordinate of the move item
     */
    int getX();
    /**
     * A getter for the y coordinate of the move item
     */
    int getY();
    /**
     * A setter for the x coordinate of the move item
     * @param x the x coordinate of the move item
     */
    void setX(int x);
    /**
     * A setter for the y coordinate of the move item
     * @param y the y coordinate of the move item
     */
    void setY(int y);
    /**
     * A getter for the move item's view
     */
    ImageView getView();

    int getPoint();
    /**
     * Move the item based on screenWidth, frameHeight, width and the change in x
     * @param screenWidth the screenwidth
     * @param  frameHeight the frameHeight of the screen
     * @param width the width of the item
     */
    void move(int screenWidth, int frameHeight, int width);

    void setSpeed(int speed);

    int getSpeed();

}