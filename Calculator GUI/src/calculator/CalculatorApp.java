package calculator;

import java.awt.EventQueue;
import javax.swing.UIManager;
import com.formdev.flatlaf.intellijthemes.FlatDraculaIJTheme;

public class CalculatorApp {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDraculaIJTheme());
        } catch (Exception e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(() -> {
            try {
                CalculatorFrame frame = new CalculatorFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}