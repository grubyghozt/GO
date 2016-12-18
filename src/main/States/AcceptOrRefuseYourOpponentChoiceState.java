package main.States;

import main.Commands.ConfirmYourOpponentChoice;
import main.GUI;
import main.Player;

import java.io.Serializable;

/**
 * Stan GUI gdy gracz musi zaakceptować bądź odrzucić martwe kamienie wybrane przez przeciwnika (po wybraniu martwych kamieni przez przeciwnika)
 */
public class AcceptOrRefuseYourOpponentChoiceState implements State, Serializable {
    @Override
    public void StartState(GUI gui) {
        gui.AcceptDead.setVisible(true);
        gui.RefuseDead.setVisible(true);
    }

    @Override
    public void EndState(GUI gui) {
        gui.AcceptDead.setVisible(false);
        gui.RefuseDead.setVisible(false);
    }

    @Override
    public void BotDo(Player player) {
        new ConfirmYourOpponentChoice().Execute(player);
    }
}
