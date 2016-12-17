package main.States;

import main.Player;
import main.color;

import java.io.Serializable;

/**
 * Stan GUI który powiadamia jakim kolorem gramy (po znalezieniu przeciwnika)
 */
public class OpponentFoundState implements State, Serializable {
    public color player;
    public int size;
    public OpponentFoundState(color player, int size){
        this.size = size;
        this.player = player;
    }
    @Override
    public void StartState() {

    }

    @Override
    public void EndState() {

    }

    @Override
    public void BotDo(Player player) {

    }
}
