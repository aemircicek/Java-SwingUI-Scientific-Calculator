package calculator;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ScientificPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    public JButton btnsqr, btnsqroot, btncube, btncuberoot, btndivby1, btnpow, btnyrootx, btnfactorial, btnln, btnlogyx, btnepowx, btntenpowx, btnsin, btncos, btntan, btnabs;
    private DisplayPanel displayPanel;
    private KeypadPanel keypadPanel;

    public ScientificPanel(DisplayPanel displayPanel, KeypadPanel keypadPanel) {
        this.displayPanel = displayPanel;
        this.keypadPanel = keypadPanel;
        setLayout(null);
        
        btnpow = keypadPanel.addOperatorButton("xʸ", 5, 118, 50, 108);
        btnyrootx = keypadPanel.addOperatorButton("ʸ√x", 6, 236, 50, 108);
        
        btnsin = keypadPanel.addTrigonometricButton("sin", Math::sin, 15, 0, 150);
        btncos = keypadPanel.addTrigonometricButton("cos", Math::cos, 16, 118, 150);
        btntan = keypadPanel.addTrigonometricButton("tan", Math::tan, 17, 236, 150);
        
        btnsqr = new JButton("x²");
        btnsqr.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (displayPanel.getTextField().getText().equals("") || 
                    displayPanel.getTextField().getText().equals("Invalid Input!") ||
                    displayPanel.getTextField().getText().equals("Cannot divide by zero!")) {
                    displayPanel.getTextField().setText("Invalid Input!");
                    displayPanel.getField().setText("");
                    CalculatorLogic.isValid = false;
                } else {
                    if (!(CalculatorLogic.command == 1 || CalculatorLogic.command == 2 ||
                          CalculatorLogic.command == 3 || CalculatorLogic.command == 4 ||
                          CalculatorLogic.command == 5 || CalculatorLogic.command == 6 ||
                          CalculatorLogic.command == 7)) {
                        double number1 = Double.parseDouble(displayPanel.getTextField().getText());
                        CalculatorLogic.result = Math.pow(number1, 2);
                        if (number1 == Math.floor(number1)) {
                            displayPanel.getField().setText((int) number1 + "²" + " =");
                        } else {
                            displayPanel.getField().setText("(" + number1 + ")" + "²" + " =");
                        }
                        if (CalculatorLogic.result == Math.floor(CalculatorLogic.result)) {
                            displayPanel.getTextField().setText(String.valueOf((int) CalculatorLogic.result));
                        } else {
                            displayPanel.getTextField().setText(String.valueOf(CalculatorLogic.result));
                        }
                    } else {
                        CalculatorLogic.switchCase();
                        double number2 = Double.parseDouble(displayPanel.getTextField().getText());
                        if (CalculatorLogic.isValid) {
                            if (number2 == Math.floor(number2)) {
                                displayPanel.getField().setText("(" + displayPanel.getField().getText() + (int) number2 + ")" + "²" + " =");
                            } else {
                                displayPanel.getField().setText("(" + displayPanel.getField().getText() + number2 + ")" + "²" + " =");
                            }
                            if (CalculatorLogic.result == Math.floor(CalculatorLogic.result)) {
                                displayPanel.getTextField().setText(String.valueOf((int) Math.pow(CalculatorLogic.result, 2)));
                            } else {
                                displayPanel.getTextField().setText(String.valueOf(Math.pow(CalculatorLogic.result, 2)));
                            }
                        }
                    }
                    CalculatorLogic.command = 8;
                }
            }
        });
        btnsqr.setFont(new Font("SansSerif", Font.BOLD, 20));
        btnsqr.setBounds(0, 0, 108, 39);
        btnsqr.setFocusable(false);
        add(btnsqr);

        btnsqroot = new JButton("√x");
        btnsqroot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (displayPanel.getTextField().getText().equals("") ||
                    displayPanel.getTextField().getText().equals("Invalid Input!") ||
                    displayPanel.getTextField().getText().equals("Cannot divide by zero!")) {
                    displayPanel.getTextField().setText("Invalid Input!");
                    CalculatorLogic.isValid = false;
                } else {
                    if (!(CalculatorLogic.command == 1 || CalculatorLogic.command == 2 ||
                          CalculatorLogic.command == 3 || CalculatorLogic.command == 4 ||
                          CalculatorLogic.command == 5 || CalculatorLogic.command == 6 ||
                          CalculatorLogic.command == 7)) {
                        double number1 = Double.parseDouble(displayPanel.getTextField().getText());
                        if (number1 < 0) {
                            displayPanel.getTextField().setText("Invalid Input!");
                            displayPanel.getField().setText("");
                            CalculatorLogic.isValid = false;
                        } else {
                            CalculatorLogic.result = Math.sqrt(number1);
                            if (number1 == Math.floor(number1)) {
                                displayPanel.getField().setText("√" + (int) number1 + " =");
                            } else {
                                displayPanel.getField().setText("√" + number1 + " =");
                            }
                            if (CalculatorLogic.result == Math.floor(CalculatorLogic.result)) {
                                displayPanel.getTextField().setText(String.valueOf((int) CalculatorLogic.result));
                            } else {
                                displayPanel.getTextField().setText(String.valueOf(CalculatorLogic.result));
                            }
                        }
                    } else {
                        double number2 = Double.parseDouble(displayPanel.getTextField().getText());
                        CalculatorLogic.switchCase();
                        if (CalculatorLogic.isValid) {
                            if (CalculatorLogic.result < 0) {
                                displayPanel.getTextField().setText("Invalid Input!");
                                displayPanel.getField().setText("");
                                CalculatorLogic.isValid = false;
                            } else {
                                if (number2 == Math.floor(number2)) {
                                    displayPanel.getField().setText("√" + "(" + displayPanel.getField().getText() + (int) number2 + ")" + " =");
                                } else {
                                    displayPanel.getField().setText("√" + "(" + displayPanel.getField().getText() + number2 + ")" + " =");
                                }
                                if (CalculatorLogic.result == Math.floor(CalculatorLogic.result)) {
                                    displayPanel.getTextField().setText(String.valueOf((int) Math.sqrt(CalculatorLogic.result)));
                                } else {
                                    displayPanel.getTextField().setText(String.valueOf(Math.sqrt(CalculatorLogic.result)));
                                }
                            }
                        }
                    }
                    CalculatorLogic.command = 9;
                }
            }
        });
        btnsqroot.setFont(new Font("SansSerif", Font.BOLD, 20));
        btnsqroot.setBounds(118, 0, 108, 39);
        btnsqroot.setFocusable(false);
        add(btnsqroot);
        
        btncube = new JButton("x³");
        btncube.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (displayPanel.getTextField().getText().equals("") ||
                    displayPanel.getTextField().getText().equals("Invalid Input!") ||
                    displayPanel.getTextField().getText().equals("Cannot divide by zero!")) {
                    displayPanel.getTextField().setText("Invalid Input!");
                    displayPanel.getField().setText("");
                    CalculatorLogic.isValid = false;
                } else {
                    if (!(CalculatorLogic.command == 1 || CalculatorLogic.command == 2 ||
                          CalculatorLogic.command == 3 || CalculatorLogic.command == 4 ||
                          CalculatorLogic.command == 5 || CalculatorLogic.command == 6 ||
                          CalculatorLogic.command == 7)) {
                        double number1 = Double.parseDouble(displayPanel.getTextField().getText());
                        CalculatorLogic.result = Math.pow(number1, 3);
                        if (number1 == Math.floor(number1)) {
                            displayPanel.getField().setText((int) number1 + "³" + " =");
                        } else {
                            displayPanel.getField().setText("(" + number1 + ")" + "³" + " =");
                        }
                        if (CalculatorLogic.result == Math.floor(CalculatorLogic.result)) {
                            displayPanel.getTextField().setText(String.valueOf((int) CalculatorLogic.result));
                        } else {
                            displayPanel.getTextField().setText(String.valueOf(CalculatorLogic.result));
                        }
                    } else {
                        double number2 = Double.parseDouble(displayPanel.getTextField().getText());
                        CalculatorLogic.switchCase();
                        if (CalculatorLogic.isValid) {
                            if (number2 == Math.floor(number2)) {
                                displayPanel.getField().setText("(" + displayPanel.getField().getText() + (int) number2 + ")" + "³" + " =");
                            } else {
                                displayPanel.getField().setText("(" + displayPanel.getField().getText() + number2 + ")" + "³" + " =");
                            }
                            if (CalculatorLogic.result == Math.floor(CalculatorLogic.result)) {
                                displayPanel.getTextField().setText(String.valueOf((int) Math.pow(CalculatorLogic.result, 3)));
                            } else {
                                displayPanel.getTextField().setText(String.valueOf(Math.pow(CalculatorLogic.result, 3)));
                            }
                        }
                    }
                    CalculatorLogic.command = 19;
                }
            }
        });
        btncube.setFont(new Font("SansSerif", Font.BOLD, 20));
        btncube.setBounds(236, 0, 108, 39);
        btncube.setFocusable(false);
        add(btncube);
        
        btncuberoot = new JButton("³√x");
        btncuberoot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (displayPanel.getTextField().getText().equals("") ||
                    displayPanel.getTextField().getText().equals("Invalid Input!") ||
                    displayPanel.getTextField().getText().equals("Cannot divide by zero!")) {
                    displayPanel.getTextField().setText("Invalid Input!");
                    displayPanel.getField().setText("");
                    CalculatorLogic.isValid = false;
                } else {
                    if (!(CalculatorLogic.command == 1 || CalculatorLogic.command == 2 ||
                          CalculatorLogic.command == 3 || CalculatorLogic.command == 4 ||
                          CalculatorLogic.command == 5 || CalculatorLogic.command == 6 ||
                          CalculatorLogic.command == 7)) {
                        double number1 = Double.parseDouble(displayPanel.getTextField().getText());
                        CalculatorLogic.result = Math.cbrt(number1);
                        if (number1 == Math.floor(number1)) {
                            displayPanel.getField().setText("³√" + (int) number1 + " =");
                        } else {
                            displayPanel.getField().setText("³√" + number1 + " =");
                        }
                        if (CalculatorLogic.result == Math.floor(CalculatorLogic.result)) {
                            displayPanel.getTextField().setText(String.valueOf((int) CalculatorLogic.result));
                        } else {
                            displayPanel.getTextField().setText(String.valueOf(CalculatorLogic.result));
                        }
                    } else {
                        double number2 = Double.parseDouble(displayPanel.getTextField().getText());
                        CalculatorLogic.switchCase();
                        if (CalculatorLogic.isValid) {
                            if (number2 == Math.floor(number2)) {
                                displayPanel.getField().setText("³√" + "(" + displayPanel.getField().getText() + (int) number2 + ")" + " =");
                            } else {
                                displayPanel.getField().setText("³√" + "(" + displayPanel.getField().getText() + number2 + ")" + " =");
                            }
                            if (CalculatorLogic.result == Math.floor(CalculatorLogic.result)) {
                                displayPanel.getTextField().setText(String.valueOf((int) Math.cbrt(CalculatorLogic.result)));
                            } else {
                                displayPanel.getTextField().setText(String.valueOf(Math.cbrt(CalculatorLogic.result)));
                            }
                        }
                    }
                    CalculatorLogic.command = 20;
                }
            }
        });
        btncuberoot.setFont(new Font("SansSerif", Font.BOLD, 20));
        btncuberoot.setBounds(354, 0, 89, 39);
        btncuberoot.setFocusable(false);
        add(btncuberoot);
        
        btndivby1 = new JButton("1/x");
        btndivby1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (displayPanel.getTextField().getText().equals("") ||
                    displayPanel.getTextField().getText().equals("Invalid Input!") ||
                    displayPanel.getTextField().getText().equals("Cannot divide by zero!")) {
                    displayPanel.getTextField().setText("Invalid Input!");
                    displayPanel.getField().setText("");
                    CalculatorLogic.isValid = false;
                } else {
                    if (!(CalculatorLogic.command == 1 || CalculatorLogic.command == 2 ||
                          CalculatorLogic.command == 3 || CalculatorLogic.command == 4 ||
                          CalculatorLogic.command == 5 || CalculatorLogic.command == 6 ||
                          CalculatorLogic.command == 7)) {
                        double number1 = Double.parseDouble(displayPanel.getTextField().getText());
                        if (number1 == 0) {
                            displayPanel.getTextField().setText("Cannot divide by zero!");
                            displayPanel.getField().setText("");
                            CalculatorLogic.isValid = false;
                        } else {
                            CalculatorLogic.result = 1 / number1;
                            if (number1 == Math.floor(number1)) {
                                displayPanel.getField().setText("1 / " + (int) number1 + " =");
                            } else {
                                displayPanel.getField().setText("1 / " + number1 + " =");
                            }
                            if (CalculatorLogic.result == Math.floor(CalculatorLogic.result)) {
                                displayPanel.getTextField().setText(String.valueOf((int) CalculatorLogic.result));
                            } else {
                                displayPanel.getTextField().setText(String.valueOf(CalculatorLogic.result));
                            }
                        }
                    } else {
                        double number2 = Double.parseDouble(displayPanel.getTextField().getText());
                        CalculatorLogic.switchCase();
                        if (CalculatorLogic.isValid) {
                            if (CalculatorLogic.result == 0) {
                                displayPanel.getTextField().setText("Cannot divide by zero!");
                                displayPanel.getField().setText("");
                                CalculatorLogic.isValid = false;
                            } else {
                                if (number2 == Math.floor(number2)) {
                                    displayPanel.getField().setText("1 / " + "(" + displayPanel.getField().getText() + (int) number2 + ")" + " =");
                                } else {
                                    displayPanel.getField().setText("1 / " + "(" + displayPanel.getField().getText() + number2 + ")" + " =");
                                }
                                if (1 / CalculatorLogic.result == Math.floor(1 / CalculatorLogic.result)) {
                                    displayPanel.getTextField().setText(String.valueOf((int)(1 / CalculatorLogic.result)));
                                } else {
                                    displayPanel.getTextField().setText(String.valueOf(1 / CalculatorLogic.result));
                                }
                            }
                        }
                    }
                    CalculatorLogic.command = 10;
                }
            }
        });
        btndivby1.setFont(new Font("SansSerif", Font.BOLD, 20));
        btndivby1.setBounds(0, 50, 108, 39);
        btndivby1.setFocusable(false);
        add(btndivby1);
        
        btnfactorial = new JButton("x!");
        btnfactorial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (displayPanel.getTextField().getText().equals("") ||
                    displayPanel.getTextField().getText().equals("Invalid Input!") ||
                    displayPanel.getTextField().getText().equals("Cannot divide by zero!")) {
                    displayPanel.getTextField().setText("Invalid Input!");
                    displayPanel.getField().setText("");
                    CalculatorLogic.isValid = false;
                } else {
                    if (!(CalculatorLogic.command == 1 || CalculatorLogic.command == 2 ||
                          CalculatorLogic.command == 3 || CalculatorLogic.command == 4 ||
                          CalculatorLogic.command == 5 || CalculatorLogic.command == 6 ||
                          CalculatorLogic.command == 7)) {
                        double number1 = Double.parseDouble(displayPanel.getTextField().getText());
                        if (number1 < 0 || number1 != Math.floor(number1)) {
                            displayPanel.getTextField().setText("Invalid Input!");
                            displayPanel.getField().setText("");
                            CalculatorLogic.isValid = false;
                        } else {
                            CalculatorLogic.result = CalculatorLogic.factorial(number1);
                            displayPanel.getField().setText((int) number1 + "!" + " =");
                            if (CalculatorLogic.result == Math.floor(CalculatorLogic.result)) {
                                displayPanel.getTextField().setText(String.valueOf((int) CalculatorLogic.result));
                            } else {
                                displayPanel.getTextField().setText(String.valueOf(CalculatorLogic.result));
                            }
                        }
                    } else {
                        double number2 = Double.parseDouble(displayPanel.getTextField().getText());
                        CalculatorLogic.switchCase();
                        if (CalculatorLogic.isValid) {
                            if (CalculatorLogic.result < 0 || CalculatorLogic.result != Math.floor(CalculatorLogic.result)) {
                                displayPanel.getTextField().setText("Invalid Input!");
                                displayPanel.getField().setText("");
                                CalculatorLogic.isValid = false;
                            } else {
                                if (number2 == Math.floor(number2)) {
                                    displayPanel.getField().setText("(" + displayPanel.getField().getText() + (int) number2 + ")" + "!" + " =");
                                } else {
                                    displayPanel.getField().setText("(" + displayPanel.getField().getText() + number2 + ")" + "!" + " =");
                                }
                                if (CalculatorLogic.factorial(CalculatorLogic.result) == Math.floor(CalculatorLogic.factorial(CalculatorLogic.result))) {
                                    displayPanel.getTextField().setText(String.valueOf((int) CalculatorLogic.factorial(CalculatorLogic.result)));
                                } else {
                                    displayPanel.getTextField().setText(String.valueOf(CalculatorLogic.factorial(CalculatorLogic.result)));
                                }
                            }
                        }
                    }
                }
            }
        });
        btnfactorial.setFont(new Font("SansSerif", Font.BOLD, 20));
        btnfactorial.setBounds(354, 50, 89, 39);
        btnfactorial.setFocusable(false);
        add(btnfactorial);
        
        btnln = new JButton("ln");
        btnln.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (displayPanel.getTextField().getText().equals("") ||
                    displayPanel.getTextField().getText().equals("Invalid Input!") ||
                    displayPanel.getTextField().getText().equals("Cannot divide by zero!")) {
                    displayPanel.getTextField().setText("Invalid Input!");
                    displayPanel.getField().setText("");
                    CalculatorLogic.isValid = false;
                } else {
                    if (!(CalculatorLogic.command == 1 || CalculatorLogic.command == 2 ||
                          CalculatorLogic.command == 3 || CalculatorLogic.command == 4 ||
                          CalculatorLogic.command == 5 || CalculatorLogic.command == 6 ||
                          CalculatorLogic.command == 7)) {
                        double number1 = Double.parseDouble(displayPanel.getTextField().getText());
                        if (number1 <= 0) {
                            displayPanel.getTextField().setText("Invalid Input!");
                            displayPanel.getField().setText("");
                            CalculatorLogic.isValid = false;
                        } else {
                            CalculatorLogic.result = Math.log(number1);
                            if (number1 == Math.floor(number1)) {
                                displayPanel.getField().setText("ln(" + (int) number1 + ")" + " =");
                            } else {
                                displayPanel.getField().setText("ln(" + number1 + ")" + " =");
                            }
                            if (CalculatorLogic.result == Math.floor(CalculatorLogic.result)) {
                                displayPanel.getTextField().setText(String.valueOf((int) CalculatorLogic.result));
                            } else {
                                displayPanel.getTextField().setText(String.valueOf(CalculatorLogic.result));
                            }
                        }
                    } else {
                        double number2 = Double.parseDouble(displayPanel.getTextField().getText());
                        CalculatorLogic.switchCase();
                        if (CalculatorLogic.isValid) {
                            if (CalculatorLogic.result <= 0) {
                                displayPanel.getTextField().setText("Invalid Input!");
                                displayPanel.getField().setText("");
                                CalculatorLogic.isValid = false;
                            } else {
                                if (number2 == Math.floor(number2)) {
                                    displayPanel.getField().setText("ln(" + displayPanel.getField().getText() + (int) number2 + ")" + " =");
                                } else {
                                    displayPanel.getField().setText("ln(" + displayPanel.getField().getText() + number2 + ")" + " =");
                                }
                                if (CalculatorLogic.result == Math.floor(CalculatorLogic.result)) {
                                    displayPanel.getTextField().setText(String.valueOf(Math.log(CalculatorLogic.result)));
                                } else {
                                    displayPanel.getTextField().setText(String.valueOf(Math.log(CalculatorLogic.result)));
                                }
                            }
                        }
                    }
                    CalculatorLogic.command = 12;
                }
            }
        });
        btnln.setFont(new Font("SansSerif", Font.BOLD, 20));
        btnln.setBounds(0, 100, 108, 39);
        btnln.setFocusable(false);
        add(btnln);
        
        btnlogyx = new JButton("logᵧx");
        btnlogyx.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (displayPanel.getTextField().getText().equals("") ||
                    displayPanel.getTextField().getText().equals("Invalid Input!") ||
                    displayPanel.getTextField().getText().equals("Cannot divide by zero!")) {
                    displayPanel.getTextField().setText("Invalid Input!");
                    displayPanel.getField().setText("");
                    CalculatorLogic.isValid = false;
                } else {
                    double number1 = Double.parseDouble(displayPanel.getTextField().getText());
                    if (number1 <= 1) {
                        displayPanel.getTextField().setText("Invalid Input!");
                        displayPanel.getField().setText("");
                        CalculatorLogic.isValid = false;
                    } else {
                        if (CalculatorLogic.isFirst) {
                            CalculatorLogic.result = number1;
                            if (number1 == Math.floor(number1)) {
                                displayPanel.getField().setText("(base " + (int) number1 + ")log");
                            } else {
                                displayPanel.getField().setText("(base " + number1 + ")log");
                            }
                            displayPanel.getTextField().setText("0");
                            CalculatorLogic.isFirst = false;
                        } else {
                            CalculatorLogic.switchCase();
                            double number2 = Double.parseDouble(displayPanel.getTextField().getText());
                            if (number2 == Math.floor(number2)) {
                                displayPanel.getField().setText("(base (" + displayPanel.getField().getText() + (int) number2 + "))log");
                            } else {
                                displayPanel.getField().setText("(base (" + displayPanel.getField().getText() + number2 + "))log");
                            }
                            displayPanel.getTextField().setText("0");
                        }
                    }
                    CalculatorLogic.command = 7;
                }
            }
        });
        btnlogyx.setFont(new Font("SansSerif", Font.BOLD, 20));
        btnlogyx.setBounds(118, 100, 108, 39);
        btnlogyx.setFocusable(false);
        add(btnlogyx);
        
        btnepowx = new JButton("eˣ");
        btnepowx.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (displayPanel.getTextField().getText().equals("") ||
                    displayPanel.getTextField().getText().equals("Invalid Input!") ||
                    displayPanel.getTextField().getText().equals("Cannot divide by zero!")) {
                    displayPanel.getTextField().setText("Invalid Input!");
                    displayPanel.getField().setText("");
                    CalculatorLogic.isValid = false;
                } else {
                    if (!(CalculatorLogic.command == 1 || CalculatorLogic.command == 2 ||
                          CalculatorLogic.command == 3 || CalculatorLogic.command == 4 ||
                          CalculatorLogic.command == 5 || CalculatorLogic.command == 6 ||
                          CalculatorLogic.command == 7)) {
                        double number1 = Double.parseDouble(displayPanel.getTextField().getText());
                        CalculatorLogic.result = Math.exp(number1);
                        if (number1 == Math.floor(number1)) {
                            displayPanel.getField().setText("e ^ " + (int) number1 + " =");
                        } else {
                            displayPanel.getField().setText("e ^ " + number1 + " =");
                        }
                        if (CalculatorLogic.result == Math.floor(CalculatorLogic.result)) {
                            displayPanel.getTextField().setText(String.valueOf((int) CalculatorLogic.result));
                        } else {
                            displayPanel.getTextField().setText(String.valueOf(CalculatorLogic.result));
                        }
                    } else {
                        double number2 = Double.parseDouble(displayPanel.getTextField().getText());
                        CalculatorLogic.switchCase();
                        if (CalculatorLogic.isValid) {
                            if (number2 == Math.floor(number2)) {
                                displayPanel.getField().setText("e ^ " + "(" + displayPanel.getField().getText() + (int) number2 + ")" + " =");
                            } else {
                                displayPanel.getField().setText("e ^ " + "(" + displayPanel.getField().getText() + number2 + ")" + " =");
                            }
                            if (Math.exp(CalculatorLogic.result) == Math.floor(Math.exp(CalculatorLogic.result))) {
                                displayPanel.getTextField().setText(String.valueOf((int) Math.exp(CalculatorLogic.result)));
                            } else {
                                displayPanel.getTextField().setText(String.valueOf(Math.exp(CalculatorLogic.result)));
                            }
                        }
                    }
                    CalculatorLogic.command = 13;
                }
            }
        });
        btnepowx.setFont(new Font("SansSerif", Font.BOLD, 20));
        btnepowx.setBounds(236, 100, 108, 39);
        btnepowx.setFocusable(false);
        add(btnepowx);
        
        btntenpowx = new JButton("10ˣ");
        btntenpowx.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (displayPanel.getTextField().getText().equals("") ||
                    displayPanel.getTextField().getText().equals("Invalid Input!") ||
                    displayPanel.getTextField().getText().equals("Cannot divide by zero!")) {
                    displayPanel.getTextField().setText("Invalid Input!");
                    displayPanel.getField().setText("");
                    CalculatorLogic.isValid = false;
                } else {
                    if (!(CalculatorLogic.command == 1 || CalculatorLogic.command == 2 ||
                          CalculatorLogic.command == 3 || CalculatorLogic.command == 4 ||
                          CalculatorLogic.command == 5 || CalculatorLogic.command == 6 ||
                          CalculatorLogic.command == 7)) {
                        double number1 = Double.parseDouble(displayPanel.getTextField().getText());
                        CalculatorLogic.result = Math.pow(10, number1);
                        if (number1 == Math.floor(number1)) {
                            displayPanel.getField().setText("10 ^ " + (int) number1 + " =");
                        } else {
                            displayPanel.getField().setText("10 ^ " + number1 + " =");
                        }
                        if (CalculatorLogic.result == Math.floor(CalculatorLogic.result)) {
                            displayPanel.getTextField().setText(String.valueOf((int) CalculatorLogic.result));
                        } else {
                            displayPanel.getTextField().setText(String.valueOf(CalculatorLogic.result));
                        }
                    } else {
                        double number2 = Double.parseDouble(displayPanel.getTextField().getText());
                        CalculatorLogic.switchCase();
                        if (CalculatorLogic.isValid) {
                            if (number2 == Math.floor(number2)) {
                                displayPanel.getField().setText("10 ^ " + "(" + displayPanel.getField().getText() + (int) number2 + ")" + " =");
                            } else {
                                displayPanel.getField().setText("10 ^ " + "(" + displayPanel.getField().getText() + number2 + ")" + " =");
                            }
                            if (Math.pow(10, CalculatorLogic.result) == Math.floor(Math.pow(10, CalculatorLogic.result))) {
                                displayPanel.getTextField().setText(String.valueOf((int) Math.pow(10, CalculatorLogic.result)));
                            } else {
                                displayPanel.getTextField().setText(String.valueOf(Math.pow(10, CalculatorLogic.result)));
                            }
                        }
                    }
                    CalculatorLogic.command = 18;
                }
            }
        });
        btntenpowx.setFont(new Font("SansSerif", Font.BOLD, 20));
        btntenpowx.setBounds(354, 100, 89, 39);
        btntenpowx.setFocusable(false);
        add(btntenpowx);
        
        btnabs = new JButton("|x|");
        btnabs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (displayPanel.getTextField().getText().equals("") ||
                    displayPanel.getTextField().getText().equals("Invalid Input!") ||
                    displayPanel.getTextField().getText().equals("Cannot divide by zero!")) {
                    displayPanel.getTextField().setText("Invalid Input!");
                    displayPanel.getField().setText("");
                    CalculatorLogic.isValid = false;
                } else {
                    if (!(CalculatorLogic.command == 1 || CalculatorLogic.command == 2 ||
                          CalculatorLogic.command == 3 || CalculatorLogic.command == 4 ||
                          CalculatorLogic.command == 5 || CalculatorLogic.command == 6 ||
                          CalculatorLogic.command == 7)) {
                        double number1 = Double.parseDouble(displayPanel.getTextField().getText());
                        CalculatorLogic.result = Math.abs(number1);
                        if (number1 == Math.floor(number1)) {
                            displayPanel.getField().setText("|" + (int) number1 + "|" + " =");
                        } else {
                            displayPanel.getField().setText("|" + number1 + "|" + " =");
                        }
                        if (CalculatorLogic.result == Math.floor(CalculatorLogic.result)) {
                            displayPanel.getTextField().setText(String.valueOf((int) CalculatorLogic.result));
                        } else {
                            displayPanel.getTextField().setText(String.valueOf(CalculatorLogic.result));
                        }
                    } else {
                        double number2 = Double.parseDouble(displayPanel.getTextField().getText());
                        CalculatorLogic.switchCase();
                        if (CalculatorLogic.isValid) {
                            if (number2 == Math.floor(number2)) {
                                displayPanel.getField().setText("|" + displayPanel.getField().getText() + (int) number2 + "|" + " =");
                            } else {
                                displayPanel.getField().setText("|" + displayPanel.getField().getText() + number2 + "|" + " =");
                            }
                            if (CalculatorLogic.result == Math.floor(CalculatorLogic.result)) {
                                displayPanel.getTextField().setText(String.valueOf((int) Math.abs(CalculatorLogic.result)));
                            } else {
                                displayPanel.getTextField().setText(String.valueOf(Math.abs(CalculatorLogic.result)));
                            }
                        }
                    }
                    CalculatorLogic.command = 14;
                }
            }
        });
        btnabs.setFont(new Font("SansSerif", Font.BOLD, 20));
        btnabs.setBounds(354, 150, 89, 39);
        btnabs.setFocusable(false);
        add(btnabs);
    }
}