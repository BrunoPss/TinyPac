package pt.isec.pa.tinypac.model.data.warp;

import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.game.Game;

/**
 * Warp Class
 * <p>Class that represents the Warp Element</p>
 *
 * @ author Bruno Guiomar
 * @ version 1.0.0
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

    //Constructor
    /**
     * Constructor
     * @param gameData Game Data Model
     * @param x Initial Position (x cord.)
     * @param y Initial Position (y cord.)
     */
    public Warp(Game gameData, int x, int y) {
        super(gameData, x, y);
    }

    //Get Methods

    /**
     * Gets the complementary Warp Element
     * @return Reference to the complementary Warp Element
     */
    public Warp getComplementWarp() {
        return this.complementWarp;
    }

    //Set Methods

    /**
     * Sets the complementary Warp Element
     * @param warp Complementary Warp Element
     */
    public void setComplementWarp(Element warp) {
        this.complementWarp = (Warp) warp;
    }

    //Methods


    //Overrides
    /**
     * Gets Element Symbol
     * @return Symbol of the Element (Warp)
     */
    @Override
    public char getSymbol() {
        return 'W';
    }

    //Internal Functions


}