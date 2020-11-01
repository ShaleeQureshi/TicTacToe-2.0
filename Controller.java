import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Controller implements ActionListener {

    private Model model;
    private JButton button;

    public Controller(Model model, JButton button) {
        this.model = model;
        this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            this.model.setButtonNum(Integer.parseInt(e.getActionCommand()));
        } catch (NumberFormatException error) {
            System.out.println(error.getMessage());
        }
    }

}
