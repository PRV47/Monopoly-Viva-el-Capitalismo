package Monopoly;

import java.util.Arrays;
import java.util.Scanner;

public class IOController {
    public static void pressEnterToContinue(String message){
        System.out.println(message);
        new Scanner(System.in).nextLine();
    }

    public static void showMenu(){
        Scanner keyboard = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("1. Mostrar informacion de los jugadores");
            System.out.println("2. Lanzar dados");
            System.out.println("3. Mostrar mis bienes");
            System.out.println("4. Hipotecar bienes");
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
                        case 4 -> {
                            showMortgageOptions(Game.GetCurrentTurnPlayer());
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

    public static int askForPlayersQuantity(){
        Scanner keyboard = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Elija la cantidad de jugadores (2-4)");
            String input = keyboard.nextLine();
            if(input != null) {
                try {
                    int option = Integer.parseInt(input);

                    if (option >= 2 && option <= 4) return option;
                    else System.out.println("Ingrese una opcion valida");

                }catch (Exception e){
                    System.out.println("Ingrese una opcion valida");
                }
            }
        }
        return 0;
    }

    public static String askForPlayerName(String message){
        Scanner keyboard = new Scanner(System.in);
        System.out.println(message);
        return keyboard.nextLine();
    }

    public static BoardToken askForPlayerToken(String message){
        Scanner keyboard = new Scanner(System.in);
        boolean exit = false;
        // Generate String[] from Enum
        String[] tokens = Arrays.stream(BoardToken.class.getEnumConstants()).map(Enum::name).toArray(String[]::new);

        while (!exit) {
            System.out.println(message);
            for (int i = 0; i < tokens.length; i++) {
                System.out.println((i+1)+". "+tokens[i]);
            }

            String input = keyboard.nextLine();
            if(input != null) {
                try {
                    int option = Integer.parseInt(input);
                    if (option >= 1 && option <= tokens.length)
                        return BoardToken.valueOf(tokens[option-1]);
                    else System.out.println("Ingrese una opcion valida");
                }catch (Exception e){
                    System.out.println("Ingrese una opcion valida");
                }
            }
        }
        return BoardToken.valueOf(tokens[0]);
    }

    public static void showMortgageOptions(Player player){
        Scanner keyboard = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Seleccione el bien que desea hipotecar (1-"+player.getEstates().size()+")");
            Game.printActualPlayerEstates();
            String input = keyboard.nextLine();
            if(input != null) {
                try {
                    int option = Integer.parseInt(input);
                    player.mortgageEstate(option-1);
                    exit = true;
                }catch (Exception e){
                    System.out.println("Ingrese una opcion valida");
                }
            }
        }
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
                            player.subtractMoney(50, false);
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
