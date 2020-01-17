package com.example.gamecenter.games.slidinggame.model;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

/*BASED ON: https://blog.csdn.net/ft_sunshine/article/details/52678815
ALL CREDIT FOR THE ORIGINAL IMPLEMENTATION OF A SIMILAR SINGLETON GOES TO THE ORIGINAL AUTHOR OF
    THE CODE.*/

/**
 * CardCollection class manages the SlidingCards.
 */
class CardCollection {
    /** emptyPoints is a List of empty points.*/
    private List<Point> emptyPoints;
    /** cardCollection is a 2D array containing SlidingCards.*/
    private  SlidingCard[][] cardCollection;
    /** num is a static int indicates the number of rows and columns of the 2D array.*/
    private static int num;

    /**
     * Create a CardCollection
     * @param num the size of the 2D array.
     */
    CardCollection(int num){
        CardCollection.num = num;
        cardCollection = new SlidingCard[num][num];
        emptyPoints = new ArrayList<>();
    }

    /**
     * Get the cardCollection.
     * @return cardCollection.
     */
     SlidingCard[][] getCards() {
        return cardCollection;
    }

    /**
     * Initiate the cardCollection to 0 so that every element in array is 0 (no pic) in
     * the beginning.
     */
    void setCardCollection() {
        for (int y = 0; y < num; y++) {
            for (int x = 0; x < num; x++) {
                cardCollection[x][y].setNum(0);
            }
        }
    }

    /**
     * The addRandomNum method can set one element in 2D array cardCollection to 2 or 4.
     */
    void addRandomNum() {

        emptyPoints.clear();

        for (int y = 0; y < num; y++) {
            for (int x = 0; x < num; x++) {
                if (cardCollection[x][y].getNum() <= 0) {
                    emptyPoints.add(new Point(x, y));
                }
            }
        }

        Point p = emptyPoints.remove((int) (Math.random() * emptyPoints.size()));
        cardCollection[p.x][p.y].setNum(Math.random() > 0.1 ? 1 : 2);
    }
}
