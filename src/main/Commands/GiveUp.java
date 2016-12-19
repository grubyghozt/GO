package main.Commands;

import main.ServerSide.Player;
import main.States.ResultState;

import java.io.Serializable;

/**
 * Akcja odopowiedzialna za poddanie się
 */
public class GiveUp implements Command, Serializable {
    @Override
    public void Execute(Player player) {
        player.update(new ResultState("Defeat"));
        player.opponent.update(new ResultState("Victory"));
    }
}
