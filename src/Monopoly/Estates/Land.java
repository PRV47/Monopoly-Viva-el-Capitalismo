package Monopoly.Estates;

import java.awt.*;

/**
 * Esta clase es un tipo de bien, representa a los Terrenos, anade un color caracteristico de la zona.
 */
public class Land extends Estate{
    private final Color colorZone;

     /**
     * Constructor de la clase
     * @param name Recibe el nombre
     * @param initialPrice Recibe el precio inicial
     * @param colorZone Recibe el color de la zona
     */
    public Land(String name, int initialPrice, Color colorZone) {
        super(name, initialPrice);
        this.colorZone = colorZone;
    }

    public Color getColorZone() { return colorZone; }
}
