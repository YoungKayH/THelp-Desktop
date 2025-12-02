package thelp.desktop.controller;

import java.awt.Color;
import javax.swing.BorderFactory;
import thelp.desktop.frmLogin;
import thelp.desktop.Utils.KeyboardManager;

public class LoginController {

    private final frmLogin view;

    public LoginController(frmLogin view) 
    {
        this.view = view;
        configurarTeclado();
    }

    private void configurarTeclado() 
    { 
        view.getTxtEmail().setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2));
        view.getPwdSenha().setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2));

        // ENTER → botão Entrar
        KeyboardManager.setEnterAsDefault(
                view.getRootPane(),
                view.getBtnEntrar()
        );

        // ENTER no campo senha → Entrar
        KeyboardManager.onEnter(
                view.getPwdSenha(),
                () -> view.getBtnEntrar().doClick()
        );

        // ESC → Voltar
        KeyboardManager.setEscAction(
                view.getRootPane(),
                () -> view.getBtnVoltar().doClick()
        );

        // foco inicial
        KeyboardManager.setInitialFocus(view.getTxtEmail());
        
        KeyboardManager.applyFocusHighlight(view.getTxtEmail());
        KeyboardManager.applyFocusHighlight(view.getPwdSenha());
        
        KeyboardManager.applySuccess(view.getTxtEmail());
        KeyboardManager.applySuccess(view.getPwdSenha());
        
        KeyboardManager.applyError(view.getTxtEmail());
        KeyboardManager.applyError(view.getPwdSenha());
    
        // TAB funcional
        KeyboardManager.enableTab(
                view.getTxtEmail(),
                view.getPwdSenha(),
                view.getBtnEntrar(),
                view.getBtnVoltar()
        );
    }
}
