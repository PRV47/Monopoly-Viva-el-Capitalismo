package Monopoly.BoardBoxes;

import Monopoly.Estates.Estate;
import Monopoly.GUI.EstateOfferPopup;
import Monopoly.Player;

public class EstateBox extends BoardBox {
    private Estate estate;

    public EstateBox(Estate estate) {
        super(estate.getName());
        this.estate = estate;
    }


    @Override
    public void onFallInto(Player player){
        if (estate.getOwner() == null)
            showEstateOffer(player);
        else collectMoney(player);
    }

    @Override
    public String toString(){
        return estate.getName();
    }

    public void showEstateOffer(Player player){
        EstateOfferPopup offer = new EstateOfferPopup(estate, player);
    }

    public void collectMoney(Player player){

    }
}
