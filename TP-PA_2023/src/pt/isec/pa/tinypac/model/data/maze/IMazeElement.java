package pt.isec.pa.tinypac.model.data.maze;

import java.io.Serializable;

public interface IMazeElement extends Serializable {
    /**
     * getSymbol
     * @return Element Symbol
     */
    char getSymbol();  // returns the symbol of this element
    //   The list of symbols is available
    //   in the description of this work
}