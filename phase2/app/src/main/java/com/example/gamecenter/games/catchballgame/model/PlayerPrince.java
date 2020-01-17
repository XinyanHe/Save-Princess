package com.example.gamecenter.games.catchballgame.model;

import android.widget.ImageView;

/**
 * The PlayerPrince
 */
class PlayerPrince {

    /**
     * The imageview of the prince
     */
    private ImageView view;
    /**
     * The size of the price image
     */
    private int size;
    /**
     * The y coordinate of the prince
     */
    private int y;

    /**
     * Create a Player Prince with the size
     * @param view The imageview of the prince
     */
    PlayerPrince(ImageView view) {
        this.view = view;
        this.size = view.getHeight();
    }

    /**
     * A setter for the y coordinate of the player
     */
    void setY(int y) {
        this.y = y;
    }


    /**
     * A getter for the y coordinate of the player
     */

    int getY() {
        return y;
    }

    /**
     * A setter for the player's view
     */
    void setView(ImageView appearance) {
        this.view = appearance;
    }

    /**
     * A getter for the player's view
     */
    ImageView getView() {
        return view;
    }

    void setSize(int size) {
        this.size = size;
    }

    /**
     * A getter for the player's view
     */
    int getSize() {
        return size;
    }

    /**
     * move the prince according to the action
     * @param action the action of the prince
     * @param frameHeight the Height of the frame
     */
    void move(boolean action, int frameHeight) {
        if (action) {
            //Touching
            y -= 20;
        } else {
            //Releasing
            y += 20;
        }

        //check box position
        if (y < 0)
            y = 0;

        if (y > frameHeight - size)
            y = frameHeight - size;

        view.setY(y);

    }


}
