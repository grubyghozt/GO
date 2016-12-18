package main.States;

import main.Commands.MakeMove;
import main.Commands.Pass;
import main.GUI;
import main.Player;

import java.io.Serializable;

/**
 * Stan GUI podczas początku ruchu gracza (użycie akcji jak pasowanie, ustalanie martwych kamieni zmienia stan GUI)
 */
public class NormalGameState implements State, Serializable {
    @Override
    public void StartState(GUI gui) {
        gui.Pass.setVisible(true);
    }

    @Override
    public void EndState(GUI gui) {
        gui.Pass.setVisible(false);
    }

    @Override
    public void BotDo(Player player) {
        for(int i = 0; i < player.CurrentGame.LocalModel.GetBoard().length; i++){
            for(int j = 0; j < player.CurrentGame.LocalModel.GetBoard().length; j++){
                MakeMove temp = new MakeMove(i, j);
                temp.Execute(player);
                if(temp.valid){
                    return;
                }
            }
        }
        new Pass().Execute(player);
    }
}
