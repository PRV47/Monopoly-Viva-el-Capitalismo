package Monopoly;

import Monopoly.BoardBoxes.BoardBox;
import Monopoly.Estates.Estate;

import javax.swing.*;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class Player {
    private String name;
    private int money;
    private BoardToken boardToken;
    private LinkedList<Estate> estates = new LinkedList<>();
    private int currentPosition;
    private boolean canMove;
    private int[] lastDicesValue;

    public Player(String name, int money, BoardToken boardToken, int currentPosition) {
        this.name = name;
        this.money = money;
        this.boardToken = boardToken;
        this.currentPosition = currentPosition;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return currentPosition;
    }

    public int getMoney() {
        return money;
    }

    public int[] getDices() {
        return lastDicesValue;
    }

    public int getSumDicesValue() {
        return lastDicesValue[0]+lastDicesValue[1];
    }

    public void throwDices(){
        int dice1 = ThreadLocalRandom.current().nextInt(1, 7);
        int dice2 = ThreadLocalRandom.current().nextInt(1, 7);
        lastDicesValue = new int[]{ dice1, dice2};
    }

    public void moveTo(int newPosition, BoardBox box){
        currentPosition = newPosition;
        box.onFallInto(this);
    }

    public void checkBuyEstate(Estate estate){
        String message;
        if (money >= estate.getCurrentPrice()){
            money -= estate.getCurrentPrice();
            estates.add(estate);
            estate.setOwner(this);
            message = "Compra exitosa";
        }
        else message = "Dinero insuficiente. Deberá hipotecar bienes";

        JFrame frame = new JFrame("Aviso");
        frame.setSize(300,300);
        JOptionPane.showMessageDialog(frame, message);
        leaveTurn();
    }

    public void leaveTurn(){
        Game.setNextTurn();
    }
}
