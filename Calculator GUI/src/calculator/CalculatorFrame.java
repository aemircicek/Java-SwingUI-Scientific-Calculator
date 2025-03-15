package calculator;

import javax.swing.JFrame;
import javax.swing.border.BevelBorder;

public class CalculatorFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	public static DisplayPanel displayPanel;
	public static KeypadPanel keypadPanel;
	public static TopControlPanel topControlPanel;
	public static ScientificPanel scientificPanel;

	public CalculatorFrame() {
		frame = this;
		setTitle("Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setFocusable(false);
		setBounds(1000, 350, 500, 385);
		getContentPane().setLayout(null);

		displayPanel = new DisplayPanel(frame);
		displayPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		displayPanel.setBounds(20, 11, 342, 74);
		getContentPane().add(displayPanel);

		keypadPanel = new KeypadPanel(displayPanel);
		keypadPanel.setBounds(20, 96, 443, 238);
		getContentPane().add(keypadPanel);

		topControlPanel = new TopControlPanel(displayPanel);
		topControlPanel.setBounds(374, 11, 89, 76);
		getContentPane().add(topControlPanel);

		scientificPanel = new ScientificPanel(displayPanel, keypadPanel);
		scientificPanel.setBounds(20, 345, 443, 188);
		getContentPane().add(scientificPanel);
	}
}