public class Board {

    private int threshold;
    private Piece[][] board; // 2D array of pieces

    // Construct new Board class
    public Board(int length, int width, int threshold){
        board = new Piece[length][width];
        if(threshold > length || threshold > width){
            throw new IllegalArgumentException();
        }
        this.threshold = threshold;
    }

    // No threshold defined
    public Board(int length, int width){
        board = new Piece[length][width];
        threshold = 4;
    }

    // No args defined
    public Board(){
        board = new Piece[6][7];
        threshold = 4;
    }

    // Print the board's current values
    public void display() {
        int counter = 0;
        for (int i = 0; i < board[0].length; i++) {
            counter++;
            System.out.print("  " + counter + " ");
        }
        System.out.println();
        for (Piece[] row : board) {
            for (Piece p : row) {
                // new part padding
                System.out.print("| ");
                // print a number if there's nothing here
                if (p == null) {
                    System.out.print(" ");
                } else {
                    // otherwise print the piece (becomes the owner's symbol)
                    System.out.print(p);
                }
                // ending padding
                System.out.print(" ");
            }
            // EOL padding
            System.out.println("|");
        }
    }

    // TODO place and remove on the way down, wait and display it for a dropping
    // animation This method will handle placing a piece in the board
    public boolean placePiece(int loc, Player currentPlayer) {
        loc--;
        // If row is full
        if (board[0][loc] != null) {
            return false;
        }

        Piece p = currentPlayer.getPiece();

        for (int i = 0; i < board.length; i++) {
            // The next piece
            Piece nxt = board[i + 1][loc];

            // If the next one is a piece
            if (nxt != null) {
                // Place it on this one
                board[i][loc] = p;
                return true;
            }

            //If we're at the end of the board (and the next piece is null)
            if (i == board.length - 2) {
                // Place it at the next piece
                board[i + 1][loc] = p;
                return true;
            }
        }
        System.out.println(
            "Something has gone horribly wrong and we couldn't find a piece to place"
        );
        return false;
    }

    // This method will calculate if anyone won the game
    public boolean isWon() {
        // Check for horizontal matches
        for (int r = 0; r < board.length; r++) {
            // Check horizontal
            if (match(board[r])) {
                return true;
            }
        }

        // Check for vertical matches
        for (int c = 0; c < board[0].length; c++) {
            Piece[] column = new Piece[0]; // Create a new array to store the column to be checked
            for (int r = 0; r < board.length; r++) {
                column = pieceAdd(column, board[r][c]); // Append a new Piece to the array
            }
            if (match(column)) { // Hand the array to match()
                return true;
            }
        }

        //print first half
        int row = 0;
        int col;

        while (row < board.length) {
            Piece[] diag = new Piece[0];
            col = 0;
            int rowTemp = row;
            while (rowTemp >= 0 && col < board[rowTemp].length) {
                diag = pieceAdd(diag, board[rowTemp][col]);
                rowTemp--;
                col++;
            }
            row++;
            if(match(diag)){
                return true;
            }
        }

        //print second half
        col = 1;

        while (col < board.length) {
            Piece[] diag = new Piece[0];
            int colTemp = col;
            row = board.length-1;
            while (colTemp <= board.length-1 && colTemp < board[row].length) {
                diag = pieceAdd(diag, board[row][colTemp]);
                row--;
                colTemp++;
            }
            if(match(diag)){
                return true;
            }
            col++;
        }
        return false;
    }

    //TODO this
    //public boolean recursiveWin(byte last){
        
    //}

    // Return true if n Pieces in array match
    private boolean match(Piece[] a) {
        int counter = 0;
        for (int i = 0; i < a.length - 1; i++) {
            // If this Piece matches the next one By asking if the entry is null first, we
            // avoid calling null methods
            if (a[i] == null || !a[i].equals(a[i + 1])) {
                counter = 0;
                continue;
            }
            counter++; // It's another match, add to the counter
            // If we have n-in-a-row in the array
            if (counter >= threshold - 1) {
                return true;
            }
        }
        return false;
    }

    // This method will return true if the board is full of pieces
    public boolean isFull() {
        for (Piece[] row : board) {
            for (Piece col : row) {
                if (col == null) 
                    return false;
                }
            }
        return true;
    }

    // Return the board
    public Piece[][] getBoard() {
        return board;
    }

    // Append a Piece to a Piece array
    private Piece[] pieceAdd(Piece[] arr, Piece p) {
        Piece[] newArr = new Piece[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        newArr[newArr.length - 1] = p;
        return newArr;
    }

}
