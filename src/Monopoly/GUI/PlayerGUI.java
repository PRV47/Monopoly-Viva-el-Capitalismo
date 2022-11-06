package Monopoly.GUI;

import Monopoly.BoardToken;
import Monopoly.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Clase encargada de mostrar los jugadores en una interfaz grafica.
 */
public class PlayerGUI {
    private final JLabel labelIcon;
    private JPanel currentPanel;

    /**
     * Constructor de la clase
     * @param token Recibe el token del jugador
     */
    public PlayerGUI(BoardToken token){
        ImageIcon imageIcon = createImageIcon("Images/"+token.toString()+".png");
        labelIcon = new JLabel(imageIcon);
    }

    /**
     * Este metodo mueve el icono (token) del jugador a un nuevo panel
     * @param panel Recibe el nuevo panel donde mover el icono
     */
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

    /**
     * Este metodo crea una ImageIcon con una imagen desde path dado
     * @param path Recibe el path de la imagen
     * @return ImageIcon de la imagen
     */
    private ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Game.class.getResource(path);
        if (imgURL != null) {
            var imageIcon = new ImageIcon(imgURL, "icon");
            var newImage = imageIcon.getImage().getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
            return new ImageIcon(newImage, "icon");
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
