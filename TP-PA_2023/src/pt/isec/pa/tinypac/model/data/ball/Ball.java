package pt.isec.pa.tinypac.model.data.ball;

import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.game.Game;

public class Ball extends Element {
    //Internal Data
    public static final char SYMBOL = 'o';

    //Constructor
    public Ball(Game gameData, int x, int y) {
        super(gameData, x, y);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    @Override
    public char getSymbol() {
        return 'o';
    }

    //Internal Functions


}