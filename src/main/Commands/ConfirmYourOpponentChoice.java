package main.Commands;

import main.Player;
import main.States.ChooseDeadStonesAndTerritoriesState;
import main.States.ResultState;

import java.io.Serializable;

/**
 * Akcja odopowiedzialna za zaakceptowanie wyboru przeciwnika
 */
public class ConfirmYourOpponentChoice implements Command, Serializable {
    @Override
    public void Execute(Player player) {
        player.CurrentGame.LocalModel.DeadAndTerritories++;
        if(player.CurrentGame.LocalModel.DeadAndTerritories == 1){
            player.update(new ChooseDeadStonesAndTerritoriesState());
        }
        else{
            if(player.CurrentGame.LocalModel.GetResults() == player.color){
                player.update(new ResultState("Victory"));
                player.opponent.update(new ResultState("Defeat"));
            }
            else{
                player.update(new ResultState("Defeat"));
                player.opponent.update(new ResultState("Victory"));
            }
        }
    }
}
