
/*
* Last Modified: November 1, 2020
* Author: Shalee (Shahrukh) Qureshi
* Description: This class creates the Model for the Game
*
* Constructor List:
* 1. Model()
*
* Method List:
* 1. public void start() = This method starts the process of prompting the player before they play the game
* 2. public String getPlayerSelection() = This method returns the selected game mode
* 3. public void outputToFile() = This method outputs the data to a file (output.txt)
* 4. public void setGUI(View view) = This method sets the GUI and initializes the button matrix
* 5. public void btnEvents(String command) = This method performs an action based on the action command
* 6. public void setButtonNum(int num) = This method sets the number to the JButton
* 7. public int getButtonNum() = This method gets the number to the JButton
* 8. public String getPlayerX() = This method gets playerX
* 9. public String getPlayerO() = This method gets playerO
* 10. public int getXCount() = This method gets the xCount
* 11. public void updateXWins() = This method updates Player X's wins
* 12. public void updateOWins() = This method updates Player O's wins
* 13. public int currentRound() = This method returns the current round
* 14. public int[] getIndex(int val) = This method gets the indices within a matrix when given a specific value
* 15. public int[] computer() = This method determines where the AI should place its letter
* 16. public boolean determineWinner() = This method determines who won the game
* 17. public void newRound() = This method will reset the score between the players when a new round starts
* 18. public void updateState() = This method invokes the updateView Method in the View Class
*
*/
// Import Statements
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Model extends Object {

    // Instance Variables
    private View view;
    private String playerX, playerO;
    private int buttonNum, playerXWins, playerOWins;
    private String playerSelection;
    private JButton[][] buttons;
    private int xCount = 0;
    private int oCount = 0;
    private int currentRound = 1;
    private Players players = new Players();

    /**
     * Model Constructor
     */
    public Model() {
        super();
    } // Model Constructor

    /**
     * This method starts the process of prompting the player before they play the
     * game
     */
    public void start() {
        this.players.gameMode();
        this.playerX = this.players.getPlayerX();
        this.playerO = this.players.getPlayerO();
        this.playerSelection = this.players.getPlayerSelection();
    } // getPlayers Method

    /**
     * This method returns the selected game mode
     * 
     * @return playerSelection
     */
    public String getPlayerSelection() {
        return this.playerSelection;
    } // getPlayerSelection Method

    /**
     * This method outputs the data to a file (output.txt)
     */
    public void outputToFile() {

        String winner = "";
        // Determining the overall winner
        if (this.playerXWins > this.playerOWins) {
            winner = this.playerX;
        } else if (this.playerXWins == this.playerOWins) {
            winner = "Tie";
        } else {
            winner = this.playerO;
        }
        // Prompting user, letting them know the game is over
        if (winner != "Tie") {
            JOptionPane.showMessageDialog(null, "Thank you for playing! " + this.playerX + " and " + this.playerO + "\n"
                    + winner + " won the most rounds!\nYour scores have been updated in output.txt!");
        } else {
            JOptionPane.showMessageDialog(null, "Thank you for playing! " + this.playerX + " and " + this.playerO
                    + "\nThere is no winner it is a Draw!\nYour scores have been updated in output.txt!");
        }
        try {
            // Outputting the data to the file (output.txt)
            FileWriter fileW = new FileWriter("output.txt", true);
            PrintWriter output = new PrintWriter(fileW);
            output.println("Player X: " + this.playerX);
            output.println("Player X Wins: " + this.playerXWins);
            output.println("Player O: " + this.playerO);
            output.println("Player O Wins: " + this.playerOWins);
            fileW.close();
            output.close();
            System.exit(0); // Terminating the program
        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
    } // outputToFile Method

    /**
     * This method sets the GUI and initializes the button matrix
     * 
     * @param view
     */
    public void setGUI(View view) {
        this.view = view;
        this.buttons = this.view.getButtons(); // Getting the 2D Buttons array
    } // setGUI Method

    /**
     * This method performs an event based on the action command
     * 
     * @param command
     */
    public void btnEvents(String command) {

        // If the user presses the Settings button the following will occur
        if (command == "Exit Game") {
            this.outputToFile(); // Calling the output to file method
        }
        // If the restart button was clicked the following will occur
        else if (command == "Restart") {
            Object[] options = { "Restart Game", "Restart Round" };
            int selectedOption = JOptionPane.showOptionDialog(null,
                    "Do you want to restart the game or the current round?",
                    "TicTacToe 2.0 by Shahrukh (Shalee) Qureshi", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, null);

            // If the user wants to Restart the Game the following will occur
            if (selectedOption == JOptionPane.OK_OPTION) {
                // Resetting the Score
                this.playerXWins = 0;
                this.playerOWins = 0;
                this.view.setLabelCurrentRound("Round: " + (this.currentRound = 1));
                // Resetting the gameboard
                this.newRound();
            }
            // If the user wants to reset the round the following will occur
            else {
                this.newRound();
            }

        }
        // If the user presses the Settings button the following will occur
        else if (command == "Settings") {
            // Opening up the Settings Model and calling its view method
            SettingsModel model = new SettingsModel(this);
            new SettingsView(model);
        }
        // If the user presses the Move the AI button the following will occur
        else {
            if (this.view.getLblTurnText() == "Current Turn: AI") {
                this.updateState();
            }
        }

    } // btnEvents Method

    /**
     * This method sets the number to the JButton
     * 
     * @param num
     */
    public void setButtonNum(int num) {
        this.buttonNum = num;
        this.updateState();
    } // setButtonNum Method

    /**
     * This method gets the number to the JButton
     * 
     * @return the number
     */
    public int getButtonNum() {
        return this.buttonNum;
    } // getButtonNum Method

    /**
     * This method gets playerX
     * 
     * @return playerX
     */
    public String getPlayerX() {
        return this.playerX;
    } // getPlayerX Method

    /**
     * THis method gets playerO
     * 
     * @return playerO
     */
    public String getPlayerO() {
        return this.playerO;
    } // getPlayerO Method

    /**
     * This method gets the xCount
     * 
     * @return xCount
     */
    public int getXCount() {
        return this.xCount;
    } // getXCount Method

    /**
     * This method updates Player X's wins
     * 
     */
    public void updateXWins() {
        this.playerXWins++;
    } // updateXWins Method

    /**
     * This method updates Player O's wins
     * 
     */
    public void updateOWins() {
        this.playerOWins++;
    } // updateOWins Method

    /**
     * This method returns the current round
     * 
     * @return current round
     */
    public int currentRound() {
        // The current round is equal to the total number of wins between the players +
        // 1 since the initial round is 1
        this.currentRound = this.playerOWins + this.playerXWins + 1;
        return this.currentRound;
    } // currentRound Method

    /**
     * This method gets the indices within a matrix when given a specific value
     * 
     * @param val
     * @return 2 indices in the matrix
     */
    public int[] getIndex(int val) {
        int[] array = new int[2]; // Creating an array with length of 2

        // Loop to traverse through the rows of the matrix
        for (int i = 0; i < this.buttons.length; i++) {
            // Loop to traverse through the individual columns of the matrix
            for (int j = 0; j < this.buttons[i].length; j++) {
                // For row 1 values
                if (i == 0 && j < 3) {
                    // If the value is in row 1 the following will occur
                    if (((i + 1) + j) == val) {
                        // Passing the values for the row and column to the array
                        array[0] = i;
                        array[1] = j;
                        return array; // Returing the array
                    }
                }
                // For row 2 values
                else if (i == 1 && j < 3) {
                    // If the value is in row 2 the following will occur
                    if (((i + 2) + j + 1) == val) {
                        // Passing the values for the row and column to the array
                        array[0] = i;
                        array[1] = j;
                        return array; // Returing the array
                    }
                }
                // For row 3 values
                else if (i == 2 && j < 3) {
                    // If the value is in row 3 the following will occur
                    if (((i + 3) + j + 2) == val) {
                        // Passing the values for the row and column to the array
                        array[0] = i;
                        array[1] = j;
                        return array; // Returing the array
                    }
                }
            } // for loop

        } // for loop

        return array; // Return array

    } // getIndex Method

    /**
     * This method determines where the AI should place its letter
     * 
     * @return an array of 2 numbers with the row and col the letter should be
     *         placed
     */
    public int[] computer() {
        int[] array = new int[2]; // Array of size 2

        // Loop to traverse the array (rows)
        for (int i = 0; i < this.buttons.length; i++) {
            // Loop to traverse the array (columns)
            for (int j = 0; j < this.buttons[i].length; j++) {
                // If the current button does not already hold a value or is not the center
                // value the following will occur
                if (this.buttons[i][j].getText() != "X" && this.buttons[i][j].getText() != "O"
                        && this.buttons[i][j].getText() != "N") {
                    array[0] = i;
                    array[1] = j;
                    return array;
                }
            } // for loop

        } // for loop

        return array;

    } // computer Method

    /**
     * This method determines who won the game
     * 
     * @return true if someone wins and false if no one wins
     */
    public boolean determineWinner() {

        // Loop to traverse through the rows of the matrix
        for (int i = 0; i < this.buttons.length; i++) {
            // Loop to traverse through the individual columns of the matrix
            for (int j = 0; j < this.buttons[i].length; j++) {
                // If a given value is equal to X or O the following will occur
                if (this.buttons[i][j].getText() == "X" || this.buttons[i][j].getText() == "O") {
                    // If the current column is not the final column the following will occur
                    if (j < this.buttons[i].length - 1) {
                        // Checking to see if adjacent values match
                        if (this.buttons[i][j].getText() == this.buttons[i][j + 1].getText()) {
                            // If its an X then we add 1 to the xCount
                            if (this.buttons[i][j].getText() == "X") {
                                this.xCount++;
                            }
                            // If its an O we add 1 to the oCount
                            else {
                                this.oCount++;
                            }
                        }
                    }
                    // If the current column is not the last column the following will occur
                    if (i < this.buttons.length - 1) {
                        // Checking to see if adjacent values match
                        if (this.buttons[i][j].getText() == this.buttons[i + 1][j].getText()) {
                            // If its an X then we add 1 to the xCount
                            if (this.buttons[i][j].getText() == "X") {
                                this.xCount++;
                            }
                            // If its an O we add 1 to the oCount
                            else {
                                this.oCount++;
                            }
                        }
                    }
                    // Check diagonally (only for indicies 0 and 2)

                    // Checking index 0 (col 0)
                    if (i == 0) {
                        if (j == 0) {
                            // Checking to see if adjacent values match
                            if (this.buttons[i][j].getText() == this.buttons[i + 1][j + 1].getText()) {
                                // If its an X then we add 1 to the xCount
                                if (this.buttons[i][j].getText() == "X") {
                                    this.xCount++;
                                }
                                // If its an O we add 1 to the oCount
                                else {
                                    this.oCount++;
                                }
                            }
                        } else {
                            // Checking to see if adjacent values match
                            if (this.buttons[i][j].getText() == this.buttons[i + 1][j - 1].getText()) {
                                // If its an X then we add 1 to the xCount
                                if (this.buttons[i][j].getText() == "X") {
                                    this.xCount++;
                                }
                                // If its an O we add 1 to the oCount
                                else {
                                    this.oCount++;
                                }
                            }
                        }
                    }
                    // Checking index 2 (col 3)
                    if (i == 2) {
                        if (j == 0) {
                            // Checking to see if adjacent values match
                            if (this.buttons[i][j].getText() == this.buttons[i - 1][j + 1].getText()) {
                                // If its an X then we add 1 to the xCount
                                if (this.buttons[i][j].getText() == "X") {
                                    this.xCount++;
                                }
                                // If its an O we add 1 to the oCount
                                else {
                                    this.oCount++;
                                }
                            }
                        } else {
                            // Checking to see if adjacent values match
                            if (this.buttons[i][j].getText() == this.buttons[i - 1][j - 1].getText()) {
                                // If its an X then we add 1 to the xCount
                                if (this.buttons[i][j].getText() == "X") {
                                    this.xCount++;
                                }
                                // If its an O we add 1 to the oCount
                                else {
                                    this.oCount++;
                                }
                            }
                        }
                    }
                }

            } // for loop

        } // for loop

        // Return statement
        return this.xCount >= 3 || this.oCount >= 3;

    } // determineWinner Method

    /**
     * This method will reset the score between the players when a new round starts
     */
    public void newRound() {
        this.xCount = 0;
        this.oCount = 0;
        /**
         * This method resets the game board to its default values
         */
        // Loop to traverse through the rows
        for (int i = 0; i < this.buttons.length; i++) {
            // Loop to traverse through the individual columns
            for (int j = 0; j < this.buttons[i].length; j++) {
                // If the current button isn't at index 1 and 1 the following will occur
                if (this.buttons[i][j].getText() != "N") {
                    // If its row 1 values the following will occur
                    if (i == 0 && j < 3) {
                        this.buttons[i][j].setText("" + ((i + 1) + j));
                    }
                    // If its row 2 values the following will occur
                    else if (i == 1 && j < 3) {
                        this.buttons[i][j].setText("" + ((i + 2) + j + 1));
                    }
                    // If its row 3 values the following will occur
                    else if (i == 2 && j < 3) {
                        this.buttons[i][j].setText("" + ((i + 3) + j + 2));
                    }
                }
            } // for loop

        } // for loop

    } // newRound Method

    /**
     * This method invokes the updateView Method in the View Class
     */
    public void updateState() {
        this.view.updateView();
    } // updateState Method

} // Model Class
