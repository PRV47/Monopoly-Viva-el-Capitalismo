package Monopoly.BoardBoxes;

public class GoToJailBox extends BoardBox{
    private int jailBoxID;

    public GoToJailBox(String text, int jailBoxID) {
        super(text);
        this.jailBoxID = jailBoxID;
    }
}
