package tictactoe;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import boardgame.BoardGame;
import boardgame.LoadSaveGame;

public class TextUI {
    private Scanner scanner;
    public TextUI() {
        this.scanner = new Scanner(System.in); // create Scanner instance for reading input
    }

    /**
     * @author Nikhita Mahajan
     *         This function asks the user for a file name to load a game from
     */
    public String getFileNameInput() {
        System.out.println("Enter file to load from or leave blank");
        return scanner.nextLine();
    }

    // gets user input of where they want to insert
    /**
     * @param turn indicates turn and shows whether it is player 1 or player 2
     *             The function also catches an exception if the user enters a
     *             non-numeric value
     * @return the game returns a valid user input
     */
    public int getColumnInput(int turn) {
        int col = 0;
        String player = turn % 2 == 0 ? "Player 1" : "Player 2";
        try {
            System.out.println(player + " Enter position between 0-8 or 9 to Save and Quit");
            col = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
        }
        while (col < 0 || col > 7) {
            System.out.println(player + " Enter position between 0-8 or 9 to Save and Quit");
            try {
                col = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
            }
        }
        return col;
    }

    //
    /**
     * This method prints the current board
     * 
     * @param board the instance of the board
     */
    /*public void displayBoard(Board board) {
        for (int col = 0; col < board.getColumns() + 2; col++) {
            System.out.print("-+");
        }

        System.out.println();

        for (int i = board.getRows() - 1; i >= 0; i--) {
            System.out.print("|");
            for (int j = 0; j < board.getColumns(); j++) {
                System.out.print((board.getBoard())[i][j] + "|");

            }
            System.out.println();

        }

        System.out.println();

        for (int col = 0; col < board.getColumns() + 2; col++) {
            System.out.print("-+");
        }
        System.out.println();
    }*/

    /**
     * This method helps in saving the game.It writes into a new file. It also
     * catches an exception if the file cannot be created.
     * 
     * @param board the instance of the board to write to file
     * 
     */
    public void saveGame(BoardGame game) throws Exception {
        System.out.println("Enter the name of the file you'd like to save it as(Should be a csv file)");
        String file = scanner.nextLine(); // gets file name to save to
        LoadSaveGame.saveGame(file, game);
        // try {
        //     FileWriter fw = new FileWriter(file);
        //     for (int i = game.getHeight() - 1; i >= 0; i--) {// starts writing from last row
        //         for (int j = 0; j < game.getWidth(); j++) {// iterates over columns
        //             char c=' ';
        //             // game.getBoard()
        //             fw.write(c);
        //             if (j < game.getWidth() - 1) {// writes comma for each entry except last
        //                 fw.write(',');
        //             }
        //         }
        //         fw.write('\n');// new line for next line
        //     }
        //     fw.close();
        // } catch (IOException e) { // catch exception if file cannot be created
        //     System.out.println("An error occurred.");
        //     e.printStackTrace();
        // }

    }

    /**
     * This method loads a previously saved game
     * 
     * @param board instance of the board
     * @param file  the file's name that needs to be retrieved
     * @return this method returns the number of blanks spaces on the board
     * @throws FileNotFoundException if file does not exist
     */
    public int loadGame(BoardGame board, String file) throws FileNotFoundException {

        Scanner sc = new Scanner(new File(file));
        String num;
        int blanks = 0;
        sc.useDelimiter(","); // sets the delimiter pattern
        for (int i = board.getHeight() - 1; i >= 0; i--) {
            // System.out.println("new line");
            for (int j = 0; j < board.getWidth() - 1; j++) {//populates from last row
                num = sc.next();
                char c = '_'; //initialize to blank
                if (num.equals("1")) { //if 1, set to 'R'
                    c = 'R';
                } else if (num.equals("2")) { //if 2 set to 'Y'
                    c = 'Y';
                } else if (num.equals("0")) { //increment num blanks otherwise
                    blanks++;
                }
                // (board.getBoard())[i][j] = c; //write to board
            }
            // System.out.println();
        }

        sc.close();//close scanner to file
        System.out.println("blanks " + blanks);
        return 42 - blanks; //return number of turns that were made
    }

    public void closeUI() { //closer user input scanner
        scanner.close();
    }

}