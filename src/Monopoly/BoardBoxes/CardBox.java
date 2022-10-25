package Monopoly.BoardBoxes;

import Monopoly.Cards.ICard;

public class CardBox extends BoardBox{
    private ICard card;

    public CardBox(String text, ICard card) {
        super(text);
        this.card = card;
    }
}
