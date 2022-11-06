package Monopoly.BoardBoxes;
import Monopoly.Player;

/**
 * Esta clase describe el comportamiento de un casillero cuando un jugador cae en el.
 */
public class BoardBox {
    private final String text;

    /**
     * Constructor de la clase
     * @param text Recibe el texto del casillero
     */
    public BoardBox(String text) {
        this.text = text;
    }

    /**
     * Este metodo se ejecuta cuando un jugador llega a este casillero
     * @param player Recibe el jugador sobre el cual actuar
     */
    public void onFallInto(Player player){
        System.out.println(player.getName()+" ha caido en "+this);
        player.leaveTurn();
    }

    /**
     * @return String con el texto del casillero
     */
    @Override
    public String toString(){
        return text;
    }
}
