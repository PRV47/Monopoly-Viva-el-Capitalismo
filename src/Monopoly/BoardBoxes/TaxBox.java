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
        System.out.println("Debe pagar $"+(amountToPay));
        new Scanner(System.in).nextLine();

        player.subtractMoney(amountToPay, true);
        player.leaveTurn();
    }
}
