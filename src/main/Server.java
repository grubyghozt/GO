package main;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Klasa Server
 */
public class Server {
    public static List<Game> ListOfGames = Collections.synchronizedList(new ArrayList<Game>());
    public static void main(String[] args){
        try {
            ServerSocket Listener = new ServerSocket(8901);
            while (true){
                HumanPlayer newPlayer = new HumanPlayer(Listener.accept());
                new Thread(newPlayer).start();
            }
        }
        catch (Exception e){System.out.println("blad w serverze");}
    }

}
