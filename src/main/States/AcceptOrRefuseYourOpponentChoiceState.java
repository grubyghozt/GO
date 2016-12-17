package main.States;

import main.Commands.ConfirmYourOpponentChoice;
import main.Player;

import java.io.Serializable;

/**
 * Stan GUI gdy gracz musi zaakceptować bądź odrzucić martwe kamienie wybrane przez przeciwnika (po wybraniu martwych kamieni przez przeciwnika)
 */
public class AcceptOrRefuseYourOpponentChoiceState implements State, Serializable {
    @Override
    public void StartState() {

    }

    @Override
    public void EndState() {

    }

    @Override
    public void BotDo(Player player) {
        new ConfirmYourOpponentChoice().Execute(player);
    }
}
