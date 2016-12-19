package main.Commands;

import main.ServerSide.Player;

/**
 * Interface do komend wykonywanych na modelu
 */
public interface Command {
    public void Execute(Player player);
}
