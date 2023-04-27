package pt.isec.pa.tinypac.model.data.element;

import pt.isec.pa.tinypac.model.data.Pacman.Pacman;
import pt.isec.pa.tinypac.model.data.ball.Ball;

public class ElementFactory {
    //Internal Data


    //Constructor


    //Get Methods


    //Set Methods


    //Methods
    public static Element createElement(ElementType type) {
        return switch (type) {
            case BALL -> new Ball();
        };
    }

    //Overrides


    //Internal Functions


}