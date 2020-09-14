package com.example.gamecenter.user;

import android.content.Context;
import android.util.Log;

import com.example.gamecenter.gameinterface.MyObserver;
import com.example.gamecenter.gameinterface.MySubject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


/**
 * The UserFileSaver.
 */
public class UserFileSaver implements Serializable, MyObserver {


    /**
     * A list that store all users.
     */
    private ArrayList<User> allUsers = new ArrayList<>();

    /**
     * A name of the file that store the object UserManager.
     */
    private static final String fileName = "allUsers.ser";

    /**
     *The subject this class observes
     */
    private UserManager subject;

    /**
     * A context.
     */
    private Context context;

    /**
     * Construct a new fall2018.csc2017.slidingtiles.UserFileSaver from context
     * @param context
     */
    public UserFileSaver(Context context){
        this.context = context;
        loadFromFile(context);
    }

    /**
     * Update the subjects users and save the users
     */
    public void update() {
        allUsers= subject.getAllUsers();
        saveToFile(fileName);
    }

    /**
     * Set the subject to be obsevred
     *
     * @param subject to be observed
     */
    @Override
    public void setSubject(MySubject subject) {
        this.subject = (UserManager) subject;
    }

    /**
     * Return the saved Array list of users
     * @return
     */
    public ArrayList<User> getAllUsers() {
        return allUsers;
    }

    /**Load Users from fileName**/

    @SuppressWarnings("unchecked")
    public void loadFromFile(Context context) {

        try {
            InputStream inputStream = context.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                allUsers = (ArrayList<User>) input.readObject();
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

    /** Save users to fileName**/
    public void saveToFile(String fileName){
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    context.openFileOutput(fileName, MODE_PRIVATE));
            outputStream.writeObject(allUsers);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }


}

