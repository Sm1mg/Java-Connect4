import java.util.Scanner;

public class Game {

    private Board board;
    private Player p1;
    private Player p2;
    private Player currentPlayer;
    private Scanner in;

    public Game() {
        // Create scanner
        in = new Scanner(System.in);

        // Create a board from user input
        System.out.print("Please enter the width of the board: ");
        int x = in.nextInt();
        System.out.print("Please enter the height of the board: ");
        int y = in.nextInt();
        System.out.print("Please enter the threshold to win: ");
        int thr = in.nextInt();
        board = new Board(x, y, thr);
        // TODO get colors from users

        // Ask for name and symbols of player 1 and 2
        System.out.print("Player 1, please enter your name: ");
        String p1Name = "\u001B[31m" + getName() + "\u001B[0m";
        System.out.print(p1Name + ", please enter your Symbol: ");
        String p1Symbol = "\u001B[31m" + getSymbol() + "\u001B[0m";

        System.out.print("Player 2, please enter your name: ");
        String p2Name = "\u001B[33m" + getName() + "\u001B[0m";
        System.out.print(p2Name + ", please enter your Symbol: ");

        // Ask for p2 symbol until valid so we dont have the same symbol for 2 people
        String p2Symbol = new String();
        while (true) {
            p2Symbol = "\u001B[33m" + getSymbol() + "\u001B[0m";
            if (!p1Symbol.equals(p2Symbol)){
                break;
            }
            System.out.println("You cannot have the same Symbol as " + p1Name + "!");
        }

        // Create new Player objects
        p1 = new Player(p1Name, p1Symbol);
        p2 = new Player(p2Name, p2Symbol);
        currentPlayer = p1;

        playGame();
    }

    private void playGame() {

        while (true) {
            // individual turn goes here

            // 1: Call turnDisplay() to show the current board
            //    and say whose turn it is.
            turnDisplay();


            // 2: Call askForPlacement() and store the result
            //    to use when placing the piece.

            // 3: Call board.placePiece() and send it the placement
            //    and the currentPlayer as arguments.
            while(!board.placePiece(askForPlacement(), currentPlayer)){
                System.out.println("Invalid input, try again!");
            }

            // End loop if game is over, otherwise change the player and start again
            if(gameOver()) break;
            changePlayer();
        }
            
            // end the game with info and final board display
            System.out.println("Game over! " + currentPlayer + " wins!");
            board.display();

        }

        // Ask the current player where they would like to place their piece
        private int askForPlacement() {
            Piece[][] b = board.getBoard();
            while (true){
                System.out.println("Place your piece (1-" + b[0].length + "): ");
                int input = in.nextInt();
                if (input > 0 && input < b[0].length + 1){
                    return input;
                }
                System.out.println("Invalid input, try again!");
            }
        }

        // Display who's turn it is
        private void turnDisplay() {
            System.out.println("It is " + currentPlayer + "'s turn.");
            board.display();
        }

        // Swap which player is placing a piece
        private void changePlayer() {
            currentPlayer = currentPlayer == p1 ? p2 : p1;
        }

        // If game is over
        private boolean gameOver() {
            return board.isFull() || board.isWon();
        }

        // Safely pull a String from scanner
        private String getName(){
            in.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            return in.nextLine();
        }

        // Pull a string and make sure it will work as a Symbol
        private String getSymbol(){
            while (true){
                in.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                String input = in.nextLine();
                if (input.length() == 1){
                    return input;
                }
                System.out.println("Your Symbol has to be a single character!");

            }
        }

    }
