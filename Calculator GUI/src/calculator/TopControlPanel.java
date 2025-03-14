package calculator;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TopControlPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton btnac, btndel;

    public TopControlPanel(DisplayPanel displayPanel) {
        setLayout(null);

        JButton btnac = new JButton("AC");
		btnac.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayPanel.text.setText("0");
				DisplayPanel.field.setText("");
				CalculatorLogic.result = 0;
				CalculatorLogic.command = 0;
				CalculatorLogic.isValid = true;
				CalculatorLogic.isFirst = true;
			}
		});
		btnac.setBounds(0, 0, 89, 35);
		btnac.setFocusable(false);
		add(btnac);
		
		JButton btndel = new JButton("⌫");
		btndel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double number1 = Double.parseDouble(DisplayPanel.text.getText());
				if (!DisplayPanel.field.getText().contains("=")) {
					if (!DisplayPanel.text.getText().equals("0")) {
						if (number1 < 0 && DisplayPanel.text.getText().length() == 2) {
							DisplayPanel.text.setText("0");
							DisplayPanel.field.setText("");
						} else if (DisplayPanel.text.getText().length() == 1) {
							DisplayPanel.text.setText("0");
							DisplayPanel.field.setText("");
						} else {
							DisplayPanel.text.setText(DisplayPanel.text.getText().substring(0, DisplayPanel.text.getText().length() - 1));
						}
				    } else {
				    	DisplayPanel.text.setText("0");
				    	DisplayPanel.field.setText("");
				    }
				}
			}
		});
		btndel.setFont(new Font("SansSerif", Font.BOLD, 20));
		btndel.setBounds(0, 41, 89, 35);
		btndel.setFocusable(false);
		add(btndel);
		
		KeyBindingsUtil.setupKeyBindings(displayPanel, btndel, btnac);
    }
}

