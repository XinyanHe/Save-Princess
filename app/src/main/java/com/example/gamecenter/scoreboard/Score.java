package com.example.gamecenter.scoreboard;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Score implements Serializable ,Comparable<Score>{

    private int score;

    private int time;

    private String user;

    private String level;


    public Score(String user,int score,int time,String level) {
        this.score = score;
        this.user = user;
        this.time = time;
        this.level = level;
    }




    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUsername(){return user;}

    public String getLevel(){return level;}

    public int getTime(){return time;}


    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Score)
        {Score o = (Score) obj;
            return (o.getUsername().equals(this.getUsername()) && o.getScore() == this.getScore());}
        return false;
    }

    /**
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(@NonNull Score o) {
        return Integer.compare(this.score,o.getScore());
    }
}
