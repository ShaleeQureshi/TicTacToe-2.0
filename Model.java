import javax.swing.JButton;

public class Model extends Object {

    private View view;
    private int buttonNum;
    private JButton[][] buttons;
    private int xCount = 0;
    private int oCount = 0;

    public Model() {
        super();
    }

    public void setGUI(View view) {
        this.view = view;
        this.buttons = this.view.getButtons();
    }

    public void setButtonNum(int num) {
        this.buttonNum = num;
        this.updateState();
    }

    public int getButtonNum() {
        return this.buttonNum;
    }

    public int getXCount() {
        return this.xCount;
    }

    public int getOCount() {
        return this.oCount;
    }

    public int[] getIndex(int val) {
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

    public boolean determineWinner() {
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

    public void updateState() {
        this.view.updateView();
    }

}
