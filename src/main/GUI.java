package main;

import main.States.State;

import javax.swing.*;
import java.awt.*;

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
        Board = new JPanel();
        //state = new State();
        NewGame = new Button();
        JoinGame = new Button();
        ChoosePlayer = new Button();
        ChooseBot = new Button();
        Choose19x19 = new Button();
        Choose13x13 = new Button();
        Choose9x9 = new Button();
        WaitingForOpponent = new Label();
        YouPlayAs = new Button();
        Pass = new Button();
        GiveUp = new Button();
        ChooseDead = new Label();
        ConfirmDead = new Button();
        Resume = new Button();
        AcceptDead = new Button();
        RefuseDead = new Button();
        AcceptResults = new Button();
    }
    /**
    * funkcja aktualizująca wygląd planszy
    */
    public void Repaint(Stone[][] board){

    }

    /**
     * funkcja zmieniająca stan GUI wraz z jego wyglądem
     */
    public void SetState(State newstate){
        if(this.state!=null) {
            this.state.EndState();
        }
        this.state = newstate;
        this.state.StartState();

    }

}
