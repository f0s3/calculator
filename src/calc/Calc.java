package calc;
import javax.swing.*;

public class Calc {
    private JFrame window;
    
    public Calc() {
        window = new JFrame("Calculator");
        window.setLocationRelativeTo(null);
        window.add(new Panel());
        window.setSize(250,340);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            void run() {
                new Calc();
            }
        });
    }
}
