package pt.isec.pa.tinypac.model.data.fruit;

import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.game.Game;

/**
 * Fruit Class
 * <p>Class that represents the Fruit Element</p>
 *
 * @ author Bruno Guiomar
 * @ version 1.0.0
 */

public class Fruit extends Element {
    //Internal Data
    public static final char SYMBOL = 'F';
    public static boolean ACTIVE = false;
    private boolean visible;
    private int nextFruitTimeout;

    //Constructor
    public Fruit(Game gameData, int x, int y) {
        super(gameData, x, y);
        this.visible = true;
        this.nextFruitTimeout = 20;
        ACTIVE = true;
    }

    //Get Methods
    public boolean getVisible() { return visible; }

    //Set Methods
    public void setVisible(boolean state) { this.visible = state; }

    //Methods
    public void decrementNextFruitTimeout() {
        if (nextFruitTimeout > 0) {
            nextFruitTimeout--;
            System.out.println("DECREMENT");
        }
        else {
            System.out.println("CREATE");
            gameData.newFruit();
            nextFruitTimeout = 20;
        }
    }

    //Overrides
    @Override
    public char getSymbol() {
        return 'F';
    }

    //Internal Functions


}