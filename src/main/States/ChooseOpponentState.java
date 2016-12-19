package main.States;

import main.ClientSide.GUI;
import main.ServerSide.Player;

import java.io.Serializable;

/**
 * Stan GUI gdy wybieramy rodzaj przeciwnika (po wybraniu nowej gry)
 */
public class ChooseOpponentState implements State, Serializable {
    @Override
    public void StartState(GUI gui) {
        gui.ChoosePlayer.setVisible(true);
        gui.ChooseBot.setVisible(true);
    }

    @Override
    public void EndState(GUI gui) {
        gui.ChoosePlayer.setVisible(false);
        gui.ChooseBot.setVisible(false);
    }

    @Override
    public void BotDo(Player player) {

    }
}