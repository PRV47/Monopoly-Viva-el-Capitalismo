package Monopoly;

import java.util.Scanner;

public class IOController {
    public static void showMenu(){
        Scanner keyboard = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("1. Mostrar informacion de los jugadores");
            System.out.println("2. Lanzar dados");
            System.out.println("3. Mostrar mis bienes");
            String input = keyboard.nextLine();
            if(input != null) {
                try {
                    int option = Integer.parseInt(input);

                    switch (option) {
                        case 1 -> {
                            Game.printPlayersInfo();
                            keyboard.nextLine();
                        }
                        case 2 -> {
                            Game.throwActualPlayerDices();
                            exit = true;
                        }
                        case 3 -> {
                            Game.printActualPlayerEstates();
                            keyboard.nextLine();
                        }
                        default -> System.out.println("Ingrese una opcion valida");
                    }
                }catch (Exception e){
                    System.out.println("Ingrese una opcion valida");
                }
            }
        }
    }

    public static boolean yesNoQuestion(String message){
        Scanner keyboard = new Scanner(System.in);
        boolean exit = false;
        boolean value = false;

        while (!exit) {
            System.out.println(message);
            String input = keyboard.nextLine();

            if (input != null) {
                input = input.toUpperCase();
                if ("Y".equals(input)){
                    value = true;
                    exit = true;
                } else if ("N".equals(input)) {
                    exit = true;
                }
                else System.out.println("Ingrese una opcion valida");
            }
        }

        return value;
    }

    public static void showJailOptions(Player player){
        Scanner keyboard = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("1. Pagar fianza ($50)");
            System.out.println("2. Lanzar dados");
            System.out.println("3. Usar carta de liberacion");
            String input = keyboard.nextLine();
            if(input != null) {
                try {
                    int option = Integer.parseInt(input);

                    switch (option) {
                        case 1 -> {
                            exit = player.canPay(50);
                            if (exit) player.setCanMove(true);
                            player.addMoney(-50);
                        }
                        case 2 -> {
                            Game.throwActualPlayerDices();
                            player.leaveTurn();
                            exit = true;
                        }
                        case 3 -> {
                            if (player.hasJailReleaseCard()) {
                                player.setHasJailReleaseCard(false);
                                player.setCanMove(true);
                                exit = true;
                            }
                            else System.out.println("No posee la carta de liberacion");
                        }
                        default -> System.out.println("Ingrese una opcion valida");
                    }
                }catch (Exception e){
                    System.out.println("Ingrese una opcion valida");
                }
            }
        }
    }
}
