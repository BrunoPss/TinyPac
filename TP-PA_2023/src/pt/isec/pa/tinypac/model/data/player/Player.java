package pt.isec.pa.tinypac.model.data.player;

import java.io.Serializable;

public class Player implements Serializable {
    //Internal Data
    private String userName;
    private int points;

    //Constructor
    public Player(String userName, int points) {
        this.userName = userName;
        this.points = points;
    }

    //Get Methods
    public String getUserName() { return this.userName; }
    public int getPoints() { return this.points; }

    //Set Methods


    //Methods


    //Overrides


    //Internal Functions


}