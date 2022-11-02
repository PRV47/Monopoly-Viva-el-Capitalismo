package Monopoly.Cards;

import Monopoly.Player;

public class JailReleaseCard implements ICard{

    @Override
    public void actOn(Player player) {
        player.setHasJailReleaseCard(true);
    }

    @Override
    public String getText() {
        return "obtiene una liberacion de la carcel";
    }
}
