package main.Commands;

import main.*;
import main.States.NormalGameState;
import main.States.OpponentFoundState;
import main.States.WaitingForOpponentState;

import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Akcja odpowiedzialna za dołączenie do istniejącej gry
 */
public class JoinGame implements Command, Serializable {
    private static Random generator = new Random();
    @Override
    public void Execute(Player player) {
        player.update(new WaitingForOpponentState());
        while(true) {
            for (Game game : Server.ListOfGames) {
                if (game.freespace) {
                    game.freespace=false;
                    game.player2 = player;
                    game.player2.CurrentGame=game;
                    game.player2.opponent=game.player1;
                    game.player1.opponent=game.player2;
                    if(generator.nextBoolean()){
                        game.player1.color = color.Black;
                        game.player2.color = color.White;
                    }
                    else{
                        game.player1.color = color.White;
                        game.player2.color = color.Black;
                    }
                    game.player1.update(new OpponentFoundState(game.player1.color, game.LocalModel.GetBoard().length));
                    game.player2.update(new OpponentFoundState(game.player2.color, game.LocalModel.GetBoard().length));
                    game.player1.update(game.LocalModel.GetBoard());
                    game.player2.update(game.LocalModel.GetBoard());
                    if(game.player1.color == color.Black){
                        game.player1.update(new NormalGameState());
                        game.player2.update(new WaitingForOpponentState());
                    }
                    else if(game.player1.color == color.White){
                        game.player1.update(new WaitingForOpponentState());
                        game.player2.update(new NormalGameState());
                    }
                    return;
                }
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            }
            catch(InterruptedException e){System.out.println("blad w wyszukiwaniu wolnej gry");}
        }
    }
}
