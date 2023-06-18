package pt.isec.pa.tinypac.ui.curses;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.wall.Wall;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.data.pacman.Pacman;
import pt.isec.pa.tinypac.model.data.ball.Ball;

import java.io.IOException;

/**
 * Curses Game UI Class
 */

public class CursesGameUI implements IGameEngineEvolve {
    //Internal Data
    Screen screen;
    TextGraphics tGraphics;
    TerminalSize size;
    GameContext fsm;

    //Constructor

    /**
     * CursesGameUI
     * @param fsm Finite State Machine
     * @throws IOException Exception
     */
    public CursesGameUI(GameContext fsm) throws IOException {
        this.fsm = fsm;
        screen = new DefaultTerminalFactory().createScreen();
        screen.setCursorPosition(null);
        screen.startScreen();

        initMenu();
    }

    //Get Methods


    //Set Methods


    //Methods

    /**
     * show
     * @throws IOException Exception
     */
    public void show() throws IOException {
        /*
        char[][] env = fsm.getMaze();
        for (int y=0; y < fsm.getMaze().length; y++) {
            for (int x=0; x < fsm.getMaze()[0].length; x++) {
                TextColor tc = switch(env[y][x]) {
                    case Pacman.SYMBOL -> TextColor.ANSI.YELLOW_BRIGHT;
                    case Wall.SYMBOL -> TextColor.ANSI.WHITE;
                    case Ball.SYMBOL -> TextColor.ANSI.YELLOW;
                    default -> TextColor.ANSI.BLACK;
                };
                TextColor bc = switch(env[y][x]) {
                    case Pacman.SYMBOL -> TextColor.ANSI.YELLOW_BRIGHT;
                    case Wall.SYMBOL -> TextColor.ANSI.BLUE;
                    default -> TextColor.ANSI.WHITE_BRIGHT;
                };
                screen.setCharacter(x,y, TextCharacter.fromCharacter(env[y][x],tc,bc)[0]);
            }
        }
        screen.refresh();

         */
    }

    //Overrides
    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        try {
            show();
            updatePoints();

            /*
            KeyStroke key = screen.pollInput();
            if (key != null && (key.getKeyType() == KeyType.Escape ||
                                    (key.getKeyType() == KeyType.Character &&
                                            key.getCharacter().equals('q')))
            ){
                gameEngine.stop();
                screen.close();
            }*/
        } catch (IOException e) { }
    }

    //Internal Functions
    private void initMenu() throws IOException {
        TextColor tColor = TextColor.ANSI.WHITE;
        TextColor bColor = TextColor.ANSI.GREEN;

        tGraphics = screen.newTextGraphics();

        screen.startScreen();
        size = screen.getTerminalSize();
        screen.clear();

        tGraphics.putString(0,0, "TinyPAcman");
        tGraphics.putString(0,size.getRows()-1, "Bruno Guiomar - 2021137345");
        tGraphics.putString(size.getColumns()-33, size.getRows()-1, "DEIS-ISEC-IPC LEI-CE PA 2022/2023");

        tGraphics.putString(size.getColumns()-15, 0, "Points: ");

        screen.refresh();
    }
    private void updatePoints() {
        tGraphics.putString(size.getColumns()-5, 0, String.valueOf(fsm.getPacmanPoints()));
    }
}