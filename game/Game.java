package game;


import java.util.Random;

public class Game {
    private final boolean log;

    private final Player[] players;
    private final Random random = new Random();

    public Game(final boolean log, final Player player1, final Player player2) {

        this.log = log;
        this.players = new Player[]{player1, player2};
    }

    public int play(Board board) {
        int turn = random.nextInt(0, 2);
        while (true) {

            final int result1 = move(board, players[turn]);
            if (result1 != -1) {
                if (result1 == 2) {
                    return players[(turn + 1) % 2].getNumber();
                }
                return result1;
            }
            final int result2 = move(board, players[(turn + 1) % 2]);
            if (result2 != -1) {
                if (result2 == 2) {
                    return players[(turn + 2) % 2].getNumber();
                }
                return result2;
            }
        }
    }

    private int move(final Board board, final Player player) {
        Move move = player.move(board.getPosition(), board.getTurn());
        Result result = board.makeMove(move);
        
        while (result == Result.BONUS) {
            log("Player " + player.getNumber() + " move: " + move);
            log("Position:\n" + board);
            log("Player " + player.getNumber() + " gets BONUS turn");
            move = player.move(board.getPosition(), board.getTurn());
            result = board.makeMove(move);
        }
        log("Player " + player.getNumber() + " move: " + move);
        log("Position:\n" + board);
        if (result == Result.WIN) {
            log("Player " + player.getNumber() + " won");
            return player.getNumber();
        } else if (result == Result.LOSE) {
            log("Player " + player.getNumber() + " lose");
            return 2;
        } else if (result == Result.DRAW) {
            log("Draw");
            return 0;
        } else {
            return -1;
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}
