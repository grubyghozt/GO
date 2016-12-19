package main.Commands;

import main.ServerSide.Player;
import main.States.WaitingForOpponentState;

import java.io.Serializable;

/**
 * Akcja odopowiedzialna za sparowanie z innym graczem
 */
public class ChoosePlayer implements Command, Serializable {
    @Override
    public void Execute(Player player) {
        player.CurrentGame.freespace=true;
        player.update(new WaitingForOpponentState());
    }
}
