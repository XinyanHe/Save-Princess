package com.example.gamecenter.games.slidinggame.model;

import com.example.gamecenter.gameinterface.GameManager;
import com.example.gamecenter.scoreboard.Scoreboard;

/*BASED ON: https://blog.csdn.net/ft_sunshine/article/details/52678815
ALL CREDIT FOR THE ORIGINAL IMPLEMENTATION OF A SIMILAR SINGLETON GOES TO THE ORIGINAL AUTHOR OF
    THE CODE.*/

/**
 * SlidingManager class contains all the game logic of the Sliding game.
 */
public class SlidingManager implements GameManager {

    /** slidingCards is a 2D array of SlidingCards.*/
    private SlidingCard[][] slidingCards;
    /** cardCollection is a CardCollection*/
    private CardCollection cardCollection;
    /** score is the Player's score.*/
    private static int score;
    /** gameOver indicates whether the game is over or not.*/
    private boolean gameOver;
    /** num is the column and row number of map in the Sliding game.*/
    public static int num;

    /**
     * Create a SlidingManager
     * @param num num of columns and rows
     * @param isLevel1 true if is level1, false otherwise
     */
    public SlidingManager(int num, boolean isLevel1) {
        SlidingManager.num = num;
        gameOver = false;
        cardCollection = new CardCollection(num);
        slidingCards = cardCollection.getCards();
        if (isLevel1) {
            score = 0;
        }
    }

    /**
     * Get slidingCards.
     * @return slidingCards.
     */
    public SlidingCard[][] getSlidingCards() {
        return slidingCards;
    }

    /**
     * Set the player's score.
     * @param score the current score.
     */
    public void setScore(int score) {
        SlidingManager.score = score;
    }

    /**
     * Get the player's score.
     * @return score.
     */
    public int getScore() {
        return score;
    }

    /**
     * The checkAllPair method checks if the 2D array slidingCards has cards pairs that can still
     * be merged or any empty spots that allows the game to continue. If so, game is not over.
     * Otherwise, gameOver is true.
     */
    private void checkAllPair() {
        gameOver = true;
        ALL:
        for (int y = 0; y < num; y++) {
            for (int x = 0; x < num; x++) {
                if (slidingCards[x][y].getNum() == 0 ||
                        (x > 0 && slidingCards[x][y].equals(slidingCards[x - 1][y])) ||
                        (x < (num - 1) && slidingCards[x][y].equals(slidingCards[x + 1][y])) ||
                        (y > 0 && slidingCards[x][y].equals(slidingCards[x][y - 1])) ||
                        (y < (num - 1) && slidingCards[x][y].equals(slidingCards[x][y + 1]))) {
                    gameOver = false;
                    break ALL;

                }

            }
        }

    }

    /**
     * The swipeLeft method moves all the cards to the left most spots and merge all the pairs that
     * can be merged when swiping left. After swiping, if gameOver is false, add a new card on the
     * map using addRandomNum method.
     */
    public void swipeLeft() {
        boolean merge = false;
        for (int y = 0; y < num; y++) {
            for (int x = 0; x < num; x++) {
                for (int x1 = x + 1; x1 < num; x1++) {
                    if (slidingCards[x1][y].getNum() > 0) {

                        if (slidingCards[x][y].getNum() <= 0) {
                            slidingCards[x][y].setNum(slidingCards[x1][y].getNum());
                            slidingCards[x1][y].setNum(0);

                            x--;
                            merge = true;
                        } else if (slidingCards[x][y].equals(slidingCards[x1][y])) {
                            slidingCards[x][y].setNum(slidingCards[x][y].getNum() + 1);
                            slidingCards[x1][y].setNum(0);

                            score += (Math.pow(2, slidingCards[x][y].getNum()));
                            merge = true;
                        }

                        break;
                    }
                }
            }
        }
        if (merge) {
            cardCollection.addRandomNum();
            checkAllPair();
        }
    }

