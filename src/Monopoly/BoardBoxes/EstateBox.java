package Monopoly.BoardBoxes;

import Monopoly.Estates.Estate;
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
            buyEstate(player);
        else collectMoney(player);
    }

    @Override
    public String toStr(){
        return estate.getName();
    }

    public void buyEstate(Player player){

    }

    public void collectMoney(Player player){

    }
}
