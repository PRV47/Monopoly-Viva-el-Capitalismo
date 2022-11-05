package Monopoly.BoardBoxes;
import Monopoly.Game;
import Monopoly.Player;

import javax.swing.*;

public class BoardBox {
    private final String text;

    public BoardBox(String text) {
        this.text = text;
    }

    public void onFallInto(Player player){
        System.out.println(player.getName()+" ha caido en "+toString());
        player.leaveTurn();
    }

    @Override
    public String toString(){
        return text;
    }
}
