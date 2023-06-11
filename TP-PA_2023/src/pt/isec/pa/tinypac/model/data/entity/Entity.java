package pt.isec.pa.tinypac.model.data.entity;

import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.game.Game;

public abstract class Entity extends Element {
    //Internal Data


    //Constructor
    public Entity(Game gameData, int x, int y) {
        super(gameData, x, y);
    }

    //Get Methods


    //Set Methods


    //Methods
    abstract public void move();

    //Overrides


    //Internal Functions


}