package Monopoly;

import Monopoly.BoardBoxes.BoardBox;
import Monopoly.GUI.BoardGUI;
import Monopoly.GUI.PlayerGUI;

import javax.swing.*;
import java.util.LinkedList;

public abstract class Game {
    private static LinkedList<Player> players;
    private static Board board;
    private static int playerTurnIndex;
    private static BoardGUI boardGUI;

    public static void main(String[] args) {
        startGame();
    }

    private static void startGame(){
        board = new Board();
        boardGUI = new BoardGUI();
        players = new LinkedList<>();
        boardGUI.init(board);

        initPlayers();
        updatePlayersMoneyInfo();
        updateBoxPanelGUI(0);
        getFirstTurn();
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
    public static void updatePlayersMoneyInfo(){ boardGUI.updatePlayersMoneyInfo(players); }

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
                position = Math.floorMod(position, board.getBoardBoxes().size());
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

    private static void showMenu(){
        Player player = players.get(playerTurnIndex);
        System.out.println("-----------------------------------------------------");
        System.out.println("Turno de "+player.getName());
        System.out.println("-----------------------------------------------------");
        System.out.println();
        if (players.get(playerTurnIndex).canMove()) IOController.showMenu();
        else IOController.showJailOptions(players.get(playerTurnIndex));
    }

    private static void getFirstTurn(){
        System.out.println("El jugador que obtenga el numero de dados mas alto es el que comenzara");
        int maxDices = 0;
        for (Player player : players) {
            IOController.pressEnterToContinue(player.getName()+" presiona enter para lanzar dados");
            player.throwDices();
            System.out.println();
            var dices = player.getDices();

            if (dices[0]+dices[1] > maxDices){
                maxDices = dices[0]+dices[1];
                playerTurnIndex = players.indexOf(player);
            }
        }
        System.out.println();
    }

    private static void initPlayers(){
        int playersQuantity = IOController.askForPlayersQuantity();
        for (int i = 0; i < playersQuantity; i++){
            String name = IOController.askForPlayerName("Escriba el nombre del jugador "+(i+1));
            BoardToken token = IOController.askForPlayerToken("Elija la ficha para "+name);
            players.add(new Player(name, 200, token, 0, new PlayerGUI(token)));
            players.get(i).getPlayerGUI().setIconIn(boardGUI.getBoxPanel(0));
        }
    }
}