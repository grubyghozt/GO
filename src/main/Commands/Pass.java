package main.Commands;

import main.ServerSide.Player;
import main.States.ChooseDeadStonesAndTerritoriesState;
import main.States.NormalGameState;
import main.States.WaitingForOpponentState;

import java.io.Serializable;

/**
 * Akcja odopowiedzialna za zrezygnowanie z ruchu
 */
public class Pass implements Command, Serializable {
    @Override
    public void Execute(Player player) {
        if(player.CurrentGame.LocalModel.pass() == 2){
            player.CurrentGame.LocalModel.pass = 0;
            player.CurrentGame.LocalModel.SetDefault();
            player.update(player.CurrentGame.LocalModel.GetBoard());
            player.opponent.update(player.CurrentGame.LocalModel.GetBoard());
            player.update(new WaitingForOpponentState());
            player.opponent.update(new ChooseDeadStonesAndTerritoriesState());
        }
        else{
            player.update(new WaitingForOpponentState());
            player.opponent.update(new NormalGameState());
        }
    }
}
