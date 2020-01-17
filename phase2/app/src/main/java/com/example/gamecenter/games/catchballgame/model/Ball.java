package com.example.gamecenter.games.catchballgame.model;

import android.widget.ImageView;

/**
 * The Ball class is the parent class of all balls and contains all the move methods.
 */
class Ball implements MoveItem {

    //view of the ball
    private ImageView view;
    //x coordinate of the ball
    private int x;
    //y coordinate of the ball
    private int y;
    //the points(score) of the ball
    private int point;
    //speed of the ball
    private int speed;

    //ball information including its x and y coordinates, view and speed
    Ball(int x, int y, ImageView view, int speed){
        this.view = view;
        view.setX(x);
        view.setY(y);
        point = 0;
        this.speed = speed;

     }

    /**
     * A getter for the point of the ball
     */
    @Override
    public int getPoint(){
        return point;
    }

    /**
     * Set the ImageView for the ball.
     * @param view
     */
    public void setView(ImageView view) {
        this.view = view;
    }

    /**
     * A setter for the x coordinate of the ball
     */
    @Override
    public void setX(int x) {
        this.x = x;
    }

    /**
     * A setter for the y coordinate of the ball
     */
    @Override
    public void setY(int y) {
        this.y = y;
    }

    /**
     * A getter for the x coordinate of the ball
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * A getter for the y coordinate of the ball
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * Set the speed for the ball.
     * @param speed ball's speed.
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * A getter for speed.
     * @return speed.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * A getter for the ball's view
     */
    //cannot change appearance
    public ImageView getView() {
        return view;
    }

    /*
    return y coordinate of the centre of the ball
    */
    int getCenterY() {
        return y + view.getHeight()/2;
    }

    /**
    *return X coordinate of the centre of the ball
    */
    int getCenterX() {
        return x + view.getWidth()/2;
    }

    /**
     * The move method.
     * @param screenWidth the screenwidth
     * @param  frameHeight the frameHeight of the screen
     * @param width the width of the item
     */
    @Override
    public void move(int screenWidth, int frameHeight, int width){

        x -= speed;
        if(x < 0){
            x = screenWidth + width;
            y = (int)Math.floor(Math.random()*(frameHeight-view.getHeight()));
        }
        view.setX(x);
        view.setY(y);
    }



}
