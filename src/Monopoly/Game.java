package Monopoly;

import Monopoly.BoardBoxes.BoardBox;
import Monopoly.GUI.BoardGUI;
import Monopoly.GUI.PlayerGUI;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Scanner;

public abstract class Game {
    private static LinkedList<Player> players;
    private static Board board;
    private static int playerTurnIndex;
    private static BoardGUI boardGUI;

    public static void main(String[] args) {
        board = new Board();
        boardGUI = new BoardGUI();
        boardGUI.init(board);
        players = new LinkedList<>(){{
            add(new Player("player1", 1500, BoardToken.Hat, 0, new PlayerGUI(BoardToken.Hat,0)));
            add(new Player("player2", 1500, BoardToken.Dog, 0, new PlayerGUI(BoardToken.Dog,1)));
        }};

        for (Player player : players) {
            player.getPlayerGUI().setIconIn(boardGUI.getBoxPanel(0));
        }

        updateBoxPanelGUI(0);

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

    public static JPanel getBoardBoxPanelByIndex(int index){
        return boardGUI.getBoxPanel(index);
    }

    public static void updateBoxPanelGUI(int index){
        boardGUI.updateBoxPanel(index);
    }

    public static Player GetCurrentTurnPlayer(){ return players.get(playerTurnIndex); }

    public static void setNextTurn(){
        playerTurnIndex++;
        if (playerTurnIndex == players.size()) playerTurnIndex = 0;
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

    public static void goBankrupt(Player player){
        System.out.println(player.getName()+" entro en bancarrota");
        players.remove(player);
        checkForWinner();
    }

    private static void checkForWinner(){
        if (players.size() == 1){
            System.out.println("-----------------------------------------------------");
            System.out.println("El ganador es "+players.get(0).getName()+"!");
            System.out.println("-----------------------------------------------------");
            boardGUI.close();
        }
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