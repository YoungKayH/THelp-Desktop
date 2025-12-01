/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package thelp.desktop;

import java.awt.BorderLayout;
import java.awt.Dimension;
import thelp.desktop.Database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AbrirChamados extends javax.swing.JPanel {

    public AbrirChamados() {
        initComponents();
       // carregarChamadosTest();
        carregarChamados();
        
        pnlMain.remove(lblTitulochamados);
        pnlMain.remove(cbFiltro);
        pnlMain.remove(jSeparator1);
        cbFiltro.addActionListener(e -> carregarChamados());
        
        // Um header para me auxiliar na montagem
        JPanel header = new JPanel();
        header.setLayout(new java.awt.GridLayout(3, 1));
        lblTitulochamados.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 0, 5));
        header.add(lblTitulochamados);
        
        cbFiltro.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        header.add(cbFiltro);
        
        jSeparator1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 10, 5));
        header.add(jSeparator1);
        
        header.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 10, 10));
        pnlMain.add(header, BorderLayout.NORTH);

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        lblTitulochamados = new javax.swing.JLabel();
        cbFiltro = new javax.swing.JComboBox<>();
        pnlList = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();

        setMaximumSize(null);
        setMinimumSize(null);
        setPreferredSize(null);

        pnlMain.setBackground(new java.awt.Color(245, 245, 245));
        pnlMain.setForeground(new java.awt.Color(89, 92, 94));
        pnlMain.setMaximumSize(null);
        pnlMain.setLayout(new java.awt.BorderLayout());

        lblTitulochamados.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitulochamados.setForeground(new java.awt.Color(51, 51, 51));
        lblTitulochamados.setText("Chamados");
        pnlMain.add(lblTitulochamados, java.awt.BorderLayout.NORTH);

        cbFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Aberto", "Fechado", "Em_andamento" }));
        pnlMain.add(cbFiltro, java.awt.BorderLayout.NORTH);

        pnlList.setBackground(new java.awt.Color(245, 245, 245));
        pnlList.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(200, 200, 200), 1, true));
        pnlList.setMaximumSize(null);
        pnlList.setMinimumSize(null);
        pnlList.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 15, 15));
        pnlList.add(jSeparator1);

        pnlMain.add(pnlList, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 971, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    public void recarregarListaChamados() 
    {
    carregarChamados();
    }
    public void voltarParaLista() 
    {
        // Remove tudo do pnlList
        pnlList.removeAll();
    
        // Restaura o layout original
        pnlList.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 15, 15));
    
        // Recarrega os chamados
        carregarChamados();
    
        // Atualiza a interface
        pnlList.revalidate();
        pnlList.repaint();
    
        // Também atualiza o header
        pnlMain.revalidate();
        pnlMain.repaint();
    }
    private void carregarChamados() 
    {
        pnlList.removeAll();
        int y = 10;
        
       String filtro = cbFiltro.getSelectedItem().toString();
       String sql = "SELECT id_chamado, cha_titulo, cha_prioridade, cha_status FROM chamado";
        
        if (!filtro.equals("Todos")) {
            sql += " WHERE cha_status = ?";
        }

        sql += " ORDER BY id_chamado DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (!filtro.equals("Todos")) {
                ps.setString(1, filtro.toLowerCase()); 
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) 
            {
                int id = rs.getInt("id_chamado");
                String titulo = rs.getString("cha_titulo");
                String prioridade = rs.getString("cha_prioridade");
                String status = rs.getString("cha_status");

                frmCartaoChamado cartao = new frmCartaoChamado(id, titulo, prioridade, status);

                cartao.setOnAbrirChamadoListener(() -> abrirChat(id));

                cartao.setBounds(10, y, 850, 90);
                y += 100;

                pnlList.add(cartao);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        pnlList.setPreferredSize(new Dimension(880, y + 20));
        pnlList.repaint();
        pnlList.revalidate();
    }
    private void abrirChat(int idChamado) 
    {
        pnlList.removeAll();
        pnlList.setLayout(new java.awt.BorderLayout());

        frmChat chat = new frmChat(idChamado);
        pnlList.add(chat, BorderLayout.CENTER);

        pnlList.revalidate();
        pnlList.repaint();
    }

   /* public void carregarChamadosTest) {

    pnlList.removeAll(); // seu painel onde estão os cartões

    pnlList.add(new frmCartaoChamado(1, "Erro ao fazer login", "alta", "aberto"));
    pnlList.add(new frmCartaoChamado(2, "Sistema lento", "media", "em_andamento"));
    pnlList.add(new frmCartaoChamado(3, "Problema na impressora", "baixa", "fechado"));
    pnlList.add(new frmCartaoChamado(4, "Não consigo acessar o e-mail", "alta", "aberto"));

    pnlList.revalidate();
    pnlList.repaint();
    }*/
      
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbFiltro;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblTitulochamados;
    private javax.swing.JPanel pnlList;
    private javax.swing.JPanel pnlMain;
    // End of variables declaration//GEN-END:variables
}
