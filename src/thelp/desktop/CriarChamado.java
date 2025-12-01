/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package thelp.desktop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

public class CriarChamado extends javax.swing.JPanel {

    public CriarChamado() {
        initComponents();
        this.removeAll();
        
        setLayout(new BorderLayout());
        configurarPainel();
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        lblTituloChamado = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        pnlForm = new javax.swing.JPanel();
        lblTitulodoChamado = new javax.swing.JLabel();
        txtTituloChamado = new javax.swing.JTextField();
        lblCategoria = new javax.swing.JLabel();
        cmbCategoria = new javax.swing.JComboBox<>();
        lblPrioridade = new javax.swing.JLabel();
        cmbPrioridade = new javax.swing.JComboBox<>();
        lblDescricao = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();
        lblAnexo = new javax.swing.JLabel();
        btnSelectArquivo = new javax.swing.JButton();
        lblArquivoSelecionado = new javax.swing.JLabel();
        btnCriarchamado = new javax.swing.JButton();

        setMaximumSize(null);

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlMain.setForeground(new java.awt.Color(255, 255, 255));
        pnlMain.setLayout(new java.awt.BorderLayout());

        lblTituloChamado.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        lblTituloChamado.setForeground(new java.awt.Color(51, 51, 51));
        lblTituloChamado.setText("Criar chamado");
        pnlMain.add(lblTituloChamado, java.awt.BorderLayout.CENTER);

        jSeparator1.setBackground(new java.awt.Color(221, 221, 221));
        pnlMain.add(jSeparator1, java.awt.BorderLayout.PAGE_START);

        pnlForm.setBackground(new java.awt.Color(255, 255, 255));
        pnlForm.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(200, 200, 200), 1, true));
        pnlForm.setMaximumSize(null);
        pnlForm.setMinimumSize(null);
        pnlForm.setPreferredSize(null);
        pnlForm.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulodoChamado.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTitulodoChamado.setText("Titulo do chamado");
        pnlForm.add(lblTitulodoChamado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));
        pnlForm.add(txtTituloChamado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 45, 300, 30));

        lblCategoria.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCategoria.setText("Categoria");
        pnlForm.add(lblCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, -1, -1));

        cmbCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Suporte Tecnico", "Rede", "Sistema", "Infraestrutura", "Segurança" }));
        pnlForm.add(cmbCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 45, 250, 30));

        lblPrioridade.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPrioridade.setText("Prioridade");
        pnlForm.add(lblPrioridade, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 20, -1, -1));

        cmbPrioridade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Baixa", "Média", "Alta", "Urgente" }));
        pnlForm.add(cmbPrioridade, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 45, 250, 30));

        lblDescricao.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDescricao.setText("Descrição do Problema");
        pnlForm.add(lblDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        txtDescricao.setColumns(20);
        txtDescricao.setLineWrap(true);
        txtDescricao.setRows(5);
        txtDescricao.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txtDescricao);

        pnlForm.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 860, 180));

        lblAnexo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblAnexo.setText("Anexo (opcional)");
        pnlForm.add(lblAnexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 325, -1, -1));

        btnSelectArquivo.setText("Selecionar arquivo");
        pnlForm.add(btnSelectArquivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 200, 30));

        lblArquivoSelecionado.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lblArquivoSelecionado.setForeground(new java.awt.Color(119, 119, 119));
        lblArquivoSelecionado.setText("Nenhum arquivo selecionado");
        pnlForm.add(lblArquivoSelecionado, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 355, -1, -1));

        btnCriarchamado.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnCriarchamado.setText("Criar chamado");
        pnlForm.add(btnCriarchamado, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 500, 200, 40));

        pnlMain.add(pnlForm, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void configurarPainel() 
    {
        this.removeAll();

        JPanel pnlHeader = new JPanel(new BorderLayout());
        pnlHeader.setBackground(new Color(245,245,245));
        pnlHeader.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel titulo = new JLabel("Criar chamado");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titulo.setForeground(new Color(40,40,40));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JSeparator sep = new JSeparator();
        sep.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        pnlHeader.add(titulo, BorderLayout.CENTER);
        pnlHeader.add(sep, BorderLayout.SOUTH);
        add(pnlHeader, BorderLayout.NORTH);


        JPanel pnlForm = new JPanel();
        pnlForm.setLayout(new BoxLayout(pnlForm, BoxLayout.Y_AXIS));
        pnlForm.setBackground(new Color(245,245,245));
        pnlForm.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        int espaco = 15;

        // ---------- LINHA 1 (Título, Categoria, Prioridade) ----------
        JPanel linha1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        linha1.setBackground(new Color(245,245,245));
        txtTituloChamado.setPreferredSize(new Dimension(400, 30));

        linha1.add(new JLabel("Título do chamado"));
        linha1.add(txtTituloChamado);
        
        linha1.add(new JLabel("Categoria"));
        linha1.add(cmbCategoria);
        linha1.add(new JLabel("Prioridade"));
        linha1.add(cmbPrioridade);
        
        linha1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        pnlForm.add(linha1);
        pnlForm.add(Box.createVerticalStrut(espaco));


        // ---------- LINHA 2 (Descrição) ----------
        JPanel linha2 = new JPanel(new BorderLayout());
        linha2.setBackground(new Color(245,245,245));

        JLabel lbl = new JLabel("Descrição do Problema");
        lbl.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

        linha2.add(lbl, BorderLayout.NORTH);
        linha2.add(jScrollPane2, BorderLayout.CENTER);

        pnlForm.add(linha2);
        pnlForm.add(Box.createVerticalStrut(espaco));


        // ---------- LINHA 3 (Anexo) ----------
        JPanel linha3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        linha3.setBackground(new Color(245,245,245));

        linha3.add(new JLabel("Anexo (opcional)"));
        linha3.add(btnSelectArquivo);
        linha3.add(lblArquivoSelecionado);

        pnlForm.add(linha3);
        add(pnlForm, BorderLayout.CENTER);

        JPanel pnlFooter = new JPanel(new FlowLayout(FlowLayout.RIGHT, 25, 10));
        pnlFooter.setBackground(new Color(245,245,245));

        pnlFooter.add(btnCriarchamado);

        add(pnlFooter, BorderLayout.SOUTH);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCriarchamado;
    private javax.swing.JButton btnSelectArquivo;
    private javax.swing.JComboBox<String> cmbCategoria;
    private javax.swing.JComboBox<String> cmbPrioridade;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblAnexo;
    private javax.swing.JLabel lblArquivoSelecionado;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblPrioridade;
    private javax.swing.JLabel lblTituloChamado;
    private javax.swing.JLabel lblTitulodoChamado;
    private javax.swing.JPanel pnlForm;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JTextArea txtDescricao;
    private javax.swing.JTextField txtTituloChamado;
    // End of variables declaration//GEN-END:variables
}
