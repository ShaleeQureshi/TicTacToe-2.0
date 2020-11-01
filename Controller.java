
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
*
*/
// Import Statements
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
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
        try {
            this.model.setButtonNum(Integer.parseInt(e.getActionCommand())); // Setting the number to the button
        } catch (NumberFormatException error) {
            System.out.println(error.getMessage());
        }
    } // actionPerformed Method

    @Override
    /**
     * This method is not used in this instance
     * 
     * @param e
     */
    public void keyTyped(KeyEvent e) {
    } // keyTyped Method

    @Override
    /**
     * This method is not used in this instance
     * 
     * @param e
     */
    public void keyPressed(KeyEvent e) {
        // If the user presses the escape button then the SettingsView will open
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            ListModel model = new ListModel();
            new SettingsView(model);
        }
    } // keyPressed Method

    @Override
    /**
     * This method is not used in this instance
     * 
     * @param e
     */
    public void keyReleased(KeyEvent e) {
    } // keyReleased Method

} // Controller Class
