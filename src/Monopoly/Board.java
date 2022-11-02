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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Board {
    private ArrayList<BoardBox> boardBoxes;
    private Estate[] estates;
    private Color[] zones;
    private ICard[] cards;

    public Board() {
        initBoardBoxes();
    }

    public ArrayList<BoardBox> getBoardBoxes() {
        return boardBoxes;
    }

    private void initBoardBoxes(){
        initZones();
        initEstates();
        initCards();
        BoardBox[] boxes = new BoardBox[]{new BoardBox("<- GO"), new EstateBox(estates[0]), new CardBox("Carta", randomCard()),
                new EstateBox(estates[1]), new TaxBox("Impuesto", 200), new EstateBox(estates[2]),
                new EstateBox(estates[3]), new CardBox("Carta", randomCard()), new EstateBox(estates[4]),
                new EstateBox(estates[5]), new BoardBox("Carcel"), new EstateBox(estates[6]), new EstateBox(estates[7]),
                new EstateBox(estates[8]), new EstateBox(estates[9]), new EstateBox(estates[10]), new EstateBox(estates[11]),
                new CardBox("Carta", randomCard()), new EstateBox(estates[12]),  new EstateBox(estates[13]),
                new BoardBox("Estacionamiento"), new EstateBox(estates[14]), new CardBox("Carta", randomCard()),
                new EstateBox(estates[15]), new EstateBox(estates[16]), new EstateBox(estates[17]), new EstateBox(estates[18]),
                new EstateBox(estates[19]), new EstateBox(estates[20]), new EstateBox(estates[21]), new GoToJailBox("Ve a la carcel", 10),
                new EstateBox(estates[22]), new EstateBox(estates[23]), new CardBox("Carta", randomCard()), new EstateBox(estates[24]),
                new EstateBox(estates[25]), new CardBox("Carta", randomCard()), new EstateBox(estates[26]),
                new TaxBox("Impuesto", 100), new EstateBox(estates[26])};
        boardBoxes = new ArrayList<>(Arrays.stream(boxes).toList());
    }

    private void initZones(){
        zones = new Color[]{new Color(159,87,56), new Color(163,207,236),new Color(204,77,176),
                new Color(240,135,68),new Color(186,78,88),new Color(215,200,164),
                new Color(33,171,78),new Color(0,122,184),};
    }

    private void initEstates(){
        estates = new Estate[]{new Land("AV Mediterraneo", 60, 50, zones[0]),
                new Land("AV Baltica", 60, 50, zones[0]), new Ferry("Reading", 200, 50),
                new Land("AV Oriental", 100, 50, zones[1]), new Land("AV Vermont", 100, 50, zones[1]),
                new Land("AV Connecticut", 180, 50, zones[1]), new Land("Plaza San Carlos", 140, 50, zones[2]),
                new Service("Electric company",150, 50), new Land("AV Status", 140, 50, zones[2]),
                new Land("AV Virginia", 160, 50, zones[2]), new Ferry("Pennsylvania", 200, 50),
                new Land("Plaza ST James", 180, 50, zones[3]), new Land("AV Tennessee", 180, 50, zones[3]),
                new Land("AV Nueva York", 200, 50, zones[3]), new Land("AV Kentucky", 220, 50, zones[4]),
                new Land("AV Indiana", 220, 50, zones[4]), new Land("AV Illinois", 240, 50, zones[4]),
                new Ferry("B. & O.", 200, 50), new Land("AV Atlantico", 260, 50, zones[5]),
                new Land("AV Ventnor", 260, 50, zones[5]), new Service("Compania de agua",150, 50),
                new Land("Jardines Marvin", 280, 50, zones[5]), new Land("AV Pacifico", 300, 50, zones[6]),
                new Land("AV Carolina del Norte", 300, 50, zones[6]), new Land("AV Pennsylvania", 320, 50, zones[6]),
                new Ferry("Via Rapida", 200, 50), new Land("Plaza Park", 350, 50, zones[7]),
                new Land("El Muelle", 400, 50, zones[7])};
    }

    private void initCards(){
        cards = new ICard[]{new TransportCard(5), new TransportCard(10), new TransportCard(15),
                new TransportCard(20), new TransportCard(25), new TransportCard(30),
                new TransportCard(35), new MoneyCard(100), new MoneyCard(200), new MoneyCard(-100),
                new MoneyCard(-50), new MoneyCard(300), new MoneyCard(-300), new JailReleaseCard()};
    }

    private ICard randomCard() {
        return cards[ThreadLocalRandom.current().nextInt(0, cards.length)];
    }
}
