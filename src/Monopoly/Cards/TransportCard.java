package Monopoly.Cards;

import Monopoly.Game;
import Monopoly.Player;

public class TransportCard implements ICard {
    private final int positionToGo;

    public TransportCard(int positionToGo){
        this.positionToGo = positionToGo;
    }

    @Override
    public void actOn(Player player) {
        player.moveTo(positionToGo);
    }

    @Override
    public String getText() {
        return "debe ir a "+ Game.getBoardBoxByIndex(positionToGo).toString();
    }
}
