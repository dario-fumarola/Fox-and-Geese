// Check Game file for original comments


import java.util.Scanner;

import javax.swing.text.Position;

public class GameEx {
    // The following five constants were defined in the starter code (kt54)
    private static String FOXPLAYS_MSG      = "Fox plays. Enter move:";
    private static String GEESEPLAY_MSG     = "Geese play. Enter move:";
    private static String ILLEGALMOVE_MSG   = "Illegal move!";
    private static String FOXWINS_MSG       = "Fox wins!";
    private static String GEESEWIN_MSG      = "Geese win!";

    private BoardEx gameBoard;
    private boolean gTurn = true;    // Starting is Guse turn. Only scenarios are guse are moving, or they are not.

    // Minimal constructor. Expand as needed (kt54)
    public GameEx() {
        gameBoard = new BoardEx();
    }

    // Build on this method to implement game logic.
    public void play() {

        Scanner reader = new Scanner(System.in);

        gameBoard = new BoardEx();

        boolean done = false;

        while(!done) {


            boolean fWinner = fWin();
            boolean gWinner = gWin();

            if (fWinner == true || gWinner == true) {
                done = true;
                break;
            }

            gameBoard.printBoard();

            if (gTurn) System.out.println(GEESEPLAY_MSG);    // Messages basing on whose turn
            if (!gTurn) System.out.println(FOXPLAYS_MSG);


            String command1 = reader.nextLine().trim();

            if (command1.equals("quit")) {
                break;
            }

            String command2 = reader.nextLine().trim();
            String command3 = reader.nextLine().trim();
            String command4 = reader.nextLine().trim();

            int x1 = Integer.parseInt(command1);
            int y1 = Integer.parseInt(command2);
            int x2 = Integer.parseInt(command3);
            int y2 = Integer.parseInt(command4);

            if (!moves(x1, y1, x2, y2)) {    //If moves are illegal (false), print the message
                System.out.println(ILLEGALMOVE_MSG);
            } else {
            gTurn = !gTurn;    // Switch turns
            }

        }
    }

    private boolean moves(int x1, int y1, int x2, int y2) {    // Movements can be only legal or illegal
        int xChange = (int) (Math.abs(x1 - x2));
        int yChange = (int) (Math.abs(y1 - y2));

        int movement;
        if ((xChange < 2 && yChange < 2) && (xChange == 1 || yChange == 1)) {   // Conditions for legal move    // Needs to move within 1 coordinate in the grid
                    movement = 1; // Distance for legal movements
                } else {
                movement = 0;    // If illegal, it doesen't move
            }
        
        char[][] board = gameBoard.getBoard();

        if (x1 < 0 || x1 >= board.length  || x2 < 0 || x2 >= board.length 
        || y1 < 0 || y1 >= board.length || y2 < 0 || y2 >= board.length) return false;

        if (!gTurn) {
            if (capt(x1, y1, x2, y2)) return true;
        }

        if(board[x2][y2] == Board.FREE && movement == 1
         && ((gTurn && board[x1][y1] == Board.GOOSE) || !gTurn && board[x1][y1] == Board.FOX)) {
            board[x2][y2] = board[x1][y1];
            board[x1][y1] = Board.FREE;    // Old coordinates are now empty
            gameBoard.setBoard(board);
            return true;
        }
    else return false;
    }


    private boolean capt(int x1, int y1, int x2, int y2) {
        int xChange = x2 - x1;
        int yChange = y2 - y1;

        char[][] board = gameBoard.getBoard();

        if (board[x1 + xChange / 2][y1 + yChange / 2] == Board.GOOSE) {
            board[x2][y2] = board[x1][y1];
            board[x1][y1] = Board.FREE;
            board[x1 + xChange / 2][y1 + yChange / 2] = Board.FREE;
            gameBoard.setBoard(board);
            return true;
        }
        else return false;
    }


    private boolean fWin() {
        for(char[] row : gameBoard.getBoard()) {
            for (char character : row) {
                if (character == Board.GOOSE) return false;
            }
        } 

        System.out.println(FOXWINS_MSG);
        return true;
    }

    private boolean gWin() {
        int x, y;
        int [] location = gameBoard.locationFox();
        x = location[0];
        y = location[1];

        char[][] board = gameBoard.getBoard();

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i != x && j != y && i > 0 && j > 0 && i < board.length && j < board[0].length)
                    if (board[i][j] == Board.FREE) return false;
            }
        }


        for (int i = x - 2; i <= x + 2; i += 2) {
            for (int j = y - 2; j <= y + 2; j += 2) {
                if (i != x && j != y && i > 0 && j > 0 && i < board.length && j < board[0].length)
                if (board[i][j] == Board.FREE) return false;
            }
        }


        System.out.println(GEESEWIN_MSG);
        return true;
    }



}