package game;

import java.io.PrintStream;
import java.util.Scanner;


public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;
    public final int number;

    public HumanPlayer(final PrintStream out, final Scanner in, int number) {
        this.out = out;
        this.in = in;
        this.number = number;
    }

    public HumanPlayer(int number) {
        this(System.out, new Scanner(System.in), number);
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            out.println("Position");
            out.println(position);
            out.println(cell + "'s move");
            out.println("Enter row and column");
            int r, c;
            while (true) {

                try {
                    String rStr = in.next();
                    String cStr = in.next();
                    r = Integer.parseInt(rStr);
                    c = Integer.parseInt(cStr);
                    if (r < 0 || r >= position.getM() || c < 0 || c >= position.getN()) {
                        throw new NumberFormatException("invalid input number");
                    }else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.print("invalid input: ");
                    System.out.println(e.getMessage());
                }

            }
            final Move move = new Move(r, c, cell);
            if (position.isValid(move)) {
                return move;
            }
            final int row = move.getRow();
            final int column = move.getColumn();
            out.println("Move " + move + " is invalid");
        }
    }

    @Override
    public int getNumber(){
        return number;
    }
}
