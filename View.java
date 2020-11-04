
/*
* Last Modified: November 3, 2020
* Author: Shalee (Shahrukh) Qureshi
* Description: This class creates the View for the Game
*
* Constructor List:
* 1. View(Model model)
*
* Method List:
* 1. public void updateView() = This method updates the state of the program
* 2. public JButton[][] getButtons() = This method allows the other classes (Model class) access to the game board matrix (buttons[][])
* 3. public void setLabelCurrentRound(String text) = This method sets the current round label
* 4. public String getLblTurnText(String text) = This method returns the current player's turn
* 5. private void initializePanel() = This method sets the layout for the entire main screen
* 6. private void initializeControllers() = This method initializes the controllers
*
*/
// Import Statements
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

public class View extends JPanel {

    /**
     * ID for Serializable Classes
     */
    private static final long serialVersionUID = 1L;

    // Instance Variables
    private Model model;
    private JButton[][] buttons = new JButton[3][3]; // Matrix of JButtons represents the game board
    private JButton btnExit = new JButton("Exit Game");
    private JButton btnSettings = new JButton("Settings");
    private JButton btnRestart = new JButton("Restart");
    private JButton btnAIMove = new JButton("Move the AI");
    private JLabel lblTurn = new JLabel("Current Turn: X");
    private JLabel lblCurrentRound = new JLabel("Round: 1");

    // 1 JPanel per row of buttons (3 by 3 grid so 3 panels)
    private JPanel panelRow1 = new JPanel();
    private JPanel panelRow2 = new JPanel();
    private JPanel panelRow3 = new JPanel();
    private String playerSelection;

    /**
     * This is the View Constructor
     * 
     * @param model
     */
    public View(Model model) {
        super(); // Invoking the Superclass
        this.model = model; // Initializing Instance Variable
        this.model.start();
        this.playerSelection = this.model.getPlayerSelection(); // Getting the type of game mode (Multiplayer or
                                                                // SinglePlayer)

        this.btnSettings.setFocusable(false);
        this.btnSettings.requestFocusInWindow();
        this.btnRestart.setFocusable(false);
        this.btnRestart.requestFocusInWindow();
        this.btnExit.setFocusable(false);
        this.btnExit.requestFocusInWindow();
        this.btnAIMove.setFocusable(false);
        this.btnAIMove.requestFocusInWindow();
        this.model.setGUI(this); // Setting this GUI
        this.setPreferredSize(new Dimension(200, 220));
        this.setFocusable(true);
        this.requestFocusInWindow();
        // Invoking class helper Methods
        this.initializePanel();
        this.initializeControllers();

    } // View Constructor

    /**
     * This method updates the state of the program
     */
    public void updateView() {
        int val = this.model.getButtonNum(); // Getting the value (button number that was clicked)

        int[] indices = new int[2]; // Getting the indices (for the 2d matrix that the num was clicked)
        int[] aiIndicies = new int[2]; // Getting the indices for the AI

        // If user selected to play with another player (multiplayer) then the following
        // will occur
        if (this.playerSelection == "Multiplayer") {
            indices = this.model.getIndex(val); // Getting the indices
            // If the current player was Player X the following will occur
            if (this.lblTurn.getText() == "Current Turn: X") {
                this.buttons[indices[0]][indices[1]].setText("X"); // Changing the button text to X
                this.lblTurn.setText("Current Turn: O"); // Letting the Players know its Player O's turn
            }
            // If the current player was Player O the following will occur
            else {
                this.buttons[indices[0]][indices[1]].setText("O"); // Changing the button text to O
                this.lblTurn.setText("Current Turn: X"); // Letting the Players know its Player X's turn
            }
        }
        // If the user selected to play by himself (against the AI) then the following
        // will occur
        else {
            // If it is the Player's turn (Player X) the following will occur
            if (this.lblTurn.getText() == "Current Turn: X") {
                indices = this.model.getIndex(val); // Getting the indices
                this.buttons[indices[0]][indices[1]].setText("X"); // Updating the gameboard
                this.lblTurn.setText("Current Turn: AI"); // Saying its the AI's turn

            }
            // If it is the AI's turn then the following will occur
            else {
                aiIndicies = this.model.computer(); // Getting the indices
                this.buttons[aiIndicies[0]][aiIndicies[1]].setText("O"); // Updating the gameboard
                this.lblTurn.setText("Current Turn: X"); // Saying its the player's turn
            }
        }

        // Checking to see if someone won yet
        if (this.model.determineWinner()) {
            // If Player X won the following will occur
            if (this.model.getXCount() >= 3) {
                JOptionPane.showMessageDialog(null, "Winner: Player X");
                this.model.updateXWins(); // Updating wins
            }
            // If Player O won the following will occur
            else {
                if (this.playerSelection == "Multiplayer") {
                    JOptionPane.showMessageDialog(null, "Winner: Player O");
                } else {
                    JOptionPane.showMessageDialog(null, "Winner: AI");
                }
                this.model.updateOWins(); // Updating wins
            }
            this.setLabelCurrentRound("Round: " + this.model.currentRound()); // Updating the Current Round Label
            // If the players have not played 3 rounds the following will occur
            if (this.model.currentRound() < 4) {
                this.model.newRound(); // Creating a new round
            }
            // If the players have played 3 rounds the following will occur
            else {
                Object[] options = { "Yes", "No" };
                int choice = JOptionPane.showOptionDialog(null,
                        "Its been " + (this.model.currentRound() - 1) + " rounds! Do you want to keep playing?",
                        "TicTacToe 2.0 by Shahrukh (Shalee) Qureshi", JOptionPane.YES_NO_OPTION,
                        JOptionPane.PLAIN_MESSAGE, null, options, null);

                // If the player(s) decided to continue playing the following will occur
                if (choice == JOptionPane.YES_OPTION) {
                    this.model.newRound(); // Setting up a new round
                }
                // If the player(s) decided to stop playing the following will occur
                else {
                    this.model.outputToFile(); // Invoking the outputToFile Method
                }
            }
        }

    } // updateView Method

