package pt.isec.pa.tinypac.model.data.warp;

import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.game.Game;

/**
 * Warp Class
 * <p>Class that represents the Warp Element</p>
 *
 * @author Bruno Guiomar
 * @version 1.0.0
 */

public class Warp extends Element {
    //Internal Data
    /**
     * Symbol of the Element (Warp)
     */
    public static final char SYMBOL = 'W';
    /**
     * Reference to the complementary Warp Element
     */
    private Warp complementWarp = null;
    private int x,y;

    //Constructor
    /**
     * Constructor
     * @param gameData DataModel
     * @param x coordinate (x)
     * @param y coordinate (y)
     */
    public Warp(Game gameData, int x, int y) {
        super(gameData);
        this.x = x;
        this.y = y;
    }

    //Get Methods
    /**
     * Get X Coordinate
     * @return x - current x position
     */
    public int getX() { return x; }
    /**
     * Get Y Coordinate
     * @return y - current y position
     */
    public int getY() { return y; }

    /**
     * Get Complementary Warp
     * @return complementary Warp
     */
    public Warp getComplementWarp() {
        return this.complementWarp;
    }

    //Set Methods

    /**
     * Sets the complementary Warp
     * @param warp complementary warp
     */
    public void setComplementWarp(Element warp) {
        this.complementWarp = (Warp) warp;
    }

    //Methods


    //Overrides
    /**
     * Gets Element Symbol
     * @return Element Symbol (Warp)
     */
    @Override
    public char getSymbol() {
        return 'W';
    }

    //Internal Functions


}