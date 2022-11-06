package Monopoly.Cards;

import Monopoly.Player;

/**
 * Interfaz que describe el comportamiento de las cartas.
 */
public interface ICard {
    /**
     * @param player Recibe el jugador sobre el cual actuara
     */
    void actOn(Player player);

    /**
     * Este metodo devuelve el texto de esta carta
     * @return String con informacion de la carta
     */
    String getText();
}
