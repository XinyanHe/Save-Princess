package com.example.gamecenter.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The User class creates different players.
 */
public class User implements Serializable {
    /**
     * username is the player's username.
     * password is the player's password.
     * score is the player's score.
     */
    private String username;
    private String password;
    private int score;

    /**
     * Construct an User.
     * @param username player's username.
     * @param password player's password.
     */
    User(String username,String password){
        this.username=username;
        this.password=password;
        this.score = 0;
    }

    /**
     * Set the player's score.
     * @param score the player's score.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Get the player's score.
     * @return the score.
     */
    public int getScore(){
        return this.score;
    }

    /**
     * Get the player's password.
     * @return the password.
     */
    String getPassword() {
        return this.password;
    }

    /**
     * Get the player's username.
     * @return username.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Override the equals method to check if the player's username has been previously registered.
     * @param obj an object user
     * @return true if it has been registered, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof User){
            User o = (User) obj;
            return(o.getUsername().equals(this.username));
        }
        return false;
    }
}
