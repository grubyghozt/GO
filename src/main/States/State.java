package main.States;

import main.Player;

/**
 * Interface do stanów opisujących wygląd GUI i zachowania Bota
 */
public interface State {
    /**
     * metoda wykonywana gdy GUI przechodzi w dany stan
     */
    void StartState();

    /**
     * metda wykonywana gdy GUI wychodzi z danego stanu
     */
    void EndState();
    /**
     * metoda wykonywana przez Bota
     */
    void BotDo(Player player);
}
