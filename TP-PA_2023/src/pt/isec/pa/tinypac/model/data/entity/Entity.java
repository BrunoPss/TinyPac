package pt.isec.pa.tinypac.model.data.entity;

import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.game.Game;

public abstract class Entity extends Element {
    //Internal Data
    protected int x, y;

    //Constructor
    public Entity(Game gameData, int x, int y) {
        super(gameData);
        this.x = x;
        this.y = y;
    }

    //Get Methods
    public int getX() { return x; }
    public int getY() { return y; }

    //Set Methods


    //Methods
    abstract public void move();

    //Overrides


    //Internal Functions


}