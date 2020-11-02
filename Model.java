
/*
* Last Modified: November 1, 2020
* Author: Shalee (Shahrukh) Qureshi
* Description: This class creates the Model for the Game
*
* Constructor List:
* 1. Model()
*
* Method List:
* 1. public void getPlayers() = This method gets 2 players
* 2. public void outputToFile() = This method outputs the data to a file (output.txt)
* 3. public void setGUI(View view) = This method sets the GUI and initializes the button matrix
* 4. public void setButtonNum(int num) = This method sets the number to the JButton
* 5. public int getButtonNum() = This method gets the number to the JButton
* 6. public int getXCount() = This method gets the xCount
* 7. public void updateXWins() = This method updates Player X's wins
* 8. public void updateOWins() = This method updates Player O's wins
* 9. public int currentRound() = This method returns the current round
* 10. public int[] getIndex(int val) = This method gets the indices within a matrix when given a specific value
* 11. public boolean determineWinner() = This method determines who won the game
* 12. public void newRound() = This method will reset the score between the players when a new round starts
* 13. public void updateState() = This method invokes the updateView Method in the View Class
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
    private JButton[][] buttons;
    private int xCount = 0;
    private int oCount = 0;

    /**
     * Model Constructor
     */
    public Model() {
        super();
    } // Model Constructor

    /**
     * This method gets 2 players
     */
    public void getPlayers() {
        this.playerX = JOptionPane.showInputDialog(null, "Please enter the name for Player X");
        this.playerO = JOptionPane.showInputDialog(null, "Please enter the name for Player O");
        JOptionPane.showMessageDialog(null, "You can open Settings at any point by pressing the Escape Key");
    } // getPlayers Method

    /**
     * This method outputs the data to a file (output.txt)
     */
    public void outputToFile() {

        // Prompting user, letting them know the game is over
        JOptionPane.showMessageDialog(null, "Thank you for playing " + this.playerX + " and " + this.playerO
                + "\nYour scores have been sent to output.txt!");
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
        // The current round is equal to the total number of wins between the players
        return this.playerOWins + this.playerXWins;
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
    } // newRound Method

    /**
     * This method invokes the updateView Method in the View Class
     */
    public void updateState() {
        this.view.updateView();
    } // updateState Method

} // Model Class
