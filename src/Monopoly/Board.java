package Monopoly;

import Monopoly.BoardBoxes.BoardBox;

public class Board {
    private BoardBox[] boardBoxes;

    public Board(BoardBox[] boardBoxes) {
        this.boardBoxes = boardBoxes;
    }

    public BoardBox[] getBoardBoxes() {
        return boardBoxes;
    }
}
