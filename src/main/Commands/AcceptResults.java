package main.Commands;

import main.Player;
import main.States.MakeOrJoinGameState;

import java.io.Serializable;

/**
 * Akcja odopowiedzialna za zakończenie rozgrywki
 */
public class AcceptResults implements Command, Serializable {
    @Override
    public void Execute(Player player) {
        player.update(new MakeOrJoinGameState());
        player.opponent.update(new MakeOrJoinGameState());
    }
}
