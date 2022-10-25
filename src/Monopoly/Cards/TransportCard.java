package Monopoly.Cards;

import Monopoly.Player;

public class TransportCard implements ICard {
    private int positionToGo;

    public TransportCard(int positionToGo){
        this.positionToGo = positionToGo;
    }

    @Override
    public void actOn(Player player) {

    }

    @Override
    public String toString() {
        return "Moverse al casillero "+positionToGo;
    }
}
