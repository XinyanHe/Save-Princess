package com.example.gamecenter.games.slidinggame.model;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.gamecenter.R;

/**
 * SlidingCard class contains the Image that are used in Sliding game.
 */
public class SlidingCard extends FrameLayout {
    /** pic is an ImageView */
    private ImageView pic;
    /** picArray is an int array that can store the pictures used in Sliding activity*/
    private int[] picArray = new int[16];
    /** num is an integer in the array corresponds to an index*/
    private int num = 0;

    /**
     * Create a SlidingCard.
     * @param context context
     */
    public SlidingCard(Context context) {
        super(context);
        matchPic();
        pic = new ImageView(getContext());
        LayoutParams lp = new LayoutParams(-1, -1);
        lp.setMargins(10, 10, 0, 0);
        addView(pic, lp);
        setNum(0);
    }

    /**
     * matchPic is a method that matches each card picture to a place in in the picArray.
     */
    public void matchPic() {
        picArray[0] = 0;
        picArray[1] = R.drawable.rare0;
        picArray[2] = R.drawable.rare1;
        picArray[3] = R.drawable.rare2;
        picArray[4] = R.drawable.rare3;
        picArray[5] = R.drawable.rare4;
        picArray[6] = R.drawable.rare5;
        picArray[7] = R.drawable.rare6;
        picArray[8] = R.drawable.rare7;
        picArray[9] = R.drawable.rare8;
        picArray[10] = R.drawable.rare9;
        picArray[11] = R.drawable.rare10;
        picArray[12] = R.drawable.rare11;
        picArray[13] = R.drawable.rare12;
        picArray[14] = R.drawable.rare13;
        picArray[15] = R.drawable.rare14;

    }

    /**
     * Get num.
     * @return num.
     */
    public int getNum() {
        return num;
    }

    /**
     * Set num.
     * @param num given an integer and set the backGround resource to
     *            the correspond picture in picArray.
     */
    public void setNum(int num) {
        this.num = num;
        pic.setBackgroundResource(picArray[num]);
    }

    /**
     * Change the equals method so that it can be used to compare if two cards are the same.
     * @param o a SlidingCard
     * @return true if they are the same, false otherwise.
     */
    public boolean equals(SlidingCard o) {
        return getNum() == o.getNum();
    }

}