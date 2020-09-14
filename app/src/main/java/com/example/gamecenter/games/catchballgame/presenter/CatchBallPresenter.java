package com.example.gamecenter.games.catchballgame.presenter;

import android.view.MotionEvent;

import com.example.gamecenter.gameinterface.GameController;
import com.example.gamecenter.gameinterface.MyObserver;
import com.example.gamecenter.gameinterface.MySubject;
import com.example.gamecenter.games.catchballgame.activity.CatchBallActivity;
import com.example.gamecenter.games.catchballgame.model.CatchBallManager;

import java.util.ArrayList;
import java.util.List;


public class CatchBallPresenter implements GameController, MySubject {

    /**
     * The list of observers of this class
     */
    private static List<MyObserver> observers;


    private CatchBallManager manager;
    private CatchBallActivity catchBallView;

    public CatchBallPresenter(CatchBallActivity boardView, CatchBallManager manager) {
        this.catchBallView = boardView;
        observers = new ArrayList<>();
        this.manager = manager;
    }


    public void onStart(MotionEvent action, boolean startFlag, int frameHeight) {
        if (startFlag) {
            catchBallView.makeAction(action);

        } else {
            catchBallView.setStartFlag(true);
            manager.updatePlayerSize();
            manager.setBoardHeight(frameHeight);
            catchBallView.hideStartLabel();
            catchBallView.getGameTimer().restart();
            catchBallView.updateTimer();
        }

    }

    public void hitCheck(boolean actionFlag) {
        manager.hitCheck();
        catchBallView.updateScore(manager.getScore());
        if (manager.isGameOver()) {
            catchBallView.getGameTimer().stop();
            catchBallView.showPrompt();

        } else {
            manager.changePos(actionFlag);
            if(manager.checkNextLevel()){
                catchBallView.setLevel("LEVEL2");
            }

        }
    }

    public void onDestroy() {
        catchBallView = null;
    }


    @Override
    public CatchBallManager getGameManager() {
        return manager;
    }


    /**
     * Add an observer, obs, to this class
     * @param obs The observer added
     */
    @Override
    public void register(MyObserver obs) {
        if(!observers.contains(obs))
        {observers.add(obs);
            obs.setSubject(this);}
    }

    @Override
    public void notifyObservers() {
        for (MyObserver obs: observers) {
            obs.update();
        }
    }



}
