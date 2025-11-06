/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package thelp.desktop;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author kaka2
 */
public class Home extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Home.class.getName());

    public Home() {
        initComponents();
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setupLayout();
        //setSize(new Dimension(getParent().getWidth(), getParent().getHeight()));
        
        setTela(new Dashboard());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        pnlLado = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnDashboard = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnAbrirChamados = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        btnCriarChamados = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        pnlFilho = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);

        pnlMain.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlLado.setBackground(new java.awt.Color(54, 33, 89));
        pnlLado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(204, 102, 0));

        btnDashboard.setText("Dashboard");
        btnDashboard.setFocusPainted(false);
        btnDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDashboardActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnDashboard, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
        );

        pnlLado.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 270, -1));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("THelp");
        pnlLado.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, -1));

        btnAbrirChamados.setText("Abrir chamados");
        btnAbrirChamados.setFocusPainted(false);
        btnAbrirChamados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirChamadosActionPerformed(evt);
            }
        });
        pnlLado.add(btnAbrirChamados, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 270, 60));

        btnSair.setText("Sair");
        btnSair.setFocusPainted(false);
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });
        pnlLado.add(btnSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 270, 60));

        btnCriarChamados.setText("Criar chamado");
        btnCriarChamados.setFocusPainted(false);
        btnCriarChamados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCriarChamadosActionPerformed(evt);
            }
        });
        pnlLado.add(btnCriarChamados, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 270, 60));

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator1.setFocusTraversalPolicyProvider(true);
        pnlLado.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 270, -1));

        pnlMain.add(pnlLado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 540));

        pnlFilho.setBackground(new java.awt.Color(110, 89, 222));
        pnlFilho.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pnlMain.add(pnlFilho, new org.netbeans.lib.awtextra.AbsoluteConstraints(268, 62, 700, 480));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        HomeLogin log = new HomeLogin();
        log.setVisible(true);
        
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardActionPerformed
       Dashboard dash = new Dashboard();
       setTela(dash);
    }//GEN-LAST:event_btnDashboardActionPerformed

    private void btnAbrirChamadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirChamadosActionPerformed
       AbrirChamados cha = new AbrirChamados();
       setTela(cha);
    }//GEN-LAST:event_btnAbrirChamadosActionPerformed

    private void btnCriarChamadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCriarChamadosActionPerformed
        CriarChamado cha = new CriarChamado();
        setTela(cha);
    }//GEN-LAST:event_btnCriarChamadosActionPerformed
   public void setTela(JPanel novaTela) 
   {
    pnlFilho.removeAll();
    pnlFilho.setLayout(new BorderLayout());
    pnlFilho.add(novaTela, BorderLayout.CENTER);
    pnlFilho.revalidate();
    pnlFilho.repaint();
   }
   private void setupLayout() {
        setupMainPanel();
        setupSidePanel();
        setupContentPanel();
    }
   private void setupMainPanel() {
        pnlMain.setLayout(new BorderLayout());
    }
   private void setupSidePanel() {
        pnlLado.setPreferredSize(new java.awt.Dimension(270, 0));
        setupSidePanelComponents();
        pnlMain.add(pnlLado, BorderLayout.WEST);
    }   
   private void setupSidePanelComponents() {
        setupHeader();
        setupDashboardButton();
        setupMenuButtons();
        setupSeparator();
    }
   private void setupContentPanel() {
        pnlFilho.setLayout(new BorderLayout());
        pnlMain.add(pnlFilho, BorderLayout.CENTER);
    }
   private void setupHeader() {
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlLado.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, -1));
    }
   
   private void setupSeparator() {
        pnlLado.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 270, -1));
    }
   private void setupMenuButtons() {
        pnlLado.add(btnAbrirChamados, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 270, 60));
        pnlLado.add(btnCriarChamados, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 270, 60));
        pnlLado.add(btnSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 270, 60));
    }
   private void setupDashboardButton() {
        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnDashboard, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
        );
        pnlLado.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 270, -1));
    }

    /**
     * @param args the command line arguments
     */
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
        java.awt.EventQueue.invokeLater(() -> new Home().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrirChamados;
    private javax.swing.JButton btnCriarChamados;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnSair;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel pnlFilho;
    private javax.swing.JPanel pnlLado;
    private javax.swing.JPanel pnlMain;
    // End of variables declaration//GEN-END:variables
}
