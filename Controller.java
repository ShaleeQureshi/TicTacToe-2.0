
/*
* Last Modified: November 1, 2020
* Author: Shalee (Shahrukh) Qureshi
* Description: This class creates the Controllers for the Game
*
* Constructor List:
* 1. Controller(Model model)
*
* Method List:
* 1. public void actionPerformed(ActionEvent e) = This method performs actions upon I/O on JComponents
* 2. public void keyTyped(KeyEvent e) = This method is not used in this program
* 3. public void keyPressed(KeyEvent e) = This method performs an action based on keyboard events (keys being pressed)
* 4. public void keyReleased(KeyEvent e) = This method is not used in this program
*
*/
// Import Statements
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;

public class Controller implements ActionListener, KeyListener {

    // Instance Variable
    private Model model;

    /**
     * This is the Controller Constructor
     * 
     * @param model
     */
    public Controller(Model model) {
        this.model = model; // Initializing the Model
    } // Controller Constructor

    @Override
    /**
     * This method performs actions upon I/O on JComponents
     * 
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        // If the user presses a button the following will occur
        if (e.getActionCommand() == "Exit Game" || e.getActionCommand() == "Settings"
                || e.getActionCommand() == "Restart" || e.getActionCommand() == "Move the AI") {
            this.model.btnEvents(e.getActionCommand());
        }
        // If 1 of the buttons stated above was not pressed then the following will
        // occur
        else {
            try {
                this.model.setButtonNum(Integer.parseInt(e.getActionCommand())); // Setting the number to the button
            } catch (NumberFormatException error) {
                // Showing an error message if there is an error with the user's selection
                JOptionPane.showMessageDialog(null, "Invalid Selection! You may only select tiles that are numbers!");
            }
        }
    } // actionPerformed Method

    @Override
    /**
     * This method is not used in this program
     * 
     * @param e
     */
    public void keyTyped(KeyEvent e) {
    } // keyTyped Method

    @Override
    /**
     * This method performs an action based on keyboard events (keys being pressed)
     * 
     * @param e
     */
    public void keyPressed(KeyEvent e) {
        // If the user presses the escape button then the SettingsView will open
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            SettingsModel settingsModel = new SettingsModel(this.model);
            new SettingsView(settingsModel);
        }
    } // keyPressed Method

    @Override
    /**
     * This method is not used in this program
     * 
     * @param e
     */
    public void keyReleased(KeyEvent e) {
    } // keyReleased Method

} // Controller Class
