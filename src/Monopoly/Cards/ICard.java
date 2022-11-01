package Monopoly.Cards;

import Monopoly.Player;

public interface ICard {
    void actOn(Player player);
    String getText();
}
