package main.Commands;

import main.Player;
import main.States.NormalGameState;
import main.States.WaitingForOpponentState;

import java.io.Serializable;

/**
 * Akcja odopowiedzialna za wnowienie gry
 */
public class Resume implements Command, Serializable {
    @Override
    public void Execute(Player player) {
        player.CurrentGame.LocalModel.DeadAndTerritories = 0;
        player.CurrentGame.LocalModel.ClearBoard();
        player.update(new WaitingForOpponentState());
        player.opponent.update(new NormalGameState());
    }
}
