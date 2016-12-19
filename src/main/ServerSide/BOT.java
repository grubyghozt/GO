package main.ServerSide;

import main.States.State;

import java.io.Serializable;

/**
 * Klasa BOT
 */
public class BOT extends Player {
    /**
     * Funkcja wykonująca akcje bota
     */
    public void update(Serializable object){
        if(object instanceof State){
            ((State) object).BotDo(this);
        }
    }
}
