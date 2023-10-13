package tictactoe;

public class TicTacToeP extends boardgame.BoardGame implements boardgame.Saveable {

    private String playerTurn;
    private int depth = 0;
    private TicTacToeBoard board;
    private static int[] posToIndex = {0, 2, 4, 12, 14, 16, 24, 26, 28};

    // private char[] board = {'0', '|', '1', '|', '2', '\n', '-', '+', '-', '+',
    //         '-', '\n', '3', '|', '4', '|', '5',
    //         '\n', '-', '+', '-', '+', '-', '\n', '6', '|', '7', '|', '8', '\n'};

    // constructors
    public TicTacToeP() {
        super(3, 3);   
        playerTurn = "X";
        board = new TicTacToeBoard();
        setGrid(board);

    }    
    public TicTacToeBoard getBoard(){
        return this.board;
    }

    public int[] getPosToIndex() {
        return posToIndex;
    }

    

    // mutators
    public void setTurn() {
        if (this.playerTurn == "X") {
            this.playerTurn = "O";
        } else {
            this.playerTurn = "X";
        }
    }

    // accessor
    public String getPlayerTurn() {
        return playerTurn;
    }

    // instance methods
    public boolean isPosOccupied(int position) {
        /*
        1->1,1   0 0+0   0
        2->2,1   1 0+1 . 1 
        3->3,1   2 0+2 . 2
        4->1,2   3 1+0 . 1
        5->2,2   4 1+1 . 2
        6->3,2   5 1+2
        7->1,3   6 2+0
        8->2,3   7 2+1
        9->3,3   8 2+2
        */
        int posDown = (position/getWidth())+1; 
        int posAcross = ((position-1) % (getWidth()))+1;
        if(getCell(posAcross, posDown).equals(" ")){
            return false;
        }
        return true;
    }

    @Override
    public boolean takeTurn(int across, int down, int input) {        
       
        return takeTurn(across, down, String.valueOf(input));
    }

    @Override
    public boolean takeTurn(int across, int down, String input) {

        this.board.setValue(across, down, input);
        this.playerTurn = input == "X"?"O":"X";
        return true;
    }

    @Override
    public boolean isDone() {
        if (depth == 9) {
            return true;
        }
        if (winner() !="?") {
            return true;
        }
        return false;
    }

    @Override
    public int getWinner() {
        String player = winner();
        if (player == "X") {
            return 1;
        } else if (player == "O") {
            return 2;
        } else if (depth == 9) {
            return 0;
        }

        return -1;
    }

    @Override
    public String getGameStateMessage() {
        int res = this.getWinner();
        if (res == 1) {
            return "Player 1 Won!";
        } else if (res == 2) {
            return "Player 2 Won!";
        } else if (res == 0) {
            return "Game is a tie";
        }
        return "Game is in progress";
    }

    public String winner(){
        String winner = "?";
        for (int i = 1; i <= 3; i++) {
            if ((getCell(1,i).equals("X") || getCell(1,i).equals("O")) && getCell(1, i) == getCell(2, i)
                    &&  getCell(2, i) == getCell(3, i)) {
                winner =  getCell(1, i);
                break;
            }
        }
        if (winner == "?") {
            if ((getCell(1,1).equals("X")) || getCell(1,1).equals("O") && getCell(1, 1) ==  getCell(2, 2)
                    &&  getCell(2, 2) ==  getCell(3, 3)) {
                winner =  getCell(1, 1);
            }
            if ((getCell(3,1).equals("X") || getCell(3,1).equals("O")) && getCell(3, 1) ==  getCell(2, 2)
                    &&  getCell(2, 2) ==  getCell(1, 3)) {
                winner =  getCell(1, 3);
            }
        }
        for (int i = 1; i <= 3; i++) {
            if ((getCell(i,1).equals("X") || getCell(i,1).equals("O")) &&getCell(i, 1) == getCell(i, 2)
                    &&  getCell(i, 2) == getCell(i, 3)) {
                winner =  getCell(i, 1);
                break;
            }
        }
        return winner;
    }

    /*public void runGame(){

         * Scanner input = new Scanner(System.in);
         * int position;
         * int depth = 0;
         * 
         * TicTacToeP newGame = new TicTacToeP();
         * System.out.println(board);
         * System.out.println("Turn = X");
         * 
         * while (newGame.winner(depth, board)) {
         * System.out.print("Enter Position between 0 and 8:\n");
         * position = input.nextInt();
         * 
         * if (position > board.length) {
         * System.out.println("Error - Out of Bounds");
         * } else {
         * 
         * if (newGame.checkBoard(position, board)) {
         * System.err.println("Illegal Move! Try Again");
         * 
         * } else {
         * depth++;
         * board[newGame.posToIndex[position]] = newGame.getTurn();
         * System.out.println(board);
         * newGame.setTurn();
         * if (depth != 9) {
         * System.out.println("Turn = " + newGame.getTurn());
         * }
         * }
         * }
         * }
         * newGame.setTurn();
         * System.out.println("Winner = " + newGame.getTurn());
         * 
         * System.out.println(" PlayerTurn ");

    }*/

    public String getStringToSave() {
        String str = toString().replaceAll("  ", ",");
        return str;
    }

    public void loadSavedString(String toLoad) {
        String[] vals = toLoad.split(",");

        for (int i = 1; i <= 9; i++) {
            int across=(i%getWidth())+1;
            int down = (i/getHeight()) + 1;
            setValue(across, down, vals[i]);
        }
    }

    
}