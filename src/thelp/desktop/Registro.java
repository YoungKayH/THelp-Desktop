package thelp.desktop;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import thelp.desktop.controller.RegistroController;

/**
 *
 * @author kaka2
 */
public class Registro extends javax.swing.JPanel {

    private JButton btnRegistrar;
    private JButton btnVoltar;
    private JPanel pnlBotoes;
    private JComboBox<String> cbPapel;
    private JLabel lblPapel;

    /**
     * Creates new form Registro
     */
    public Registro() {
        initComponents();
        
        configurarComboBoxPapel();
        
        configurarCampos();
        configurarLayout();
        configurarEstilos();
        configurarBotoes();
        reorganizarLayout();
    }
    
    private void configurarCampos() {
        txtNome.setPreferredSize(new Dimension(300, 35));
        txtEmail.setPreferredSize(new Dimension(300, 35));
        pwdSenha.setPreferredSize(new Dimension(300, 35));
        pwdConfirmarSenha.setPreferredSize(new Dimension(300, 35));

        Font fonte = new Font("Segoe UI", Font.PLAIN, 16);
        txtNome.setFont(fonte);
        txtEmail.setFont(fonte);
        pwdSenha.setFont(fonte);
        pwdConfirmarSenha.setFont(fonte);
        
        // Limpar textos padrão
        txtNome.setText("");
        txtEmail.setText("");
        pwdSenha.setText("");
        pwdConfirmarSenha.setText("");
        
        // Adicionar placeholders
        txtNome.setToolTipText("Digite seu nome completo");
        txtEmail.setToolTipText("Digite seu email");
        pwdSenha.setToolTipText("Digite sua senha");
        pwdConfirmarSenha.setToolTipText("Confirme sua senha");
    }
    
