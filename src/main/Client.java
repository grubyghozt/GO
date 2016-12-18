package main;

import main.Commands.*;
import main.States.ChooseDeadStonesAndTerritoriesState;
import main.States.MakeOrJoinGameState;
import main.States.NormalGameState;
import main.States.State;

import javax.imageio.IIOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Klasa klient
 */
public class Client {
    private GUI MyGui;
    private static int PORT = 8901;
    private Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;

    /**
     * konstruktor
     * @param ServerAddress
     */
    public Client(String ServerAddress){
        try {
            socket = new Socket(ServerAddress, PORT);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            MyGui= new GUI();
        }
        catch(IOException e){System.out.println("blad w tworzeniu klienta");}
        SetListeners();
    }
    /**
     * Funkcja odpowiedzialna za ustawienie komunikacji do servera
     */
    private void SetListeners(){
        MyGui.Board.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                if(MyGui.state instanceof NormalGameState){
                    SendCommand(new MakeMove((int)((double)x/(((double)MyGui.Board.getWidth()/(double)MyGui.size))),((int)((double)y/(((double)MyGui.Board.getHeight()/(double)MyGui.size))))));
                }
                else if(MyGui.state instanceof ChooseDeadStonesAndTerritoriesState){
                    SendCommand(new ChooseDeadAndTerritories((int)((double)x/(((double)MyGui.Board.getWidth()/(double)MyGui.size))),((int)((double)y/(((double)MyGui.Board.getHeight()/(double)MyGui.size))))));
                }
            }
        });
        MyGui.NewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { SendCommand(new NewGame()); }
        });
        MyGui.JoinGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SendCommand(new JoinGame());
            }
        });
        MyGui.ChoosePlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SendCommand(new ChoosePlayer());
            }
        });
        MyGui.ChooseBot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SendCommand(new ChooseBot());
            }
        });
        MyGui.Choose19x19.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { SendCommand(new ChooseSize(19)); }
        });
        MyGui.Choose13x13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { SendCommand(new ChooseSize(13)); }
        });
        MyGui.Choose9x9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { SendCommand(new ChooseSize(9)); }
        });
        MyGui.YouPlayAs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyGui.YouPlayAs.setVisible(false);
            }
        });
        MyGui.Pass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SendCommand(new Pass());
            }
        });
        MyGui.GiveUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SendCommand(new GiveUp());
            }
        });
        MyGui.ConfirmDead.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SendCommand(new ConfirmYourChoice());
            }
        });
        MyGui.Resume.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SendCommand(new Resume());
            }
        });
        MyGui.AcceptDead.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SendCommand(new ConfirmYourOpponentChoice());
            }
        });
        MyGui.RefuseDead.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SendCommand(new RefuseYourOpponentChoice());
            }
        });
        MyGui.AcceptResults.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SendCommand(new AcceptResults());
            }
        });

    }
    /**
     * Funkcja wysyłająca komendę do servera
     */
    private void SendCommand(Command command){
        try{
            out.reset();
            out.writeObject(command);
        }
        catch(IOException e){System.out.println("blad w kliencie w wysylaniu komendy");}

    }

    /**
     * Funkcja zmieniająca GUI na podstawie komendy gracza
     */
    private void update(){
            try{
                while(true) {
                    Object obj = (Object) in.readObject();
                    if (obj instanceof State) {
                        MyGui.SetState((State) obj);
                    } else if (obj instanceof Stone[][]) {
                        MyGui.Repaint((Stone[][]) obj);
                    }
                }
            }
            catch(IOException e){System.out.println("blad w kliencie - IO");}
            catch(ClassNotFoundException e){System.out.println("blad w kliencie - Class");}
            finally{
                try{
                    socket.close();
                    in.close();
                    out.close();
                }
                catch(Exception e){}
            }
    }
    public static void main(String[] args){
        Client newClient = new Client("localhost");
        newClient.MyGui.SetState(new MakeOrJoinGameState());
        newClient.update();
    }

}
