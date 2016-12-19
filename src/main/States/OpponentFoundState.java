package main.States;

import main.ClientSide.GUI;
import main.ServerSide.Player;
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
    public void StartState(GUI gui) {
        gui.size=this.size;
        gui.YouPlayAs.setLabel("You play as " + player);
        gui.YouPlayAs.setVisible(true);
        gui.GiveUp.setVisible(true);
        gui.Board.setSize(size*20-1,size*20-1);
        gui.Board.setVisible(true);
    }

    @Override
    public void EndState(GUI gui) {

    }

    @Override
    public void BotDo(Player player) {

    }
}
