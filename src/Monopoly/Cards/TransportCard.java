package Monopoly.Cards;

import Monopoly.Game;
import Monopoly.Player;

/**
 * Implementa ICard, encargada de mover al jugador a otra posicion.
 */
public class TransportCard implements ICard {
    private final int positionToGo;

    /**
     * Constructor de la clase
     * @param positionToGo Recibe la posicion a la que transporta
     */
    public TransportCard(int positionToGo){
        this.positionToGo = positionToGo;
    }

    /**
     * Este metodo mueve a un jugador a la posicion establecida
     * @param player Recibe el jugador sobre el cual actuara
     */
    @Override
    public void actOn(Player player) {
        player.moveTo(positionToGo);
    }

    /**
     * Este metodo devuelve el texto de esta carta
     * @return String con informacion de la carta
     */
    @Override
    public String getText() {
        return "debe ir a "+ Game.getBoardBoxByIndex(positionToGo).toString();
    }
}
