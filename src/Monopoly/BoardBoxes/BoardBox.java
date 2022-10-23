package Monopoly.BoardBoxes;
import Monopoly.Player;

public class BoardBox {
    private int id;

    public BoardBox(int id) {
        this.id = id;
    }

    public void onFallInto(Player player){

    }

    public String toStr(){
        return String.valueOf(id);
    }
}
