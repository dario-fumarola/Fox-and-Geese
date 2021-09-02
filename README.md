# Fox-and-Geese

This project makes a simple interactive version of the Fox and Geese game using Java.

## Description

The input  will be a set of four coordinates (initial x and y, and final x and y), which moves the player from one point to another. These movements are legal only in adjacent spaces, and within the board. The goal is for geese to surround the foxes, and for the fox to capture all geese. A fox captures a goose if it can “jump over it, so ending up in an empty box.


First, I made a loop that placed goose up to the middle row, for every column, in the board array. Then, I made a similar loop, but within the conditions that describe the four corners, so making these spaces invalid. The fox was at the centre of the map. 

```
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
```


In the game class, I recorded the four coordinates as integers from the user – with the option to type “quit” and end the game. These coordinates describe a movement that could be legal or not. For this reason, I made a Boolean method that used the coordinates as parameters. The method recorded the changes in x and y coordinates, which both had to be less than two (otherwise the new position would be too far). In addition, the method prevented negative coordinates or position outside of the board. A last if-statement checked that the new position is empty, and set the previous coordinates empty, moving the player. If the conditions for a legal move are met, the Boolean turns true, and switches turn, otherwise, a message is printed, and the user needs to input again coordinates. Another feature of the game is the fox’s ability to capture goose. I declared a new Boolean method that checked if a goose was standing in between the old and new coordinates. In that case, it made the position empty, so erasing the goose from the map. 

```
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

        if (x1 < 0 || x1 >= board.length  || x2 < 0 || x2 >= board.length    // Can t go into negative coordinates, or outside the board
        || y1 < 0 || y1 >= board.length || y2 < 0 || y2 >= board.length) return false;

        if (!gTurn) {
            if (capt(x1, y1, x2, y2)) return true;    // capture is a legal movement
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
```


Lastly, I had to describe the scenarios that would end the game: either fox or geese victory. Foxes would win if no geese were standing, so I made a method that returned true if no character in the board array was a goose. Geese would win if two layers of geese or invalid blocks surrounded the fox, so it could not capture or move. In the board class, I made a method that checked all coordinates in the board array, and recorded the ones that contained the fox. Once, I registered the fox location – back in the game class- I made if-statements that checked if any block within two coordinates from the fox were empty. If no, the method returned true, and the game ended. For part 3, I copied the board class, and changed the numerical constraints that I hard-coded to describe the 7x7 board. I changed each number with its equivalent in terms of the variable board size, which could be arbitrarily changed. Similarly, I made a variable for the number of foxes, which – through a for loop - would be generated at distances of one coordinate. (Modify the constants to change these conditions).

```
    private static final int  BOARD_SIZE = 10;
    private static final int NUMBER_FOXES = 3;
    static final char FREE    = '.';
    static final char INVALID = ' ';
    static final char FOX     = '*';
    static final char GOOSE   = 'o';
```


A cool implementation would be to develop an AI which plays against the user.

### Dependencies

The program inherits from the other classes and tests in the folder, so make sure to not change names or positions in directories.

You need Java installed and some code editors (such as Visual Studio). Then, run the tests to check if the game works correctly: you should get similar outputs

![newconditions](https://user-images.githubusercontent.com/86791449/131861513-61dfe9f9-8990-4366-bb8a-3ab59a993598.png)

Remember to enter the coordinates of where you want to move the pointer. Then, alternate between turns.


## Version History

* 0.1
    * Initial Release

## License

This project is licensed under the Unlicense License - see the LICENSE.md file for details
