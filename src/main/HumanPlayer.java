package main;

import main.Commands.Command;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

/**
 * klasa reprezentująca gracza
 */
public class HumanPlayer extends Player implements Runnable{
    //public color color;
    private Socket socket;
    //public Game CurrentGame;
    //public Player opponent;
    ObjectInputStream in;
    ObjectOutputStream out;
    /**
     * konstruktor
     */
    public HumanPlayer(Socket newsocket){
        this.socket=newsocket;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        }
        catch(Exception e){System.out.println("blad w tworzeniu instancji gracza na serverze");}
    }
    /**
     * Funkcja wykonująca komendę
     */
    private void ExecuteCommand(Command command) {
        command.Execute(this);
    }

    /**
     * Funkcja przesyłająca wynik do klienta
     */
    public void update(Serializable object) {
        try {
            out.writeObject(object);
        }
        catch(Exception e){System.out.println("blad w przesylaniu wyniku klientowi");}
    }
    @Override
    public void run(){
        while (true){
            try{
                Command newCommand = (Command) in.readObject();
                ExecuteCommand(newCommand);
                //CurrentGame.player1.update(CurrentGame.player1.color);
                //CurrentGame.player2.update(CurrentGame.player2.color);
            }
            catch(ClassNotFoundException e){System.out.println("blad w watku uruchamiajacym komendy");}
            catch(IOException e){                System.out.print("aaaa");}
            }
    }
}
