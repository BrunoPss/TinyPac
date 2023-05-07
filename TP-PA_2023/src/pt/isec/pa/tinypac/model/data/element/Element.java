package pt.isec.pa.tinypac.model.data.element;

import pt.isec.pa.tinypac.model.data.game.Game;
import pt.isec.pa.tinypac.model.data.maze.IMazeElement;

public abstract class Element implements IMazeElement {
    //Internal Data
    protected int x, y;
    protected Game gameData;

    //Constructor
    public Element(Game gameData, int x, int y) {
        this.x = x;
        this.y = y;
        this.gameData = gameData;
    }

    //Get Methods
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }

    //Set Methods


    //Methods
    //GET ELEMENTO FROM DIRECTION()


    //Overrides


    //Internal Functions


}