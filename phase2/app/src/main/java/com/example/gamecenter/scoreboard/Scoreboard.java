package com.example.gamecenter.scoreboard;

import com.example.gamecenter.gameinterface.MyObserver;
import com.example.gamecenter.gameinterface.MySubject;
import com.example.gamecenter.user.User;
import com.example.gamecenter.user.UserManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Scoreboard implements Serializable, MySubject {

    private ArrayList<Score> GlobalScore;

    private static List<MyObserver> observers;

    public Scoreboard(){
        GlobalScore = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public Scoreboard(ArrayList<Score> scores){
        GlobalScore = scores;
        observers = new ArrayList<>();
    }

    ArrayList<Score> getGlobalScore(){
        return GlobalScore;
    }

    public void setGlobalScore(ArrayList<Score> globalScore) {
        GlobalScore = globalScore;
    }


    private ArrayList<Score> sortScores(ArrayList<Score> scores){
        Collections.sort(scores,Collections.reverseOrder());
        return scores;
    }



    public String getScoreValues(boolean displayName, boolean userScoresOnly,User currentPlayer){

        ArrayList<Score> scoreList;
        int numScores;

        scoreList = GlobalScore;


        if (userScoresOnly) {
            scoreList = getUserScoreboard(currentPlayer);
        }
        else {
            scoreList = GlobalScore;
        }

        if (scoreList.size() < 3) {
            numScores = scoreList.size();
        }
        else {
            numScores = 3;
        }

        StringBuilder scoreValues = new StringBuilder();
        for (int i = 0; i < numScores; i++) {
            Score currentItem = scoreList.get(i);
            String names = currentItem.getUsername();
            if (userScoresOnly){
                names = currentItem.getUsername();
            }
            else if (!displayName && currentItem.getUsername().equals(UserManager.getCurrentUser().getUsername())){
                names = "Anonymous";
            }
            scoreValues.append(String.format(Locale.US, "%s: %d, Time used: %ds, Rank: %s",
                    names,
                    currentItem.getScore(),
                    currentItem.getTime(),
                    currentItem.getLevel())).append("\n");
        }
        return scoreValues.toString();
    }



    public void addScore(String currentPlayer, int score, int time, String level){
        Score s = new Score(currentPlayer,score,time,level);
        GlobalScore.add(s);
        sortScores(GlobalScore);
        notifyObservers();
    }


    public ArrayList<Score> getGlobalScoreboard(){
        return GlobalScore;
    }

    /**
     * Register the MyObserver object to observe
     *
     * @param obj to register
     */
    @Override
    public void register(MyObserver obj) {
        if (obj == null)
            throw new NullPointerException();
        if (!observers.contains(obj)) {
            observers.add(obj);
            obj.setSubject(this);
        }

    }
    @Override
    public void notifyObservers(){
        for (MyObserver obj : observers) {
            obj.update();
        }
    }

    /**
     * Returns an ArrayList representing the users scoreboard for sliding tiles game.
     * @return UserScores
     */
    private ArrayList<Score> getUserScoreboard(User current_player){
        ArrayList<Score> UserScores = new ArrayList<>();
        for(int i = 0; i < GlobalScore.size(); i++){
            if(GlobalScore.get(i).getUsername().equals(current_player.getUsername())){
                UserScores.add(GlobalScore.get(i));
            }
        }
        return UserScores;
    }

}