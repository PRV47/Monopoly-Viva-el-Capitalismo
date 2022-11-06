package Monopoly.BoardBoxes;

import Monopoly.Player;

import java.util.Scanner;

/**
 * Es un tipo de BoardBox, cobra un impuesto al jugador que cae en el.
 */
public class TaxBox extends BoardBox{
    private final int amountToPay;

    /**
     * Constructor de la clase
     * @param text Recibe el texto del casillero
     * @param amountToPay Recibe la cantidad de dinero a cobrar
     */
    public TaxBox(String text, int amountToPay) {
        super(text);
        this.amountToPay = amountToPay;
    }

    /**
     * Este metodo cobra un impuesto a un jugador cuando llega a este casillero
     * @param player Recibe el jugador sobre el cual actuar
     */
    @Override
    public void onFallInto(Player player) {
        System.out.println(player.getName()+" ha caido en "+this);
        System.out.println("Debe pagar $"+(amountToPay));
        new Scanner(System.in).nextLine();

        player.subtractMoney(amountToPay, true);
        player.leaveTurn();
    }
}
