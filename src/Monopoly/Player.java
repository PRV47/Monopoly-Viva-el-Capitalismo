package Monopoly;

import Monopoly.BoardBoxes.BoardBox;
import Monopoly.Estates.Estate;
import Monopoly.GUI.PlayerGUI;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Clase con la informacion necesaria y comportamientos del jugador. Cada jugador tiene su nombre, dinero, token,
 * posicion actual, bienes y su interfaz grafica. Cada jugador debe ser capaz de moverse, modificar el valor de su dinero, chequear si
 * esta en bancarrota, hipotecar bienes y lanzar sus dados.
 */
public class Player {
    private final String name;
    private int money;
    private final BoardToken boardToken;
    private final LinkedList<Estate> estates = new LinkedList<>();
    private int currentPosition;
    private boolean canMove = true;
    private int[] lastDicesValue;
    private boolean hasJailReleaseCard;
    private final PlayerGUI playerGUI;

    /**
     * Contructor de la clase
     * @param name Recibe el nombre del jugador
     * @param money Recibe la cantidad de dinero inicial
     * @param boardToken Recibe la ficha del jugador
     * @param currentPosition Recibe la posición inicial
     * @param playerGUI Recibe el valor para PlayerGUI
     */
    public Player(String name, int money, BoardToken boardToken, int currentPosition, PlayerGUI playerGUI) {
        this.name = name;
        this.money = money;
        this.boardToken = boardToken;
        this.currentPosition = currentPosition;
        this.playerGUI = playerGUI;
    }

    public String getName() {
        return name;
    }
    public int getMoney() { return money; }

    public int getPosition() {
        return currentPosition;
    }

    public LinkedList<Estate> getEstates() {
        return estates;
    }

    public int[] getDices() {
        return lastDicesValue;
    }

    public boolean canMove() { return canMove; }

    public PlayerGUI getPlayerGUI() { return playerGUI; }

    public boolean hasJailReleaseCard() { return hasJailReleaseCard; }

    public void setHasJailReleaseCard(boolean hasCard){ hasJailReleaseCard = hasCard; }

    public void setCanMove(boolean value){
        canMove = value;
        if (value) System.out.println(name+" fue liberado de la carcel");
    }

    /**
     * Este metodo realiza la suma entre los ultimos dos dados obtenidos
     * @return int resultado de la suma
     */
    public int getSumDicesValue() {
        return lastDicesValue[0]+lastDicesValue[1];
    }

    /**
     * Este metodo obtiene el valor de dos dados aleatoriamente
     */
    public void throwDices(){
        int dice1 = ThreadLocalRandom.current().nextInt(1, 7);
        int dice2 = ThreadLocalRandom.current().nextInt(1, 7);
        lastDicesValue = new int[]{ dice1, dice2};
        System.out.println("Dados de "+name+": "+dice1+" "+dice2);
    }

    /**
     * Este metodo realiza el movimiento del jugador a una nueva posicion
     * @param newPosition Recibe el id del casillero al que debe dirigirse
     */
    public void moveTo(int newPosition){
        playerGUI.setIconIn(Game.getBoardBoxPanelByIndex(newPosition));
        Game.updateBoxPanelGUI(currentPosition);
        Game.updateBoxPanelGUI(newPosition);
        currentPosition = newPosition;
        BoardBox box = Game.getBoardBoxByIndex(currentPosition);
        box.onFallInto(this);
    }

    /**
     * Este metodo chequea si es posible comprar un bien
     * @param estate Recibe el bien que se desea comprar
     */
    public void checkBuyEstate(Estate estate){
        if (money >= estate.getPrice()){
            subtractMoney(estate.getPrice(), false);
            estates.add(estate);
            estate.setOwner(this);
            System.out.println("Compra exitosa");
        }
        else {
            System.out.println("Dinero insuficiente");
            if (estates.size() > 0 && IOController.yesNoQuestion("Deseas hipotecar bienes? (Y/N)")) {
                IOController.showMortgageOptions(this);
                checkBuyEstate(estate);
            }
        }
    }

    /**
     * Este metodo avisa a Game que termino el turno de este jugador
     */
    public void leaveTurn(){
        Game.setNextTurn();
    }

    /**
     * Este metodo se encarga de añadir dinero al jugador
     * @param amount Recibe la cantidad de dinero a añadir
     */
    public void addMoney(int amount){
        money += amount;
        Game.updatePlayersMoneyInfo();
    }

    /**
     * Este metodo se encarga de restar dinero al jugador
     * @param amount Recibe la cantidad de dinero a restar
     * @param isRequired Es verdadero si es un pago que si o si debe realizarse
     */
    public void subtractMoney(int amount, boolean isRequired){
        if (money-amount < 0) {
            System.out.println("Dinero insuficiente");
            if(isRequired) checkBankruptcy();
        }
        money -= amount;
        Game.updatePlayersMoneyInfo();
    }

    /**
     * Este metodo chequea si el jugador puede realizar un pago
     * @param amount Recibe la cantidad de dinero a pagar
     * @return Verdadero si puede pagarlo, falso si no
     */
    public boolean canPay(int amount){ return money-amount >= 0; }

    /**
     * Este metodo se encarga de hipotecar bienes
     * @param index Recibe el indice dentro de estates del bien que se desea hipotecar
     */
    public void mortgageEstate(int index){
        if (index >= estates.size()) return;
        addMoney(estates.get(index).getMortgageValue());
        estates.remove(index);
    }

    /**
     * Este metodo se encarga de chequear si el jugador entra en bancarrota
     */
    private void checkBankruptcy(){
        if (estates.size() > 0) IOController.showMortgageOptions(this);
        else Game.goBankrupt(this);
    }

    /**
     * Este metodo se encarga de transformar la informacion de un jugador a texto
     * @return String con nombre, tipo de pieza, posicion y dinero
     */
    @Override
    public String toString(){
        return "Nombre: "+name+", Pieza: "+boardToken+", Posicion: "+
                Game.getBoardBoxByIndex(currentPosition)+", Dinero: "+money;
    }
}
