/*
* Last Modified: November 1, 2020
* Author: Shalee (Shahrukh) Qureshi
* Description: This class creates the View for the Game
*
* Constructor List:
* 1. View(Model model)
*
* Method List:
* 1. private void layoutView() = This method creates the initial layout for the Calculator
* 2. private void initializeControllers() = This method initializes the controllers
* 3. public void update() = This method updates the UI
*
*/
// Import Statements

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

public class View extends JPanel {

    private Model model;
    private JButton[][] buttons = new JButton[3][3];
    private JLabel lblTurn = new JLabel("Current Turn: X");
    private int xCount = 0;
    private int oCount = 0;

    public View(Model model) {
        super();
        this.model = model;
        this.model.setGUI(this);
        this.setPreferredSize(new Dimension(200, 150));
        this.initializePanel();
        this.initializeControllers();
    }

    public void updateView() {
        int val = this.model.getButtonNum();
        int[] indices = new int[2];

        if (val != 5) {
            indices = this.model.getIndex(val);
            if (this.lblTurn.getText() == "Current Turn: X") {
                this.buttons[indices[0]][indices[1]].setText("X");
                this.lblTurn.setText("Current Turn: O");
            } else {
                this.buttons[indices[0]][indices[1]].setText("O");
                this.lblTurn.setText("Current Turn: X");
            }
            if (this.model.determineWinner()) {
                if (this.model.getXCount() >= 3) {
                    JOptionPane.showMessageDialog(null, "Winner Player X");
                } else {
                    JOptionPane.showMessageDialog(null, "Winner Player O");
                }
            }
        }

    }

    public JButton[][] getButtons() {
        return this.buttons;
    }

    private int[] getIndex(int val) {
        int[] array = new int[2];
        for (int i = 0; i < this.buttons.length; i++) {
            for (int j = 0; j < this.buttons[i].length; j++) {
                if (i == 0 && j < 3) {
                    if (((i + 1) + j) == val) {
                        array[0] = i;
                        array[1] = j;
                        return array;
                    }
                } else if (i == 1 && j < 3) {
                    if (((i + 2) + j + 1) == val) {
                        array[0] = i;
                        array[1] = j;
                        return array;
                    }
                } else if (i == 2 && j < 3) {
                    if (((i + 3) + j + 2) == val) {
                        array[0] = i;
                        array[1] = j;
                        return array;
                    }
                }
            }
        }
        return array;
    }

    private boolean winner() {

        for (int i = 0; i < this.buttons.length; i++) {
            for (int j = 0; j < this.buttons[i].length; j++) {
                // Check row for j-1
                if (this.buttons[i][j].getText() == "X" || this.buttons[i][j].getText() == "O") {
                    if (j < this.buttons[i].length - 1) {
                        if (this.buttons[i][j].getText() == this.buttons[i][j + 1].getText()) {
                            if (this.buttons[i][j].getText() == "X") {
                                this.xCount++;
                            } else {
                                this.oCount++;
                            }
                        }
                    }
                    // Check vertically for i-1
                    if (i < this.buttons.length - 1) {
                        if (this.buttons[i][j].getText() == this.buttons[i + 1][j].getText()) {
                            if (this.buttons[i][j].getText() == "X") {
                                this.xCount++;
                            } else {
                                this.oCount++;
                            }
                        }
                    }
                    // Check diagonally (only for indicies 0 and 2)
                    if (i == 0) {
                        if (j == 0) {
                            if (this.buttons[i][j].getText() == this.buttons[i + 1][j + 1].getText()) {
                                if (this.buttons[i][j].getText() == "X") {
                                    this.xCount++;
                                } else {
                                    this.oCount++;
                                }
                            }
                        } else {
                            if (this.buttons[i][j].getText() == this.buttons[i + 1][j - 1].getText()) {
                                if (this.buttons[i][j].getText() == "X") {
                                    this.xCount++;
                                } else {
                                    this.oCount++;
                                }
                            }
                        }
                    }
                    if (i == 2) {
                        if (j == 0) {
                            if (this.buttons[i][j].getText() == this.buttons[i - 1][j + 1].getText()) {
                                if (this.buttons[i][j].getText() == "X") {
                                    this.xCount++;
                                } else {
                                    this.oCount++;
                                }
                            }
                        } else {
                            if (this.buttons[i][j].getText() == this.buttons[i - 1][j - 1].getText()) {
                                if (this.buttons[i][j].getText() == "X") {
                                    this.xCount++;
                                } else {
                                    this.oCount++;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (this.xCount >= 3 || this.oCount >= 3) {
            return true;
        }
        return false;

    }

    private void initializePanel() {

        JPanel panelTurn = new JPanel();
        JPanel panelRow1 = new JPanel();
        JPanel panelRow2 = new JPanel();
        JPanel panelRow3 = new JPanel();
        BoxLayout layoutRow1 = new BoxLayout(panelRow1, BoxLayout.X_AXIS);
        BoxLayout layoutRow2 = new BoxLayout(panelRow2, BoxLayout.X_AXIS);
        BoxLayout layoutRow3 = new BoxLayout(panelRow3, BoxLayout.X_AXIS);

        for (int i = 0; i < this.buttons.length; i++) {
            for (int j = 0; j < this.buttons[i].length; j++) {
                if (i == 0 && j < 3) {
                    this.buttons[i][j] = new JButton("" + ((i + 1) + j));
                    panelRow1.add(this.buttons[i][j]);
                } else if (i == 1 && j < 3) {
                    if (i == 1 && j == 1) {
                        this.buttons[i][j] = new JButton("N");
                        this.buttons[i][j].setBackground(Color.BLACK);
                    } else {
                        this.buttons[i][j] = new JButton("" + ((i + 2) + j + 1));
                    }
                    panelRow2.add(this.buttons[i][j]);
                } else if (i == 2 && j < 3) {
                    this.buttons[i][j] = new JButton("" + ((i + 3) + j + 2));
                    panelRow3.add(this.buttons[i][j]);
                }
            }
        }
        panelTurn.add(this.lblTurn);
        panelRow1.setLayout(layoutRow1);
        panelRow2.setLayout(layoutRow2);
        panelRow3.setLayout(layoutRow3);
        this.add(panelTurn);
        this.add(panelRow1);
        this.add(panelRow2);
        this.add(panelRow3);

    }

    private void initializeControllers() {
        Controller[][] controllers = new Controller[this.buttons.length][this.buttons.length];
        for (int i = 0; i < this.buttons.length; i++) {
            for (int j = 0; j < this.buttons[i].length; j++) {
                controllers[i][j] = new Controller(this.model, this.buttons[i][j]);
                this.buttons[i][j].addActionListener(controllers[i][j]);
            }
        }
    }

    public static void main(String[] args) {
        Model model = new Model();
        View view = new View(model);
        JFrame frame = new JFrame();
        frame.setContentPane(view);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

}