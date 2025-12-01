/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package thelp.desktop;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import thelp.desktop.session.SessaoUsuario;
import java.util.prefs.Preferences;

public class frmLogin extends javax.swing.JFrame 
{
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(frmLogin.class.getName());
    
    private Preferences prefs;
    private static final String PREF_NODE = "thelp_desktop";
    private static final String PREF_EMAIL = "email_salvo";
    private static final String PREF_SENHA = "senha_salva";
    private static final String PREF_LEMBRAR = "lembrar_senha";
    
    public frmLogin() {
        initComponents();
        setAutoRequestFocus(true);
        setTitle("THelp - Desktop");
        setLocationRelativeTo(null);
        
        prefs = Preferences.userRoot().node(PREF_NODE);
        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() 
        {
        @Override
            public void windowClosing(java.awt.event.WindowEvent e) 
            {
              voltarUltForm(); 
            }
        });
        
        carregarCredenciaisSalvas();
    }
    private void carregarCredenciaisSalvas() 
    {
        boolean lembrar = prefs.getBoolean(PREF_LEMBRAR, false);
        
        if (lembrar) {
            String emailSalvo = prefs.get(PREF_EMAIL, "");
            String senhaSalva = prefs.get(PREF_SENHA, "");
            
            if (!emailSalvo.isEmpty()) {
                txtEmail.setText(emailSalvo);
                
                if (!senhaSalva.isEmpty()) {
                    pwdSenha.setText(senhaSalva);
                }
                
                cbLembrarSenha.setSelected(true);
                
                // Opcional: foco no campo senha se email já preenchido
                if (!senhaSalva.isEmpty()) {
                    btnEntrar.requestFocus();
                } else {
                    pwdSenha.requestFocus();
                }
            }
        } else {
            // Limpar campos se "lembrar" não estiver marcado
            txtEmail.setText("");
            pwdSenha.setText("");
            cbLembrarSenha.setSelected(false);
        }
    }
    private void salvarCredenciais(String email, String senha, boolean lembrar) 
    {
        if (lembrar) 
        {
            prefs.put(PREF_EMAIL, email);
            prefs.put(PREF_SENHA, senha);
            prefs.putBoolean(PREF_LEMBRAR, true);
            
            // Tentar sincronizar (opcional)
            try {
                prefs.flush();
            } catch (Exception e) {
                logger.warning("Erro ao salvar preferências: " + e.getMessage());
            }
        } else {
            removerCredenciaisSalvas();
        }
    }
    private void removerCredenciaisSalvas() 
    {
        try {
            prefs.remove(PREF_EMAIL);
            prefs.remove(PREF_SENHA);
            prefs.remove(PREF_LEMBRAR);
            prefs.flush();
        } catch (Exception e) {
            logger.warning("Erro ao remover credenciais: " + e.getMessage());
        }
    }
    private String criptografarSenha(String senha) {
        try {
            byte[] bytes = senha.getBytes("UTF-8");
            return java.util.Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            return senha;
        }
    }
    private String descriptografarSenha(String senhaCriptografada) {
        try {
            byte[] bytes = java.util.Base64.getDecoder().decode(senhaCriptografada);
            return new String(bytes, "UTF-8");
        } catch (Exception e) {
            return senhaCriptografada;
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        pwdSenha = new javax.swing.JPasswordField();
        cbLembrarSenha = new javax.swing.JCheckBox();
        lblEsqueci = new javax.swing.JLabel();
        btnEntrar = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);

        jPanel1.setBackground(new java.awt.Color(15, 23, 42));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(245, 245, 245));
        jLabel1.setText("Email");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 270, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(245, 245, 245));
        jLabel2.setText("Senha");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 232, -1));
        jPanel1.add(pwdSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 270, -1));

        cbLembrarSenha.setBackground(new java.awt.Color(15, 23, 42));
        cbLembrarSenha.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbLembrarSenha.setForeground(new java.awt.Color(245, 245, 245));
        cbLembrarSenha.setText("Lembrar login");
        cbLembrarSenha.setFocusPainted(false);
        cbLembrarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLembrarSenhaActionPerformed(evt);
            }
        });
        jPanel1.add(cbLembrarSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, 20));

        lblEsqueci.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblEsqueci.setForeground(new java.awt.Color(245, 245, 245));
        lblEsqueci.setText("Esqueceu a senha?");
        lblEsqueci.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEsqueciMouseClicked(evt);
            }
        });
        jPanel1.add(lblEsqueci, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, -1, 20));

        btnEntrar.setText("Entrar");
        btnEntrar.setFocusPainted(false);
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEntrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 180, -1, -1));

        btnVoltar.setText("Voltar");
        btnVoltar.setFocusPainted(false);
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });
        jPanel1.add(btnVoltar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        String email = txtEmail.getText().trim();
        String senha = new String(pwdSenha.getPassword());
        
        if (email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Por favor, preencha todos os campos.",
                "Campos obrigatórios",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        boolean autenticado = SessaoUsuario.login(email, senha);
        
        if (autenticado) 
        {
            salvarCredenciais(email, senha, cbLembrarSenha.isSelected());
            
            JOptionPane.showMessageDialog(this,
                "Login realizado com sucesso!\nBem-vindo, " + SessaoUsuario.getNome() + "!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
            
            Home home = new Home();
            home.setVisible(true);
            this.dispose();
        } 
        else 
        {
            
            JOptionPane.showMessageDialog(this,
                "Email ou senha incorretos.\nVerifique suas credenciais.",
                "Falha no login",
                JOptionPane.ERROR_MESSAGE);

            pwdSenha.setText("");
            txtEmail.requestFocus();
        }
    }//GEN-LAST:event_btnEntrarActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        HomeLogin log = new HomeLogin();
        log.setVisible(true);
        
        this.dispose();
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void cbLembrarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLembrarSenhaActionPerformed
         if (!cbLembrarSenha.isSelected()) {
            int resposta = JOptionPane.showConfirmDialog(this,
                "Deseja remover as credenciais salvas do sistema?",
                "Remover credenciais",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
            
            if (resposta == JOptionPane.YES_OPTION) {
                removerCredenciaisSalvas();
            }
        }
    }//GEN-LAST:event_cbLembrarSenhaActionPerformed

    private void lblEsqueciMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEsqueciMouseClicked
        JOptionPane.showMessageDialog(this,
            "Entre em contato com o administrador do sistema\n" +
            "para recuperar sua senha.",
            "Recuperação de Senha",
            JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_lblEsqueciMouseClicked

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new frmLogin().setVisible(true));
    }

    private void voltarUltForm() 
    {
        this.dispose();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JCheckBox cbLembrarSenha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblEsqueci;
    private javax.swing.JPasswordField pwdSenha;
    private javax.swing.JTextField txtEmail;
    // End of variables declaration//GEN-END:variables
}