    /**
     * The swipeRight method moves all the cards to the right most spots and merge all the pairs that
     * can be merged when swiping right. After swiping, if gameOver is false, add a new card on the
     * map using addRandomNum method.
     */
    public void swipeRight() {
        boolean merge = false;
        for (int y = 0; y < num; y++) {
            for (int x = (num - 1); x >= 0; x--) {

                for (int x1 = x - 1; x1 >= 0; x1--) {
                    if (slidingCards[x1][y].getNum() > 0) {

                        if (slidingCards[x][y].getNum() <= 0) {
                            slidingCards[x][y].setNum(slidingCards[x1][y].getNum());
                            slidingCards[x1][y].setNum(0);

                            x++;
                            merge = true;
                        } else if (slidingCards[x][y].equals(slidingCards[x1][y])) {
                            slidingCards[x][y].setNum(slidingCards[x][y].getNum() + 1);
                            slidingCards[x1][y].setNum(0);
                            score += (Math.pow(2, slidingCards[x][y].getNum()));
                            merge = true;
                        }

                        break;
                    }
                }
            }
        }
        if (merge) {
            cardCollection.addRandomNum();
            checkAllPair();

        }
    }

    /**
     * The swipeUp method moves all the cards to the up most spots and merge all the pairs that
     * can be merged when swiping up. After swiping, if gameOver is false, add a new card on the
     * map using addRandomNum method.
     */
    public void swipeUp() {
        boolean merge = false;
        for (int x = 0; x < num; x++) {
            for (int y = 0; y < num; y++) {

                for (int y1 = y + 1; y1 < num; y1++) {
                    if (slidingCards[x][y1].getNum() > 0) {

                        if (slidingCards[x][y].getNum() <= 0) {
                            slidingCards[x][y].setNum(slidingCards[x][y1].getNum());
                            slidingCards[x][y1].setNum(0);

                            y--;
                            merge = true;

                        } else if (slidingCards[x][y].equals(slidingCards[x][y1])) {
                            slidingCards[x][y].setNum(slidingCards[x][y].getNum() + 1);
                            slidingCards[x][y1].setNum(0);
                            score += (Math.pow(2, slidingCards[x][y].getNum()));
                            merge = true;

                        }

                        break;
                    }
                }
            }
        }
        if (merge) {
            cardCollection.addRandomNum();
            checkAllPair();

        }
    }

    /**
     * The swipeDown method moves all the cards to the down most spots and merge all the pairs that
     * can be merged when swiping down. After swiping, if gameOver is false, add a new card on the
     * map using addRandomNum method.
     */
    public void swipeDown() {
        boolean merge = false;
        for (int x = 0; x < num; x++) {
            for (int y = (num - 1); y >= 0; y--) {

                for (int y1 = y - 1; y1 >= 0; y1--) {
                    if (slidingCards[x][y1].getNum() > 0) {

                        if (slidingCards[x][y].getNum() <= 0) {
                            slidingCards[x][y].setNum(slidingCards[x][y1].getNum());
                            slidingCards[x][y1].setNum(0);

                            y++;
                            merge = true;

                        } else if (slidingCards[x][y].equals(slidingCards[x][y1])) {
                            slidingCards[x][y].setNum(slidingCards[x][y].getNum() + 1);
                            slidingCards[x][y1].setNum(0);

                            score += (Math.pow(2, slidingCards[x][y].getNum()));
                            merge = true;

                        }

                        break;
                    }
                }
            }
        }
        if (merge) {
            cardCollection.addRandomNum();
            checkAllPair();

        }
    }

    /**
     * Get gameOver.
     * @return gameOver.
     */
    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * The checkNextLevel checks if the player can enter level2.
     * @return true if the player's score is greater than 50, false otherwise.
     */
    @Override
    public boolean checkNextLevel() {
        return getScore() >= 50;

    }

    /**
     * The setCardCollection method uses the setCardCollection and addRandomNum method to set the
     * 2D array with 2 elements with pics and others without any pic.
     */
    public void setCardCollection() {
        cardCollection.setCardCollection();
        cardCollection.addRandomNum();
        cardCollection.addRandomNum();
    }


    /**
     * @param scoreboard a scoreboard
     * @param user the current player
     * @return true if Game is over, false otherwise.
     */
    public boolean checkToAddScore(Scoreboard scoreboard, String user, int time, String level) {
        if(isGameOver()) {
            scoreboard.addScore(user,this.getScore(),time,level);
            return true;
        }
        return false;
    }

    }
