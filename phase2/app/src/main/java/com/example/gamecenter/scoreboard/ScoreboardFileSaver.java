package com.example.gamecenter.scoreboard;

import android.content.Context;
import android.util.Log;

import com.example.gamecenter.gameinterface.MyObserver;
import com.example.gamecenter.gameinterface.MySubject;

import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class ScoreboardFileSaver implements Serializable, MyObserver {

    private String fileName;

    private Context context;

    /*
    A List of globalScores
     */
    private ArrayList<Score> globalScores  = new ArrayList<>();

    /**
     *
     */

    private Scoreboard subject;

    /**
     * Update accordingly after subject calls notifyObservers()
     */

    /**
     * Return the save global scores
     * @return
     */
    public ArrayList<Score> getGlobalScores() {
        return globalScores;
    }


    public ScoreboardFileSaver(Context context,String fileName){
        this.context = context;
        this.fileName = fileName;
        loadFromFile(fileName);

    }


    //Deserialization

    public void loadFromFile(String fileName) {
        try {
            InputStream inputStream = context.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                globalScores = (ArrayList<Score>)input.readObject();
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        }
        catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
    }

    //serialization

    public void saveToFile(String fileName) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    context.openFileOutput(fileName,Context.MODE_PRIVATE));
            outputStream.writeObject(globalScores);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }



    @Override
    public void update() {
        globalScores = subject.getGlobalScore();
        saveToFile(fileName);
    }

    /**
     * Set the subject to be obsevred
     *
     * @param subject to be observed
     */
    @Override
    public void setSubject(MySubject subject) {
        this.subject = (Scoreboard)subject;

    }
}
