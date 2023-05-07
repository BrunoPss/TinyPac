package pt.isec.pa.tinypac.model.data.warp;

import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.game.Game;

public class Warp extends Element {
    //Internal Data
    public static final char SYMBOL = 'W';
    private Warp complementWarp = null;

    //Constructor
    public Warp(Game gameData, int x, int y) {
        super(gameData, x, y);
    }

    //Get Methods
    public Warp getComplementWarp() {
        return this.complementWarp;
    }

    //Set Methods
    public void setComplementWarp(Element warp) {
        this.complementWarp = (Warp) warp;
    }

    //Methods


    //Overrides
    @Override
    public char getSymbol() {
        return 'W';
    }

    //Internal Functions


}