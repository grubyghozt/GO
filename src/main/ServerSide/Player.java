package main.ServerSide;

import java.io.Serializable;

/**
 * Interface gracza (człowieka i Bota)
 */
public abstract class Player {
    public main.color color;
    public Game CurrentGame;
    public Player opponent;
    /**
     * Funkcja przesyłająca wynik
     */
    public void update(Serializable object){}
}
