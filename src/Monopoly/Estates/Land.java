package Monopoly.Estates;

import java.awt.*;

public class Land extends Estate{
    private int houses;
    private int hotels;
    private int housesCost;
    private Color colorZone;

    public Land(String name, int initialPrice, int mortgageValue, Color colorZone) {
        super(name, initialPrice, mortgageValue);
        this.colorZone = colorZone;
    }
}
