
/*
* Last Modified: November 1, 2020
* Author: Shalee (Shahrukh) Qureshi
* Description: This class creates the Controller for the SettingsView 
*
* Constructor List:
* 1. SettingsController(ListModel model)
*
* Method List:
* 1. public void valueChanged(ListSelectionEvent e) = This method updates the state of the JSplitPane when a new list item is selected
* 2. public void actionPerformed(ActionEvent e) = This method performs an action upon events on the JComponents
*
*/
// Import Statements
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SettingsController implements ListSelectionListener, ActionListener {

    // Instance Variables
    private SettingsModel model;

    /**
     * This is the SettingsController Constructor
     * 
     * @param model
     */
    public SettingsController(SettingsModel model) {
        this.model = model;
    } // SettingsController Constructor

    @Override
    /**
     * This method updates the state of the JSplitPane when a new list item is
     * selected
     * 
     * @param e
     */
    public void valueChanged(ListSelectionEvent e) {
        this.model.updateState(); // Invoking the update state method whenever a list item is changed
    } // valueChanged Method

    @Override
    /**
     * This method performs an action upon events on the JComponents
     * 
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        this.model.browse(e.getActionCommand()); // Passing the command to the browse function
    } // actionPerformed Method

} // SettingsController Method
