package Monopoly.BoardBoxes;

import Monopoly.Cards.ICard;
import Monopoly.Player;

/**
 * Es un tipo de BoardBox, otorga una carta al jugador que cae en el.
 */
public class CardBox extends BoardBox{
    private final ICard card;

    /**
     * Constructor de la clase
     * @param text Recibe el texto del casillero
     * @param card Recibe la carta del casillero
     */
    public CardBox(String text, ICard card) {
        super(text);
        this.card = card;
    }

    /**
     * Este metodo ejecuta la accion de la carta cuando un jugador llega a este casillero
     * @param player Recibe el jugador sobre el cual actuar
     */
    @Override
    public void onFallInto(Player player) {
        System.out.println(player.getName()+" ha caido en "+this+" y obtuvo la carta: "+card.getText());
        card.actOn(player);
        player.leaveTurn();
    }
}
