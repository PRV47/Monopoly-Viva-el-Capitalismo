package Monopoly.GUI;

import Monopoly.Board;
import Monopoly.BoardBoxes.EstateBox;
import Monopoly.Estates.Land;
import Monopoly.Player;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.LinkedList;

public class BoardGUI {
    private JFrame mainFrame;
    private JPanel mainPanel;
    private JPanel[] boardBoxesPanels = new JPanel[40];
    private JPanel[][] matrixPanels = new JPanel[11][11];
    private JList playersMoney;
    private int rows = 11;
    private int columns = 11;

    public void init(Board board){
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(new Color(191,218,193));
        initMainFrame();
        createPanelsMatrix(rows,columns);
        initBoardBoxesPanels();

        for (int i = 0; i < boardBoxesPanels.length; i++){
            try {
                EstateBox estateBox = (EstateBox) board.getBoardBoxes().get(i);
                Land land = (Land) estateBox.getEstate();
                fillLandBox(boardBoxesPanels[i], board.getBoardBoxes().get(i).toString(),land.getColorZone());
            }
            catch (Exception e){
                fillTextBox(boardBoxesPanels[i], board.getBoardBoxes().get(i).toString());
            }
        }
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    public void updateBoxPanel(int index){
        mainFrame.update(boardBoxesPanels[index].getGraphics());
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    public void updatePlayersMoneyInfo(LinkedList<Player> players){
        if (playersMoney != null) matrixPanels[5][5].remove(playersMoney);
        String[] playersMoneyStr = new String[players.size()+2];
        playersMoneyStr[0] = "Dinero actual:";
        playersMoneyStr[1] = "   ";
        for (int i = 2; i < playersMoneyStr.length; i++) {
            playersMoneyStr[i] = players.get(i-2).getName()+": $"+players.get(i-2).getMoney();
        }

        playersMoney = new JList(playersMoneyStr);
        var font = playersMoney.getFont();
        playersMoney.setFont(new Font(font.getName(), font.getStyle(), 13));
        matrixPanels[5][5].add(playersMoney);
    }

    public JPanel getBoxPanel(int index){ return boardBoxesPanels[index]; }

    public void close(){
        mainFrame.setVisible(false);
    }

    private void initMainFrame(){
        mainFrame = new JFrame("Monopoly");
        mainFrame.setContentPane(mainPanel);
        mainFrame.setSize(900, 900);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createPanelsMatrix(int rows, int columns){
        var constraints = new GridBagConstraints();
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weighty = 5.0;
        constraints.weightx = 5.0;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(1,1,1,1);

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                JPanel panel = new JPanel();
                panel.setLayout(new GridBagLayout());
                panel.setBackground(new Color(0,0,0, 0));
                constraints.gridx = i;
                constraints.gridy = j;
                matrixPanels[i][j] = panel;
                mainPanel.add(panel, constraints);
            }
        }
    }

    private void initBoardBoxesPanels(){
        int id = 0;

        for (int i = columns-1; i >= 0; i--){
            boardBoxesPanels[id] = matrixPanels[i][rows-1];
            id++;
        }
        for (int i = rows-2; i >= 0; i--){
            boardBoxesPanels[id] = matrixPanels[0][i];
            id++;
        }
        for (int i = 1; i < columns; i++){
            boardBoxesPanels[id] = matrixPanels[i][0];
            id++;
        }
        for (int i = 1; i < rows-1; i++){
            boardBoxesPanels[id] = matrixPanels[columns-1][i];
            id++;
        }
    }

    private void fillLandBox(JPanel box, String text, Color color){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = -1;
        constraints.gridy = -1;
        constraints.weighty = 1;
        constraints.weightx = 5;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        JPanel colorPanel = new JPanel();
        colorPanel.setBorder(new LineBorder(Color.BLACK));
        colorPanel.setBackground(color);
        box.add(colorPanel, constraints);

        fillTextBox(box, text);
    }

    private void fillTextBox(JPanel box, String text){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weighty = 30;
        constraints.weightx = 5;
        constraints.fill = GridBagConstraints.CENTER;

        JTextArea textArea = new JTextArea(text);
        textArea.setBackground(new Color(0,0,0,0));
        //textArea.setFont(new Font("Kabel-Heavy", Font.PLAIN, 15));

        box.add(textArea, constraints);
        box.setBorder(new LineBorder(Color.BLACK));
    }
}
