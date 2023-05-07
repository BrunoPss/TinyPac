package pt.isec.pa.tinypac.model.data.portal;

import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.game.Game;

public class Portal extends Element {
    //Internal Data
    public static final char SYMBOL = 'Y';

    //Constructor
    public Portal(Game gameData, int x, int y) {
        super(gameData, x, y);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    @Override
    public char getSymbol() {
        return 'Y';
    }

    //Internal Functions


}