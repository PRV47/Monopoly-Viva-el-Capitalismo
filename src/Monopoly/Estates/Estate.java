package Monopoly.Estates;

import Monopoly.Player;

/**
 * Clase encargada de contener la informacion de bienes: nombre, propietario, precio
 */
public abstract class Estate {
    private final String name;
    private Player owner;
    private final int price;
    private final int rentValue;
    private final int mortgageValue;

    /**
     * Constructor de la clase
     * @param name Recibe el nombre
     * @param price Recibe el precio
     */
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
