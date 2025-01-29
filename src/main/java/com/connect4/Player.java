package  connect4;

public class Player {
    private final String name;
    private final char symbol;

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public int makeMove(Gameboard board, int column) {
        if (board.dropDisc(column, symbol)) {
            return column;
        }
        return -1; // Érvénytelen lépés
    }
}
