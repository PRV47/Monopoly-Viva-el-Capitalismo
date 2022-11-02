package Monopoly;

import Monopoly.BoardBoxes.BoardBox;
import Monopoly.GUI.BoardGUI;

import java.util.LinkedList;
import java.util.Scanner;

public class Game {
    private static LinkedList<Player> players;
    private static Board board;
    private static int playerTurnIndex;
    private static BoardGUI gui;

    public static void main(String[] args) {
        board = new Board();
        gui = new BoardGUI();
        gui.initGUI(board);
        players = new LinkedList<>(){{
            add(new Player("player1", 1500, BoardToken.Hat, 0));
            add(new Player("player2", 1500, BoardToken.Dog, 0));
        }};

        int maxDices = 0;
        for (Player player : players) {
            pressEnterToContinue(player.getName()+" presiona enter para lanzar dados");
            player.throwDices();
            System.out.println();
            var dices = player.getDices();

            if (dices[0]+dices[1] > maxDices){
                maxDices = dices[0]+dices[1];
                playerTurnIndex = players.indexOf(player);
            }
        }
        System.out.println();
        showMenu();
    }

    public static BoardBox getBoardBoxByIndex(int index){
        return board.getBoardBoxes().get(index);
    }

    public static void setNextTurn(){
        playerTurnIndex++;
        if (playerTurnIndex == players.size()) playerTurnIndex = 0;
        System.out.println("canMove: "+players.get(playerTurnIndex).canMove());
        showMenu();
    }

    public static void printPlayersInfo(){
        for (Player player : players) {
            System.out.println(player.toString());
        }
    }

    public static void throwActualPlayerDices(){
        Player player = players.get(playerTurnIndex);
        player.throwDices();

        if (player.canMove()) {
            int position = player.getPosition() + player.getSumDicesValue();
            if (position >= board.getBoardBoxes().size())
                position = (board.getBoardBoxes().size() - 1) - player.getPosition() + player.getSumDicesValue();
            player.moveTo(position);
        }
        else {
            if (player.getDices()[0] == player.getDices()[1]) player.setCanMove(true);
        }
    }

    public static void printActualPlayerEstates(){
        Player player = players.get(playerTurnIndex);
       player.getEstates().forEach(e -> System.out.println(e.getName()));
    }

    private static void pressEnterToContinue(String message){
        System.out.println(message);
        new Scanner(System.in).nextLine();
    }

    private static void showMenu(){
        Player player = players.get(playerTurnIndex);
        System.out.println("-----------------------------------------------------");
        System.out.println("Turno de "+player.getName());
        System.out.println("-----------------------------------------------------");
        System.out.println();
        if (players.get(playerTurnIndex).canMove()) IOController.showMenu();
        else IOController.showJailOptions(players.get(playerTurnIndex));
    }
}