package main.States;

import main.GUI;
import main.Player;

import java.io.Serializable;

/**
 * Stan GUI gdy wybieramy rozmiar planszy (po wybraniu przeciwnika)
 */
public class ChooseBoardSizeState implements State, Serializable {
    @Override
    public void StartState(GUI gui) {
        gui.Choose19x19.setVisible(true);
        gui.Choose13x13.setVisible(true);
        gui.Choose9x9.setVisible(true);
    }

    @Override
    public void EndState(GUI gui) {
        gui.Choose19x19.setVisible(false);
        gui.Choose13x13.setVisible(false);
        gui.Choose9x9.setVisible(false);
    }

    @Override
    public void BotDo(Player player) {

    }
}
