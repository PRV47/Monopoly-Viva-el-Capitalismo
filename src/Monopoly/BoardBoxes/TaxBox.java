package Monopoly.BoardBoxes;

public class TaxBox extends BoardBox{
    private int amountToPay;

    public TaxBox(String text, int amountToPay) {
        super(text);
        this.amountToPay = amountToPay;
    }
}
