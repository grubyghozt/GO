package main.Commands;

import main.BOT;
import main.Player;
import main.States.NormalGameState;
import main.States.OpponentFoundState;
import main.States.WaitingForOpponentState;
import main.color;

import java.io.Serializable;
import java.util.Random;

/**
 * Akcja odopowiedzialna za sparowanie z Botem
 */
public class ChooseBot implements Command, Serializable {
    private static Random generator = new Random();
    @Override
    public void Execute(Player player) {
        player.CurrentGame.player2 = new BOT();
        player.opponent=player.CurrentGame.player2;
        player.opponent.CurrentGame=player.CurrentGame;
        player.opponent.opponent=player;
        if(generator.nextBoolean()){
            player.color = color.Black;
            player.opponent.color = color.White;
        }
        else{
            player.color = color.White;
            player.opponent.color = color.Black;
        }
        player.update(new OpponentFoundState(player.color, player.CurrentGame.LocalModel.GetBoard().length));
        player.opponent.update(new OpponentFoundState(player.opponent.color, player.CurrentGame.LocalModel.GetBoard().length));
        player.update(player.CurrentGame.LocalModel.GetBoard());
        player.opponent.update(player.CurrentGame.LocalModel.GetBoard());
        if(player.color == color.Black){
            player.update(new NormalGameState());
            player.opponent.update(new WaitingForOpponentState());
        }
        else if(player.color == color.White){
            player.update(new WaitingForOpponentState());
            player.opponent.update(new NormalGameState());
        }
    }
}
