package main.States;

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
    public void StartState() {

    }

    @Override
    public void EndState() {

    }

    @Override
    public void BotDo(Player player) {

    }
}
