
/*
* Last Modified: November 1, 2020
* Author: Shalee (Shahrukh) Qureshi
* Description: This class creates the Model for the Game
*
* Constructor List:
* 1. Model()
*
* Method List:
* 1. public void setGUI(View view) = This method sets the GUI and initializes the button matrix
* 2. public void setButtonNum(int num) = This method sets the number to the JButton
* 3. public int getButtonNum() = This method gets the number to the JButton
* 4. public int getXCount() = This method gets the xCount
* 5. public int[] getIndex(int val) = This method gets the indices within a matrix when given a specific value
* 6. public boolean determineWinner() = This method determines who won the game
* 7. public void updateState() = This method invokes the updateView Method in the View Class
*
*/
// Import Statements
import javax.swing.JButton;

public class Model extends Object {

    // Instance Variables
    private View view;
    private int buttonNum;
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
     * This method invokes the updateView Method in the View Class
     */
    public void updateState() {
        this.view.updateView();
    } // updateState Method

} // Model Class
