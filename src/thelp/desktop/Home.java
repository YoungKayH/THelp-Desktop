/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package thelp.desktop;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author kaka2
 */
public class Home extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Home.class.getName());

    public Home () {
        initComponents();
 
        setTitle("THelp - Home");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

            int width = (int) (screenSize.width * 0.9);
            int height = (int) (screenSize.height * 0.9);

            setSize(width, height);
            setLocationRelativeTo(null);
            /*
            obs: Tratamento de erros de espaçamento nos Panel
            */
            getContentPane().setLayout(new BorderLayout());
            pnlMainPanel.setPreferredSize(new Dimension((int)(width * 0.3), height));
            pnlChildPanel.setPreferredSize(new Dimension((int)(width * 0.7), height));
            getContentPane().add(pnlMainPanel, BorderLayout.WEST);
            getContentPane().add(pnlChildPanel, BorderLayout.CENTER);
            pnlMainPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            pnlChildPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            pnlMainPanel.setBorder(null);
            pnlChildPanel.setBorder(null);
            
            dimensionamentoPanel();
            
            setVisible(true);
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlChildPanel = new javax.swing.JPanel();
        pnlMainPanel = new javax.swing.JPanel();
        lblBemVindo = new javax.swing.JLabel();
        btnEntrar = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        pnlChildPanel.setBackground(new java.awt.Color(30, 30, 30));

        javax.swing.GroupLayout pnlChildPanelLayout = new javax.swing.GroupLayout(pnlChildPanel);
        pnlChildPanel.setLayout(pnlChildPanelLayout);
        pnlChildPanelLayout.setHorizontalGroup(
            pnlChildPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 494, Short.MAX_VALUE)
        );
        pnlChildPanelLayout.setVerticalGroup(
            pnlChildPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        pnlMainPanel.setBackground(new java.awt.Color(44, 44, 44));

        lblBemVindo.setBackground(new java.awt.Color(44, 44, 44));
        lblBemVindo.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        lblBemVindo.setForeground(new java.awt.Color(255, 255, 255));
        lblBemVindo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBemVindo.setText("Bem vindo");

        btnEntrar.setBackground(new java.awt.Color(77, 120, 204));
        btnEntrar.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnEntrar.setForeground(new java.awt.Color(255, 255, 255));
        btnEntrar.setBorderPainted(false);
        btnEntrar.setLabel("Entrar");
        btnEntrar.setName(""); // NOI18N
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });

        btnRegistrar.setBackground(new java.awt.Color(44, 44, 44));
        btnRegistrar.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setText("Registrar");
        btnRegistrar.setBorderPainted(false);
        btnRegistrar.setName(""); // NOI18N
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMainPanelLayout = new javax.swing.GroupLayout(pnlMainPanel);
        pnlMainPanel.setLayout(pnlMainPanelLayout);
        pnlMainPanelLayout.setHorizontalGroup(
            pnlMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblBemVindo, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
            .addComponent(btnEntrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnRegistrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlMainPanelLayout.setVerticalGroup(
            pnlMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainPanelLayout.createSequentialGroup()
                .addComponent(lblBemVindo, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(128, 128, 128)
                .addComponent(btnEntrar)
                .addGap(41, 41, 41)
                .addComponent(btnRegistrar)
                .addContainerGap(205, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlChildPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlChildPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pnlMainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        dimensionamentoPanel();
    }//GEN-LAST:event_formComponentResized

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        btnEntrar.setBackground(new Color (77,120,204));
        btnRegistrar.setBackground(new Color (44,44,44));
        
        Login log = new Login();
        
    }//GEN-LAST:event_btnEntrarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        btnRegistrar.setBackground(new Color (77,120,204));
        btnEntrar.setBackground(new Color (44,44,44));
    }//GEN-LAST:event_btnRegistrarActionPerformed
    
    private void dimensionamentoPanel() 
    {    
        int mainPanelWidth = (int) (getContentPane().getWidth() * 0.3);
        pnlMainPanel.setSize(new Dimension(mainPanelWidth, getContentPane().getHeight()));
        pnlMainPanel.setPreferredSize(new Dimension(mainPanelWidth, getContentPane().getHeight()));
    
        int childPanelWidth = getContentPane().getWidth() - mainPanelWidth - 6;
        pnlChildPanel.setSize(new Dimension(childPanelWidth, getContentPane().getHeight()));
        pnlChildPanel.setPreferredSize(new Dimension(childPanelWidth, getContentPane().getHeight()));
    
        getContentPane().revalidate();
        getContentPane().repaint();
    }
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
    private javax.swing.JButton btnEntrar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JLabel lblBemVindo;
    private javax.swing.JPanel pnlChildPanel;
    private javax.swing.JPanel pnlMainPanel;
    // End of variables declaration//GEN-END:variables
}
