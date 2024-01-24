package game;


public class SequentialPlayer implements Player {
    public final int number;

    public SequentialPlayer(int number){
        this.number = number;
    }
    @Override
    public Move move(final Position position, final Cell cell) {
        Board board = (Board) position;
       //board.makeMove();

        for (int r = 0; r < position.getM(); r++) {
            for (int c = 0; c < position.getN(); c++) {
                final Move move = new Move(r, c, cell);
                if (position.isValid(move)) {
                    return move;
                }
            }
        }
        throw new IllegalStateException("No valid moves");
    }
    @Override
    public int getNumber(){
        return number;
    }
}