    /**
     * This method allows the other classes (Model class) access to the game board
     * matrix (buttons[][])
     * 
     * @return 2D button array
     */
    public JButton[][] getButtons() {
        return this.buttons;
    } // getButtons Method

    /**
     * This method sets the current round label
     * 
     * @param text
     */
    public void setLabelCurrentRound(String text) {
        this.lblCurrentRound.setText(text);
    } // setLabelCurrentRound Method

    /**
     * This method returns the current player's turn
     * 
     * @return current player's turn
     */
    public String getLblTurnText() {
        return this.lblTurn.getText();
    } // getLblTurnText Method

    // Helper Methods

    /**
     * This method sets the layout for the entire main screen
     */
    private void initializePanel() {

        // Instantiating Objects
        JPanel panelTurn = new JPanel();
        BoxLayout layoutRow1 = new BoxLayout(this.panelRow1, BoxLayout.X_AXIS);
        BoxLayout layoutRow2 = new BoxLayout(this.panelRow2, BoxLayout.X_AXIS);
        BoxLayout layoutRow3 = new BoxLayout(this.panelRow3, BoxLayout.X_AXIS);

        // Loop to traverse through the rows
        for (int i = 0; i < this.buttons.length; i++) {
            // Loop to traverse through the individual columns
            for (int j = 0; j < this.buttons[i].length; j++) {
                // If its row 1 values the following will occur
                if (i == 0 && j < 3) {
                    this.buttons[i][j] = new JButton("" + ((i + 1) + j));
                    this.panelRow1.add(this.buttons[i][j]);
                }
                // If its row 2 values the following will occur
                else if (i == 1 && j < 3) {
                    // If its button number 5 the following will occur
                    if (i == 1 && j == 1) {
                        this.buttons[i][j] = new JButton("N");
                        this.buttons[i][j].setBackground(Color.BLACK);
                    } else {
                        this.buttons[i][j] = new JButton("" + ((i + 2) + j + 1));
                    }
                    this.panelRow2.add(this.buttons[i][j]);
                }
                // If its row 3 values the following will occur
                else if (i == 2 && j < 3) {
                    this.buttons[i][j] = new JButton("" + ((i + 3) + j + 2));
                    this.panelRow3.add(this.buttons[i][j]);
                }
                this.buttons[i][j].setFocusable(false);
            } // for loop

        } // for loop

        // Adding the layouts to the individual panels
        panelTurn.add(this.lblTurn);
        this.panelRow1.setLayout(layoutRow1);
        this.panelRow2.setLayout(layoutRow2);
        this.panelRow3.setLayout(layoutRow3);

        // Adding each panel to the main panel
        this.add(panelTurn);
        if (this.playerSelection == "Singleplayer") {
            this.add(this.btnAIMove);
        }
        this.add(this.lblCurrentRound);
        this.add(this.panelRow1);
        this.add(this.panelRow2);
        this.add(this.panelRow3);
        this.add(this.btnSettings);
        this.add(this.btnRestart);
        this.add(this.btnExit);

    } // initializePanel Method

    /**
     * This method initializes the controllers
     */
    private void initializeControllers() {

        // Creating a 2D controller array to match the 2D JButton array in size
        Controller[][] controllers = new Controller[this.buttons.length][this.buttons.length];

        // Loop to traverse the rows of the matrix
        for (int i = 0; i < this.buttons.length; i++) {
            // Loop to traverse the individual columns of the matrix
            for (int j = 0; j < this.buttons[i].length; j++) {
                controllers[i][j] = new Controller(this.model); // Initializing the Controller
                this.buttons[i][j].addActionListener(controllers[i][j]); // Adding an ActionListener to the JButton
            } // for loop
        } // for loop

        // Adding a Controller to the exit button
        Controller btnController = new Controller(this.model);
        this.btnExit.addActionListener(btnController);
        this.btnRestart.addActionListener(btnController);
        this.btnSettings.addActionListener(btnController);
        this.btnAIMove.addActionListener(btnController);

        // // Adding a Controller for the KeyListener
        Controller keyController = new Controller(this.model);
        this.addKeyListener(keyController);

    } // initializeControllers Method

} // View Class