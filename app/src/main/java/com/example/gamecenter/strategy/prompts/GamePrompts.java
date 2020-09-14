package com.example.gamecenter.strategy.prompts;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.gamecenter.R;

public class GamePrompts extends Fragment implements Prompts {

    private Button displayBoth;
    private Button backToMain;
    private Button onlyScore;


    /**
     * Create Prompt window according to context
     * @param inflater the inflator
     * @param container the view group
     * @param context the context
     * @return AlertDialog
     */
    @Override
    public AlertDialog createPrompt(LayoutInflater inflater,
                                    ViewGroup container,
                                    Context context) {

        View view = inflater.inflate(R.layout.fragment_massege,
                container, false);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        alertDialogBuilder.setView(view);
        AlertDialog dialog = alertDialogBuilder.create();

         displayBoth = view.findViewById(R.id.YesBoth);
         backToMain = view.findViewById(R.id.No);
         onlyScore = view.findViewById(R.id.onlyScore);
        return dialog;

    }

    /**
     * get back button
     * @return button
     */
    @Override
    public Button getBackToMainBtn() {
        return backToMain;
    }

    /**
     * get onlyscore button
     * @return button
     */
    @Override
    public Button getOnlyScoreBtn() {
        return onlyScore;
    }

    /**
     * get display both button
     * @return button
     */
    @Override
    public Button getDisplayBothBtn() {
        return displayBoth;
    }

}