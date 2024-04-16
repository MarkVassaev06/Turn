import javax.swing.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class Turn {
    public static void main(String[] args) {

        try {
            SwingUtilities.invokeAndWait(() -> {
                Main main = new Main("NewFrame");
                main.setLocationRelativeTo(null);
                main.setBounds(10, 10, 1000, 1000);
                main.setLayout(null);
                main.setResizable(false);
                main.setDefaultCloseOperation(EXIT_ON_CLOSE);
                main.setVisible(true);
            });
        } catch (Exception e) {
            System.err.println("createGUI didn't successfully complete");
        }
    }
}