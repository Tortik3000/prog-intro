package game;

import java.util.Arrays;
import java.util.Map;

public class TicTacToeBoard implements Position, Board {


    protected final int m, n, k;


    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.',
            Cell.S, ' '
    );

    protected final Cell[][] cells;

    protected Cell turn;

    private int maxTurn = 0;
    private int countTurn = 0;


    public TicTacToeBoard(int m, int n, int k) {
        this.k = k;
        this.m = m;
        this.n = n;
        this.cells = new Cell[m][n];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
        maxTurn = m * n;
    }

    public TicTacToeBoard(int diam, int k) {
        this.k = k;
        this.m = diam;
        this.n = diam;

        this.cells = new Cell[diam][diam];
        double center = (diam % 2 == 0) ? diam / 2 + 0.5 : diam / 2 + 1;
        double radius = center - 0.5;
        for (int i = 1; i < diam + 1; i++) {
            for (int j = 1; j < diam + 1; j++) {
                if (Math.sqrt((i - center) * (i - center) + (j - center) * (j - center)) <= radius) {
                    cells[i - 1][j - 1] = Cell.E;
                    maxTurn += 1;
                } else {
                    cells[i - 1][j - 1] = Cell.S;
                }
            }
        }
        turn = Cell.X;
    }

    @Override
    public Cell getCell(int r, int c) {
        return cells[r][c];
    }

    @Override
    public int getM() {
        return m;
    }

    @Override

    public int getN() {
        return n;
    }

    @Override
    public Cell getTurn() {
        return turn;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < m
                && 0 <= move.getColumn() && move.getColumn() < n
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == move.getValue();
    }

    @Override
    public Position getPosition() {
        return new MnkPosition(this);
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }

        cells[move.getRow()][move.getColumn()] = move.getValue();

        countTurn++;

        int inDiag1 = 0;
        int inDiag2 = 0;
        int inRow = 0;
        int inColumn = 0;
        int left = Math.max(move.getColumn() - k + 1, 0);
        int right = Math.min(move.getColumn() + k - 1, n - 1);
        int up = Math.max(move.getRow() - k + 1, 0);
        int down = Math.min(move.getRow() + k - 1, m - 1);

        for (int i = 0; i < 4; i++) {

        }
        int lastR = 0;
        int lastL = 0;
        for (int u = left; u <= right; u++) {

            if (cells[move.getRow()][u] == turn) {
                if (u >= move.getColumn() -4 && u < move.getColumn()){
                    lastL ++;
                }
                if (u <= move.getColumn() +4 && u > move.getColumn()){
                    lastR ++;
                }

                inRow++;
            } else {
                inRow = 0;
            }
            if (inRow >= k) {
                return Result.WIN;
            }

            if (inRow >= 4 && countTurn != maxTurn && (lastL < 4 && lastR < 4)) {
                return Result.BONUS;
            }
        }

        int lastU = 0;
        int lastD = 0;
        for (int u = up; u <= down; u++) {

            if (cells[u][move.getColumn()] == turn) {

                if (u >= move.getRow() -4 && u < move.getRow()){
                    lastU ++;
                }
                if (u <= move.getRow() +4 && u > move.getRow()){
                    lastD ++;
                }
                inColumn++;
            } else {
                inColumn = 0;
            }
            if (inColumn >= k) {
                return Result.WIN;
            }



            if (inColumn >= 4 && countTurn != maxTurn && (lastU < 4 && lastD < 4)) {
                return Result.BONUS;
            }
        }

        int lastUpR = 0;
        int lastUpL = 0;
        int lastDownR = 0;
        int lastDownL = 0;
        for (int u = -k + 1; u <= k - 1; u++) {

            if (m > move.getRow() - u && move.getRow() - u >= 0) {
                if (n > move.getColumn() + u && move.getColumn() + u >= 0) {
                    if (cells[move.getRow() - u][move.getColumn() + u] == turn) {
                        if(u>=-4 && u <0){
                            lastDownL++;
                        }
                        if(u<=4 && u>0){
                            lastUpR++;
                        }
                        inDiag2++;
                    } else {
                        inDiag2 = 0;
                    }
                }
            }

            if (m > move.getRow() + u && move.getRow() + u >= 0) {
                if (n > move.getColumn() + u && move.getColumn() + u >= 0) {
                    if (cells[move.getRow() + u][move.getColumn() + u] == turn) {
                        if(u>=-4 && u <0){
                            lastUpL++;
                        }
                        if(u<=4 && u>0){
                            lastDownR++;
                        }
                        inDiag1++;
                    } else {
                        inDiag1 = 0;
                    }
                }
            }
            if (inDiag2 >= k || inDiag1 >= k) {
                return Result.WIN;
            }

            if ((inDiag2 >= 4 || inDiag1 >= 4) && countTurn != maxTurn &&
                    (lastUpL < 4 && lastDownL < 4 && lastUpR < 4 && lastDownR < 4)) {
                return Result.BONUS;
            }
        }
        if (countTurn == maxTurn) {
            return Result.DRAW;
        }
        turn = turn == Cell.X ? Cell.O : Cell.X;

        return Result.UNKNOWN;

    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(" ");
            sb.append(i);
        }
        for (int r = 0; r < m; r++) {
            sb.append("\n");
            sb.append(r);
            for (int c = 0; c < n; c++) {
                sb.append(SYMBOLS.get(cells[r][c]));
                sb.append(" ");
            }
        }
        return sb.toString();

    }

    @Override
    public void reset() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (cells[i][j] != Cell.S) {
                    cells[i][j] = Cell.E;
                }
            }
        }
        countTurn = 0;
        turn = Cell.X;
    }
}
