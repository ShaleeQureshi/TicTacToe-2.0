
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
* 3. public void browse(JButton btn) = This method determines which URI to pursue based on the button that is clicked
* 4. private void openWeb(String uri) = This method opens a specific URI on the web
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
    private final String github = "https://github.com/ShaleeQureshi";
    private final String personalSite = "https://www.squreshi.net/";

    /**
     * This is the SettingsModel Constructor
     */
    public SettingsModel() {
        super();
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
     * This method determines which URI to pursue based on the button that is
     * clicked
     * 
     * @param btn
     */
    public void browse(String command) {
        // If the GitHub button was clicked the following will occur
        if (command == "GitHub") {
            this.openWeb(this.github);
        }
        // If the Website button was clicked the following will occur
        else if (command == "Website") {
            this.openWeb(this.personalSite);
        }

    } // browse Method

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
