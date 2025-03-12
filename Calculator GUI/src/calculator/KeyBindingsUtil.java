package calculator;

import javax.swing.InputMap;
import javax.swing.ActionMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class KeyBindingsUtil {
    public static void setupKeyBindings(JComponent component, JButton... buttons) {
        InputMap inputMap = component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = component.getActionMap();

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
				private static final long serialVersionUID = 1L;

				@Override
                public void actionPerformed(ActionEvent e) {
                    button.doClick();
                }
            });
        }
    }
}

