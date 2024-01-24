package game;

public interface Board {
    Cell getTurn();
    Position getPosition();

    Result makeMove(Move move);
    void reset();
}
