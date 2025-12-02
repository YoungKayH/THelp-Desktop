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
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import thelp.desktop.controller.AbrirChamadoController;
import thelp.desktop.model.ChamadoModel;

public class AbrirChamados extends javax.swing.JPanel 
{    
  private AbrirChamadoController controller;
  
    public AbrirChamados() {
        initComponents();
        controller = new AbrirChamadoController();
       // carregarChamadosTest();
        pnlMain.remove(lblTitulochamados);
        pnlMain.remove(cbFiltro);
        pnlMain.remove(jSeparator1);
        cbFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Aberto", "Fechado", "Em_andamento" }));
        cbFiltro.addActionListener(e -> carregarChamados());
        carregarChamados();
        
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
    private void carregarChamados() {
        pnlList.removeAll();
        
        // Define o filtro correto
        String filtroSelecionado = (cbFiltro.getSelectedItem() != null) ? cbFiltro.getSelectedItem().toString() : "Todos";
        String filtro = null;
        switch (filtroSelecionado.toLowerCase()) {
            case "aberto": filtro = "aberto"; break;
            case "fechado": filtro = "fechado"; break;
            case "em_andamento": filtro = "em_andamento"; break;
            default: filtro = null; break;
        }


        // Lista de chamados
        List<ChamadoModel> chamados = controller.listarChamados(filtro);

        int y = 10;
        if (chamados != null && !chamados.isEmpty()) {
            for (ChamadoModel c : chamados) {
                frmCartaoChamado cartao = new frmCartaoChamado(
                        c.getIdChamado(),
                        c.getTitulo(),
                        c.getPrioridade(),
                        c.getStatus()
                );

                // Ao clicar, abre chat
                cartao.setOnAbrirChamadoListener(() -> abrirChat(c.getIdChamado()));

                cartao.setBounds(10, y, 850, 90);
                y += 100;
                pnlList.add(cartao);
            }
        } else {
            JLabel lblVazio = new JLabel("Nenhum chamado encontrado.");
            lblVazio.setPreferredSize(new Dimension(850, 50));
            pnlList.add(lblVazio);
        }

        pnlList.setPreferredSize(new Dimension(880, y + 20));
        pnlList.revalidate();
        pnlList.repaint();
        System.out.println("Filtro selecionado: " + filtro);
        System.out.println("Chamados encontrados: " + (chamados != null ? chamados.size() : "null"));

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
