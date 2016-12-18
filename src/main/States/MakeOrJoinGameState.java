package main.States;

import main.GUI;
import main.Player;

import java.io.Serializable;

/**
 * Stan GUI gdy rozpoczynamy lub dołączamy do istniejącej gry (po uruchomienu programu lub zakończeniu rozgrywki)
 */
public class MakeOrJoinGameState implements State, Serializable {
    @Override
    public void StartState(GUI gui) {
        gui.NewGame.setVisible(true);
        gui.JoinGame.setVisible(true);
    }

    @Override
    public void EndState(GUI gui) {
        gui.NewGame.setVisible(false);
        gui.JoinGame.setVisible(false);
    }

    @Override
    public void BotDo(Player player) {

    }
}
