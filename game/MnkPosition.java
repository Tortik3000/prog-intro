package game;

public class MnkPosition implements Position {
    private Position position;



    public MnkPosition(Position board){
        position = board;
    }

    @Override
    public boolean isValid(final Move move) {
        return position.isValid(move);
    }


    @Override
    public Cell getCell(int r, int c){
        return position.getCell(r, c);
    }




    @Override
    public String toString() {
        return position.toString();
    }

    @Override
    public int getM(){
        return position.getM();
    }

    @Override
    public int getN(){
        return position.getN();
    }
}
