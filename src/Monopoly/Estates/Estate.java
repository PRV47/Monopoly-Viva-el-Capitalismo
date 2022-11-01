package Monopoly.Estates;

import Monopoly.Player;

public abstract class Estate {
    private String name;
    private Player owner;
    private int initialPrice;
    private int currentPrice;
    private int mortgageValue;

    public Estate(String name, int initialPrice, int mortgageValue) {
        this.name = name;
        this.initialPrice = initialPrice;
        this.currentPrice = initialPrice;
        this.mortgageValue = mortgageValue;
    }

    public void updatePrice(int newPrice){
        currentPrice = newPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public int getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }

    public int getMortgageValue() {
        return mortgageValue;
    }
}
