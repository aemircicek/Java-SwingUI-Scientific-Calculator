package calculator;

public class CalculatorLogic {
    public static double result = 0;
    public static int command = 0;
    public static int openParenthesesCount = 0;
    public static boolean isValid = true;
    public static boolean isFirst = true;
    public static boolean isScientific = false;

    public static double factorial(double number1) {
        if (number1 < 0) {
            return -1;
        }
        if (number1 == 0 || number1 == 1)
            return 1;
        return number1 * factorial(number1 - 1);
    }

    public static void switchCase() {
		double number2 = Double.parseDouble(DisplayPanel.text.getText());
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
				DisplayPanel.text.setText("Cannot divide by zero!");
				DisplayPanel.field.setText("");
				isValid = false;
			} else {
				result /= number2;
			}
			break;
		case 5: // x to the power of y
			if ((result == 0 && number2 <= 0) || (result < 0 && number2 != Math.floor(number2))) {
				DisplayPanel.text.setText("Invalid Input!");
				DisplayPanel.field.setText("");
				isValid = false;
			} else {
				result = Math.pow(result, number2);
			}
			break;
		case 6: // y root x
			if (number2 % 2 == 0 && result < 0) {
				DisplayPanel.text.setText("Invalid Input!");
				DisplayPanel.field.setText("");
				isValid = false;
			} else {
				result = Math.signum(result) * Math.pow(Math.abs(result), 1.0 / number2);
			}
			break;
		case 7: // log y based x
			if (number2 == 0 || result == 0) {
				DisplayPanel.text.setText("Invalid Input!");
				DisplayPanel.field.setText("");
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
				DisplayPanel.text.setText("Invalid Input!");
				DisplayPanel.field.setText("");
				isValid = false;
			} else {
				result = Math.sqrt(result);
			}
			break;
		case 10: // one divided by x
			if (result == 0) {
				DisplayPanel.text.setText("Invalid Input!");
				DisplayPanel.field.setText("");
				isValid = false;
			} else {
				result = 1 / result;
			}
			break;
		case 12: // ln x
			if (result <= 0) {
				DisplayPanel.text.setText("Invalid Input!");
				DisplayPanel.field.setText("");
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
    
    public static double getResult() {
    	return result;
    }
    
    public static int getCommand() {
    	return command;
    }
    
    public static boolean getIsValid() {
    	return isValid;
    }
    
    public static boolean getIsFirst() {
    	return isFirst;
    }
    
	public static boolean getIsScientific() {
	return isScientific;
}
    
    public static String formatResult(double value) {
        if (value == (long) value) {
            return String.format("%d", (long) value);
        } else {
            return String.valueOf(value);
        }
    }

    public static double evaluateExpression(String expr) {
        Parser parser = new Parser(expr);
        double value = parser.parseExpression();
        if (parser.pos < expr.length()) {
            throw new RuntimeException("Unexpected: " + (char)parser.ch);
        }
        return value;
    }

    public static void reset() {
        result = 0;
        command = 0;
        isValid = true;
        isFirst = true;
    }

    private static class Parser {
        int pos = -1, ch;
        String str;

        public Parser(String s) {
            this.str = s;
            nextChar();
        }

        private void nextChar() {
            pos++;
            ch = pos < str.length() ? str.charAt(pos) : -1;
        }

        private boolean eat(int charToEat) {
            while (ch == ' ') nextChar();
            if (ch == charToEat) {
                nextChar();
                return true;
            }
            return false;
        }

        public double parseExpression() {
            double x = parseTerm();
            for (;;) {
                if (eat('+')) {
                    x += parseTerm();
                } else if (eat('-')) {
                    x -= parseTerm();
                } else {
                    return x;
                }
            }
        }

        private double parseTerm() {
            double x = parseFactor();
            for (;;) {
                if (eat('*')) {
                    x *= parseFactor();
                } else if (eat('/')) {
                    x /= parseFactor();
                } else {
                    return x;
                }
            }
        }

        private double parseFactor() {
            if (eat('+')) return parseFactor();
            if (eat('-')) return -parseFactor();

            double x;
            int startPos = this.pos;
            if (eat('(')) {
                x = parseExpression();
                if (!eat(')')) throw new RuntimeException("Missing )");
            } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                x = Double.parseDouble(str.substring(startPos, pos));
            } else if (ch >= 'a' && ch <= 'z') {
                while (ch >= 'a' && ch <= 'z') nextChar();
                String func = str.substring(startPos, pos);
                x = parseFactor();
                if (func.equals("sqrt")) x = Math.sqrt(x);
                else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                else if (func.equals("ln")) x = Math.log(x);
                else if (func.equals("log")) x = Math.log10(x);
                else throw new RuntimeException("Unknown function: " + func);
            } else {
                throw new RuntimeException("Unexpected: " + (char)ch);
            }

            if (eat('^')) {
                x = Math.pow(x, parseFactor());
            }
            return x;
        }
    }
}

