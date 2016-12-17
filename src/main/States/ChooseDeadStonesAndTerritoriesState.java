package main.States;

import main.Commands.ConfirmYourChoice;
import main.Player;

import java.io.Serializable;

/**
 * Stan GUI gdy gracze wybierają martwe kamienie oraz terytoria (po naciśnięciu pass przez obu graczy)
 */
public class ChooseDeadStonesAndTerritoriesState implements State, Serializable {
    @Override
    public void StartState() {

    }

    @Override
    public void EndState() {

    }

    @Override
    public void BotDo(Player player) {
        new ConfirmYourChoice().Execute(player);
    }
}
