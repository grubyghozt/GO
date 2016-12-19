package main.Commands;

import main.ServerSide.Player;
import main.States.ChooseDeadStonesAndTerritoriesState;
import main.States.WaitingForOpponentState;

import java.io.Serializable;

/**
 * Akcja odopowiedzialna za odrzucenie wyboru przeciwnika
 */
public class RefuseYourOpponentChoice implements Command, Serializable {
    @Override
    public void Execute(Player player) {
        player.CurrentGame.LocalModel.DeadAndTerritories = 0;
        player.CurrentGame.LocalModel.ClearBoard();
        player.CurrentGame.LocalModel.SetDefault();
        player.update(player.CurrentGame.LocalModel.GetBoard());
        player.opponent.update(player.CurrentGame.LocalModel.GetBoard());
        player.update(new WaitingForOpponentState());
        player.opponent.update(new ChooseDeadStonesAndTerritoriesState());
    }
}
