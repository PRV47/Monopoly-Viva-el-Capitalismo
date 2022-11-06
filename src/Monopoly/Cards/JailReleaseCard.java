package Monopoly.Cards;

import Monopoly.Player;

/**
 * Implementa ICard, encargada de dar al jugador la oportunidad de salir de la carcel.
 */
public class JailReleaseCard implements ICard{

    /**
     * Este metodo otorga a un jugador la oportunidad de salir de la carcel
     * @param player Recibe el jugador sobre el cual actuara
     */
    @Override
    public void actOn(Player player) {
        player.setHasJailReleaseCard(true);
    }

    /**
     * Este metodo devuelve el texto de esta carta
     * @return String con informacion de la carta
     */
    @Override
    public String getText() {
        return "obtiene una liberacion de la carcel";
    }
}
