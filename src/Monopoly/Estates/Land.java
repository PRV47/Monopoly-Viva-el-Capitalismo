package Monopoly.Estates;

import java.awt.*;

public class Land extends Estate{
    private int houses;
    private int hotels;
    private int housesCost;
    private Color colorZone;

    public Land(String name, int initialPrice, Color colorZone) {
        super(name, initialPrice);
        this.colorZone = colorZone;
    }

    public Color getColorZone() { return colorZone; }
}
