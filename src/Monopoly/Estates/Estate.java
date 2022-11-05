package Monopoly.Estates;

import Monopoly.Player;

public abstract class Estate {
    private String name;
    private Player owner;
    private int price;
    private int rentValue;
    private int mortgageValue;

    public Estate(String name, int price) {
        this.name = name;
        this.price = price;
        this.mortgageValue = Math.floorDiv(price,2);
        rentValue = (int) (price*0.09);
    }

    public String getName() {
        return name;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public int getPrice() {
        return price;
    }

    public int getRentValue() {return rentValue;}

    public int getMortgageValue() {
        return mortgageValue;
    }
}
