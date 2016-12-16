package main.Commands;

import main.Model;
import main.Player;
import main.States.ChooseOpponentState;

import java.io.Serializable;

/**
 * Akcja odopowiedzialna za utworzenie planszy o podanym rozmiarze
 */
public class ChooseSize implements Command, Serializable {
    private int size;
    public ChooseSize(int size){
        this.size=size;
    }
    @Override
    public void Execute(Player player) {
        player.CurrentGame.LocalModel = new Model(size);
        player.update(new ChooseOpponentState());
    }
}
