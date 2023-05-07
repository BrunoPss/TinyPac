package pt.isec.pa.tinypac.model.data.cave;

import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.game.Game;

public class Cave extends Element {
    //Internal Data
    public static final char SYMBOL = 'y';

    //Constructor
    public Cave(Game gameData, int x, int y) {
        super(gameData, x, y);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    @Override
    public char getSymbol() {
        return 'y';
    }

    //Internal Functions


}