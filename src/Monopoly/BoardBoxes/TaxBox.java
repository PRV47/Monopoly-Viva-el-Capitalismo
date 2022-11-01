package Monopoly.BoardBoxes;

import Monopoly.Player;

public class TaxBox extends BoardBox{
    private int amountToPay;

    public TaxBox(String text, int amountToPay) {
        super(text);
        this.amountToPay = amountToPay;
    }

    @Override
    public void onFallInto(Player player) {
        System.out.println(player.getName()+" cayo en casillero: "+ toString());
        player.leaveTurn();
    }
}
