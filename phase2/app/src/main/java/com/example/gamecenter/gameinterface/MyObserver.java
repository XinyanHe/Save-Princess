package com.example.gamecenter.gameinterface;

public interface MyObserver {
    /**
     * Update accordingly after subject calls notifyObservers()
     */
    void update();

    /**
     * Set the subject to be observed
     * @param subject to be observed
     */
    void setSubject(MySubject subject);
}
