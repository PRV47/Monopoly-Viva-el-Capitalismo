package Monopoly;

import Monopoly.BoardBoxes.BoardBox;
import Monopoly.Estates.Estate;
import Monopoly.GUI.PlayerGUI;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class Player {
    private String name;
    private int money;
    private BoardToken boardToken;
    private LinkedList<Estate> estates = new LinkedList<>();
    private int currentPosition;
    private boolean canMove = true;
    private int[] lastDicesValue;
    private boolean hasJailReleaseCard;
    private PlayerGUI playerGUI;

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
    public BoardToken getBoardToken() { return boardToken; }
    public PlayerGUI getPlayerGUI() { return playerGUI; }

    public boolean hasJailReleaseCard() { return hasJailReleaseCard; }

    public int getSumDicesValue() {
        return lastDicesValue[0]+lastDicesValue[1];
    }

    public void throwDices(){
        int dice1 = ThreadLocalRandom.current().nextInt(1, 7);
        int dice2 = ThreadLocalRandom.current().nextInt(1, 7);
        lastDicesValue = new int[]{ dice1, dice2};
        System.out.println("Dados de "+name+": "+dice1+" "+dice2);
    }

    public void moveTo(int newPosition){
        playerGUI.setIconIn(Game.getBoardBoxPanelByIndex(newPosition));
        Game.updateBoxPanelGUI(currentPosition);
        Game.updateBoxPanelGUI(newPosition);
        currentPosition = newPosition;
        BoardBox box = Game.getBoardBoxByIndex(currentPosition);
        box.onFallInto(this);
    }

    public void checkBuyEstate(Estate estate){
        if (money >= estate.getPrice()){
            money -= estate.getPrice();
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

    public void leaveTurn(){
        Game.setNextTurn();
    }

    public void addMoney(int amount){
        if (money+amount < 0) checkBankruptcy();
        money += amount;
    }

    public boolean canPay(int amount){ return money-amount >= 0; }

    public void setHasJailReleaseCard(boolean hasCard){
        hasJailReleaseCard = hasCard;
    }

    public void setCanMove(boolean value){
        canMove = value;
        if (value) System.out.println(name+" fue liberado de la carcel");
    }

    public void mortgageEstate(int index){
        if (index >= estates.size()) return;
        addMoney(estates.get(index).getMortgageValue());
        estates.remove(index);
    }

    private void checkBankruptcy(){
        System.out.println("Dinero insuficiente");
        if (estates.size() > 0) IOController.showMortgageOptions(this);
        else Game.goBankrupt(this);
    }

    @Override
    public String toString(){
        return "Nombre: "+name+", Pieza: "+boardToken+", Posicion: "+
                Game.getBoardBoxByIndex(currentPosition)+", Dinero: "+money;
    }
}
