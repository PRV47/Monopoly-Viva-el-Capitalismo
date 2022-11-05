package Monopoly.BoardBoxes;

import Monopoly.Estates.Estate;
import Monopoly.Game;
import Monopoly.IOController;
import Monopoly.Player;

import java.util.Scanner;

public class EstateBox extends BoardBox {
    private Estate estate;

    public EstateBox(Estate estate) {
        super(estate.getName());
        this.estate = estate;
    }

    public Estate getEstate(){ return estate; }

    @Override
    public void onFallInto(Player player){
        System.out.println(player.getName()+" ha caido en "+toString());
        if (estate.getOwner() == null)
            showEstateOffer(player);
        else {
            if (estate.getOwner() != player)
                collectMoney(player);
        }
        Game.setNextTurn();
    }

    @Override
    public String toString(){
        return estate.getName();
    }

    public void showEstateOffer(Player player){
        boolean bought = IOController.yesNoQuestion("Quieres comprar el bien: "+estate.getName()+
                " con precio: "+estate.getPrice()+"? (Y/N)");

        if (bought) player.checkBuyEstate(estate);
    }

    public void collectMoney(Player player){
        int amount = estate.getRentValue();
        System.out.println("Debes pagar $"+amount+" a "+ estate.getOwner().getName());
        player.subtractMoney(amount, true);
        estate.getOwner().addMoney(amount);
        new Scanner(System.in).nextLine();
    }
}
