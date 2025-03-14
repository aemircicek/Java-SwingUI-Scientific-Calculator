package calculator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.BevelBorder;

public class DisplayPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    public static JTextField text;
    public static JTextField field;

    private void initComponents() {
        setLayout(null);
        setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

        field = new JTextField();
        field.setEnabled(false);
        field.setHorizontalAlignment(SwingConstants.RIGHT);
        field.setFont(new Font("SansSerif", Font.ITALIC, 18));
        field.setDisabledTextColor(Color.WHITE);
        field.setEditable(false);
        field.setBorder(null);
        field.setColumns(10);
        field.setBounds(4, 4, 334, 33);
        add(field);

        text = new JTextField();
        text.setEditable(false);
        text.setEnabled(false);
        text.setBorder(null);
        text.setHorizontalAlignment(SwingConstants.CENTER);
        text.setFont(new Font("SansSerif", Font.BOLD, 26));
        text.setDisabledTextColor(Color.WHITE);
        text.setBounds(4, 37, 334, 33);
        text.setText("0");
        add(text);
    }

    public DisplayPanel() {
        initComponents();
    }

    public DisplayPanel(JFrame frame) {
        this();
    }

    public JTextField getTextField() {
        return text;
    }

    public JTextField getField() {
        return field;
    }
}