
/*
* Last Modified: November 3, 2020
* Author: Shalee (Shahrukh) Qureshi
* Description: This class runs the entire program by instantiating and linking the Model and View Classes
*
* Method List:
* 1. public static void main(String[] args) = This method runs the entire program
*
*/
// Import Statements
import javax.swing.JFrame;

public class MainFile {

    /**
     * This method runs the entire program
     * 
     * @param args
     */
    public static void main(String[] args) {

        // Instantiating Objects
        Model model = new Model();
        View view = new View(model);
        JFrame frame = new JFrame("Shahrukh (Shalee) Qureshi's TicTacToe 2.0 Game");

        // Adjusting Frame Settings
        frame.setContentPane(view);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

    } // main Method

} // MainFile Class
