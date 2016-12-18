package main.States;

import main.GUI;
import main.Player;

import java.io.Serializable;

/**
 * Stan GUI gdy zakończy sie rogrywka (wyświetlany jest wynik)
 */
public class ResultState implements State, Serializable {
    String Result;
    public ResultState(String result){
        this.Result=result;
    }
    @Override
    public void StartState(GUI gui) {
        gui.YouPlayAs.setVisible(false);
        gui.GiveUp.setVisible(false);
        gui.Board.setVisible(false);
        gui.AcceptResults.setLabel(Result);
        gui.AcceptResults.setVisible(true);
    }

    @Override
    public void EndState(GUI gui) {
        gui.AcceptResults.setVisible(false);
    }

    @Override
    public void BotDo(Player player) {

    }
}
