package pt.isec.pa.tinypac.model.data.player;

import java.io.Serializable;

public class Player implements Serializable {
    //Internal Data
    private int place;
    private String userName;
    private int points;

    //Constructor
    public Player(int place, String userName, int points) {
        this.place = place;
        this.userName = userName;
        this.points = points;
    }

    //Get Methods
    public int getPlace() { return this.place; }
    public String getUserName() { return this.userName; }
    public int getPoints() { return this.points; }

    //Set Methods


    //Methods


    //Overrides


    //Internal Functions


}