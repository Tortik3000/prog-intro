package game;

import java.util.Random;

public class RandomPlayer implements Player {
    private final Random random;
    private final int number;

    public RandomPlayer(final Random random, int number) {
        this.random = random;
        this.number = number;
    }

    public RandomPlayer(int number) {
        this(new Random(), number);
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            int r = random.nextInt(position.getM());
            int c = random.nextInt(position.getN());
            final Move move = new Move(r, c, cell);
            if (position.isValid(move)) {
                return move;
            }
        }
    }

    @Override
    public int getNumber(){
        return number;
    }
}
