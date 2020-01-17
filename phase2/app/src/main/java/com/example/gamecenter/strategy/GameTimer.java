package com.example.gamecenter.strategy;

import android.os.SystemClock;
import android.widget.Chronometer;


import java.util.Timer;

/**
 * The GameTimer class.
 */
public class GameTimer {

    /**
     * A timer.
     */
    private Timer timer = new Timer();

    /**
     * A Chronometer.
     */
    private Chronometer chrono;

    /**
     * A long type attribute lastPause which is initialized to 0.
     */
    private long lastPause = 0;

    public GameTimer(Chronometer chrono){
        this.chrono = chrono;
        timer = new Timer();
    }

    /**
     * The restart method restarts the timer.
     */
    public void restart(){
        timer = new Timer();
        this.chrono.setFormat("USED TIME：%s");
        if(lastPause == 0){
        chrono.setBase(SystemClock.elapsedRealtime());}
        else{
            chrono.setBase(chrono.getBase() + SystemClock.elapsedRealtime() - lastPause);
        }
        chrono.start();
    }

    /**
     * The stop method stops the timer.
     */
    public void stop(){
        timer.cancel();
        timer = null;
        chrono.stop();
        lastPause = SystemClock.elapsedRealtime();

    }

    public Timer getTimer() {
        return timer;
    }


    /**
     * Gets the time.
     * @return time in seconds.
     */
    public int getTime(){
        return  (int)(SystemClock.elapsedRealtime() - chrono.getBase())/1000;
    }

    public void setResume(Chronometer chronos){
        chronos.setBase(chrono.getBase() + SystemClock.elapsedRealtime() - lastPause);
        chronos.start();
        this.chrono = chronos;
    }
}
