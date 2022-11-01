package Monopoly.BoardBoxes;
import Monopoly.Player;

import javax.swing.*;

public class BoardBox {
    private String text;

    public BoardBox(String text) {
        this.text = text;
    }

    public void onFallInto(Player player){
        JFrame frame = new JFrame(text);
        frame.setSize(300,300);
        JOptionPane.showMessageDialog(frame, player.getName()+" ha caído en "+toString());
        player.leaveTurn();
    }

    @Override
    public String toString(){
        return text;
    }
}
