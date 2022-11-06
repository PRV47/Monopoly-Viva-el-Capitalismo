package Monopoly;

import Monopoly.BoardBoxes.*;
import Monopoly.Cards.ICard;
import Monopoly.Cards.JailReleaseCard;
import Monopoly.Cards.MoneyCard;
import Monopoly.Cards.TransportCard;
import Monopoly.Estates.Estate;
import Monopoly.Estates.Ferry;
import Monopoly.Estates.Land;
import Monopoly.Estates.Service;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Clase encargada de contener e inicializar casilleros, bienes y cartas.
 */
public class Board {
    private BoardBox[] boardBoxes;
    private Estate[] estates;
    private Color[] zones;
    private ICard[] cards;

    public BoardBox[] getBoardBoxes() {
        return boardBoxes;
    }

    /**
     * Este metodo inicializa los valores de los casilleros
     */
    public void initBoardBoxes(){
        initZones();
        initEstates();
        initCards();
        boardBoxes = new BoardBox[]{new BoardBox("<- GO"), new EstateBox(estates[0]), new CardBox("Carta", getRandomCard()),
                new EstateBox(estates[1]), new TaxBox("Impuesto", 200), new EstateBox(estates[2]),
                new EstateBox(estates[3]), new CardBox("Carta", getRandomCard()), new EstateBox(estates[4]),
                new EstateBox(estates[5]), new BoardBox("Carcel"), new EstateBox(estates[6]), new EstateBox(estates[7]),
                new EstateBox(estates[8]), new EstateBox(estates[9]), new EstateBox(estates[10]), new EstateBox(estates[11]),
                new CardBox("Carta", getRandomCard()), new EstateBox(estates[12]),  new EstateBox(estates[13]),
                new BoardBox("Estacionamiento"), new EstateBox(estates[14]), new CardBox("Carta", getRandomCard()),
                new EstateBox(estates[15]), new EstateBox(estates[16]), new EstateBox(estates[17]), new EstateBox(estates[18]),
                new EstateBox(estates[19]), new EstateBox(estates[20]), new EstateBox(estates[21]), new GoToJailBox("Ve a la carcel", 10),
                new EstateBox(estates[22]), new EstateBox(estates[23]), new CardBox("Carta", getRandomCard()), new EstateBox(estates[24]),
                new EstateBox(estates[25]), new CardBox("Carta", getRandomCard()), new EstateBox(estates[26]),
                new TaxBox("Impuesto", 100), new EstateBox(estates[27])};
    }

    /**
     * Este metodo inicializa los valores de las zonas de los terrenos
     */
    private void initZones(){
        zones = new Color[]{new Color(159,87,56), new Color(163,207,236),new Color(204,77,176),
                new Color(240,135,68),new Color(204, 63, 80),new Color(215,200,164),
                new Color(33,171,78),new Color(0,122,184),};
    }

    /**
     * Este metodo inicializa los valores de los bienes
     */
    private void initEstates(){
        estates = new Estate[]{new Land("AV Mediterraneo", 60, zones[0]),
                new Land("AV Baltica", 60, zones[0]), new Ferry("Ferro. Reading", 200),
                new Land("AV Oriental", 100, zones[1]), new Land("AV Vermont", 100, zones[1]),
                new Land("AV Connecticut", 180, zones[1]), new Land("Plaza San Carlos", 140, zones[2]),
                new Service("Compania electrica",150), new Land("AV Status", 140, zones[2]),
                new Land("AV Virginia", 160, zones[2]), new Ferry("Ferro. Pennsylvania", 200),
                new Land("Plaza ST James", 180, zones[3]), new Land("AV Tennessee", 180, zones[3]),
                new Land("AV Nueva York", 200, zones[3]), new Land("AV Kentucky", 220, zones[4]),
                new Land("AV Indiana", 220, zones[4]), new Land("AV Illinois", 240, zones[4]),
                new Ferry("Ferro. B. & O.", 200), new Land("AV Atlantico", 260, zones[5]),
                new Land("AV Ventnor", 260, zones[5]), new Service("Compania de agua",150),
                new Land("Jardines Marvin", 280, zones[5]), new Land("AV Pacifico", 300, zones[6]),
                new Land("AV Carolina del Norte", 300, zones[6]), new Land("AV Pennsylvania", 320, zones[6]),
                new Ferry("Ferro. Via Rapida", 200), new Land("Plaza Park", 350, zones[7]),
                new Land("El Muelle", 400, zones[7])};
    }

    /**
     * Este metodo inicializa los valores de las cartas
     */
    private void initCards(){
        cards = new ICard[]{new TransportCard(5), new TransportCard(10), new TransportCard(15),
                new TransportCard(20), new TransportCard(25), new TransportCard(30),
                new TransportCard(35), new MoneyCard(100), new MoneyCard(200), new MoneyCard(-100),
                new MoneyCard(-50), new MoneyCard(300), new MoneyCard(-300), new JailReleaseCard()};
    }

    /**
     * Este metodo obtiene una carta aleatoria
     * @return ICard carta aleatoria
     */
    private ICard getRandomCard() {
        return cards[ThreadLocalRandom.current().nextInt(0, cards.length)];
    }
}
