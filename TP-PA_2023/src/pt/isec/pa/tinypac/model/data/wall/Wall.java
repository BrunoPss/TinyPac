package pt.isec.pa.tinypac.model.data.wall;

import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.game.Game;

public class Wall extends Element {
    //Internal Data
    public static final char SYMBOL = 'x';

    //Constructor
    public Wall(Game gameData, int x, int y) {
        super(gameData, x, y);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    @Override
    public char getSymbol() {
        return 'x';
    }

    //Internal Functions


}