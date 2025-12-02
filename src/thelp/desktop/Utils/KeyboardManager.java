package thelp.desktop.Utils;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.*;
import java.awt.event.KeyEvent;

public class KeyboardManager 
{
    private static final Color FOCUS_COLOR = new Color(51, 153, 255);
    private static final Color ERROR_COLOR = new Color(220, 53, 69);
    private static final Color SUCCESS_COLOR = new Color(40, 167, 69);
    private static final Color NORMAL_COLOR = new Color(200, 200, 200);

    // ENTER = botão padrão
    public static void setEnterAsDefault(JRootPane rootPane, JButton btn) {
        btn.setDefaultCapable(true);
        rootPane.setDefaultButton(btn);
    }

    // ENTER em um componente executa ação
    public static void onEnter(JTextField field, Runnable action) {
        field.addActionListener(e -> action.run());
    }
    public static void onEnter(JPasswordField field, Runnable action) {
        field.addActionListener(e -> action.run());
    }

    // ESC executa ação
    public static void setEscAction(JRootPane rootPane, Runnable action) {
        rootPane.registerKeyboardAction(
                e -> action.run(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW
        );
    }

    // Foco inicial
    public static void setInitialFocus(JComponent comp) {
        SwingUtilities.invokeLater(comp::requestFocusInWindow);
    }
    public static void applyFocusHighlight(JComponent comp) {

        comp.setBorder(BorderFactory.createLineBorder(NORMAL_COLOR, 2));

        comp.addFocusListener(new FocusAdapter() 
        {
            @Override
            public void focusGained(FocusEvent e) 
            {
                comp.setBorder(BorderFactory.createLineBorder(FOCUS_COLOR, 2));
            }

           @Override
           public void focusLost(FocusEvent e) 
           {
                // Só volta para cinza se não estiver com erro ou sucesso
                Color current = comp.getBorder() instanceof javax.swing.border.LineBorder
                ? ((javax.swing.border.LineBorder) comp.getBorder()).getLineColor()
                : NORMAL_COLOR;

                boolean isError = current.equals(ERROR_COLOR);
                boolean isSuccess = current.equals(SUCCESS_COLOR);

                if (!isError && !isSuccess) 
                {
                    comp.setBorder(BorderFactory.createLineBorder(NORMAL_COLOR, 2));
                }
            }
        });
    }

    // Vermelho em caso de erro
    public static void applyError(JComponent comp) {
        comp.setBorder(BorderFactory.createLineBorder(ERROR_COLOR, 2));
    }

    // Verde quando correto
    public static void applySuccess(JComponent comp) {
        comp.setBorder(BorderFactory.createLineBorder(SUCCESS_COLOR, 2));
    }

    // TAB travessia correta
    public static void enableTab(JComponent... components) {
        for (JComponent c : components) {
            c.setFocusTraversalKeysEnabled(true);
        }
    }
}
