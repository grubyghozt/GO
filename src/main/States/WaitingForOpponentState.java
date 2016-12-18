package main.States;

import main.GUI;
import main.Player;

import java.io.Serializable;

/**
 * Stan GUI gdy czekamy na przeciwnika (po stworzeniu nowej gry, próbie dołączenia lub oczekiwanie na ruch przeciwnika)
 */
public class WaitingForOpponentState implements State, Serializable {
    @Override
    public void StartState(GUI gui) {
        gui.WaitingForOpponent.setVisible(true);
    }

    @Override
    public void EndState(GUI gui) {
        gui.WaitingForOpponent.setVisible(false);
    }

    @Override
    public void BotDo(Player player) {

    }
}
