package main.ServerSide;

import main.Stone;
import main.color;

import java.util.Arrays;

/**
 * Klasa odpowiedzialna za logike
 */
public class Model {
    public Stone[][] Board;
    private int Blackx=-1;
    private int Blacky=-1;
    private int Whitex=-1;
    private int Whitey=-1;
    public double whitescore = 6.5;
    public double blackscore = 0;
    public int pass = 0;
    public int DeadAndTerritories = 0;
    /**
     * konstruktor
     */
    public Model(int size){
        Board = new Stone[size][size];
        for (Stone[] row: Board) {
            Arrays.fill(row, Stone.Empty);
        }
    }
    /**
     * funkcja szukająca łańcuchów
     */
    public Stone[][] MakeChain(int x, int y,boolean[][] visited, Stone stone, Stone[][] chain){
        visited[x][y]=true;
        if(Board[x][y]==stone) {
            chain[x][y]=stone;
            if((x+1 < Board.length) && (!visited[x+1][y])) {
                MakeChain(x + 1, y, visited, stone, chain);
            }
            if((x-1 >= 0) && (!visited[x-1][y])) {
                MakeChain(x - 1, y, visited, stone, chain);
            }
            if((y+1 < Board.length) && (!visited[x][y+1])) {
                MakeChain(x, y + 1, visited, stone, chain);
            }
            if((y-1 >= 0) && (!visited[x][y-1])) {
                MakeChain(x, y - 1, visited, stone, chain);
            }
        }
        return chain;
    }
    /**
     * funkcja sprawdzajaca czy łańcuch ma dostęp do pola o danym parametrze
     * (sprawdza czy łańcuch ma oddech lub potencjalne terytorium jest otoczone przez tylko 1 kolor kamieni)
     */
    public boolean CheckChainForProperty(Stone[][] Chain, Stone property){
        for(int i = 0; i < Board.length; i++){
            for(int j = 0; j < Board.length; j++){
                if(Chain[i][j]!=null){
                    if((i+1 < Board.length && Board[i+1][j]==property)
                            || (i-1 >= 0 && Board[i-1][j]==property)
                            || (j+1 < Board.length && Board[i][j+1]==property)
                            || (j-1 >= 0 && Board[i][j-1]==property)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     *Funkcja, która zamienia łańcuch jednego typu na łańcuch innego typu (przydatne przy kasowaniu łancuchów, zaznaczaniu martwych kamieni itp.)
     */
    public void ChangeChainTo(Stone[][] Chain, Stone ChangeTo){
        for(int i = 0; i < Board.length; i++) {
            for (int j = 0; j < Board.length; j++) {
                if (Chain[i][j] != null) {
                    Board[i][j] = ChangeTo;
                }
            }
        }
    }
    /**
     * Funkcja licząca z lu elemntów składa się łańcuch (liczenie jeńców, terytoriów)
     */
    public int CountChainElements(Stone[][] Chain){
        int score=0;
        for(int i = 0; i < Board.length; i++) {
            for (int j = 0; j < Board.length; j++) {
                if (Chain[i][j] != null) {
                    score++;
                }
            }
        }
        return score;
    }
    /**
     * Funkcja, która wykonuje ruch, sprawdzając jego legalnosc
     */
    public boolean MakeValidMove(int x, int y, color player){
        if(Board [x][y]!=Stone.Empty){
            return false;
        }
        else if(player == player.Black){
            if((x == Blackx) && (y == Blacky)) {
                return false;
            }
            else{
                Board[x][y]=Stone.Black;
                if((x+1 < Board.length) && (Board[x+1][y] == Stone.White)){
                    Stone[][] tempChain = MakeChain(x+1, y, new boolean[Board.length][Board.length], Stone.White, new Stone[Board.length][Board.length]);
                    if(!CheckChainForProperty(tempChain, Stone.Empty)){
                        blackscore += CountChainElements(tempChain);
                        ChangeChainTo(tempChain, Stone.Empty);
                    }
                }
                if((x-1 >=0) && (Board[x-1][y] == Stone.White)){
                    Stone[][] tempChain = MakeChain(x-1, y, new boolean[Board.length][Board.length], Stone.White, new Stone[Board.length][Board.length]);
                    if(!CheckChainForProperty(tempChain, Stone.Empty)){
                        blackscore += CountChainElements(tempChain);
                        ChangeChainTo(tempChain, Stone.Empty);
                    }
                }
                if((y+1 < Board.length) && (Board[x][y+1] == Stone.White)){
                    Stone[][] tempChain = MakeChain(x, y+1, new boolean[Board.length][Board.length], Stone.White, new Stone[Board.length][Board.length]);
                    if(!CheckChainForProperty(tempChain, Stone.Empty)){
                        blackscore += CountChainElements(tempChain);
                        ChangeChainTo(tempChain, Stone.Empty);
                    }
                }
                if((y-1 >= 0) && (Board[x][y-1] == Stone.White)){
                    Stone[][] tempChain = MakeChain(x, y-1, new boolean[Board.length][Board.length], Stone.White, new Stone[Board.length][Board.length]);
                    if(!CheckChainForProperty(tempChain, Stone.Empty)){
                        blackscore += CountChainElements(tempChain);
                        ChangeChainTo(tempChain, Stone.Empty);
                    }
                }
                if(!CheckChainForProperty(MakeChain(x, y, new boolean[Board.length][Board.length], Stone.Black, new Stone[Board.length][Board.length]),Stone.Empty)){
                    Board[x][y]=Stone.Empty;
                    return false;
                }
            }
            Blackx=x;
            Blacky=y;
        }
        else if(player == player.White){
            if((x == Whitex) && (y == Whitey)){
                return false;
            }
            else{
                Board[x][y]=Stone.White;
                if((x+1 < Board.length) && (Board[x+1][y] == Stone.Black)){
                    Stone[][] tempChain = MakeChain(x+1, y, new boolean[Board.length][Board.length], Stone.Black, new Stone[Board.length][Board.length]);
                    if(!CheckChainForProperty(tempChain, Stone.Empty)){
                        whitescore += CountChainElements(tempChain);
                        ChangeChainTo(tempChain, Stone.Empty);
                    }
                }
                if((x-1 >=0) && (Board[x-1][y] == Stone.Black)){
                    Stone[][] tempChain = MakeChain(x-1, y, new boolean[Board.length][Board.length], Stone.Black, new Stone[Board.length][Board.length]);
                    if(!CheckChainForProperty(tempChain, Stone.Empty)){
                        whitescore += CountChainElements(tempChain);
                        ChangeChainTo(tempChain, Stone.Empty);
                    }
                }
                if((y+1 < Board.length) && (Board[x][y+1] == Stone.Black)){
                    Stone[][] tempChain = MakeChain(x, y+1, new boolean[Board.length][Board.length], Stone.Black, new Stone[Board.length][Board.length]);
                    if(!CheckChainForProperty(tempChain, Stone.Empty)){
                        whitescore += CountChainElements(tempChain);
                        ChangeChainTo(tempChain, Stone.Empty);
                    }
                }
                if((y-1 >= 0) && (Board[x][y-1] == Stone.Black)){
                    Stone[][] tempChain = MakeChain(x, y-1, new boolean[Board.length][Board.length], Stone.Black, new Stone[Board.length][Board.length]);
                    if(!CheckChainForProperty(tempChain, Stone.Empty)){
                        whitescore += CountChainElements(tempChain);
                        ChangeChainTo(tempChain, Stone.Empty);
                    }
                }
                if(!CheckChainForProperty(MakeChain(x, y, new boolean[Board.length][Board.length], Stone.White, new Stone[Board.length][Board.length]),Stone.Empty)){
                    Board[x][y]=Stone.Empty;
                    return false;
                }
            }
            Whitex=x;
            Whitey=y;
        }
        pass=0;
        return true;
    }
    /**
     * Funkcja odpowiadająca za przetwarzanie informacji o opuszczonych ruchach
     */
    public int pass(){
        pass++;
        return pass;
    }
    /**
     * Funkcja odpowiedzialna za ustawianie martwych kamieni i terytoriów
     */
    public void SetDeadAndTerritories(int x, int y, color player){
        if(player == color.Black){
            if(Board[x][y] == Stone.White){
                ChangeChainTo(MakeChain(x, y, new boolean[Board.length][Board.length], Stone.White, new Stone[Board.length][Board.length]),Stone.WhiteDead);
            }
            else if(Board[x][y] == Stone.WhiteDead){
                ChangeChainTo(MakeChain(x, y, new boolean[Board.length][Board.length], Stone.WhiteDead, new Stone[Board.length][Board.length]),Stone.White);
            }
            else if(Board[x][y] == Stone.Empty){
                ChangeChainTo(MakeChain(x, y, new boolean[Board.length][Board.length], Stone.Empty, new Stone[Board.length][Board.length]),Stone.BlackTerritory);
            }
            else if(Board[x][y] == Stone.BlackTerritory){
                ChangeChainTo(MakeChain(x, y, new boolean[Board.length][Board.length], Stone.BlackTerritory, new Stone[Board.length][Board.length]),Stone.Empty);
            }
        }
        else if(player == color.White){
            if(Board[x][y] == Stone.Black){
                ChangeChainTo(MakeChain(x, y, new boolean[Board.length][Board.length], Stone.Black, new Stone[Board.length][Board.length]),Stone.BlackDead);
            }
            else if(Board[x][y] == Stone.BlackDead){
                ChangeChainTo(MakeChain(x, y, new boolean[Board.length][Board.length], Stone.BlackDead, new Stone[Board.length][Board.length]),Stone.Black);
            }
            else if(Board[x][y] == Stone.Empty){
                ChangeChainTo(MakeChain(x, y, new boolean[Board.length][Board.length], Stone.Empty, new Stone[Board.length][Board.length]),Stone.WhiteTerritory);
            }
            else if(Board[x][y] == Stone.WhiteTerritory){
                ChangeChainTo(MakeChain(x, y, new boolean[Board.length][Board.length], Stone.WhiteTerritory, new Stone[Board.length][Board.length]),Stone.Empty);
            }
        }
    }
    /**
     * Funkcja odpowiedzialna za odrzucanie decyzji o martwych kamienaich i terytoriach
     */
    public void ClearBoard(){
        for(int i = 0; i < Board.length; i++) {
            for (int j = 0; j < Board.length; j++) {
                if (Board[i][j] == Stone.BlackDead) {
                    Board[i][j] = Stone.Black;
                }
                else if (Board[i][j] == Stone.WhiteDead) {
                    Board[i][j] = Stone.White;
                }
                else if (Board[i][j] == Stone.BlackTerritory || Board[i][j] == Stone.WhiteTerritory) {
                    Board[i][j] = Stone.Empty;
                }
            }
        }
    }
    /**
     * Funkcja odpowiedzialna za ustawienie domyślnych martwych kamieni oraz terytoriów
     */
    public void SetDefault(){
        for(int i = 0; i < Board.length; i++) {
            for (int j = 0; j < Board.length; j++) {
                if (Board[i][j] == Stone.Empty) {
                    Stone[][] tempChain = MakeChain(i, j, new boolean[Board.length][Board.length], Stone.Empty, new Stone[Board.length][Board.length]);
                    if(CheckChainForProperty(tempChain, Stone.Black) && !CheckChainForProperty(tempChain, Stone.White)){
                        ChangeChainTo(tempChain, Stone.BlackTerritory);
                    }
                    else if(!CheckChainForProperty(tempChain, Stone.Black) && CheckChainForProperty(tempChain, Stone.White)){
                        ChangeChainTo(tempChain, Stone.WhiteTerritory);
                    }
                }
            }
        }
    }
    /**
     * Funkcja podliczająca wynik
     */
    public color GetResults(){
        for(int i = 0; i < Board.length; i++) {
            for (int j = 0; j < Board.length; j++) {
                if (Board[i][j] == Stone.WhiteTerritory) {
                    whitescore++;
                }
                else if(Board[i][j] == Stone.BlackTerritory) {
                    blackscore++;
                }
            }
        }
        if(whitescore > blackscore){
            return color.White;
        }
        else{
            return color.Black;
        }
    }
    /**
     * Funkcja zwracająca informacje o rozgrywce
     */
    public Stone[][] GetBoard(){
        return Board;
    }
}
