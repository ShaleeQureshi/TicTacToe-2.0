
/*
* Last Modified: November 1, 2020
* Author: Shalee (Shahrukh) Qureshi
* Description: This class creates the Model for the Game
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
        JFrame frame = new JFrame();

        // Adjusting Frame Settings
        frame.setContentPane(view);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    } // main Method

} // MainFile Class
