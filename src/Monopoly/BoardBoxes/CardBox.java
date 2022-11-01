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
        JFrame frame = new JFrame("Carta obtenida!");
        frame.setSize(300,300);
        JOptionPane.showMessageDialog(frame,  player.getName()+" "+card.getText());
        player.leaveTurn();
    }
}
