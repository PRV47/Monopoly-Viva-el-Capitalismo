package Monopoly.BoardBoxes;

import Monopoly.Player;

import java.util.Scanner;

public class TaxBox extends BoardBox{
    private int amountToPay;

    public TaxBox(String text, int amountToPay) {
        super(text);
        this.amountToPay = amountToPay;
    }

    @Override
    public void onFallInto(Player player) {
        System.out.println(player.getName()+" ha caido en "+toString());
        if (amountToPay >= 0) System.out.println("Recibe $"+amountToPay);
        else System.out.println("Debe pagar $"+(-amountToPay));
        new Scanner(System.in).nextLine();

        player.addMoney(amountToPay);
        player.leaveTurn();
    }
}
