package main;

import main.States.State;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Interfejs użytkownika
 */
public class GUI extends JFrame{
    /**
     * panel na którym będzie rysowana plansza
     */
    public JPanel Board;
    /**
     * zmienna przechowująca w jakim stanie znajduje się GUI
     */
    public State state;
    /**
     * zmienna przehowująca rozmiar planszy
     */
    public int size;
    /**
     * Przyciski, powiadomienia itp. wyświetlane w różnych stanach GUI
     */
    public Button NewGame;
    public Button JoinGame;
    public Button ChoosePlayer;
    public Button ChooseBot;
    public Button Choose19x19;
    public Button Choose13x13;
    public Button Choose9x9;
    public Label WaitingForOpponent;
    public Button YouPlayAs;
    public Button Pass;
    public Button GiveUp;
    public Label ChooseDead;
    public Button ConfirmDead;
    public Button Resume;
    public Button AcceptDead;
    public Button RefuseDead;
    public Button AcceptResults;
    /**
     * konstruktor GUI
     */
    public GUI(){
        setSize(800,800);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Board = new JPanel();
        add(Board);
        Board.setLocation(0,0);
        Board.setVisible(false);

        //ImageIcon background = new ImageIcon("Textures/Board19x19.png");
        //JLabel icon = new JLabel("aasdasdas");
        //icon.setSize(400,400);
        //icon.setIcon(background);
        //add(icon);
        //icon.setVisible(true);
        //icon.setLocation(400,400);

        NewGame = new Button("New Game");
        NewGame.setSize(400,400);
        add(NewGame);
        NewGame.setLocation(0,0);
        NewGame.setVisible(false);

        JoinGame = new Button("Join Game");
        JoinGame.setSize(400,400);
        add(JoinGame);
        JoinGame.setLocation(400,0);
        JoinGame.setVisible(false);

        ChoosePlayer = new Button("Choose Player");
        ChoosePlayer.setSize(400,400);
        add(ChoosePlayer);
        ChoosePlayer.setLocation(0,0);
        ChoosePlayer.setVisible(false);

        ChooseBot = new Button("Choose Bot");
        ChooseBot.setSize(400,400);
        add(ChooseBot);
        ChooseBot.setLocation(400,0);
        ChooseBot.setVisible(false);

        Choose19x19 = new Button("19x19");
        Choose19x19.setSize(267, 400);
        add(Choose19x19);
        Choose19x19.setLocation(0,0);
        Choose19x19.setVisible(false);

        Choose13x13 = new Button("13x13");
        Choose13x13.setSize(267,400);
        add(Choose13x13);
        Choose13x13.setLocation(267,0);
        Choose13x13.setVisible(false);

        Choose9x9 = new Button("9x9");
        Choose9x9.setSize(266,400);
        add(Choose9x9);
        Choose9x9.setLocation(534,0);
        Choose9x9.setVisible(false);

        WaitingForOpponent = new Label("Waiting For Opponent");
        WaitingForOpponent.setSize(200, 50);
        add(WaitingForOpponent);
        WaitingForOpponent.setLocation(300,0);
        WaitingForOpponent.setVisible(false);

        YouPlayAs = new Button();
        YouPlayAs.setSize(200, 100);
        add(YouPlayAs);
        YouPlayAs.setLocation(300,350);
        YouPlayAs.setVisible(false);

        Pass = new Button("Pass");
        Pass.setSize(100, 100);
        add(Pass);
        Pass.setLocation(0,400);
        Pass.setVisible(false);


        GiveUp = new Button("Give Up");
        GiveUp.setSize(100, 100);
        add(GiveUp);
        GiveUp.setLocation(100,400);
        GiveUp.setVisible(false);

        ChooseDead = new Label("Choose Dead Stones and Territories");
        ChooseDead.setSize(200, 50);
        add(ChooseDead);
        ChooseDead.setLocation(300,0);
        ChooseDead.setVisible(false);

        ConfirmDead = new Button("Confirm your choice");
        ConfirmDead.setSize(100, 100);
        add(ConfirmDead);
        ConfirmDead.setLocation(0,400);
        ConfirmDead.setVisible(false);

        Resume = new Button("Resume");
        Resume.setSize(100, 100);
        add(Resume);
        Resume.setLocation(200,400);
        Resume.setVisible(false);

        AcceptDead = new Button("Accept");
        AcceptDead.setSize(100, 100);
        add(AcceptDead);
        AcceptDead.setLocation(0,400);
        AcceptDead.setVisible(false);

        RefuseDead = new Button("Refuse");
        RefuseDead.setSize(100, 100);
        add(RefuseDead);
        RefuseDead.setLocation(200,400);
        RefuseDead.setVisible(false);

        AcceptResults = new Button();
        AcceptResults.setSize(200, 200);
        add(AcceptResults);
        AcceptResults.setLocation(300,300);
        setVisible(true);
        AcceptResults.setVisible(false);
    }
    /**
    * funkcja aktualizująca wygląd planszy
    */
    public void Repaint(Stone[][] board){
        Board.removeAll();
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == Stone.White) {
                    ImageIcon stone = new ImageIcon("Textures/White.png");
                    JLabel icon = new JLabel();
                    icon.setIcon(stone);
                    Board.add(icon);
                    icon.setLocation(i*20, j*20);
                }
                else if (board[i][j] == Stone.WhiteTerritory) {
                    ImageIcon stone = new ImageIcon("Textures/WhiteTerritory.png");
                    JLabel icon = new JLabel();
                    icon.setIcon(stone);
                    Board.add(icon);
                    icon.setLocation(i*20, j*20);
                }
                else if (board[i][j] == Stone.WhiteDead) {
                    ImageIcon stone = new ImageIcon("Textures/WhiteDead.png");
                    JLabel icon = new JLabel();
                    icon.setIcon(stone);
                    Board.add(icon);
                    icon.setLocation(i*20, j*20);
                }
                else if (board[i][j] == Stone.Black) {
                    ImageIcon stone = new ImageIcon("Textures/Black.png");
                    JLabel icon = new JLabel();
                    icon.setIcon(stone);
                    Board.add(icon);
                    icon.setLocation(i*20, j*20);
                }
                else if (board[i][j] == Stone.BlackDead) {
                    ImageIcon stone = new ImageIcon("Textures/BlackDead.png");
                    JLabel icon = new JLabel();
                    icon.setIcon(stone);
                    Board.add(icon);
                    icon.setLocation(i*20, j*20);
                }
                else if (board[i][j] == Stone.BlackTerritory) {
                    ImageIcon stone = new ImageIcon("Textures/BlackTerritory.png");
                    JLabel icon = new JLabel();
                    icon.setIcon(stone);
                    Board.add(icon);
                    icon.setLocation(i*20, j*20);
                }
            }
        }
        if(size == 9){
            ImageIcon background = new ImageIcon("Textures/Board9x9.png");
            JLabel icon = new JLabel();
            icon.setSize(179,179);
            icon.setIcon(background);
            Board.add(icon);
            icon.setLocation(0, 0);
        }
        else if(size == 13){
            ImageIcon background = new ImageIcon("Textures/Board13x13.png");
            JLabel icon = new JLabel();
            icon.setSize(259,259);
            icon.setIcon(background);
            Board.add(icon);
            icon.setLocation(0, 0);
        }
        else if(size == 19){
            ImageIcon background = new ImageIcon("Textures/Board19x19.png");
            JLabel icon = new JLabel();
            icon.setSize(379,379);
            icon.setIcon(background);
            Board.add(icon);
            icon.setLocation(0, 0);
        }
        Board.repaint();
    }

    /**
     * funkcja zmieniająca stan GUI wraz z jego wyglądem
     */
    public void SetState(State newstate){
        if(this.state!=null) {
            this.state.EndState(this);
        }
        this.state = newstate;
        this.state.StartState(this);

    }

}
