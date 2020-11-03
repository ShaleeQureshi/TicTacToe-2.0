
/*
* Last Modified: November 1, 2020
* Author: Shalee (Shahrukh) Qureshi
* Description: This class creates the Model for the SettingsView 
*
* Constructor List:
* 1. SettingsModel()
*
* Method List:
* 1. public void setGUI(SettingsView view) = This method sets the GUI for the SettingsView
* 2. public void updateState() = This method updates the state of the program
* 3. public String getPlayerSelection() = This method gets the player selection (game mode) from the Model Class
* 4. public String getPlayerX() = This method returns the Model Class value of playerX
* 5. public String getPlayerO() = This method gets the Model Class value of playerO
* 6. public void performAction(String command) = This method performs an action when a button is clicked
* 7. private void openWeb(String uri) = This method opens a specific URI on the web
*
*/
// Import Statements
import javax.swing.JOptionPane;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class SettingsModel extends Object {

    // Instance Variables
    private SettingsView view;
    private Model model;
    private final String github = "https://github.com/ShaleeQureshi";
    private final String personalSite = "https://www.squreshi.net/";

    /**
     * This is the SettingsModel Constructor
     */
    public SettingsModel(Model model) {
        super();
        this.model = model;
    } // SettingsModel Constructor

    /**
     * This method sets the GUI for the SettingsView
     * 
     * @param view
     */
    public void setGUI(SettingsView view) {
        this.view = view;
    } // setGUI Method

    /**
     * This method updates the state of the program
     */
    public void updateState() {
        this.view.updateView();
    } // updateState Method

    /**
     * This method gets the player selection (game mode) from the Model Class
     * 
     * @return the model class instance of player selection
     */
    public String getPlayerSelection() {
        return this.model.getPlayerSelection();
    } // getPlayerSelection Method

    /**
     * This method returns the Model Class value of playerX
     * 
     * @return model class instance of playerX
     */
    public String getPlayerX() {
        return this.model.getPlayerX();
    } // getPlayerX Method

    /**
     * This method gets the Model Class value of playerO
     * 
     * @return model class instance of playerO
     */
    public String getPlayerO() {
        return this.model.getPlayerO();
    } // getPlayerO Method

    /**
     * This method performs an action when a button is clicked
     * 
     * @param command
     */
    public void performAction(String command) {
        // If the GitHub button was clicked the following will occur
        if (command == "GitHub") {
            this.openWeb(this.github);
        }
        // If the Website button was clicked the following will occur
        else if (command == "Website") {
            this.openWeb(this.personalSite);
        }
    } // performAction Method

    // Helper Methods

    /**
     * This method opens a specific URI on the web
     * 
     * @param uri
     */
    private void openWeb(String uri) {
        try {
            Desktop desktop = Desktop.getDesktop(); // Creating an instance of a Desktop
            desktop.browse(new URI(uri)); // Opening the URI on the user's default browswer
        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        } catch (URISyntaxException error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
    } // openWeb Method

} // SettingsModel Class
