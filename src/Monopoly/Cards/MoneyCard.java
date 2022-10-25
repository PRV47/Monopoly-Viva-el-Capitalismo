package Monopoly.Cards;

import Monopoly.Player;

public class MoneyCard implements ICard{
    private int amount;

    public MoneyCard(int amount){
        this.amount = amount;
    }

    @Override
    public void actOn(Player player) {

    }
}
