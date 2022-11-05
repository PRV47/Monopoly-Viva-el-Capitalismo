package Monopoly.BoardBoxes;

import Monopoly.Cards.ICard;
import Monopoly.Player;

public class CardBox extends BoardBox{
    private final ICard card;

    public CardBox(String text, ICard card) {
        super(text);
        this.card = card;
    }

    @Override
    public void onFallInto(Player player) {
        System.out.println(player.getName()+" ha caido en "+toString()+" y obtuvo la carta: "+card.getText());
        card.actOn(player);
        player.leaveTurn();
    }
}
