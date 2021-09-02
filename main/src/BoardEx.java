// Check board file for original comments


public class BoardEx {


    private static final int  BOARD_SIZE = 10;
    private static final int NUMBER_FOXES = 3;
    static final char FREE    = '.';
    static final char INVALID = ' ';
    static final char FOX     = '*';
    static final char GOOSE   = 'o';

    private int boardsize;
    private int numFoxes;
    private char[][] board;


    public BoardEx() {
        this.boardsize = BOARD_SIZE;
        this.numFoxes = NUMBER_FOXES;

        board = new char[boardsize][boardsize];

        // Clear all playable fields
        for(int x=0; x<boardsize; x++)
            for(int y=0; y<boardsize; y++)
                board[x][y] = FREE;


        // Make fox
        for (int i = 0; i <= numFoxes; i++)
            board[boardsize/2][boardsize/2 + i] = FOX;

        // Make geese
        for (int i = 0; i <= boardsize/2; i++) {
            for (int j = 0; j < boardsize; j++) {
                if(!(i == boardsize && j >= boardsize / 2 - 1 && j <= boardsize / 2 + 1)) board[j][i] = GOOSE;
            }
        }

        // Make null spaces
        for (int i = 0; i < boardsize; i++)
        for (int j = 0; j < boardsize; j++)
            if ((i < boardsize / 2 - 1 || i > boardsize/ 2 + 1) && (j < boardsize / 2 - 1 || j > boardsize / 2 + 1))
                board[i][j] = INVALID;

        }



    // Prints the board. This method was provided with the starter code. Better not modify to ensure
    // output consistent with the autochecker 
    public void printBoard() {

        for(int y=0; y < boardsize; y++)
        {
            for(int x=0; x < boardsize; x++) {
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
                if (board[i][j] == Board.FOX) {
                    location[0] = i;
                    location[1] = j;
                }
            }
        }
        return location;

    }
}
