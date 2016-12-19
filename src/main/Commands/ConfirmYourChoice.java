package main.Commands;

import main.ServerSide.Player;
import main.States.AcceptOrRefuseYourOpponentChoiceState;
import main.States.WaitingForOpponentState;

import java.io.Serializable;

/**
 * Akcja odopowiedzialna za potwierdzenie wybrania martwych kamieni oraz terytoriów
 */
public class ConfirmYourChoice implements Command, Serializable {
    @Override
    public void Execute(Player player) {
        player.update(new WaitingForOpponentState());
        player.opponent.update(player.CurrentGame.LocalModel.GetBoard());
        player.opponent.update(new AcceptOrRefuseYourOpponentChoiceState());
    }
}
