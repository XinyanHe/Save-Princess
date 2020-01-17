package com.example.gamecenter.strategy.prompts;



import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.ViewGroup;
import android.widget.Button;

/**
 * The interface for creating Prompts
 */
public interface Prompts {


     /**
      * Create Prompt window according to context
      * @param inflater the inflator
      * @param container the view group
      * @param context the context
      * @return AlertDialog
      */
     AlertDialog createPrompt(LayoutInflater inflater,
                              ViewGroup container,
                              Context context);

     /**
      * get back button
      * @return button
      */
     Button getBackToMainBtn();
     /**
      * get onlyscore button
      * @return button
      */
     Button getOnlyScoreBtn();
     /**
      * get display both button
      * @return button
      */
     Button getDisplayBothBtn();
}