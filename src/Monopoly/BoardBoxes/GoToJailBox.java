package Monopoly.BoardBoxes;

import Monopoly.Player;

public class GoToJailBox extends BoardBox{
    private int jailBoxID;

    public GoToJailBox(String text, int jailBoxID) {
        super(text);
        this.jailBoxID = jailBoxID;
    }

    @Override
    public void onFallInto(Player player) {
        System.out.println(player.getName()+" cayo en casillero: "+ toString());
        player.setCanMove(false);
        player.moveTo(jailBoxID);
    }
}