    private void configurarEstilos() {
        // Cor de fundo do painel - [54, 33, 89]
        Color corFundo = new Color(54, 33, 89);
        pnlRegistro.setBackground(corFundo);
        
        // Cor dos labels para branco para contrastar com o fundo escuro
        Color corTexto = Color.WHITE;
        lblNome.setForeground(corTexto);
        lblEmail.setForeground(corTexto);
        lblSenha.setForeground(corTexto);
        lblConfirmarSenha.setForeground(corTexto);
        lblPapel.setForeground(corTexto);
        
        // Labels com fonte maior
        Font fonteLabel = new Font("Segoe UI", Font.BOLD, 14);
        lblNome.setFont(fonteLabel);
        lblEmail.setFont(fonteLabel);
        lblSenha.setFont(fonteLabel);
        lblConfirmarSenha.setFont(fonteLabel);
        lblPapel.setFont(fonteLabel);
        
        // Bordas dos campos com cor mais clara
        Color corBorda = new Color(200, 200, 200);
        txtNome.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(corBorda, 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        txtEmail.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(corBorda, 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        pwdSenha.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(corBorda, 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        pwdConfirmarSenha.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(corBorda, 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        
        // Estilo do ComboBox
        cbPapel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cbPapel.setPreferredSize(new Dimension(300, 35));
        cbPapel.setBackground(new Color(240, 240, 240));
        cbPapel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(corBorda, 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        
        // Fundo dos campos de texto
        Color corFundoCampo = new Color(240, 240, 240);
        txtNome.setBackground(corFundoCampo);
        txtEmail.setBackground(corFundoCampo);
        pwdSenha.setBackground(corFundoCampo);
        pwdConfirmarSenha.setBackground(corFundoCampo);
    }
    
    private void configurarLayout() {
        pnlRegistro.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
    }
    
    private void configurarComboBoxPapel() {
        // Criar label para o papel
        lblPapel = new JLabel("Papel:");
        
        // Criar ComboBox para seleção de papel
        cbPapel = new JComboBox<>();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("Cliente");      // ID 3
        model.addElement("Analista");     // ID 2  
        model.addElement("Admin"); // ID 1
        cbPapel.setModel(model);
        cbPapel.setSelectedIndex(0); // Seleciona "Cliente" por padrão
        cbPapel.setToolTipText("Selecione o tipo de usuário");
    }
    
    private void configurarBotoes() {
        // Criar painel para os botões
        pnlBotoes = new JPanel();
        pnlBotoes.setOpaque(false);
        
        // Criar botões
        btnRegistrar = new JButton("Registrar");
        btnVoltar = new JButton("Voltar");
        
        // Configurar estilos dos botões
        configurarEstiloBotoes();
        
        // Configurar ações dos botões
        configurarAcoesBotoes();
        
        // Adicionar botões ao painel
        pnlBotoes.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 15, 0));
        pnlBotoes.add(btnRegistrar);
        pnlBotoes.add(btnVoltar);
    }
    
    private void configurarEstiloBotoes() {
        Font fonteBotao = new Font("Segoe UI", Font.BOLD, 14);
        
        // Botão Registrar
        btnRegistrar.setFont(fonteBotao);
        btnRegistrar.setPreferredSize(new Dimension(120, 40));
        btnRegistrar.setBackground(new Color(74, 144, 226)); // Azul do THelp
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnRegistrar.setFocusPainted(false);
        
        // Botão Voltar
        btnVoltar.setFont(fonteBotao);
        btnVoltar.setPreferredSize(new Dimension(120, 40));
        btnVoltar.setBackground(new Color(100, 65, 165)); // Cor roxa que combina com [54, 33, 89]
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnVoltar.setFocusPainted(false);
        
        // Efeito hover simples
        btnRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegistrar.setBackground(new Color(60, 120, 200));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegistrar.setBackground(new Color(74, 144, 226));
            }
        });
        
        btnVoltar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVoltar.setBackground(new Color(120, 85, 185));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnVoltar.setBackground(new Color(100, 65, 165));
            }
        });
    }
    
    private void configurarAcoesBotoes() {
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarActionPerformed(evt);
            }
        });
        
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarActionPerformed(evt);
            }
        });
    }
    
    private void reorganizarLayout() {
        // Remover layout atual
        pnlRegistro.removeAll();
        
        // Criar novo layout com GridBagLayout para melhor controle
        pnlRegistro.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Nome
        gbc.gridx = 0;
        gbc.gridy = 0;
        pnlRegistro.add(lblNome, gbc);
        
        gbc.gridy = 1;
        pnlRegistro.add(txtNome, gbc);
        
        // Email
        gbc.gridy = 2;
        pnlRegistro.add(lblEmail, gbc);
        
        gbc.gridy = 3;
        pnlRegistro.add(txtEmail, gbc);
        
        // Senha
        gbc.gridy = 4;
        pnlRegistro.add(lblSenha, gbc);
        
        gbc.gridy = 5;
        pnlRegistro.add(pwdSenha, gbc);
        
        // Confirmar Senha
        gbc.gridy = 6;
        pnlRegistro.add(lblConfirmarSenha, gbc);
        
        gbc.gridy = 7;
        pnlRegistro.add(pwdConfirmarSenha, gbc);
        
        gbc.gridy = 8;
        pnlRegistro.add(lblPapel, gbc);
        
        gbc.gridy = 9;
        pnlRegistro.add(cbPapel, gbc);
        
        gbc.gridy = 10;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 8, 8, 8);
        pnlRegistro.add(pnlBotoes, gbc);
        
        // Atualizar o painel
        pnlRegistro.revalidate();
        pnlRegistro.repaint();
    }
    
    private void registrarActionPerformed(java.awt.event.ActionEvent evt) {
        String nome = txtNome.getText().trim();
    String email = txtEmail.getText().trim();
    String senha = new String(pwdSenha.getPassword());
    String confirmarSenha = new String(pwdConfirmarSenha.getPassword());
    String papelSelecionado = (String) cbPapel.getSelectedItem();
    int papelId = getPapelId(papelSelecionado);
    
    System.out.println("=== FORMULÁRIO DE REGISTRO ===");
    System.out.println("Nome: " + nome);
    System.out.println("Email: " + email);
    System.out.println("Papel: " + papelSelecionado + " (ID: " + papelId + ")");
    
    if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, 
            "Preencha todos os campos!", 
            "Atenção", 
            javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    if (!senha.equals(confirmarSenha)) {
        javax.swing.JOptionPane.showMessageDialog(this, 
            "As senhas não coincidem!", 
            "Erro", 
            javax.swing.JOptionPane.ERROR_MESSAGE);
        pwdSenha.setText("");
        pwdConfirmarSenha.setText("");
        pwdSenha.requestFocus();
        return;
    }
    
    if (senha.length() < 6) {
        javax.swing.JOptionPane.showMessageDialog(this, 
            "A senha deve ter pelo menos 6 caracteres!", 
            "Erro", 
            javax.swing.JOptionPane.ERROR_MESSAGE);
        pwdSenha.setText("");
        pwdConfirmarSenha.setText("");
        pwdSenha.requestFocus();
        return;
    }
    
    RegistroController controller = new RegistroController();
    boolean sucesso = controller.registrar(nome, email, senha, confirmarSenha, papelId);
    
    if (sucesso) {
        limparCampos();
        
        // Fechar janela de registro após sucesso
        if (this.getTopLevelAncestor() instanceof java.awt.Window) {
            java.awt.Window window = (java.awt.Window) this.getTopLevelAncestor();
            window.dispose();
        }
    }
    }
    
    private boolean validarEmail(String email) {
        // Validação simples de email
        return email.contains("@") && email.contains(".") && email.length() > 5;
    }
    
    private int getPapelId(String papel) {
        switch(papel) {
            case "Admin":
                return 1;
            case "Analista":
                return 2;
            case "Cliente":
                return 3;
            default:
                return 3; // Default para cliente
        }
    }
    
    private void voltarActionPerformed(java.awt.event.ActionEvent evt) {
        limparCampos();
        
        // Fecha a janela/dialog atual
        if (this.getTopLevelAncestor() instanceof java.awt.Window) {
            java.awt.Window window = (java.awt.Window) this.getTopLevelAncestor();
            window.dispose();
        }
    }
    
    private void limparCampos() {
        txtNome.setText("");
        txtEmail.setText("");
        pwdSenha.setText("");
        pwdConfirmarSenha.setText("");
        cbPapel.setSelectedIndex(0); // Volta para "Cliente"
    }
    
    // Método para obter os dados do formulário
    public Object[] getDadosRegistro() {
        String papelSelecionado = (String) cbPapel.getSelectedItem();
        int papelId = getPapelId(papelSelecionado);
        
        return new Object[] {
            txtNome.getText().trim(),           // Nome
            txtEmail.getText().trim(),          // Email
            new String(pwdSenha.getPassword()), // Senha
            papelId,                            // ID do Papel (1, 2 ou 3)
            papelSelecionado                    // Nome do Papel
        };
    }
    
    // Método para validar o formulário
    public boolean validarFormulario() {
        String nome = txtNome.getText().trim();
        String email = txtEmail.getText().trim();
        String senha = new String(pwdSenha.getPassword());
        String confirmarSenha = new String(pwdConfirmarSenha.getPassword());
        
        return !nome.isEmpty() && 
               !email.isEmpty() && 
               !senha.isEmpty() && 
               senha.equals(confirmarSenha) &&
               senha.length() >= 6 &&
               validarEmail(email);
    }
    
    // Método para obter o ID do papel selecionado
    public int getPapelIdSelecionado() {
        String papelSelecionado = (String) cbPapel.getSelectedItem();
        return getPapelId(papelSelecionado);
    }
    
    // Método para obter o nome do papel selecionado
    public String getPapelNomeSelecionado() {
        return (String) cbPapel.getSelectedItem();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlRegistro = new javax.swing.JPanel();
        lblNome = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblSenha = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        pwdSenha = new javax.swing.JPasswordField();
        lblConfirmarSenha = new javax.swing.JLabel();
        pwdConfirmarSenha = new javax.swing.JPasswordField();

        lblNome.setText("Nome");

        lblEmail.setText("Email");

        lblSenha.setText("Senha");

        txtNome.setText("jTextField1");

        txtEmail.setText("jTextField1");

        pwdSenha.setText("jPasswordField1");

        lblConfirmarSenha.setText("Confirmar a senha");

        pwdConfirmarSenha.setText("jPasswordField1");

        javax.swing.GroupLayout pnlRegistroLayout = new javax.swing.GroupLayout(pnlRegistro);
        pnlRegistro.setLayout(pnlRegistroLayout);
        pnlRegistroLayout.setHorizontalGroup(
            pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegistroLayout.createSequentialGroup()
                .addGroup(pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlRegistroLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNome)
                            .addComponent(lblEmail)
                            .addComponent(lblSenha)
                            .addComponent(pwdConfirmarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtNome, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(pwdSenha, javax.swing.GroupLayout.Alignment.LEADING))))
                    .addGroup(pnlRegistroLayout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(lblConfirmarSenha)))
                .addContainerGap(156, Short.MAX_VALUE))
        );
        pnlRegistroLayout.setVerticalGroup(
            pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegistroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNome)
                .addGap(5, 5, 5)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSenha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pwdSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(lblConfirmarSenha)
                .addGap(5, 5, 5)
                .addComponent(pwdConfirmarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(101, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblConfirmarSenha;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JPanel pnlRegistro;
    private javax.swing.JPasswordField pwdConfirmarSenha;
    private javax.swing.JPasswordField pwdSenha;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
