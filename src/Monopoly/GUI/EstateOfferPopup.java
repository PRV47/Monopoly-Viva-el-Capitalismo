package Monopoly.GUI;

import Monopoly.Estates.Estate;
import Monopoly.Player;

import javax.swing.*;

public class EstateOfferPopup {

    public EstateOfferPopup(Estate estate, Player player)
    {
        String message = "¿Desea comprar "+estate.getName()+"? \n Costo: "+estate.getCurrentPrice();
        JFrame mainFrame = new JFrame("Oferta de bien");
        mainFrame.setSize(300,300);

        int option = JOptionPane.showConfirmDialog(mainFrame, message);
        if (option == JOptionPane.YES_OPTION){
            player.checkBuyEstate(estate);
        }
        else {
            player.leaveTurn();
        }
    }
}
