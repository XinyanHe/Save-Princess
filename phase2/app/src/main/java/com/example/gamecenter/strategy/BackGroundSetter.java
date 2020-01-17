package com.example.gamecenter.strategy;

import android.app.Activity;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.gamecenter.R;

/**
 * The BackGroundSetter class that set the background and change the text color.
 */
public class BackGroundSetter {

    /**
     * switchStatus indicates whether or not to switch status.
     */
    private static boolean switchStatus;

    /**
     * Check if it's a SwitchStatus.
     * @return true if is going to swith status, false otherwise.
     */
    public static boolean isSwitchStatus() {
        return switchStatus;
    }

    /**
     * set the Switch Status.
     * @param switchStatus is switchStatus.
     */
    public static void setSwitchStatus(boolean switchStatus) {
        BackGroundSetter.switchStatus = switchStatus;
    }

    /**
     * set the Wall Paper.
     * @param texts a list of TextView
     * @param context the context of Activity
     * @param layout the layout of ViewGroup
     */
    public static void setWallPaper(TextView[] texts, Activity context, ViewGroup layout){
        if (switchStatus) {
            layout.setBackground(ContextCompat.getDrawable(context, R.drawable.deernight));

            for (TextView k : texts) {
                k.setTextColor(Color.parseColor("#FFFFFF"));}
        } else {
            layout.setBackground(ContextCompat.getDrawable(context, R.drawable.whitebackground));

            for (TextView t : texts) {
                t.setTextColor(Color.parseColor("#0081BB"));
            }
        }

    }
}
