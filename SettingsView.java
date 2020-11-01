
/*
* Last Modified: November 1, 2020
* Author: Shalee (Shahrukh) Qureshi
* Description: This class creates the View for the Settings panel for the game
*
* Constructor List:
* 1. SettingsView(ListModel model)
*
* Method List:
* 1. public void updateView() = This method updates the state of the SettingsView
* 2. private void initializeControllers() = This method initializes the Controllers for the buttons 
* 3. private JButton btnSetter(JButton btn) = This method adjusts the properties of the JButton
* 4. private void setLayout() = This method sets the layout of the JPanel
*
*/
// Import Statements
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Color;

public class SettingsView extends JFrame {

    // Instance Variables
    private JSplitPane splitPane;
    private JScrollPane scrollPane;
    private JPanel mainPanel;
    private String[] listItems = { "About", "Help", "Game Settings" };
    private JPanel[] panels;
    private JLabel[] labels;
    private JButton btnGitHub = new JButton("GitHub");
    private JButton btnWebsite = new JButton("Website");
    private final JList<String> list;
    private ListModel model;
    private final Font lblFont = new Font("Arial", Font.PLAIN, 10);

    /**
     * This is the SettingsView Constructor
     * 
     * @param model
     */
    public SettingsView(ListModel model) {
        super();
        this.model = model;
        this.model.setGUI(this);

        // JFrame
        this.setSize(new Dimension(500, 200));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Settings Manager");

        // JSplitPane, ScrollPane, JPanels
        this.splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        this.list = new JList<String>(this.listItems);
        this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.list.setSelectedIndex(0);
        this.scrollPane = new JScrollPane(this.list);
        this.setLayout();
        this.initializeControllers();
        this.setVisible(true);

    } // Constructor

    /**
     * This method updates the state of the SettingsView
     */
    public void updateView() {

        int index = this.list.getSelectedIndex(); // Getting the selected index on the list
        this.list.setSelectedIndex(index); // Updating the index on the list

        // Re-adjusting the JSplitPane
        this.splitPane.setDividerLocation(200);
        this.splitPane.setDividerSize(0);

        this.splitPane.setRightComponent(this.panels[index]); // Updating the JPanel to match the JList index

    } // updateView Method

    // Helper Methods

    /**
     * This method initializes the Controllers for the buttons
     */
    private void initializeControllers() {

        // This creates an instance of the Controllers' class for the list
        ListController controller = new ListController(this.model, this.btnGitHub, this.btnWebsite);
        this.list.addListSelectionListener(controller);

        // This creates an instance of the Controllers' class for the buttons
        ListController controllerBtn = new ListController(this.model, this.btnGitHub, this.btnWebsite);
        this.btnGitHub.addActionListener(controllerBtn);
        this.btnWebsite.addActionListener(controllerBtn);

    } // initializeControllers Method

    /**
     * This method adjusts the properties of the JButton
     * 
     * @param btn
     * @return JButton
     */
    private JButton btnSetter(JButton btn) {

        final Font btnFont = new Font("Arial", Font.BOLD, 15);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setFont(btnFont);
        btn.setBackground(Color.GRAY);

        return btn; // Returning the JButton

    } // btnSetter Method

    /**
     * This method sets the layout of the JPanel
     */
    private void setLayout() {

        this.mainPanel = new JPanel(); // Initializing the main JPanel

        // Array of JPanels, JLabels and BoxLayout
        this.panels = new JPanel[3];
        this.labels = new JLabel[this.panels.length];

        // Loop to initialize the JPanel[] and JLabel[]
        for (int i = 0; i < this.panels.length; i++) {
            this.panels[i] = new JPanel();
            this.labels[i] = new JLabel();
        } // for loop

        // panels[0]
        this.labels[0].setText("<html><u>Developed by Shahrukh (Shalee) Qureshi");
        this.labels[0].setFont(this.lblFont);
        this.btnGitHub = this.btnSetter(this.btnGitHub);
        this.btnWebsite = this.btnSetter(this.btnWebsite);
        this.panels[0].add(this.labels[0], BorderLayout.NORTH);
        this.panels[0].add(this.btnGitHub, BorderLayout.CENTER);
        this.panels[0].add(this.btnWebsite, BorderLayout.CENTER);

        // panels[1]
        this.labels[1].setText("<html><u>How to Play");
        this.labels[1].setFont(this.lblFont);
        this.panels[1].add(this.labels[1]);

        // Adjusting the SplitPane
        this.splitPane.setLeftComponent(this.scrollPane);
        this.splitPane.setRightComponent(this.panels[0]);
        this.splitPane.setDividerLocation(200);
        this.splitPane.setDividerSize(0);
        this.splitPane.setSize(new Dimension(400, 200));

        this.mainPanel.add(this.splitPane); // Adding the SplitPane to the JPanel
        this.add(this.mainPanel); // Adding the main JPanel to this (JFrame)

    } // setLayout Method

} // SettingsView Class
