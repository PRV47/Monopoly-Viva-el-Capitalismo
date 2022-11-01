package Monopoly;

import Monopoly.BoardBoxes.BoardBox;
import Monopoly.GUI.GUIController;

import java.util.LinkedList;

public class Game {
    private static LinkedList<Player> players;
    private static Board board;
    private static int playerTurnIndex;
    private static GUIController gui;

    public static void main(String[] args) {
        board = new Board();
        gui = new GUIController();
        gui.initGUI(board);
        players = new LinkedList<>(){{
            add(new Player("player1", 1500, BoardToken.Hat, 0));
            add(new Player("player2", 1500, BoardToken.Dog, 0));
        }};

        int maxDices = 0;
        for (Player player : players) {
            player.throwDices();
            var dices = player.getDices();
            System.out.println("Dados de "+player.getName()+": "+dices[0]+" "+dices[1]);

            if (dices[0]+dices[1] > maxDices){
                maxDices = dices[0]+dices[1];
                playerTurnIndex = players.indexOf(player);
            }
        }

        playTurn();
    }

    public static BoardBox getBoardBoxByIndex(int index){
        return board.getBoardBoxes().get(index);
    }

    public static void setNextTurn(){
        playerTurnIndex++;
        if (playerTurnIndex == players.size()) playerTurnIndex = 0;
        playTurn();
    }

    private static void playTurn(){
        Player player = players.get(playerTurnIndex);
        System.out.println("Turno de "+player.getName());

        player.throwDices();
        int position = player.getPosition()+player.getSumDicesValue();
        player.moveTo(position, board.getBoardBoxes().get(position));
    }
}