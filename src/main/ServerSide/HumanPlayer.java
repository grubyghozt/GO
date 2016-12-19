package main.ServerSide;

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
    private Socket socket;
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
        catch(IOException e){System.out.println("blad w tworzeniu instancji gracza na serverze");}
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
            out.reset();
            out.writeObject(object);
        }
        catch(IOException e ){System.out.println("blad w przesylaniu wyniku klientowi");}
    }
    @Override
    public void run(){

            try{
                while (true) {
                    Command newCommand = (Command) in.readObject();
                    ExecuteCommand(newCommand);
                }
            }
            catch(ClassNotFoundException e){System.out.println("blad w watku uruchamiajacym komendy - Class");}
            catch(IOException e){System.out.print("blad w watku uruchamiajacym komendy - IO");}
            finally{
                try{
                    socket.close();
                    in.close();
                    out.close();
                }
                catch(Exception e){}
            }
    }
}
