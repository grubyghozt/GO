package main;

import main.States.State;

import java.io.Serializable;

/**
 * Klasa BOT
 */
public class BOT extends Player {
    //private State state;
    //private Stone[][] Board;
    /**
     * Funkcja wykonująca akcje bota
     */
    public void update(Serializable object){
        if(object instanceof State){
            ((State) object).BotDo(this);
        }
    }
}
