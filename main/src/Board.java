public class Board {

    // The following five constants were defined in the starter code (kt54)
    private static final int  BOARD_SIZE = 7;
    static final char FREE    = '.';
    static final char INVALID = ' ';
    static final char FOX     = '*';
    static final char GOOSE   = 'o';

    private int boardsize;
    private char[][] board;

    // Default constructor was provided by the starter code. Extend as needed (kt54) 
    public Board() {
        this.boardsize = BOARD_SIZE;

        board = new char[boardsize][boardsize];

        // Clear all playable fields
        for(int x=0; x<boardsize; x++)
            for(int y=0; y<boardsize; y++)
                board[x][y] = FREE;


        // Make fox
        board[boardsize/2][boardsize/2 + 2] = FOX;

        // Make geese
        for (int i = 0; i <= 3.5; i++) {        /// Half of the board rows
            for (int j = 0; j < 7; j++) {       /// All columns
                if(!(i == 3 && j >= 2 && j <= 4)) board[j][i] = GOOSE;    /// Except in the corners for nulls
            }
        }

        // Make null spaces
        for (int i = 0; i < 7; i++)    /// In all 4 corners of the board
        for (int j = 0; j < 7; j++)
            if ((i < 2 || i > 4) && (j < 2 || j > 4))    /// conditions for corners
                board[i][j] = INVALID;

        }



    // Prints the board. This method was provided with the starter code. Better not modify to ensure
    // output consistent with the autochecker (kt54)
    public void printBoard() {

        for(int y=0; y<boardsize; y++)
        {
            for(int x=0; x<boardsize; x++) {
                System.out.print(" ");
                switch(board[x][y]) {
                    case FREE: 
                        System.out.print(".");
                        break;
                    case FOX:
                        System.out.print("*");
                        break;
                    case GOOSE:
                        System.out.print("o");
                        break;
                    default:
                        System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    public int[] locationFox() {
        int[] location = new int [2];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == Board.FOX) {    /// Look for coordinates in the array that contain fox
                    location[0] = i;   /// Record these coordinates
                    location[1] = j;
                }
            }
        }
        return location;

    }
}
