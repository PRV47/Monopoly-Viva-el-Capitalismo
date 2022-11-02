package Monopoly.BoardBoxes;

import Monopoly.Cards.ICard;
import Monopoly.Player;

import javax.swing.*;

public class CardBox extends BoardBox{
    private ICard card;

    public CardBox(String text, ICard card) {
        super(text);
        this.card = card;
    }

    @Override
    public void onFallInto(Player player) {
        System.out.println(player.getName()+" ha caido en "+toString()+" y obtuvo la carta: "+card.getText());
        player.leaveTurn();
    }
}
