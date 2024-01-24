package game;

import java.util.ArrayList;
import java.util.Random;

public class Tourney {

    private final ArrayList<Player> players;
    private final boolean log;
    private final ArrayList<Player> results = new ArrayList<>();
    private final Random random = new Random();

    public Tourney(final boolean log, ArrayList<Player> players) {
        this.players = players;
        this.log = log;
    }

    public ArrayList<Player> playOff(Board board) {
        int size0 = players.size();
        for (int i = 0; i < Math.ceil(Math.log(size0) / Math.log(2)); i++) {
            int k = 0;
            do {
                int result;
                int numberPlayer2 = random.nextInt(k + 1, players.size());
                Game game = new Game(log, players.get(k), players.get(numberPlayer2));
                do {
                    board.reset();
                    result = game.play(board);
                    System.out.println("Game result: " + result);
                } while (result == 0);

                if (result == players.get(numberPlayer2).getNumber()) {
                    results.add(players.get(k));
                    players.set(k, players.get(numberPlayer2));
                } else {
                    results.add(players.get(numberPlayer2));
                }
                players.remove(numberPlayer2);
                k += 1;
            } while ((players.size() & players.size() - 1) != 0);
        }
        results.add(players.get(0));
        return results;
    }
}
