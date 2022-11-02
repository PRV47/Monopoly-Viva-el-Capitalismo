package Monopoly.Cards;

import Monopoly.Game;
import Monopoly.Player;

public class MoneyCard implements ICard{
    private int amount;

    public MoneyCard(int amount){
        this.amount = amount;
    }

    @Override
    public void actOn(Player player) {
        player.addMoney(amount);
    }

    @Override
    public String getText() {
        if (amount > 0) return "recibe $"+amount;
        else return "debe pagar $"+(-amount);
    }
}
