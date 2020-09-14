package com.example.gamecenter.games.slidinggame.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import com.example.gamecenter.games.slidinggame.SlidingPresenter;
import com.example.gamecenter.games.slidinggame.model.SlidingCard;
import com.example.gamecenter.games.slidinggame.model.SlidingManager;

/**
 * SlidingGrid is the GridLayout of SlidingActivity.
 */
public class SlidingGrid extends GridLayout{

    /** presenter is the SlidinggPresenter*/
    private static SlidingPresenter presenter;
    /** startX is the x coordinate of player's touching event*/
    private float startX;
    /** startY is the y coordinate of player's touching event*/
    private float startY;
    /** num is the number of columns and rows of the map.*/
    public static int num;

    /**
     * Create a SlidingGrid.
     * @param context Context
     */
    public SlidingGrid(Context context) {
        super(context);
        setNum(SlidingActivity.getNum());
        initGameView();

    }

    /**
     * Create a SlidingGrid.
     * @param context Context
     * @param attrs AttributeSet
     */
    public SlidingGrid(Context context, AttributeSet attrs) {

        super(context, attrs);
        setNum(SlidingActivity.getNum());
        initGameView();
    }

    /**
     * Create a SlidingGrid.
     * @param context Context
     * @param attrs AttributeSet
     * @param defStyleAttr int
     */
    public SlidingGrid(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
        setNum(SlidingActivity.getNum());
        initGameView();

    }

    /**
     * A getter for the SlidingPresenter.
     * @return presenter
     */
    public static SlidingPresenter getPresenter() {
        return presenter;
    }

    /**
     * Initiate the game view. Create a new presenter, set the number of columns,
     * set the Background color, addCards and setOnTouchListener.
     */
    private void initGameView() {
        presenter = new SlidingPresenter(new SlidingManager(SlidingActivity.getNum(),
                SlidingActivity.getIsLevel1()),this);
        setColumnCount(num);
        setBackgroundColor(0xFFFFFFFF);
        addCards(getCardwidth(), getCardwidth(), presenter.getSlidingManager().getSlidingCards());
        setOnTouchListener(this::setOnTouch);
    }

    /**
     * A setter for the num.
     * @param num number of columns.
     */
    public static void setNum(int num){
        SlidingGrid.num = num;
    }

    /**
     * Check the Player's motion event.
     * @param v View
     * @param event MotionEvent
     * @return true if get the action
     */
    private boolean setOnTouch(View v, MotionEvent event){

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                //Determine direction
                float offsetX = event.getX() - startX;
                float offsetY = event.getY() - startY;
                if (Math.abs(offsetX) > Math.abs(offsetY)) {
                    if (offsetX < -5) {
                        //Left
                        presenter.swipe(false, true);
                    } else if (offsetX > 5) {
                        presenter.swipe(false, false);

                    }
                } else {
                    if (offsetY < -5) {
                        presenter.swipe(true, true);
                    } else if (offsetY > 5) {
                        presenter.swipe(true, false);
                    }
                }
                break;
        }
        return true;
    }

    /**
     * onPause.
     */
    @SuppressLint("ClickableViewAccessibility")
    public void onPause(){
        this.setOnTouchListener((view, motionEvent) -> true);
    }

    /**
     * onResume.
     */
    public void onResume(){
        setOnTouchListener(this::setOnTouch);
    }


    /**
     * Restart the presenter when size changed.
     * @param w width
     * @param h height
     * @param oldw old width
     * @param oldh old height
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        presenter.restart();

    }

    /**
     * Get the Cardwidth
     * @return the Cardwidth
     */
    private int getCardwidth() {
        DisplayMetrics displayMetrics;
        displayMetrics = getResources().getDisplayMetrics();

        int cardWidth;
        cardWidth = displayMetrics.widthPixels;

        return (cardWidth - 10) / num;
    }

    /**
     * addCards to display.
     * @param cardWidth the cardWidth
     * @param cardHeight the cardHeight(which is the same as the cardWidth)
     * @param slidingCard the 2D array of SlidingCards
     */
    private void addCards(int cardWidth, int cardHeight, SlidingCard[][] slidingCard) {

        for (int y = 0; y < num; y++) {
            for (int x = 0; x < num; x++) {
                SlidingCard c = new SlidingCard(getContext());
                c.setNum(1);
                addView(c, cardWidth, cardHeight);
                slidingCard[x][y] = c;
            }
        }
    }

    /**
     * Destroy the presenter.
     */
    public static void onDestory(){
        presenter = null;
    }

}
