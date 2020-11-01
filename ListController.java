
/*
* Last Modified: November 1, 2020
* Author: Shalee (Shahrukh) Qureshi
* Description: This class creates the Model for the SettingsView 
*
* Constructor List:
* 1. ListController(ListModel model, JButton btnGitHub, JButton btnWebsite) = This is the ListController Constructor
*
* Method List:
* 1. public void valueChanged(ListSelectionEvent e) = This method updates the state of the JSplitPane when a new list item is selected
* 2. public void actionPerformed(ActionEvent e) = This method performs an action upon events on the JComponents
*
*/
// Import Statements
import javax.swing.JButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListController implements ListSelectionListener, ActionListener {

    // Instance Variables
    private ListModel model;
    private JButton btnGitHub, btnWebsite;

    /**
     * This is the ListController Constructor
     * 
     * @param model
     * @param btnGitHub
     * @param btnWebsite
     */
    public ListController(ListModel model, JButton btnGitHub, JButton btnWebsite) {
        this.model = model;
        this.btnGitHub = btnGitHub;
        this.btnWebsite = btnWebsite;
    } // ListConstroller Constructor

    @Override
    /**
     * This method updates the state of the JSplitPane when a new list item is
     * selected
     * 
     * @param e
     */
    public void valueChanged(ListSelectionEvent e) {
        this.model.updateState();
    } // valueChanged Method

    @Override
    /**
     * This method performs an action upon events on the JComponents
     * 
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        // If the GitHub button is clicked then the following will occur
        if (e.getSource() == btnGitHub) {
            this.model.browse(btnGitHub);
        }
        // If the Website button is clicked then the following will occur
        else if (e.getSource() == btnWebsite) {
            this.model.browse(btnWebsite);
        }
    } // actionPerformed Method

} // ListController Method
