package Monopoly.BoardBoxes;

import Monopoly.Estates.Estate;
import Monopoly.Player;

public class EstateBox extends BoardBox {
    private Estate estate;

    public EstateBox(int id, Estate estate) {
        super(id);
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
