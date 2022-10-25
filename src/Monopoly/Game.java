package Monopoly;

import Monopoly.BoardBoxes.BoardBox;

public class Game {
    private static Player[] players;
    private static Board board;
    private static int indexOfPlayerTurn;
    private static GUIController gui;

    public static void main(String[] args) {
        board = new Board();
        gui = new GUIController();
        gui.initGUI(board);
    }
}