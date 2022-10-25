package Monopoly.BoardBoxes;
import Monopoly.Player;

public class BoardBox {
    private String text;

    public BoardBox(String text) {
        this.text = text;
    }

    public void onFallInto(Player player){

    }

    public String toStr(){
        return text;
    }
}
