package thelp.desktop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import thelp.desktop.Database.DatabaseConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import thelp.desktop.session.SessaoUsuario;



public class frmChat extends javax.swing.JPanel 
{
    private int chamadoId;
    private int usuarioLogadoId;
    private boolean bloqueandoEventos = false;
    
    private JList<String> listMensagens;
    private DefaultListModel<String> listModel;
    private JScrollPane scrollMensagens;
    private JLabel lblTituloChamado;
    private JButton btnVoltar;
    private JLabel lblUsuarioLogado;
    private JComboBox<String> cbStatus;
    private JPanel pnlMensagensContainer;
    private JPanel pnlHeaderContainer;
    
    public frmChat(int idChamado) 
    {
       this.chamadoId = idChamado;
       this.usuarioLogadoId = SessaoUsuario.getId(); //idUsuario;
      
        if (!SessaoUsuario.isLogado()) 
        {
           JOptionPane.showMessageDialog(null,
               "Você precisa fazer login primeiro!",
               "Acesso não autorizado",
               JOptionPane.WARNING_MESSAGE);
           return;
        }
       initComponents();
       criarComponentesAdicionais();
       configurarLayoutCustomizado();
       configurarAparenciaBotoes();
       configurarEventos();
       
       if (idChamado != 0) {
            carregarMensagens();
            carregarStatusAtual();
        }
       
    }
    public void setChamadoId(int idChamado) 
    {
        this.chamadoId = idChamado;
        atualizarTitulo();
        
        if (idChamado != 0) {
            carregarMensagens();
            carregarStatusAtual();
        }
    }
     private void atualizarTitulo() 
     {
        if (lblTituloChamado != null) {
            lblTituloChamado.setText("Chat do Chamado #" + chamadoId);
        }
    }
     private void criarComponentesAdicionais() 
     {
        listModel = new DefaultListModel<>();
        listMensagens = new JList<>(listModel);
        listMensagens.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        listMensagens.setBackground(Color.WHITE);
        listMensagens.setSelectionBackground(new Color(240, 240, 240));
        
        scrollMensagens = new JScrollPane(listMensagens);
        scrollMensagens.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        
        lblTituloChamado = new JLabel("Chat do Chamado #" + chamadoId);
        lblTituloChamado.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTituloChamado.setForeground(new Color(51, 51, 51));
        
        lblUsuarioLogado = new JLabel();
        atualizarLabelUsuario();
        
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(
            new String[] {"ABERTO", "EM_ANDAMENTO", "FECHADO"}
        ));
        cmbStatus.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    }
     
    private void atualizarLabelUsuario() 
    {
        if (SessaoUsuario.isLogado()) {
            String nomeUsuario = SessaoUsuario.getNome();
            String papel = obterPapelUsuario();
            lblUsuarioLogado.setText("Usuário: " + nomeUsuario + " (" + papel + ")");
            lblUsuarioLogado.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            lblUsuarioLogado.setForeground(new Color(100, 100, 100));
        } else {
            lblUsuarioLogado.setText("Não logado");
        }
    }
    private String obterPapelUsuario() {
        if (SessaoUsuario.isAdmin()) return "Admin";
        if (SessaoUsuario.isAnalista()) return "Analista";
        if (SessaoUsuario.isCliente()) return "Cliente";
        return "Usuário";
    }
   
    private void configurarLayoutCustomizado() 
    {
        removeAll();
        setLayout(new BorderLayout(0, 5));
        setBackground(new Color(245, 245, 245));
        
        pnlHeaderContainer = criarPainelSuperior();
        add(pnlHeaderContainer, BorderLayout.NORTH);
        
        pnlMensagensContainer = criarPainelMensagens();
        add(pnlMensagensContainer, BorderLayout.CENTER);
        
        JPanel pnlBottom = criarPainelInferior();
        add(pnlBottom, BorderLayout.SOUTH);
        
        revalidate();
        repaint();
    }
    
    private JPanel criarPainelSuperior() 
    {
        JPanel panel = new JPanel(new BorderLayout(10, 5));
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel pnlTitulo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlTitulo.setBackground(new Color(245, 245, 245));
        pnlTitulo.add(lblTituloChamado);
        
        JPanel pnlStatus = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlStatus.setBackground(new Color(245, 245, 245));
        
        lblStatus.setText("Status:");
        lblStatus.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        
        pnlStatus.add(lblStatus);
        pnlStatus.add(cmbStatus);
        
        panel.add(pnlTitulo, BorderLayout.WEST);
        panel.add(pnlStatus, BorderLayout.EAST);
        
        return panel;
    }
    
    private JPanel criarPainelMensagens() 
    {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Histórico de Mensagens"),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        
        scrollMensagens.setPreferredSize(new Dimension(600, 300));
        panel.add(scrollMensagens, BorderLayout.CENTER);
        
        return panel;
    }
    private JPanel criarPainelInferior() 
    {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel pnlTextArea = new JPanel(new BorderLayout(5, 5));
        pnlTextArea.setBackground(new Color(245, 245, 245));
        
        JLabel lblNovaMensagem = new JLabel("Nova Mensagem:");
        lblNovaMensagem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        pnlTextArea.add(lblNovaMensagem, BorderLayout.NORTH);
        
        txtMensagem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtMensagem.setLineWrap(true);
        txtMensagem.setWrapStyleWord(true);
        txtMensagem.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        pnlTextArea.add(jScrollPane1, BorderLayout.CENTER);
        
        JPanel pnlBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        pnlBotoes.setBackground(new Color(245, 245, 245));
        
        if (btnVoltar == null) 
        {
            btnVoltar = new javax.swing.JButton();
            btnVoltar.setText("Voltar");
        }
        pnlBotoes.add(btnVoltar);
        pnlBotoes.add(btnFechar);
        pnlBotoes.add(btnEnviar);
        
        panel.add(pnlTextArea, BorderLayout.CENTER);
        panel.add(pnlBotoes, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private void configurarAparenciaBotoes() 
    {
        btnEnviar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnEnviar.setBackground(new Color(70, 130, 180));
        btnEnviar.setForeground(Color.WHITE);
        btnEnviar.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        btnEnviar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnFechar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnFechar.setBackground(new Color(220, 80, 80));
        btnFechar.setForeground(Color.WHITE);
        btnFechar.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        btnFechar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnVoltar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnVoltar.setBackground(new Color(100, 100, 100)); // Cinza
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        btnVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    private void configurarEventos() 
    {
        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarMensagem();
            }
        });
        
        btnFechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fecharChamado();
            }
        });
         btnVoltar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            voltarParaChamados();
        }
        });
        cmbStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!bloqueandoEventos) {
                alterarStatusChamado();
                }
            }
        });
        
        // Permitir envio com Ctrl+Enter
        txtMensagem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.isControlDown() && evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    enviarMensagem();
                }
            }
        });
    }
    private void voltarParaChamados() 
    {
        // Busca o AbrirChamados
        java.awt.Container parent = this.getParent();
        AbrirChamados abrirPanel = null;
    
        while (parent != null) 
        {
            if (parent instanceof AbrirChamados) {
                abrirPanel = (AbrirChamados) parent;
                break;
            }
            parent = parent.getParent();
        }
    
        if (abrirPanel != null) {
            // Remove este painel primeiro
            java.awt.Container Pai = this.getParent();
            if (Pai != null) {
                Pai.removeAll();
            }
            abrirPanel.voltarParaLista();
    }
}
    private void carregarMensagens() 
    {
        listModel.clear();
    
        String sql = "SELECT ic.int_mensagem, u.usu_nome, ic.int_criado_em " +
                    "FROM interacao_chamado ic " +
                    "JOIN usuario u ON ic.id_usuario = u.id_usuario " +
                    "WHERE ic.id_chamado = ? " +
                    "ORDER BY ic.int_criado_em ASC";
    
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
        
            if (conn == null) {
                JOptionPane.showMessageDialog(this,
                    "Não foi possível conectar ao banco de dados.",
                    "Erro de Conexão",
                    JOptionPane.ERROR_MESSAGE);
            return;
            }
        
        ps.setInt(1, chamadoId);
        ResultSet rs = ps.executeQuery();
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        boolean temMensagens = false;
        
        while (rs.next()) {
            temMensagens = true;
            String nome = rs.getString("usu_nome");
            String mensagem = rs.getString("int_mensagem");
            Timestamp timestamp = rs.getTimestamp("int_criado_em");
            
            if (timestamp != null) {
                String dataFormatada = sdf.format(new Date(timestamp.getTime()));
                String exibicao = String.format("[%s] %s: %s", dataFormatada, nome, mensagem);
                listModel.addElement(exibicao);
            }
        }
        
        if (!temMensagens) {
            listModel.addElement("Nenhuma mensagem encontrada para este chamado.");
        }
        
        // Rolar para a última mensagem
        if (listModel.size() > 0) {
            listMensagens.ensureIndexIsVisible(listModel.size() - 1);
        }
        
        }  catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Erro ao carregar mensagens.\n" +
                "Erro: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Erro inesperado: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    private void carregarStatusAtual() 
    {
         carregarStatusAtualSemEvento();
    }
     private void carregarStatusAtualSemEvento() 
    {
        bloqueandoEventos = true;
        
        try {
            String sql = "SELECT cha_status FROM chamado WHERE id_chamado = ?";
            
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                
                if (conn == null) {
                    return;
                }
                
                ps.setInt(1, chamadoId);
                ResultSet rs = ps.executeQuery();
                
                if (rs.next()) {
                    String status = rs.getString("cha_status");
                    if (status != null) {
                        String statusUpper = status.toUpperCase();
                        lblStatus.setText("Status: " + status);
                        
                        // Usar setSelectedItem sem disparar eventos
                        cmbStatus.setSelectedItem(statusUpper);
                        
                        // Desabilitar envio se chamado estiver fechado
                        if (status.equalsIgnoreCase("FECHADO")) {
                            txtMensagem.setEnabled(false);
                            btnEnviar.setEnabled(false);
                            txtMensagem.setText("Este chamado está fechado. Não é possível enviar novas interações.");
                        } else {
                            txtMensagem.setEnabled(true);
                            btnEnviar.setEnabled(true);
                            if (txtMensagem.getText().equals("Este chamado está fechado. Não é possível enviar novas interações.")) {
                                txtMensagem.setText("");
                            }
                        }
                    }
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            bloqueandoEventos = false;
        }
    }
    private void enviarMensagem() 
    {
        if (!SessaoUsuario.isLogado()) {
            JOptionPane.showMessageDialog(this,
                "Você precisa estar logado para enviar mensagens.",
                "Acesso não autorizado",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String texto = txtMensagem.getText().trim();
    
        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Digite uma mensagem.",
                "Aviso",
                JOptionPane.WARNING_MESSAGE);
        return;
        }
    
        if (chamadoId == 0) {
            JOptionPane.showMessageDialog(this,
                "Selecione um chamado primeiro.",
                "Aviso",
                JOptionPane.WARNING_MESSAGE);
        return;
        }
    
    String sql = "INSERT INTO interacao_chamado (id_chamado, id_usuario, int_mensagem, int_criado_em) " +
                 "VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        if (conn == null) {
            JOptionPane.showMessageDialog(this,
                "Não foi possível conectar ao banco de dados.",
                "Erro de Conexão",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        ps.setInt(1, chamadoId);
        ps.setInt(2, usuarioLogadoId);
        ps.setString(3, texto);
        
        int rows = ps.executeUpdate();
        
        if (rows > 0) 
        {
            txtMensagem.setText("");
            carregarMensagens(); // Recarrega as mensagens
            
            // Atualiza a data de atualização do chamado
            atualizarDataAtualizacaoChamado();
            
            System.out.println("Interação enviada - Chamado #" + chamadoId + 
                    " por Usuário #" + usuarioLogadoId);
        }
        
        } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this,
            "Erro ao enviar mensagem: " + e.getMessage(),
            "Erro",
            JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Erro inesperado: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    private void fecharChamado() 
    {
         if (!SessaoUsuario.isAdmin() && !SessaoUsuario.isAnalista()) {
            JOptionPane.showMessageDialog(this,
                "Apenas administradores e analistas podem fechar chamados.",
                "Permissão negada",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        int resposta = JOptionPane.showConfirmDialog(this,
            "Tem certeza que deseja fechar este chamado?\n" +
            "Esta ação não pode ser desfeita.",
            "Fechar Chamado",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (resposta == JOptionPane.YES_OPTION) {
            atualizarStatus("FECHADO");
        }
    }
    private void alterarStatusChamado() 
    {
       String novoStatus = (String) cmbStatus.getSelectedItem();
        if (novoStatus != null) {
            if (novoStatus.equals("FECHADO") && !SessaoUsuario.isAdmin() && !SessaoUsuario.isAnalista()) {
                JOptionPane.showMessageDialog(this,
                    "Apenas administradores e analistas podem fechar chamados.",
                    "Permissão negada",
                    JOptionPane.WARNING_MESSAGE);
                
                bloqueandoEventos = true;
                try {
                    carregarStatusAtualSemEvento();
                } finally {
                    bloqueandoEventos = false;
                }
                return;
            }
            
            atualizarStatus(novoStatus);
        }
    }
    private void atualizarStatus(String status) 
    {
        String sql = "UPDATE chamado SET cha_status = ?, cha_atualizado_em = CURRENT_TIMESTAMP ";
        
        // Se for fechar, adiciona a data de fechamento
        if (status.equalsIgnoreCase("FECHADO")) {
            sql += ", cha_finalizado_em = CURRENT_TIMESTAMP ";
        } else if (status.equalsIgnoreCase("ABERTO")) {
            sql += ", cha_finalizado_em = NULL ";
        }
        
        sql += "WHERE id_chamado = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            if (conn == null) 
            {
                JOptionPane.showMessageDialog(this,
                    "Não foi possível conectar ao banco de dados.",
                    "Erro de Conexão",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            ps.setString(1, status.toLowerCase());
            ps.setInt(2, chamadoId);
            
            int rows = ps.executeUpdate();
            
            if (rows > 0) 
            {
                lblStatus.setText("Status: " + status);
                 bloqueandoEventos = true;
                try {
                    cmbStatus.setSelectedItem(status);
                } finally {
                    bloqueandoEventos = false;
                }
                
                if (status.equalsIgnoreCase("FECHADO")) {
                    txtMensagem.setEnabled(false);
                    btnEnviar.setEnabled(false);
                    txtMensagem.setText("Este chamado está fechado. Não é possível enviar novas interações.");
                } else {
                    txtMensagem.setEnabled(true);
                    btnEnviar.setEnabled(true);
                    txtMensagem.setText("");
                }
                
                JOptionPane.showMessageDialog(this,
                    "Status atualizado para: " + status,
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            }
            
        } catch (SQLException e) 
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Erro ao atualizar status: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    private void atualizarDataAtualizacaoChamado() 
    {
        String sql = "UPDATE chamado SET cha_atualizado_em = CURRENT_TIMESTAMP WHERE id_chamado = ?";
    
        try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        if (conn == null) 
        {
            return;
        }
        
        ps.setInt(1, chamadoId);
        ps.executeUpdate();
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void recarregarMensagens() 
    {
        if (chamadoId != 0) 
        {
            carregarMensagens();
        }
    }
    
    public void limparChat() 
    {
        listModel.clear();
        txtMensagem.setText("");
        chamadoId = 0;
        if (lblTituloChamado != null) {
            lblTituloChamado.setText("Chat do Chamado");
        }
        lblStatus.setText("Status: ");
        cmbStatus.setSelectedIndex(0);
    }
    
    public void enviarMensagemRapida(String mensagem) {
        txtMensagem.setText(mensagem);
        enviarMensagem();
    }   
    public String getStatusAtual() {
        return (String) cmbStatus.getSelectedItem();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnEnviar = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMensagem = new javax.swing.JTextArea();
        lblStatus = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox<>();

        btnEnviar.setText("Enviar");

        btnFechar.setText("Fechar chat");

        txtMensagem.setColumns(20);
        txtMensagem.setRows(5);
        jScrollPane1.setViewportView(txtMensagem);

        lblStatus.setText("jLabel1");

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnFechar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEnviar)))
                        .addGap(19, 19, 19))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cmbStatus, javax.swing.GroupLayout.Alignment.LEADING, 0, 89, Short.MAX_VALUE)
                            .addComponent(lblStatus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lblStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEnviar)
                    .addComponent(btnFechar))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnFechar;
    private javax.swing.JComboBox<String> cmbStatus;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JTextArea txtMensagem;
    // End of variables declaration//GEN-END:variables
}
