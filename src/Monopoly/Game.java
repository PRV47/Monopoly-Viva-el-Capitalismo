package Monopoly;

import Monopoly.BoardBoxes.BoardBox;
import Monopoly.GUI.BoardGUI;
import Monopoly.GUI.PlayerGUI;

import javax.swing.*;
import java.util.LinkedList;

/**
 * Clase con las principales funcionalidades del juego. Contiene el tablero, los jugadores, maneja los turnos,
 * chequea quien gana y quien pierde, maneja la GUI y los llamados por consola.
 */
public abstract class Game {
    private static LinkedList<Player> players;
    private static Board board;
    private static int playerTurnIndex;
    private static BoardGUI boardGUI;

    public static void main(String[] args) {
        startGame();
    }

    /**
     * Este metodo se encarga de dar comienzo al juego
     */
    private static void startGame(){
        board = new Board();
        boardGUI = new BoardGUI();
        players = new LinkedList<>();
        board.initBoardBoxes();
        boardGUI.init(board);

        initPlayers();
        updatePlayersMoneyInfo();
        updateBoxPanelGUI(0);
        getFirstTurn();
        showMenu();
    }

    /**
     * Este metodo se encarga de acceder a un casillero dado
     * @param index Recibe el indice del casillero a buscar
     * @return BoardBox con el indice dado
     */
    public static BoardBox getBoardBoxByIndex(int index){
        return board.getBoardBoxes()[index];
    }

    /**
     * Este metodo se encarga de acceder a la interfaz grafica de un casillero dado
     * @param index Recibe el indice del casillero a buscar
     * @return JPanel del casillero con el indice dado
     */
    public static JPanel getBoardBoxPanelByIndex(int index){
        return boardGUI.getBoxPanel(index);
    }

    /**
     * Se encarga de actualizar la interfaz grafica del casillero dado
     * @param index Recibe el indice del casillero a actualizar
     */
    public static void updateBoxPanelGUI(int index){
        boardGUI.updateBoxPanel(index);
    }

    /**
     * Se encarga de actualizar la interfaz grafica del dinero de los jugadores
     */
    public static void updatePlayersMoneyInfo(){ boardGUI.updatePlayersMoneyInfo(players); }

    /**
     * @return Player cuyo turno es el actual
     */
    public static Player GetCurrentTurnPlayer(){ return players.get(playerTurnIndex); }

    /**
     * Este metodo otorga el turno al proximo jugador
     */
    public static void setNextTurn(){
        playerTurnIndex++;
        if (playerTurnIndex == players.size()) playerTurnIndex = 0;
        showMenu();
    }

    /**
     * Este metodo imprime por pantalla informacion de los jugadores
     */
    public static void printPlayersInfo(){
        for (Player player : players) {
            System.out.println(player.toString());
        }
    }

    /**
     * Este metodo lanza los dados del jugador cuyo turno es el actual
     */
    public static void throwActualPlayerDices(){
        Player player = players.get(playerTurnIndex);
        player.throwDices();

        if (player.canMove()) {
            int position = player.getPosition() + player.getSumDicesValue();
            if (position >= board.getBoardBoxes().length)
                position = Math.floorMod(position, board.getBoardBoxes().length);
            player.moveTo(position);
        }
        else {
            if (player.getDices()[0] == player.getDices()[1]) player.setCanMove(true);
        }
    }

    /**
     * Este metodo imprime por pantalla los bienes del jugador cuyo turno es el actual
     */
    public static void printActualPlayerEstates(){
        Player player = players.get(playerTurnIndex);
        player.getEstates().forEach(e -> System.out.println(e.getName()+": $"+e.getMortgageValue()));
    }

    /**
     * Este metodo manda en bancarrota a un jugador
     * @param player Recibe el jugador a enviar en bancarrota
     */
    public static void goBankrupt(Player player){
        System.out.println(player.getName()+" entro en bancarrota");
        players.remove(player);
        checkForWinner();
    }

    /**
     * Este metodo chequea si el juego tiene un ganador
     */
    private static void checkForWinner(){
        if (players.size() == 1){
            System.out.println("-----------------------------------------------------");
            System.out.println("El ganador es "+players.get(0).getName()+"!");
            System.out.println("-----------------------------------------------------");
            boardGUI.close();
        }
    }

    /**
     * Este metodo imprime por pantalla un menu de opciones de IOController
     */
    private static void showMenu(){
        Player player = players.get(playerTurnIndex);
        System.out.println("-----------------------------------------------------");
        System.out.println("Turno de "+player.getName());
        System.out.println("-----------------------------------------------------");
        System.out.println();
        if (players.get(playerTurnIndex).canMove()) IOController.showMenu();
        else IOController.showJailOptions(players.get(playerTurnIndex));
    }

    /**
     * Este metodo lanza los dados de cada jugador y otorga el primer turno al que mayor numero obtenga
     */
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

    /**
     * Este metodo inicializa los valores de los jugadores segun los enviados por consola a IOController
     */
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