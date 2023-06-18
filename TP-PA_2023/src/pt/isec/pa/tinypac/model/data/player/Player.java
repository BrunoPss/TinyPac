package pt.isec.pa.tinypac.model.data.player;

import java.io.Serializable;

/**
 * Player Class
 * <p>Class that represents the Current Player</p>
 *
 * @author Bruno Guiomar
 * @version 1.0.0
 */

public class Player implements Serializable, Comparable<Player> {
    //Internal Data
    private int place;
    private String userName;
    private int points;

    //Constructor
    /**
     * Constructor
     * @param place place in top5
     * @param userName name of the player
     * @param points player's points
     */
    public Player(int place, String userName, int points) {
        this.place = place;
        this.userName = userName;
        this.points = points;
    }

    //Get Methods

    /**
     * getPlace
     * @return Player Place
     */
    public int getPlace() { return this.place; }

    /**
     * getUserName
     * @return player user name
     */
    public String getUserName() { return this.userName; }

    /**
     * Gets Player points
     * @return current points
     */
    public int getPoints() { return this.points; }

    //Set Methods

    /**
     * Sets Player Place
     * @param place current place
     */
    public void setPlace(int place) { this.place = place; }

    //Methods


    //Overrides

    /**
     * Compares two player object points
     * @param o the object to be compared.
     * @return comparation result
     */
    @Override
    public int compareTo(Player o) {
        int comparePoints = ((Player)o).getPoints();
        return comparePoints - this.points;
    }

    //Internal Functions


}