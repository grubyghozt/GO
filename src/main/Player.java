package main;

import main.Commands.Command;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Interface gracza (człowieka i Bota)
 */
public abstract class Player {
    public color color;
    public Game CurrentGame;
    public Player opponent;
    //ObjectInputStream in;
    //ObjectOutputStream out;
    /**
     * Funkcja wykonująca komendę
     */
    //private void ExecuteCommand(Command command){}
    /**
     * Funkcja przesyłająca wynik
     */
    public void update(Serializable object){}
}
