package pt.isec.pa.tinypac.model.data.fruit;

import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.game.Game;

/**
 * Fruit Class
 * <p>Class that represents the Fruit Element</p>
 * @author Bruno Guiomar
 * @version 1.0.0
 */

public class Fruit extends Element {
    //Internal Data
    /**
     * Symbol of the Element (Fruit)
     */
    public static final char SYMBOL = 'F';
    /**
     * State of the Element
     */
    public static boolean ACTIVE = false;
    private boolean visible;
    private int nextFruitTimeout;
    private int x,y;

    //Constructor
    /**
     * Constructor
     * @param gameData DataModel
     * @param x coordinate (x)
     * @param y coordinate (y)
     */
    public Fruit(Game gameData, int x, int y) {
        super(gameData);
        this.x = x;
        this.y = y;
        this.visible = true;
        this.nextFruitTimeout = 20;
        ACTIVE = true;
    }

    //Get Methods

    /**
     * Get element visibility
     * @return visibility
     */
    public boolean getVisible() { return visible; }
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

    //Set Methods

    /**
     * Set visibility
     * @param state boolean
     */
    public void setVisible(boolean state) { this.visible = state; }

    //Methods

    /**
     * Decrement time for the next Fruit Creation
     */
    public void decrementNextFruitTimeout() {
        if (nextFruitTimeout > 0) {
            nextFruitTimeout--;
        }
        else {
            System.out.println("CREATE FRUIT");
            gameData.newFruit();
            nextFruitTimeout = 20;
        }
    }

    //Overrides
    /**
     * Gets Element Symbol
     * @return Element Symbol (Fruit)
     */
    @Override
    public char getSymbol() {
        return 'F';
    }

    //Internal Functions


}