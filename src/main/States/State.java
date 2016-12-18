package main.States;

import main.GUI;
import main.Player;

/**
 * Interface do stanów opisujących wygląd GUI i zachowania Bota
 */
public interface State {
    /**
     * metoda wykonywana gdy GUI przechodzi w dany stan
     */
    void StartState(GUI gui);

    /**
     * metda wykonywana gdy GUI wychodzi z danego stanu
     */
    void EndState(GUI gui);
    /**
     * metoda wykonywana przez Bota
     */
    void BotDo(Player player);
}
