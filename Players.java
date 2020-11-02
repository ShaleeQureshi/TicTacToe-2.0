
/*
* Last Modified: November 2, 2020
* Author: Shalee (Shahrukh) Qureshi
* Description: This is a Helper Class for the Model Class, it stores data regarding the player(s)
*
* Constructor List:
* 1. Players()
*
* Method List:
* 1. public void gameMode() = This method prompts the user to ask which game mode they would like to play
* 2. public String getPlayerSelection() = This method returns the game mode that the player chose
* 3. public String getPlayerX() = This method returns playerX
* 4. public String getPlayerO() = This method returns playerO
* 5. private void promptMultiplayer() = This method prompts the user a series of questions if they chose to play multiplayer
* 6. private void promptSinglePlayer() = This method prompts the user a question if they chose to play singleplayer
*
*/
// Import Statements
import javax.swing.JOptionPane;

public class Players extends Object {

    // Instance Variables
    private String playerSelection;
    private String playerX, playerO;

    /**
     * This is the Players Constructor
     */
    public Players() {
        super();
    } // Players Constructor

    /**
     * This method prompts the user to ask which game mode they would like to play
     */
    public void gameMode() {
        Object[] options = { "Multiplayer", "Singleplayer" }; // Object Array of Options

        // Prompting the user
        int selectedOption = JOptionPane.showOptionDialog(null, "Choose game mode",
                "TicTacToe 2.0 by Shahrukh (Shalee) Qureshi", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, null);

        // If the user wants to play Multiplayer the following will occur
        if (selectedOption == JOptionPane.OK_OPTION) {
            this.playerSelection = "Multiplayer"; // Updating the instance variable
            this.promptMultiplayer(); // Getting the next prompts ready
        }
        // If the user wants to play Singleplayer the following will occur
        else {
            this.playerSelection = "Singleplayer"; // Updating the instance variable
            this.promptSinglePlayer(); // Getting the next prompts ready
        }

    } // gameMode Method

    /**
     * This method returns the game mode that the player chose
     * 
     * @return the player selection instance variable
     */
    public String getPlayerSelection() {
        return this.playerSelection;
    } // getPlayerSelection Method

    /**
     * This method returns playerX
     * 
     * @return playerX instance variable
     */
    public String getPlayerX() {
        return this.playerX;
    } // getPlayerX Method

    /**
     * This method returns playerO
     * 
     * @return playerO instance variable
     */
    public String getPlayerO() {
        return this.playerO;
    } // getPlayerO Method

    // Helper Methods

    /**
     * This method prompts the user a series of questions if they chose to play
     * multiplayer
     */
    private void promptMultiplayer() {
        this.playerX = JOptionPane.showInputDialog(null, "Please enter the name for Player X");
        this.playerO = JOptionPane.showInputDialog(null, "Please enter the name for Player O");
        JOptionPane.showMessageDialog(null, "You can open Settings at any point by pressing the Escape Key");
    } // promptMultiplayer Method

    /**
     * This method prompts the user a question if they chose to play singleplayer
     */
    private void promptSinglePlayer() {
        this.playerX = JOptionPane.showInputDialog(null, "Please enter the name for Player X");
        this.playerO = "Computer";
        JOptionPane.showMessageDialog(null, "You can open Settings at any point by pressing the Escape Key");
    } // promptSinglePlayer Method

} // Players Class
