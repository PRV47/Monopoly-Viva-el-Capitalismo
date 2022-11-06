package Monopoly.Cards;

import Monopoly.Game;
import Monopoly.Player;

/**
 * Implementa ICard, encargada de dar o quitar dinero al jugador.
 */
public class MoneyCard implements ICard{
    private final int amount;

    /**
     * Constructor de la clase
     * @param amount Recibe la cantidad de dinero (positiva o negativa)
     */
    public MoneyCard(int amount){
        this.amount = amount;
    }

    /**
     * Este metodo otorga o quita dinero a un jugador
     * @param player Recibe el jugador sobre el cual actuara
     */
    @Override
    public void actOn(Player player) {
        if (amount < 0) player.subtractMoney(-amount, true);
        else player.addMoney(amount);
    }

    /**
     * Este metodo devuelve el texto de esta carta
     * @return String con informacion de la carta
     */
    @Override
    public String getText() {
        if (amount > 0) return "recibe $"+amount;
        else return "debe pagar $"+(-amount);
    }
}
