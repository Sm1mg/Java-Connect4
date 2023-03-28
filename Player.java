import java.util.ArrayList;
public class Player {

    private String name;
    private String symbol;
    private ArrayList<Piece> pieces;

    public Player(String name, String symbol) {
        // initialize everything in the constructor
        this.name = name;
        this.symbol = symbol;
        pieces = new ArrayList<Piece>();
        createPieces();
    }

    private void createPieces() {
        // add 21 pieces to the player's "hand"
        for(byte i = 0; i != 21; i++){
            pieces.add(new Piece(this));
        }
    }

    public Piece getPiece() {
        // return a piece from the player's "hand"
        return pieces.remove(0);
    }

    public String getSymbol() {
        // return the symbol field
        return symbol;
    }

    @Override public String toString() {
        // return the player's name
        return name;
    }

}
