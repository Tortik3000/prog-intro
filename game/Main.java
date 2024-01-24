package game;


import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int typeField;
        int m = 0;
        int n = 0;
        int diameter = 0;
        int k;

        while (true) {
            System.out.println("Enter field type (Rectangle: 1 ,Circle : 2)");
            try {
                typeField = Integer.parseInt(in.next());
                if (typeField != 1 & typeField != 2) {
                    throw new NumberFormatException("invalid input number");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage() + ", Expected 1 or 2");
            }
        }


        while (true) {
            try {
                if (typeField == 1) {
                    System.out.println("Enter parameters game: height, length, k (integers)");
                    m = Integer.parseInt(in.next());
                    n = Integer.parseInt(in.next());
                    k = Integer.parseInt(in.next());
                    if (m <= 0 || n <= 0 || k <= 0) {
                        throw new NumberFormatException("invalid input number");
                    }
                } else {
                    System.out.println("Enter parameters game: diameter, k  (integers)");
                    diameter = Integer.parseInt(in.next());
                    k = Integer.parseInt(in.next());
                    if (diameter <= 0 || k <= 0) {
                        throw new NumberFormatException("invalid input number");
                    }
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage() + ", Expected integers");
            }catch (NoSuchElementException e){
                System.out.println(e.getMessage() + "missing element");
            }

        }
        ArrayList<Player> players = new ArrayList<>();

//        players.add(new HumanPlayer(1));
//        players.add(new HumanPlayer(2));

        for (int i = 2; i < 20; i++) {
            players.add(new RandomPlayer(i));
        }

        ArrayList<Player> results;
        Tourney tourney = new Tourney(true, players);
        if (typeField == 1) {
            results = tourney.playOff(new TicTacToeBoard(m, n, k));
        } else {
            results = tourney.playOff(new TicTacToeBoard(diameter, k));
        }
        int countMatch = 0;
        int stage = 1;
        for (int i = results.size() - 1; i >= 0; i--) {
            System.out.println("place " + (stage / 2 + 1) + ":" + "player " + results.get(i).getNumber());
            countMatch += 1;
            if (stage == countMatch) {
                stage = stage * 2;
            }
        }
    }
}
