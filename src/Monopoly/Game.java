package Monopoly;

import Monopoly.BoardBoxes.BoardBox;
import Monopoly.BoardBoxes.EstateBox;
import Monopoly.Estates.Estate;

public class Game {
    private static Player[] players;
    private static Board board;
    private static int indexOfPlayerTurn;
    private static GUIController gui;

    public static void main(String[] args) {
        var boardBoxes = new BoardBox[40];

        for (int i = 0; i < boardBoxes.length; i++){
            boardBoxes[i] = new EstateBox(i, new Estate("estate"+i, 100, 50));
        }

        board = new Board(boardBoxes);
        gui = new GUIController();
        gui.initGUI(board);
    }
}
