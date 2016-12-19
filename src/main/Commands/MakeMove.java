package main.Commands;

import main.ServerSide.Player;
import main.States.NormalGameState;
import main.States.WaitingForOpponentState;
import java.io.Serializable;

/**
 * Akcja odopowiedzialna za wykonanie ruchu
 */
public class MakeMove implements Command, Serializable {
    private int x;
    private int y;
    public boolean valid = false;
    public MakeMove(int x, int y){
        this.x=x;
        this.y=y;
    }
    @Override
    public void Execute(Player player) {
        if(player.CurrentGame.LocalModel.MakeValidMove(x, y, player.color)){
            player.update(player.CurrentGame.LocalModel.GetBoard());
            player.opponent.update(player.CurrentGame.LocalModel.GetBoard());
            player.update(new WaitingForOpponentState());
            player.opponent.update(new NormalGameState());
            valid = true;
        }
    }
}
