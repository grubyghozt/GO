package main.Commands;

import main.ServerSide.Player;

import java.io.Serializable;

/**
 * Akcja odopowiedzialna za wybieranie martwych kamieni oraz terytoriów
 */
public class ChooseDeadAndTerritories implements Command, Serializable {
    private int x;
    private int y;
    public ChooseDeadAndTerritories(int x, int y){
        this.x=x;
        this.y=y;
    }
    @Override
    public void Execute(Player player) {
        player.CurrentGame.LocalModel.SetDeadAndTerritories(x, y, player.color);
        player.update(player.CurrentGame.LocalModel.GetBoard());
    }
}
