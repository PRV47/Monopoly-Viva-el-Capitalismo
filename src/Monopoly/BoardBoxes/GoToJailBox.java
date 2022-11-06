package Monopoly.BoardBoxes;

import Monopoly.Player;

/**
 * Es un tipo de BoardBox, envia a la carcel al jugador que cae en el.
 */
public class GoToJailBox extends BoardBox{
    private final int jailBoxID;

    /**
     * Constructor de la clase
     * @param text Recibe el texto del casillero
     * @param jailBoxID Recibe el indice del casillero de la carcel
     */
    public GoToJailBox(String text, int jailBoxID) {
        super(text);
        this.jailBoxID = jailBoxID;
    }

    /**
     * Este metodo inhabilita el movimiento de un jugador llega a este casillero
     * @param player Recibe el jugador sobre el cual actuar
     */
    @Override
    public void onFallInto(Player player) {
        System.out.println(player.getName()+" cayo en casillero: "+this);
        player.setCanMove(false);
        player.moveTo(jailBoxID);
    }
}
