package Monopoly.BoardBoxes;

import Monopoly.Estates.Estate;
import Monopoly.Game;
import Monopoly.IOController;
import Monopoly.Player;

import java.util.Scanner;

/**
 * Es un tipo de BoardBox, otorga la posibilidad de comprar o cobrar renta al jugador que cae en el.
 */
public class EstateBox extends BoardBox {
    private final Estate estate;

    /**
     * Constructor de la clase
     * @param estate Recibe el bien
     */
    public EstateBox(Estate estate) {
        super(estate.getName());
        this.estate = estate;
    }

    public Estate getEstate(){ return estate; }

    /**
     * Este metodo otorga la oportunidad de comprar el bien a un jugador cuando llega a este casillero
     * @param player Recibe el jugador sobre el cual actuar
     */
    @Override
    public void onFallInto(Player player){
        System.out.println(player.getName()+" ha caido en "+this);
        if (estate.getOwner() == null)
            showEstateOffer(player);
        else {
            if (estate.getOwner() != player)
                collectMoney(player);
        }
        Game.setNextTurn();
    }

    /**
     * @return String con el nombre del bien
     */
    @Override
    public String toString(){
        return estate.getName();
    }

    /**
     * Este metodo es el encargado de mostrar al usuario la oferta de este bien
     * @param player Recibe el jugador al cual ofrecer
     */
    private void showEstateOffer(Player player){
        boolean bought = IOController.yesNoQuestion("Quieres comprar el bien: "+estate.getName()+
                " con precio: "+estate.getPrice()+"? (Y/N)");

        if (bought) player.checkBuyEstate(estate);
    }

    /**
     * Este metodo se encarga de cobrar al jugador que llega a este casillero
     * @param player Recibe el jugador al cual se debe cobrar
     */
    private void collectMoney(Player player){
        int amount = estate.getRentValue();
        System.out.println("Debes pagar $"+amount+" a "+ estate.getOwner().getName());
        player.subtractMoney(amount, true);
        estate.getOwner().addMoney(amount);
        new Scanner(System.in).nextLine();
    }
}
