package Monopoly.GUI;

import Monopoly.Board;
import Monopoly.BoardBoxes.EstateBox;
import Monopoly.Estates.Land;
import Monopoly.Player;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.LinkedList;

/**
 * Clase encargada de mostrar el tablero en una interfaz grafica.
 */
public class BoardGUI {
    private JFrame mainFrame;
    private JPanel mainPanel;
    private final JPanel[] boardBoxesPanels = new JPanel[40];
    private final JPanel[][] matrixPanels = new JPanel[11][11];
    private JList playersMoney;
    private final int rows = 11;
    private final int columns = 11;

    /**
     * Este metodo inicializa los valores de la interfaz grafica del tablero
     * @param board Recibe el tablero a representar
     */
    public void init(Board board){
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(new Color(191,218,193));
        initMainFrame();
        createPanelsMatrix(rows,columns);
        initBoardBoxesPanels();

        for (int i = 0; i < boardBoxesPanels.length; i++){
            try {
                EstateBox estateBox = (EstateBox) board.getBoardBoxes()[i];
                Land land = (Land) estateBox.getEstate();
                fillLandBox(boardBoxesPanels[i], board.getBoardBoxes()[i].toString(),land.getColorZone());
            }
            catch (Exception e){
                fillTextBox(boardBoxesPanels[i], board.getBoardBoxes()[i].toString());
            }
        }
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    /**
     * Este metodo actualiza la interfaz grafica de un casillero
     * @param index Recibe el indice del casillero
     */
    public void updateBoxPanel(int index){
        mainFrame.update(boardBoxesPanels[index].getGraphics());
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    /**
     * Este metodo actualiza la interfaz grafica con la informacion del dinero de los jugadores
     * @param players Recibe la lista de jugadores
     */
    public void updatePlayersMoneyInfo(LinkedList<Player> players){
        if (playersMoney != null) matrixPanels[5][5].remove(playersMoney);
        String[] playersMoneyStr = new String[players.size()+2];
        playersMoneyStr[0] = "Dinero actual:";
        playersMoneyStr[1] = "   ";
        for (int i = 2; i < playersMoneyStr.length; i++) {
            playersMoneyStr[i] = players.get(i-2).getName()+": $"+players.get(i-2).getMoney();
        }
        var constraints = new GridBagConstraints();
        constraints.weighty = 5.0;
        constraints.weightx = 5.0;
        constraints.fill = GridBagConstraints.BOTH;

        playersMoney = new JList(playersMoneyStr);
        playersMoney.setBackground(new Color(191,218,193));
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) playersMoney.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        var font = playersMoney.getFont();
        playersMoney.setFont(new Font(font.getName(), font.getStyle(), 16));
        matrixPanels[5][5].add(playersMoney, constraints);
        matrixPanels[5][5].setBorder(new LineBorder(Color.BLACK));
    }

    /**
     * Este metodo obtiene la interfaz grafica de un casillero
     * @param index Recibe el indice del casillero
     * @return JPanel del casillero
     */
    public JPanel getBoxPanel(int index){ return boardBoxesPanels[index]; }

    /**
     * Este metodo cierra la interfaz grafica del tablero
     */
    public void close(){
        mainFrame.setVisible(false);
    }

    /**
     * Este metodo inicializa el JFrame de la interfaz grafica del tablero
     */
    private void initMainFrame(){
        mainFrame = new JFrame("Monopoly");
        mainFrame.setContentPane(mainPanel);
        mainFrame.setSize(900, 900);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Este metodo crea una division de tipo matriz en el JPanel principal para los casilleros del tablero
     * @param rows Recibe la cantidad de filas
     * @param columns Recibe la cantidad de columnas
     */
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

    /**
     * Inicializa la interfaz grafica de los casilleros del tablero
     */
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

    /**
     * Este metodo se encarga de llenar la interfaz grafica de un casillero con la informacion dada de un terreno
     * @param box Recibe el panel del casillero
     * @param text Recibe el texto del casillero
     * @param color Recibe el color a colocar en el casillero
     */
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

    /**
     * Este metodo se encarga de llenar la interfaz grafica de un casillero con la informacion dada de un casillero
     * @param box Recibe el panel del casillero
     * @param text Recibe el texto del casillero
     */
    private void fillTextBox(JPanel box, String text){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weighty = 30;
        constraints.weightx = 5;
        constraints.fill = GridBagConstraints.CENTER;

        JTextArea textArea = new JTextArea(text);
        textArea.setBackground(new Color(0,0,0,0));

        box.add(textArea, constraints);
        box.setBorder(new LineBorder(Color.BLACK));
    }
}
