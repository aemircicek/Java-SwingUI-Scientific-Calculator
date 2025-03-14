package calculator;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class KeypadPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private DisplayPanel displayPanel;

    public KeypadPanel(DisplayPanel displayPanel) {
        this.displayPanel = displayPanel;
        setLayout(null);
        addSpecialButtons();
    }

    private JButton addNumberButton(char digit, int x, int y) {
        JButton button = new JButton(String.valueOf(digit));
        button.setFont(new Font("SansSerif", Font.BOLD, 20));
        button.setBounds(x, y, 108, 39);
        button.setFocusable(false);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String currentText = displayPanel.getTextField().getText();
                if (currentText.equals("Invalid Input!") || currentText.equals("Cannot divide by zero!")) {
                    displayPanel.getTextField().setText("");
                }
                if (!displayPanel.getField().getText().contains("=")) {
                    if (CalculatorLogic.isValid) {
                        if (digit == '0') {
                            if (!displayPanel.getTextField().getText().equals("0")) {
                                displayPanel.getTextField().setText(displayPanel.getTextField().getText() + "0");
                            }
                        } else if (digit == '.') {
                            if (!displayPanel.getTextField().getText().contains(".")) {
                                if (displayPanel.getTextField().getText().equals("")) {
                                    displayPanel.getTextField().setText("0.");
                                } else {
                                    displayPanel.getTextField().setText(displayPanel.getTextField().getText() + ".");
                                }
                            }
                        } else {
                            if (displayPanel.getTextField().getText().equals("0")) {
                                displayPanel.getTextField().setText("");
                            }
                            displayPanel.getTextField().setText(displayPanel.getTextField().getText() + digit);
                        }
                    } else {
                        displayPanel.getField().setText("");
                        displayPanel.getTextField().setText("");
                        CalculatorLogic.result = 0;
                        CalculatorLogic.command = 0;
                        CalculatorLogic.isValid = true;
                        if (digit == '.') {
                            displayPanel.getTextField().setText("0.");
                        } else {
                            displayPanel.getTextField().setText(displayPanel.getTextField().getText() + digit);
                        }
                    }
                } else {
                    CalculatorLogic.result = 0;
                    CalculatorLogic.command = 0;
                    CalculatorLogic.isFirst = true;
                    displayPanel.getTextField().setText("");
                    displayPanel.getField().setText("");
                    if (digit == '.') {
                        displayPanel.getTextField().setText("0.");
                    } else {
                        displayPanel.getTextField().setText(displayPanel.getTextField().getText() + digit);
                    }
                }
            }
        });
        add(button);
        return button;
    }

    JButton addOperatorButton(String label, int cmnd, int x, int y, int z) {
        JButton button = new JButton(label);
        button.setFont(new Font("SansSerif", Font.BOLD, 20));
        button.setBounds(x, y, z, 39);
        button.setFocusable(false);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (displayPanel.getTextField().getText().equals("") || displayPanel.getTextField().getText().equals("Invalid Input!")
                        || displayPanel.getTextField().getText().equals("Cannot divide by zero!")) {
                    displayPanel.getTextField().setText("Invalid Input!");
                    displayPanel.getField().setText("");
                    CalculatorLogic.isValid = false;
                } else {
                    if (CalculatorLogic.isFirst) {
                        double number1 = Double.parseDouble(displayPanel.getTextField().getText());
                        CalculatorLogic.result = number1;
                        displayPanel.getTextField().setText("0");
                        if (number1 == Math.floor(number1)) {
                            if (displayPanel.getField().getText().contains("=")) {
                                displayPanel.getField().setText("");
                            }
                            if (label.equals("÷")) {
                                displayPanel.getField().setText(displayPanel.getField().getText() + (int) number1 + " / ");
                            } else if (label.equals("*")) {
                                displayPanel.getField().setText(displayPanel.getField().getText() + (int) number1 + " * ");
                            } else if (label.equals("-")) {
                                displayPanel.getField().setText(displayPanel.getField().getText() + (int) number1 + " - ");
                            } else if (label.equals("+")) {
                                displayPanel.getField().setText(displayPanel.getField().getText() + (int) number1 + " + ");
                            }
                        } else {
                            if (displayPanel.getField().getText().contains("=")) {
                                displayPanel.getField().setText("");
                            }
                            if (label.equals("÷")) {
                                displayPanel.getField().setText(displayPanel.getField().getText() + number1 + " / ");
                            } else if (label.equals("*")) {
                                displayPanel.getField().setText(displayPanel.getField().getText() + number1 + " * ");
                            } else if (label.equals("-")) {
                                displayPanel.getField().setText(displayPanel.getField().getText() + number1 + " - ");
                            } else if (label.equals("+")) {
                                displayPanel.getField().setText(displayPanel.getField().getText() + number1 + " + ");
                            }
                        }
                        CalculatorLogic.isFirst = false;
                    } else {
                        double number2 = Double.parseDouble(displayPanel.getTextField().getText());
                        CalculatorLogic.switchCase();
                        displayPanel.getTextField().setText("0");
                        if (number2 == Math.floor(number2)) {
                            if (displayPanel.getField().getText().contains("=")) {
                                displayPanel.getField().setText("");
                            }
                            if (label.equals("÷")) {
                                displayPanel.getField().setText(displayPanel.getField().getText() + (int) number2 + " / ");
                            } else if (label.equals("*")) {
                            	if (displayPanel.getField().getText().contains("(") && !displayPanel.getField().getText().contains(")")) {
                            		displayPanel.getField().setText(displayPanel.getField().getText() + (int) number2 + ")" + " * ");
                            	}  else if (displayPanel.getField().getText().contains(")")) {
                            		displayPanel.getField().setText(displayPanel.getField().getText() + " * ");
                            	} else {
                            		displayPanel.getField().setText(displayPanel.getField().getText() + (int) number2 + " * ");
                            	}
                            } else if (label.equals("-")) {
                                displayPanel.getField().setText(displayPanel.getField().getText() + (int) number2 + " - ");
                            } else if (label.equals("+")) {
                                displayPanel.getField().setText(displayPanel.getField().getText() + (int) number2 + " + ");
                            }
                        } else {
                            if (displayPanel.getField().getText().contains("=")) {
                                displayPanel.getField().setText("");
                            }
                            if (label.equals("÷")) {
                                displayPanel.getField().setText(displayPanel.getField().getText() + number2 + " / ");
                            } else if (label.equals("*")) {
                                displayPanel.getField().setText(displayPanel.getField().getText() + number2+ " * ");
                            } else if (label.equals("-")) {
                                displayPanel.getField().setText(displayPanel.getField().getText() + number2 + " - ");
                            } else if (label.equals("+")) {
                                displayPanel.getField().setText(displayPanel.getField().getText() + number2 + " + ");
                            }
                        }
                    }
                    CalculatorLogic.command = cmnd;
                }
            }
        });
        add(button);
        return button;
    }

    JButton btn0 = addNumberButton('0', 118, 200);
    JButton btn1 = addNumberButton('1', 0, 150);
    JButton btn2 = addNumberButton('2', 118, 150);
    JButton btn3 = addNumberButton('3', 236, 150);
    JButton btn4 = addNumberButton('4', 0, 100);
    JButton btn5 = addNumberButton('5', 118, 100);
    JButton btn6 = addNumberButton('6', 236, 100);
    JButton btn7 = addNumberButton('7', 0, 50);
    JButton btn8 = addNumberButton('8', 118, 50);
    JButton btn9 = addNumberButton('9', 236, 50);
    JButton btndot = addNumberButton('.', 236, 200);
    
    JButton btnplus = addOperatorButton("+", 1, 354, 150, 89);
    JButton btnsubs = addOperatorButton("-", 2, 354, 100, 89);
    JButton btnmult = addOperatorButton("*", 3, 354, 50, 89);
    JButton btndiv = addOperatorButton("÷", 4, 354, 0, 89);

    private void addSpecialButtons() {

        JButton btnposneg = new JButton("+/-");
        btnposneg.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (displayPanel.getTextField().getText().equals("") || displayPanel.getTextField().getText().equals("Invalid Input!")
                        || displayPanel.getTextField().getText().equals("Cannot divide by zero!")) {
                    displayPanel.getTextField().setText("Invalid Input!");
                    displayPanel.getField().setText("");
                    CalculatorLogic.isValid = false;
                } else {
                    if (!(CalculatorLogic.command == 1 || CalculatorLogic.command == 2 || CalculatorLogic.command == 3 ||
                          CalculatorLogic.command == 4 || CalculatorLogic.command == 5 || CalculatorLogic.command == 6 ||
                          CalculatorLogic.command == 7)) {
                        double number1 = Double.parseDouble(displayPanel.getTextField().getText());
                        CalculatorLogic.result = number1 * -1;
                        if (CalculatorLogic.result == Math.floor(CalculatorLogic.result)) {
                            displayPanel.getTextField().setText(String.valueOf((int) CalculatorLogic.result));
                        } else {
                            displayPanel.getTextField().setText(String.valueOf(CalculatorLogic.result));
                        }
                    } else {
                        double number2 = Double.parseDouble(displayPanel.getTextField().getText());
                        number2 *= -1;
                        if (number2 == Math.floor(number2)) {
                            displayPanel.getTextField().setText(String.valueOf((int) number2));
                        } else {
                            displayPanel.getTextField().setText(String.valueOf(number2));
                        }
                    }
                }
            }
        });
        btnposneg.setFont(new Font("SansSerif", Font.BOLD, 20));
        btnposneg.setBounds(0, 200, 108, 39);
        btnposneg.setFocusable(false);
        add(btnposneg);

        JButton btnequals = new JButton("=");
        btnequals.setFont(new Font("SansSerif", Font.BOLD, 24));
        btnequals.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (displayPanel.getTextField().getText().equals("") ||
                    displayPanel.getTextField().getText().equals("Invalid Input!") ||
                    displayPanel.getTextField().getText().equals("Cannot divide by zero!")) {
                    displayPanel.getTextField().setText("Invalid Input!");
                    displayPanel.getField().setText("");
                    CalculatorLogic.isValid = false;
                } else {
                    String fieldExpr = displayPanel.getField().getText();
                    String currentNum = displayPanel.getTextField().getText();
                    String expr;
                    if (fieldExpr.contains("x")) {
                        fieldExpr = fieldExpr.replace("x", currentNum);
                    }
                    if (fieldExpr.contains("(") || fieldExpr.contains(")")) {
                        if(currentNum.equals("0") || currentNum.isEmpty()){
                            expr = fieldExpr;
                        } else {
                            if (!fieldExpr.trim().endsWith(")")) {
                                expr = fieldExpr + currentNum;
                            } else {
                                expr = fieldExpr;
                            }
                        }
                    } else {
                        expr = fieldExpr + currentNum;
                    }
                    try {
                        double result = CalculatorLogic.evaluateExpression(expr);
                        displayPanel.getTextField().setText(CalculatorLogic.formatResult(result));
                        displayPanel.getField().setText(expr + " =");
                    } catch (Exception ex) {
                        displayPanel.getTextField().setText("Invalid Input!");
                        displayPanel.getField().setText("");
                        CalculatorLogic.isValid = false;
                    }
                    CalculatorLogic.command = 0;
                    CalculatorLogic.isFirst = true;
                    CalculatorLogic.isValid = true;
                }
            }
        });
        btnequals.setBounds(354, 200, 89, 39);
        btnequals.setFocusable(false);
        add(btnequals);


        JButton btnsci = new JButton("Scientific");
        btnsci.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnsci.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!CalculatorLogic.isScientific) {
                	CalculatorFrame.frame.setBounds(1000, 350, 500, 585);
                    btnsci.setText("Basic");
                    CalculatorFrame.scientificPanel.btnsqr.setVisible(true);
                    CalculatorFrame.scientificPanel.btnsqroot.setVisible(true);
                    CalculatorFrame.scientificPanel.btncube.setVisible(true);
                    CalculatorFrame.scientificPanel.btncuberoot.setVisible(true);
                    CalculatorFrame.scientificPanel.btndivby1.setVisible(true);
                    CalculatorFrame.scientificPanel.btnpow.setVisible(true);
                    CalculatorFrame.scientificPanel.btnyrootx.setVisible(true);
                    CalculatorFrame.scientificPanel.btnfactorial.setVisible(true);
                    CalculatorFrame.scientificPanel.btnln.setVisible(true);
                    CalculatorFrame.scientificPanel.btnlogyx.setVisible(true);
                    CalculatorFrame.scientificPanel.btnepowx.setVisible(true);
                    CalculatorFrame.scientificPanel.btnabs.setVisible(true);
                    CalculatorFrame.scientificPanel.btnsin.setVisible(true);
                    CalculatorFrame.scientificPanel.btncos.setVisible(true);
                    CalculatorFrame.scientificPanel.btntan.setVisible(true);
                    CalculatorFrame.scientificPanel.btntenpowx.setVisible(true);
                    CalculatorLogic.isScientific = true;
                } else {
                	CalculatorFrame.frame.setBounds(1000, 350, 500, 385);
                    btnsci.setText("Scientific");
                    CalculatorFrame.scientificPanel.btnsqr.setVisible(false);
                    CalculatorFrame.scientificPanel.btnsqroot.setVisible(false);
                    CalculatorFrame.scientificPanel.btncube.setVisible(false);
                    CalculatorFrame.scientificPanel.btncuberoot.setVisible(false);
                    CalculatorFrame.scientificPanel.btndivby1.setVisible(false);
                    CalculatorFrame.scientificPanel.btnpow.setVisible(false);
                    CalculatorFrame.scientificPanel.btnyrootx.setVisible(false);
                    CalculatorFrame.scientificPanel.btnfactorial.setVisible(false);
                    CalculatorFrame.scientificPanel.btnln.setVisible(false);
                    CalculatorFrame.scientificPanel.btnlogyx.setVisible(false);
                    CalculatorFrame.scientificPanel.btnepowx.setVisible(false);
                    CalculatorFrame.scientificPanel.btnabs.setVisible(false);
                    CalculatorFrame.scientificPanel.btnsin.setVisible(false);
                    CalculatorFrame.scientificPanel.btncos.setVisible(false);
                    CalculatorFrame.scientificPanel.btntan.setVisible(false);
                    CalculatorFrame.scientificPanel.btntenpowx.setVisible(false);
                    CalculatorLogic.isScientific = false;
                }
            }
        });
        btnsci.setBounds(0, 0, 108, 39);
        btnsci.setFocusable(false);
        add(btnsci);
        
        JButton btnprntopen = new JButton("(");
        btnprntopen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	displayPanel.getField().setText(displayPanel.getField().getText() + "(");
            	CalculatorLogic.openParenthesesCount++;
            }
        });
        btnprntopen.setFont(new Font("SansSerif", Font.BOLD, 20));
        btnprntopen.setBounds(118, 0, 108, 39);
        btnprntopen.setFocusable(false);
        add(btnprntopen);
        
        JButton btnprntclose = new JButton(")");
        btnprntclose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            		double number2 = Double.parseDouble(displayPanel.getTextField().getText());
            		if (CalculatorLogic.openParenthesesCount > 0) {
            			if (number2 == Math.floor(number2)) {
                    		displayPanel.getField().setText(displayPanel.getField().getText() + (int) number2 + ")");
                    	} else {
                    		displayPanel.getField().setText(displayPanel.getField().getText() + number2 + ")");
                    	}
                    	CalculatorLogic.isFirst = false;
                        CalculatorLogic.openParenthesesCount--;
                    }
            }
        });
        btnprntclose.setFont(new Font("SansSerif", Font.BOLD, 20));
        btnprntclose.setBounds(236, 0, 108, 39);
        btnprntclose.setFocusable(false);
        add(btnprntclose);
        
        KeyBindingsUtil.setupKeyBindings(displayPanel, btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btndot, btnplus, btnsubs, btnmult, btndiv, btnequals, btnprntopen, btnprntclose);
    }
}