package Monopoly.GUI;

import Monopoly.BoardToken;

import javax.swing.*;
import java.awt.*;

public class PlayerGUI {
    private final JLabel labelIcon;
    private JPanel currentPanel;

    public PlayerGUI(BoardToken token){
        ImageIcon imageIcon = createImageIcon("../../Images/"+token.toString()+".png");
        Image newImage = imageIcon.getImage().getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImage);
        labelIcon = new JLabel(imageIcon);
    }

    public void setIconIn(JPanel panel){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = -1;
        constraints.gridy = 2;
        constraints.weighty = 30;
        constraints.weightx = 5;

        if (currentPanel != null) currentPanel.remove(labelIcon);
        panel.add(labelIcon,constraints);
        currentPanel = panel;
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    private ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, "icon");
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
