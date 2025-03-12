package calculator;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.function.Function;
import com.formdev.flatlaf.intellijthemes.FlatDraculaIJTheme;

public class Calculator {

	private JFrame frmCalculator;
	private static JTextField text;
	private static double result = 0;
	private static int command = 0;
	private static boolean isValid = true;
	private static boolean isFirst = true;
	private static boolean isScientific = false;
	private static JTextField field;
	
	private void setupKeyBindings(JPanel panel, JButton... buttons) {
	    InputMap inputMap = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	    ActionMap actionMap = panel.getActionMap();

	    for (JButton button : buttons) {
	        String key = button.getText();

	        inputMap.put(KeyStroke.getKeyStroke(key), key);
	        inputMap.put(KeyStroke.getKeyStroke("NUMPAD" + key), key);
	        if ("+".equals(key)) {
	        	inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ADD, 0), key);
	        	inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_4, InputEvent.SHIFT_DOWN_MASK), key);
	        } else if ("-".equals(key)) {
	        	inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SUBTRACT, 0), key);
	        	inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, 0), key);
	        } else if ("*".equals(key)) {
	        	inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_MULTIPLY, 0), key);
	        	inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ASTERISK, 0), key);
	        } else if ("÷".equals(key)) {
	        	inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DIVIDE, 0), key);
	        	inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_7, InputEvent.SHIFT_DOWN_MASK), key);
	        } else if ("=".equals(key)) {
	            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), key);
	            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_0, InputEvent.SHIFT_DOWN_MASK), key);
	        } else if (".".equals(key)) {
	        	inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_PERIOD, 0), key);
	            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DECIMAL, 0), key);
	        } else if ("⌫".equals(key)) {
	            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), key);
	        } else if ("AC".equals(key)) {
	            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), key);
	        } else if ("(".equals(key)) {
	        	inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_8, InputEvent.SHIFT_DOWN_MASK), key);
	        } else if (")".equals(key)) {
	        	inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_9, InputEvent.SHIFT_DOWN_MASK), key);
	        }
	        actionMap.put(key, new AbstractAction() {
	            /**
				 * 
				 */
				private static final long serialVersionUID = -2038718464156043354L;

				@Override
	            public void actionPerformed(ActionEvent e) {
	                button.doClick();
	            }
	        });
	    }
	}

	private JButton addNumberButton(char digit, int x, int y) {
		JButton button = new JButton(String.valueOf(digit));
		button.setFont(new Font("SansSerif", Font.BOLD, 20));
		button.setBounds(x, y, 108, 39);
		button.setFocusable(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (text.getText().equals("Invalid Input!") || text.getText().equals("Cannot divide by zero!")) {
					isValid = false;
				}
				if (!field.getText().contains("=")) {
					if (isValid) {
						if (digit == '0') {
							if (!text.getText().equals("0")) {
								text.setText(text.getText() + "0");
							}
						} else if (digit == '.') {
							if (!text.getText().contains(".")) {
								if (text.getText().equals("")) {
									text.setText(text.getText() + "0" + ".");
								} else {
									text.setText(text.getText() + ".");
								}
							}
						} else {
							if (text.getText().equals("0")) {
								text.setText("");
							}
							text.setText(text.getText() + digit);
						}
					} else {
						field.setText("");
						text.setText("");
						result = 0;
						command = 0;
						isValid = true;
						if (digit == '.') {
							text.setText(text.getText() + "0" + ".");
						} else {
							text.setText(text.getText() + digit);
						}
					}
				} else {
					result = 0;
					command = 0;
					isFirst = true;
					text.setText("");
					field.setText("");
					if (digit == '.') {
						text.setText(text.getText() + "0" + ".");
					} else {
						text.setText(text.getText() + digit);
					}
				}
			}
		});
		return button;
	}

	private JButton addOperatorButton(String label, int cmnd, int x, int y, int z) {
		JButton button = new JButton(String.valueOf(label));
		button.setFont(new Font("SansSerif", Font.BOLD, 20));
		button.setBounds(x, y, z, 39);
		button.setFocusable(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (text.getText().equals("") || text.getText().equals("Invalid Input!")
						|| text.getText().equals("Cannot divide by zero!")) {
					text.setText("Invalid Input!");
					field.setText("");
					isValid = false;
				} else {
					if (isFirst) {
						double number1 = Double.parseDouble(text.getText());
						result = number1;
						text.setText("0");
						if (number1 == Math.floor(number1)) {
							if (field.getText().contains("=")) {
								field.setText("");
							}
							if (label == "÷") {
								field.setText(field.getText() + (int) number1 + " / ");
							} else if (label == "*") {
								field.setText(field.getText() + (int) number1 + " * ");
							} else if (label == "-") {
								field.setText(field.getText() + (int) number1 + " - ");
							} else if (label == "+") {
								field.setText(field.getText() + (int) number1 + " + ");
							} else if (label == "xʸ") {
								field.setText(field.getText() + (int) number1 + " ^ ");
							} else if (label == "ʸ√x") {
								field.setText(field.getText() + (int) number1 + " ^ " + "1 / ");
							}
						} else {
							if (field.getText().contains("=")) {
								field.setText("");
							}
							if (label == "÷") {
								field.setText(field.getText() + number1 + " / ");
							} else if (label == "*") {
								field.setText(field.getText() + number1 + " * ");
							} else if (label == "-") {
								field.setText(field.getText() + number1 + " - ");
							} else if (label == "+") {
								field.setText(field.getText() + number1 + " + ");
							} else if (label == "xʸ") {
								field.setText(field.getText() + number1 + " ^ ");
							} else if (label == "ʸ√x") {
								field.setText(field.getText() + number1 + " ^ " + "1 / ");
							}
						}
						isFirst = false;
					} else {
						double number2 = Double.parseDouble(text.getText());
						switchCase();
						text.setText("0");
						if (number2 == Math.floor(number2)) {
							if (field.getText().contains("=")) {
								field.setText("");
							}
							if (label == "÷") {
								field.setText("(" + field.getText() + (int) number2 + ")" + " / ");
							} else if (label == "*") {
								field.setText("(" + field.getText() + (int) number2 + ")" + " * ");
							} else if (label == "-") {
								field.setText(field.getText() + (int) number2 + " - ");
							} else if (label == "+") {
								field.setText(field.getText() + (int) number2 + " + ");
							} else if (label == "xʸ") {
								field.setText("(" + field.getText() + (int) number2 + ")" + " ^ ");
							} else if (label == "ʸ√x") {
								field.setText("(" + field.getText() + (int) number2 + ")" + " ^ " + "1 / ");
							}
						} else {
							if (field.getText().contains("=")) {
								field.setText("");
							}
							if (label == "÷") {
								field.setText("(" + field.getText() + number2 + ")" + " / ");
							} else if (label == "*") {
								field.setText("(" + field.getText() + number2 + ")" + " * ");
							} else if (label == "-") {
								field.setText(field.getText() + number2 + " - ");
							} else if (label == "+") {
								field.setText(field.getText() + number2 + " + ");
							} else if (label == "xʸ") {
								field.setText("(" + field.getText() + number2 + ")" + " ^ ");
							} else if (label == "ʸ√x") {
								field.setText("(" + field.getText() + "1 / " + number2 + ")" + " ^ " + "1 / ");
							}
						}
					}
					command = cmnd;
				}
			}
		});
		return button;
	}

	private JButton addTrigonometricButton(String label, Function<Double, Double> trigFunction, int cmnd, int x, int y) {
		JButton button = new JButton(label);
		button.setFont(new Font("SansSerif", Font.BOLD, 20));
		button.setBounds(x, y, 108, 39);
		button.setFocusable(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (text.getText().equals("") || text.getText().equals("Invalid Input!")
						|| text.getText().equals("Cannot divide by zero!")) {
					text.setText("Invalid Input!");
					field.setText("");
					isValid = false;
				} else {
					if (!(command == 1 || command == 2 || command == 3 || command == 4 || command == 5 || command == 6
							|| command == 7)) {
						double number1 = Double.parseDouble(text.getText());
						double normalizedAngle = number1 % 360;
						if (normalizedAngle < 0) {
							normalizedAngle += 360;
						}
						if (cmnd == 17 && (normalizedAngle == 90 || normalizedAngle == 270)) {
							text.setText("Invalid Input!");
							field.setText("");
							isValid = false;
						} else {
							result = trigFunction.apply(Math.toRadians(number1));
							if (number1 == Math.floor(number1)) {
								field.setText(label + "(" + (int) number1 + ")" + " =");
							} else {
								field.setText(label + "(" + number1 + ")" + " =");
							}
							if (result == Math.floor(result)) {
								text.setText(String.valueOf((int) result));
							} else {
								text.setText(String.valueOf(result));
							}
						}
					} else {
						switchCase();
						if (isValid) {
							double number2 = Double.parseDouble(text.getText());
							double trigResult = trigFunction.apply(Math.toRadians(result));
							double normalizedAngle = result % 360;
							if (normalizedAngle < 0) {
								normalizedAngle += 360;
							}
							if (cmnd == 17 && (normalizedAngle == 90 || normalizedAngle == 270)) {
								text.setText("Invalid Input!");
								field.setText("");
								isValid = false;
							} else {
								if (number2 == Math.floor(number2)) {
									field.setText(label + "(" + field.getText() + (int) number2 + ")" + " =");
								} else {
									field.setText(label + "(" + field.getText() + number2 + ")" + " =");
								}
								if (trigResult == Math.floor(trigResult)) {
									text.setText(String.valueOf((int) trigResult));
								} else {
									text.setText(String.valueOf(trigResult));
								}
							}
						}
					}
					command = cmnd;
				}
			}
		});
		return button;
	}

	private static double factorial(double number1) {
		if (number1 < 0) {
			return -1;
		}
		if (number1 == 0 || number1 == 1)
			return 1;
		return number1 * factorial(number1 - 1);
	}

	private static void switchCase() {
		double number2 = Double.parseDouble(text.getText());
		switch (command) {
		case 1: // plus
			result += number2;
			break;
		case 2: // minus
			result -= number2;
			break;
		case 3: // multiplication
			result *= number2;
			break;
		case 4: // division
			if (number2 == 0) {
				text.setText("Cannot divide by zero!");
				field.setText("");
				isValid = false;
			} else {
				result /= number2;
			}
			break;
		case 5: // x to the power of y
			if ((result == 0 && number2 <= 0) || (result < 0 && number2 != Math.floor(number2))) {
				text.setText("Invalid Input!");
				field.setText("");
				isValid = false;
			} else {
				result = Math.pow(result, number2);
			}
			break;
		case 6: // y root x
			if (number2 % 2 == 0 && result < 0) {
				text.setText("Invalid Input!");
				field.setText("");
				isValid = false;
			} else {
				result = Math.signum(result) * Math.pow(Math.abs(result), 1.0 / number2);
			}
			break;
		case 7: // log y based x
			if (number2 == 0 || result == 0) {
				text.setText("Invalid Input!");
				field.setText("");
				isValid = false;
			} else {
				result = Math.log(number2) / Math.log(result);
			}
			break;
		case 8: // x square
			result = Math.pow(number2, 2);
			break;
		case 9: // square root of x
			if (result < 0) {
				text.setText("Invalid Input!");
				field.setText("");
				isValid = false;
			} else {
				result = Math.sqrt(result);
			}
			break;
		case 10: // one divided by x
			if (result == 0) {
				text.setText("Invalid Input!");
				field.setText("");
				isValid = false;
			} else {
				result = 1 / result;
			}
			break;
		case 12: // ln x
			if (result <= 0) {
				text.setText("Invalid Input!");
				field.setText("");
				isValid = false;
			} else {
				result = Math.log(result);
			}
			break;
		case 13: // e to the power of x
			result = Math.exp(result);
			break;
		case 14: // absolute value of x
			result = Math.abs(result);
			break;
		case 15: // sin x
			result = Math.sin(Math.toRadians(result));
			break;
		case 16: // cos x
			result = Math.cos(Math.toRadians(result));
			break;
		case 17: // tan x
			result = Math.tan(Math.toRadians(result));
			break;
		case 18: // 10 to the power of X
			result = Math.pow(10, result);
			break;
		case 19: // x cube
			result = Math.pow(number2, 3);
			break;
		case 20: // cube root of x
			result = Math.cbrt(result);
			break;
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
            UIManager.setLookAndFeel(new FlatDraculaIJTheme());
        } catch (Exception e) {
            e.printStackTrace();
        }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculator window = new Calculator();
					window.frmCalculator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Calculator() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCalculator = new JFrame();
		frmCalculator.setBounds(1000, 500, 500, 608);
		frmCalculator.setTitle("Calculator");
		frmCalculator.setFocusable(true);
		frmCalculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCalculator.getContentPane().setLayout(null);

		text = new JTextField();
		text.setEditable(false);
		text.setEnabled(false);
		text.setBorder(null);
		text.setText("0");
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setFont(new Font("SansSerif", Font.BOLD, 26));
		text.setDisabledTextColor(Color.WHITE);
		text.setBounds(4, 37, 334, 33);
		
		field = new JTextField();
		field.setEnabled(false);
		field.setHorizontalAlignment(SwingConstants.RIGHT);
		field.setFont(new Font("SansSerif", Font.ITALIC, 18));
		field.setDisabledTextColor(Color.WHITE);
		field.setEditable(false);
		field.setBorder(null);
		field.setColumns(10);
		field.setBounds(4, 4, 334, 33);
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 11, 342, 74);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setLayout(null);
		panel.add(text);
		panel.add(field);
		frmCalculator.getContentPane().add(panel);

		JButton btn0 = addNumberButton('0', 138, 296);
		JButton btn1 = addNumberButton('1', 20, 246);
		JButton btn2 = addNumberButton('2', 138, 246);
		JButton btn3 = addNumberButton('3', 256, 246);
		JButton btn4 = addNumberButton('4', 20, 196);
		JButton btn5 = addNumberButton('5', 138, 196);
		JButton btn6 = addNumberButton('6', 256, 196);
		JButton btn7 = addNumberButton('7', 20, 146);
		JButton btn8 = addNumberButton('8', 138, 146);
		JButton btn9 = addNumberButton('9', 256, 146);
		JButton btndot = addNumberButton('.', 256, 296);
		JButton btnplus = addOperatorButton("+", 1, 374, 246, 89);
		JButton btnsubs = addOperatorButton("-", 2, 374, 196, 89);
		JButton btnmult = addOperatorButton("*", 3, 374, 146, 89);
		JButton btndiv = addOperatorButton("÷", 4, 374, 96, 89);
		JButton btnpow = addOperatorButton("xʸ", 5, 138, 396, 108);
		JButton btnyrootx = addOperatorButton("ʸ√x", 6, 256, 396, 108);
		JButton btnsin = addTrigonometricButton("sin", Math::sin, 15, 20, 496);
		JButton btncos = addTrigonometricButton("cos", Math::cos, 16, 138, 496);
		JButton btntan = addTrigonometricButton("tan", Math::tan, 17, 256, 496);
		
		frmCalculator.getContentPane().add(btn0);
		frmCalculator.getContentPane().add(btn1);
		frmCalculator.getContentPane().add(btn2);
		frmCalculator.getContentPane().add(btn3);
		frmCalculator.getContentPane().add(btn4);
		frmCalculator.getContentPane().add(btn5);
		frmCalculator.getContentPane().add(btn6);
		frmCalculator.getContentPane().add(btn7);
		frmCalculator.getContentPane().add(btn8);
		frmCalculator.getContentPane().add(btn9);
		frmCalculator.getContentPane().add(btndot);
		frmCalculator.getContentPane().add(btnplus);
		frmCalculator.getContentPane().add(btnsubs);
		frmCalculator.getContentPane().add(btnmult);
		frmCalculator.getContentPane().add(btndiv);
		frmCalculator.getContentPane().add(btnpow);
		frmCalculator.getContentPane().add(btnyrootx);
		frmCalculator.getContentPane().add(btnsin);
		frmCalculator.getContentPane().add(btncos);
		frmCalculator.getContentPane().add(btntan);

		JButton btnlogyx = new JButton("logᵧx");
		btnlogyx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (text.getText().equals("") || text.getText().equals("Invalid Input!")
						|| text.getText().equals("Cannot divide by zero!")) {
					text.setText("Invalid Input!");
					field.setText("");
					isValid = false;
				} else {
					double number1 = Double.parseDouble(text.getText());
					if (number1 <= 1) {
						text.setText("Invalid Input!");
						field.setText("");
						isValid = false;
					} else {
						if (isFirst) {
							result = number1;
							if (number1 == Math.floor(number1)) {
								field.setText("(base " + (int) number1 + ")log");
							} else {
								field.setText("(base " + number1 + ")log");
							}
							text.setText("0");
							isFirst = false;
						} else {
							switchCase();
							double number2 = Double.parseDouble(text.getText());
							if (number2 == Math.floor(number2)) {
								field.setText("(base (" + field.getText() + (int) number2 + "))log");
							} else {
								field.setText("(base (" + field.getText() + number2 + "))log");
							}
							text.setText("0");
						}
					}
					command = 7;
				}
			}
		});
		btnlogyx.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnlogyx.setBounds(138, 446, 108, 39);
		btnlogyx.setFocusable(false);
		btnlogyx.setVisible(false);
		frmCalculator.getContentPane().add(btnlogyx);

		JButton btnsqr = new JButton("x²");
		btnsqr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (text.getText().equals("") || text.getText().equals("Invalid Input!")
						|| text.getText().equals("Cannot divide by zero!")) {
					text.setText("Invalid Input!");
					field.setText("");
					isValid = false;
				} else {
					if (!(command == 1 || command == 2 || command == 3 || command == 4 || command == 5 || command == 6
							|| command == 7)) {
						double number1 = Double.parseDouble(text.getText());
						result = Math.pow(number1, 2);
						if (number1 == Math.floor(number1)) {
							field.setText((int) number1 + "²" + " =");
						} else {
							field.setText("(" + number1 + ")" + "²" + " =");
						}
						if (result == Math.floor(result)) {
							text.setText(String.valueOf((int) result));
						} else {
							text.setText(String.valueOf(result));
						}
					} else {
						switchCase();
						double number2 = Double.parseDouble(text.getText());
						if (isValid) {
							if (number2 == Math.floor(number2)) {
								field.setText("(" + field.getText() + (int) number2 + ")" + "²" + " =");
							} else {
								field.setText("(" + field.getText() + number2 + ")" + "²" + " =");
							}
							if (result == Math.floor(result)) {
								text.setText(String.valueOf((int) Math.pow(result, 2)));
							} else {
								text.setText(String.valueOf(Math.pow(result, 2)));
							}
						}
					}
					command = 8;
				}
			}
		});
		btnsqr.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnsqr.setBounds(20, 346, 108, 39);
		btnsqr.setFocusable(false);
		frmCalculator.getContentPane().add(btnsqr);

		JButton btnsqroot = new JButton("√x");
		btnsqroot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (text.getText().equals("") || text.getText().equals("Invalid Input!")
						|| text.getText().equals("Cannot divide by zero!")) {
					text.setText("Invalid Input!");
					isValid = false;
				} else {
					if (!(command == 1 || command == 2 || command == 3 || command == 4 || command == 5 || command == 6
							|| command == 7)) {
						double number1 = Double.parseDouble(text.getText());
						if (number1 < 0) {
							text.setText("Invalid Input!");
							field.setText("");
							isValid = false;
						} else {
							result = Math.sqrt(number1);
							if (number1 == Math.floor(number1)) {
								field.setText("√" + (int) number1 + " =");
							} else {
								field.setText("√" + number1 + " =");
							}
							if (result == Math.floor(result)) {
								text.setText(String.valueOf((int) result));
							} else {
								text.setText(String.valueOf(result));
							}
						}
					} else {
						double number2 = Double.parseDouble(text.getText());
						switchCase();
						if (isValid) {
							if (result < 0) {
								text.setText("Invalid Input!");
								field.setText("");
								isValid = false;
							} else {
								if (number2 == Math.floor(number2)) {
									field.setText("√" + "(" + field.getText() + (int) number2 + ")" + " =");
								} else {
									field.setText("√" + "(" + field.getText() + number2 + ")" + " =");
								}
								if (result == Math.floor(result)) {
									text.setText(String.valueOf((int) Math.sqrt(result)));
								} else {
									text.setText(String.valueOf(Math.sqrt(result)));
								}
							}
						}
					}
					command = 9;
				}
			}
		});
		btnsqroot.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnsqroot.setBounds(138, 346, 108, 39);
		btnsqroot.setFocusable(false);
		frmCalculator.getContentPane().add(btnsqroot);

		JButton btndivby1 = new JButton("1/x");
		btndivby1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (text.getText().equals("") || text.getText().equals("Invalid Input!")
						|| text.getText().equals("Cannot divide by zero!")) {
					text.setText("Invalid Input!");
					field.setText("");
					isValid = false;
				} else {
					if (!(command == 1 || command == 2 || command == 3 || command == 4 || command == 5 || command == 6
							|| command == 7)) {
						double number1 = Double.parseDouble(text.getText());
						if (number1 == 0) {
							text.setText("Cannot divide by zero!");
							field.setText("");
							isValid = false;
						} else {
							result = 1 / number1;
							if (number1 == Math.floor(number1)) {
								field.setText("1 / " + (int) number1 + " =");
							} else {
								field.setText("1 / " + number1 + " =");
							}
							if (result == Math.floor(result)) {
								text.setText(String.valueOf((int) result));
							} else {
								text.setText(String.valueOf(result));
							}
						}
					} else {
						double number2 = Double.parseDouble(text.getText());
						switchCase();
						if (isValid) {
							if (result == 0) {
								text.setText("Cannot divide by zero!");
								field.setText("");
								isValid = false;
							} else {
								if (number2 == Math.floor(number2)) {
									field.setText("1 / " + "(" + field.getText() + (int) number2 + ")" + " =");
								} else {
									field.setText("1 / " + "(" + field.getText() + number2 + ")" + " =");
								}
								if (1 / result == Math.floor(1 / result)) {
									text.setText(String.valueOf((int) 1 / result));
								} else {
									text.setText(String.valueOf(1 / result));
								}
							}
						}
					}
					command = 10;
				}
			}
		});
		btndivby1.setFont(new Font("SansSerif", Font.BOLD, 20));
		btndivby1.setBounds(20, 396, 108, 39);
		btndivby1.setFocusable(false);
		btndivby1.setVisible(false);
		frmCalculator.getContentPane().add(btndivby1);

		JButton btnln = new JButton("ln");
		btnln.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (text.getText().equals("") || text.getText().equals("Invalid Input!")
						|| text.getText().equals("Cannot divide by zero!")) {
					text.setText("Invalid Input!");
					field.setText("");
					isValid = false;
				} else {
					if (!(command == 1 || command == 2 || command == 3 || command == 4 || command == 5 || command == 6
							|| command == 7)) {
						double number1 = Double.parseDouble(text.getText());
						if (number1 <= 0) {
							text.setText("Invalid Input!");
							field.setText("");
							isValid = false;
						} else {
							result = Math.log(number1);
							if (number1 == Math.floor(number1)) {
								field.setText("ln(" + (int) number1 + ")" + " =");
							} else {
								field.setText("ln(" + number1 + ")" + " =");
							}
							if (result == Math.floor(result)) {
								text.setText(String.valueOf((int) result));
							} else {
								text.setText(String.valueOf(result));
							}
						}
					} else {
						double number2 = Double.parseDouble(text.getText());
						switchCase();
						if (isValid) {
							if (result <= 0) {
								text.setText("Invalid Input!");
								field.setText("");
								isValid = false;
							} else {
								if (number2 == Math.floor(number2)) {
									field.setText("ln(" + field.getText() + (int) number2 + ")" + " =");
								} else {
									field.setText("ln(" + field.getText() + number2 + ")" + " =");
								}
								if (result == Math.floor(result)) {
									text.setText(String.valueOf(Math.log(result)));
								} else {
									text.setText(String.valueOf(Math.log(result)));
								}
							}
						}
					}
					command = 12;
				}
			}
		});
		btnln.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnln.setBounds(20, 446, 108, 39);
		btnln.setFocusable(false);
		btnln.setVisible(false);
		frmCalculator.getContentPane().add(btnln);

		JButton btnepowx = new JButton("eˣ");
		btnepowx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (text.getText().equals("") || text.getText().equals("Invalid Input!")
						|| text.getText().equals("Cannot divide by zero!")) {
					text.setText("Invalid Input!");
					field.setText("");
					isValid = false;
				} else {
					if (!(command == 1 || command == 2 || command == 3 || command == 4 || command == 5 || command == 6
							|| command == 7)) {
						double number1 = Double.parseDouble(text.getText());
						result = Math.exp(number1);
						if (number1 == Math.floor(number1)) {
							field.setText("e ^ " + (int) number1 + " =");
						} else {
							field.setText("e ^ " + number1 + " =");
						}
						if (result == Math.floor(result)) {
							text.setText(String.valueOf((int) result));
						} else {
							text.setText(String.valueOf(result));
						}
					} else {
						double number2 = Double.parseDouble(text.getText());
						switchCase();
						if (isValid) {
							if (number2 == Math.floor(number2)) {
								field.setText("e ^ " + "(" + field.getText() + (int) number2 + ")" + " =");
							} else {
								field.setText("e ^ " + "(" + field.getText() + number2 + ")" + " =");
							}
							if (Math.exp(result) == Math.floor(Math.exp(result))) {
								text.setText(String.valueOf((int) Math.exp(result)));
							} else {
								text.setText(String.valueOf(Math.exp(result)));
							}
						}
					}
					command = 13;
				}
			}
		});
		btnepowx.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnepowx.setBounds(256, 446, 108, 39);
		btnepowx.setFocusable(false);
		btnepowx.setVisible(false);
		frmCalculator.getContentPane().add(btnepowx);

		JButton btnabs = new JButton("|x|");
		btnabs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (text.getText().equals("") || text.getText().equals("Invalid Input!")
						|| text.getText().equals("Cannot divide by zero!")) {
					text.setText("Invalid Input!");
					field.setText("");
					isValid = false;
				} else {
					if (!(command == 1 || command == 2 || command == 3 || command == 4 || command == 5 || command == 6
							|| command == 7)) {
						double number1 = Double.parseDouble(text.getText());
						result = Math.abs(number1);
						if (number1 == Math.floor(number1)) {
							field.setText("|" + (int) number1 + "|" + " =");
						} else {
							field.setText("|" + number1 + "|" + " =");
						}
						if (result == Math.floor(result)) {
							text.setText(String.valueOf((int) result));
						} else {
							text.setText(String.valueOf(result));
						}
					} else {
						double number2 = Double.parseDouble(text.getText());
						switchCase();
						if (isValid) {
							if (number2 == Math.floor(number2)) {
								field.setText("|" + field.getText() + (int) number2 + "|" + " =");
							} else {
								field.setText("|" + field.getText() + number2 + "|" + " =");
							}
							if (result == Math.floor(result)) {
								text.setText(String.valueOf((int) Math.abs(result)));
							} else {
								text.setText(String.valueOf(Math.abs(result)));
							}
						}
					}
					command = 14;
				}
			}
		});
		btnabs.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnabs.setBounds(374, 496, 89, 39);
		btnabs.setFocusable(false);
		btnabs.setVisible(false);
		frmCalculator.getContentPane().add(btnabs);

		JButton btntenpowx = new JButton("10ˣ");
		btntenpowx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (text.getText().equals("") || text.getText().equals("Invalid Input!")
						|| text.getText().equals("Cannot divide by zero!")) {
					text.setText("Invalid Input!");
					field.setText("");
					isValid = false;
				} else {
					if (!(command == 1 || command == 2 || command == 3 || command == 4 || command == 5 || command == 6
							|| command == 7)) {
						double number1 = Double.parseDouble(text.getText());
						result = Math.pow(10, number1);
						if (number1 == Math.floor(number1)) {
							field.setText("10 ^ " + (int) number1 + " =");
						} else {
							field.setText("10 ^ " + number1 + " =");
						}
						if (result == Math.floor(result)) {
							text.setText(String.valueOf((int) result));
						} else {
							text.setText(String.valueOf(result));
						}
					} else {
						double number2 = Double.parseDouble(text.getText());
						switchCase();
						if (isValid) {
							if (number2 == Math.floor(number2)) {
								field.setText("10 ^ " + "(" + field.getText() + (int) number2 + ")" + " =");
							} else {
								field.setText("10 ^ " + "(" + field.getText() + number2 + ")" + " =");
							}
							if (Math.pow(10, result) == Math.floor(Math.pow(10, result))) {
								text.setText(String.valueOf((int) Math.pow(10, result)));
							} else {
								text.setText(String.valueOf(Math.pow(10, result)));
							}
						}
					}
					command = 18;
				}
			}
		});
		btntenpowx.setFont(new Font("SansSerif", Font.BOLD, 20));
		btntenpowx.setBounds(374, 446, 89, 39);
		btntenpowx.setFocusable(false);
		btntenpowx.setVisible(false);
		frmCalculator.getContentPane().add(btntenpowx);
		
		JButton btncube = new JButton("x³");
		btncube.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (text.getText().equals("") || text.getText().equals("Invalid Input!")
						|| text.getText().equals("Cannot divide by zero!")) {
					text.setText("Invalid Input!");
					field.setText("");
					isValid = false;
				} else {
					if (!(command == 1 || command == 2 || command == 3 || command == 4 || command == 5 || command == 6
							|| command == 7)) {
						double number1 = Double.parseDouble(text.getText());
						result = Math.pow(number1, 3);
						if (number1 == Math.floor(number1)) {
							field.setText((int) number1 + "³" + " =");
						} else {
							field.setText("(" + number1 + ")" + "³" + " =");
						}
						if (result == Math.floor(result)) {
							text.setText(String.valueOf((int) result));
						} else {
							text.setText(String.valueOf(result));
						}
					} else {
						double number2 = Double.parseDouble(text.getText());
						switchCase();
						if (isValid) {
							if (number2 == Math.floor(number2)) {
								field.setText("(" + field.getText() + (int) number2 + ")" + "³" + " =");
							} else {
								field.setText("(" + field.getText() + number2 + ")" + "³" + " =");
							}
							if (result == Math.floor(result)) {
								text.setText(String.valueOf((int) Math.pow(result, 3)));
							} else {
								text.setText(String.valueOf(Math.pow(result, 3)));
							}
						}
					}
					command = 19;
				}
			}
		});
		btncube.setFont(new Font("SansSerif", Font.BOLD, 20));
		btncube.setBounds(256, 346, 108, 39);
		btncube.setFocusable(false);
		frmCalculator.getContentPane().add(btncube);
		
		JButton btncuberoot = new JButton("³√x");
		btncuberoot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (text.getText().equals("") || text.getText().equals("Invalid Input!")
						|| text.getText().equals("Cannot divide by zero!")) {
					text.setText("Invalid Input!");
					field.setText("");
					isValid = false;
				} else {
					if (!(command == 1 || command == 2 || command == 3 || command == 4 || command == 5 || command == 6
							|| command == 7)) {
						double number1 = Double.parseDouble(text.getText());
							result = Math.cbrt(number1);
							if (number1 == Math.floor(number1)) {
								field.setText("³√" + (int) number1 + " =");
							} else {
								field.setText("³√" + number1 + " =");
							}
							if (result == Math.floor(result)) {
								text.setText(String.valueOf((int) result));
							} else {
								text.setText(String.valueOf(result));
						}
					} else {
						double number2 = Double.parseDouble(text.getText());
						switchCase();
						if (isValid) {
							if (number2 == Math.floor(number2)) {
								field.setText("³√" + "(" + field.getText() + (int) number2 + ")" + " =");
							} else {
								field.setText("³√" + "(" + field.getText() + number2 + ")" + " =");
							}
							if (result == Math.floor(result)) {
								text.setText(String.valueOf((int) Math.cbrt(result)));
							} else {
								text.setText(String.valueOf(Math.cbrt(result)));
							}
						}
					}
					command = 20;
				}
			}
		});
		btncuberoot.setFont(new Font("SansSerif", Font.BOLD, 20));
		btncuberoot.setBounds(374, 346, 89, 39);
		btncuberoot.setFocusable(false);
		frmCalculator.getContentPane().add(btncuberoot);

		JButton btnac = new JButton("AC");
		btnac.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text.setText("0");
				field.setText("");
				result = 0;
				command = 0;
				isValid = true;
				isFirst = true;
			}
		});
		btnac.setBounds(374, 11, 89, 35);
		btnac.setFocusable(false);
		frmCalculator.getContentPane().add(btnac);
		
		JButton btndel = new JButton("⌫");
		btndel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double number1 = Double.parseDouble(text.getText());
				if (!field.getText().contains("=")) {
					if (!text.getText().equals("0")) {
						if (number1 < 0 && text.getText().length() == 2) {
							text.setText("0");
					    	field.setText("");
						} else if (text.getText().length() == 1) {
							text.setText("0");
					    	field.setText("");
						} else {
							text.setText(text.getText().substring(0, text.getText().length() - 1));
						}
				    } else {
				    	text.setText("0");
				    	field.setText("");
				    }
				}
			}
		});
		btndel.setFont(new Font("SansSerif", Font.BOLD, 20));
		btndel.setBounds(374, 52, 89, 35);
		btndel.setFocusable(false);
		frmCalculator.getContentPane().add(btndel);

		JButton btnposneg = new JButton("+/-");
		btnposneg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (text.getText().equals("") || text.getText().equals("Invalid Input!")
						|| text.getText().equals("Cannot divide by zero!")) {
					text.setText("Invalid Input!");
					field.setText("");
					isValid = false;
				} else {
					if (!(command == 1 || command == 2 || command == 3 || command == 4 || command == 5 || command == 6
							|| command == 7)) {
						double number1 = Double.parseDouble(text.getText());
						result = number1 * -1;
						if (result == Math.floor(result)) {
							text.setText(String.valueOf((int) result));
						} else {
							text.setText(String.valueOf(result));
						}
					} else {
						double number2 = Double.parseDouble(text.getText());
						number2 *= -1;
						if (number2 == Math.floor(number2)) {
							text.setText(String.valueOf((int) number2));
						} else {
							text.setText(String.valueOf(number2));
						}
					}
				}
			}
		});
		btnposneg.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnposneg.setBounds(20, 296, 108, 39);
		btnposneg.setFocusable(false);
		frmCalculator.getContentPane().add(btnposneg);
		
		JButton btnfactorial = new JButton("x!");
		btnfactorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (text.getText().equals("") || text.getText().equals("Invalid Input!")
						|| text.getText().equals("Cannot divide by zero!")) {
					text.setText("Invalid Input!");
					field.setText("");
					isValid = false;
				} else {
					if (!(command == 1 || command == 2 || command == 3 || command == 4 || command == 5 || command == 6
							|| command == 7)) {
						double number1 = Double.parseDouble(text.getText());
						if (number1 < 0 || number1 != Math.floor(number1)) {
							text.setText("Invalid Input!");
							field.setText("");
							isValid = false;
						} else {
							result = factorial(number1);
							field.setText((int) number1 + "!" + " =");
							if (result == Math.floor(result)) {
								text.setText(String.valueOf((int) result));
							} else {
								text.setText(String.valueOf(result));
							}
						}
					} else {
						double number2 = Double.parseDouble(text.getText());
						switchCase();
						if (isValid) {
							if (result < 0 || result != Math.floor(result)) {
								text.setText("Invalid Input!");
								field.setText("");
								isValid = false;
							} else {
								if (number2 == Math.floor(number2)) {
									field.setText("(" + field.getText() + (int) number2 + ")" + "!" + " =");
								} else {
									field.setText("(" + field.getText() + number2 + ")" + "!" + " =");
								}
								if (factorial(result) == Math.floor(factorial(result))) {
									text.setText(String.valueOf((int) factorial(result)));
								} else {
									text.setText(String.valueOf(factorial(result)));
								}
							}
						}
					}
				}
			}
		});
		btnfactorial.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnfactorial.setBounds(374, 396, 89, 39);
		btnfactorial.setFocusable(false);
		btnfactorial.setVisible(false);
		frmCalculator.getContentPane().add(btnfactorial);

		JButton btnequals = new JButton("=");
		btnequals.setFont(new Font("SansSerif", Font.BOLD, 24));
		btnequals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (text.getText().equals("") || text.getText().equals("Invalid Input!")
						|| text.getText().equals("Cannot divide by zero!")) {
					text.setText("Invalid Input!");
					field.setText("");
					isValid = false;
				} else {
					double number2 = Double.parseDouble(text.getText());
					switchCase();
					if (isValid) {
						if (command == 0) {
							double number1 = Double.parseDouble(text.getText());
							text.setText(String.valueOf((int) number1));
						} else {
							if (number2 == Math.floor(number2)) {
								field.setText(field.getText() + (int) number2 + " =");
							} else {
								field.setText(field.getText() + number2 + " =");
							}
							if (result == Math.floor(result)) {
								text.setText(String.valueOf((int) result));
							} else {
								text.setText(String.valueOf(result));
							}
						}
					}
				}
				command = 0;
				isFirst = true;
				isValid = true;
			}
		});
		btnequals.setBounds(374, 296, 89, 39);
		btnequals.setFocusable(false);
		frmCalculator.getContentPane().add(btnequals);

		JButton btnsci = new JButton("Scientific");
		btnsci.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnsci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isScientific) {
					frmCalculator.setBounds(1000, 500, 500, 585);
					btnsci.setText("Basic");
					btnsqr.setVisible(true);
					btnsqroot.setVisible(true);
					btncube.setVisible(true);
					btncuberoot.setVisible(true);
					btndivby1.setVisible(true);
					btnpow.setVisible(true);
					btnyrootx.setVisible(true);
					btnfactorial.setVisible(true);
					btnln.setVisible(true);
					btnlogyx.setVisible(true);
					btnepowx.setVisible(true);
					btnabs.setVisible(true);
					btnsin.setVisible(true);
					btncos.setVisible(true);
					btntan.setVisible(true);
					btntenpowx.setVisible(true);
					isScientific = true;
				} else {
					frmCalculator.setBounds(1000, 500, 500, 385);
					btnsci.setText("Scientific");
					btnsqr.setVisible(false);
					btnsqroot.setVisible(false);
					btncube.setVisible(false);
					btncuberoot.setVisible(false);
					btndivby1.setVisible(false);
					btnpow.setVisible(false);
					btnyrootx.setVisible(false);
					btnfactorial.setVisible(false);
					btnln.setVisible(false);
					btnlogyx.setVisible(false);
					btnepowx.setVisible(false);
					btnabs.setVisible(false);
					btnsin.setVisible(false);
					btncos.setVisible(false);
					btntan.setVisible(false);
					btntenpowx.setVisible(false);
					isScientific = false;
				}
			}
		});
		btnsci.setBounds(20, 96, 108, 39);
		btnsci.setFocusable(false);
		frmCalculator.getContentPane().add(btnsci);
		
		JButton btnprntopen = new JButton("(");
		btnprntopen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				field.setText(null);
			}
		});
		btnprntopen.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnprntopen.setBounds(138, 96, 108, 39);
		btnprntopen.setFocusable(false);
		frmCalculator.getContentPane().add(btnprntopen);
		
		JButton btnprntclose = new JButton(")");
		btnprntclose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				field.setText(null);
			}
		});
		btnprntclose.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnprntclose.setBounds(256, 96, 108, 39);
		btnprntclose.setFocusable(false);
		frmCalculator.getContentPane().add(btnprntclose);
		
		setupKeyBindings(panel, btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btndot, btnplus, btnsubs, btnmult, btndiv, btnequals, btndel, btnprntopen, btnprntclose, btnac);
	}
}
