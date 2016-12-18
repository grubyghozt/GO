package main.States;

import main.Commands.ConfirmYourChoice;
import main.GUI;
import main.Player;

import java.io.Serializable;

/**
 * Stan GUI gdy gracze wybierają martwe kamienie oraz terytoria (po naciśnięciu pass przez obu graczy)
 */
public class ChooseDeadStonesAndTerritoriesState implements State, Serializable {
    @Override
    public void StartState(GUI gui) {
        gui.ChooseDead.setVisible(true);
        gui.ConfirmDead.setVisible(true);
        gui.Resume.setVisible(true);
    }

    @Override
    public void EndState(GUI gui) {
        gui.ChooseDead.setVisible(false);
        gui.ConfirmDead.setVisible(false);
        gui.Resume.setVisible(false);
    }

    @Override
    public void BotDo(Player player) {
        new ConfirmYourChoice().Execute(player);
    }
}
