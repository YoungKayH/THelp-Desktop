/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package thelp.desktop;


import java.awt.*;
import java.awt.geom.Path2D;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


/**
 *
 * @author kaka2
 */
public class Dashboard extends javax.swing.JPanel {

    // Cores do tema
  //  private final Color PRIMARY_COLOR = new Color(41, 128, 185);
    //private final Color SUCCESS_COLOR = new Color(39, 174, 96);
    //private final Color WARNING_COLOR = new Color(243, 156, 18);
    //private final Color DANGER_COLOR = new Color(231, 76, 60);
    //private final Color CARD_BG = new Color(255, 255, 255);
    //private final Color PANEL_BG = new Color(245, 245, 245);
    
    /**
     * Creates new form Dashboard
     */
   public Dashboard() {
        //setTitle("THelp - Desktop");
        createDashboard();
        initComponents();
        configurarLayout();
        
    }
   private void configurarLayout() {

    // === PAINEL DOS CARDS ===
    jPanel1.setLayout(new java.awt.GridLayout(1, 3, 20, 0));

    // === PAINEL DO CENTRO (GRÁFICO + TABELA) ===
    pnlCenter.setLayout(new java.awt.BorderLayout(20, 0));

    // parte esquerda (gráfico) cresce AUTOMATICAMENTE
    pnlCenter.add(pnlGrafico, java.awt.BorderLayout.CENTER);

    // parte direita (tabela) fica com largura fixa
    pnlTabela.setPreferredSize(new java.awt.Dimension(330, 0));
    pnlCenter.add(pnlTabela, java.awt.BorderLayout.EAST);

}
   
     private static class Card extends JPanel {
        private final Color color;

        public Card(String valor, String titulo, Color color) {
            this.color = color;
            setOpaque(false);
            setPreferredSize(new Dimension(250, 120));
            setLayout(new BorderLayout());

            JLabel lblValor = new JLabel(valor);
            lblValor.setFont(new Font("SansSerif", Font.BOLD, 34));
            lblValor.setForeground(Color.WHITE);

            JLabel lblTitulo = new JLabel(titulo);
            lblTitulo.setFont(new Font("SansSerif", Font.PLAIN, 15));
            lblTitulo.setForeground(new Color(220, 220, 220));

            JPanel content = new JPanel(new BorderLayout());
            content.setOpaque(false);
            content.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            content.add(lblValor, BorderLayout.CENTER);
            content.add(lblTitulo, BorderLayout.SOUTH);

            add(content);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        }
    }
    private static class LineChartPanel extends JPanel {

        private final int[] serieA = {12, 19, 3, 5, 2, 3, 14, 11, 8, 10, 14, 20};
        private final int[] serieB = {8, 15, 2, 4, 2, 3, 10, 9, 6, 8, 12, 17};

        public LineChartPanel() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(new Color(17, 24, 39));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);

            int margin = 40;
            int w = getWidth() - margin * 2;
            int h = getHeight() - margin * 2;

            g2.setColor(Color.GRAY);
            for (int i = 0; i <= 5; i++) {
                int y = margin + i * (h / 5);
                g2.drawLine(margin, y, margin + w, y);
            }

            drawCurve(g2, serieA, new Color(167, 139, 250), margin, w, h);
            drawCurve(g2, serieB, new Color(16, 185, 129), margin, w, h);
        }
    
    private void drawCurve(Graphics2D g2, int[] data, Color color, int margin, int w, int h) {
            g2.setStroke(new BasicStroke(3f));
            g2.setColor(color);

            Path2D path = new Path2D.Double();

            for (int i = 0; i < data.length; i++) {
                double x = margin + (i * (w / 11.0));
                double y = margin + h - (data[i] / 20.0 * h);

                if (i == 0) path.moveTo(x, y);
                else path.lineTo(x, y);
            }

            g2.draw(path);
        }
    }
     private static class DonutChartPanel extends JPanel {

        public DonutChartPanel() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(new Color(17, 24, 39));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);

            int size = Math.min(getWidth(), getHeight()) - 80;
            int x = (getWidth() - size) / 2;
            int y = (getHeight() - size) / 2;

            int[] valores = {55, 15, 20, 10};
            Color[] cores = {
                new Color(167, 139, 250),
                new Color(16, 185, 129),
                new Color(251, 146, 60),
                new Color(239, 68, 68)
            };
            String[] labels = {"Suporte TI", "Financeiro", "RH", "Infraestrutura"};

            int total = 0;
            for (int v : valores) total += v;

            int anguloInicial = 0;

            // Pizza
            for (int i = 0; i < valores.length; i++) {
                int ang = (int) ((valores[i] / (double) total) * 360);

                g2.setColor(cores[i]);
                g2.fillArc(x, y, size, size, anguloInicial, ang);

                anguloInicial += ang;
            }

            // Buraco do donut
            g2.setColor(new Color(17, 24, 39));
            int inner = size / 2;
            g2.fillOval(x + inner / 2, y + inner / 2, inner, inner);

            // Legenda
            int lx = 20;
            int ly = 20;

            for (int i = 0; i < valores.length; i++) {
                g2.setColor(cores[i]);
                g2.fillRect(lx, ly + i * 25, 15, 15);

                g2.setColor(Color.WHITE);
                g2.drawString(labels[i], lx + 25, ly + 12 + i * 25);
            }
        }
    }
     
    private void createDashboard() 
    {
        setLayout(new BorderLayout());
        setBackground(new Color(15, 23, 42));

        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBackground(new Color(15, 23, 42));
        add(main);

        JLabel titulo = new JLabel("Painel Principal");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 26));
        titulo.setForeground(Color.WHITE);
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        main.add(titulo);

        JPanel cards = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        cards.setBackground(new Color(15, 23, 42));

        cards.add(new Card("450", "Chamados Abertos", new Color(167, 139, 250)));
        cards.add(new Card("92%", "SLA Atendido", new Color(16, 185, 129)));
        cards.add(new Card("388", "Resolvidos", new Color(17, 24, 39)));
        cards.add(new Card("62", "Pendentes", new Color(239, 68, 68)));

        main.add(cards);

        JPanel graficos = new JPanel(new GridLayout(1, 2, 20, 20));
        graficos.setBackground(new Color(15, 23, 42));
        graficos.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        graficos.add(new LineChartPanel());
        graficos.add(new DonutChartPanel());

        main.add(graficos);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        pnlCardAbertos = new javax.swing.JPanel();
        lblTitulo1 = new javax.swing.JLabel();
        lblValor1 = new javax.swing.JLabel();
        pnlCardFechados = new javax.swing.JPanel();
        lblTitulo2 = new javax.swing.JLabel();
        lblValor2 = new javax.swing.JLabel();
        pnlCardPendentes = new javax.swing.JPanel();
        lblTitulo3 = new javax.swing.JLabel();
        lblValor3 = new javax.swing.JLabel();
        pnlChart = new javax.swing.JPanel();
        lblChartgrafico = new javax.swing.JLabel();
        pnlTabela = new javax.swing.JPanel();
        lblTitulorecente = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblChamados = new javax.swing.JTable();

        setBackground(new java.awt.Color(15, 23, 42));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlMain.setBackground(new java.awt.Color(245, 245, 245));
        pnlMain.setPreferredSize(new java.awt.Dimension(280, 280));
        pnlMain.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setBackground(new java.awt.Color(51, 51, 51));
        lblTitulo.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        lblTitulo.setText("Dashboard");
        pnlMain.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jSeparator1.setBackground(new java.awt.Color(221, 221, 221));
        pnlMain.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 900, 2));

        pnlCardAbertos.setBackground(new java.awt.Color(255, 255, 255));
        pnlCardAbertos.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(200, 200, 200), 1, true));
        pnlCardAbertos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        lblTitulo1.setText("Chamados abertos");
        pnlCardAbertos.add(lblTitulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 10, -1, -1));

        lblValor1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 28)); // NOI18N
        lblValor1.setForeground(new java.awt.Color(74, 144, 226));
        lblValor1.setText("12");
        pnlCardAbertos.add(lblValor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 40, -1, -1));

        pnlMain.add(pnlCardAbertos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 200, 120));

        pnlCardFechados.setBackground(new java.awt.Color(255, 255, 255));
        pnlCardFechados.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(200, 200, 200), 1, true));
        pnlCardFechados.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        lblTitulo2.setText("Chamados Fechados");
        pnlCardFechados.add(lblTitulo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 10, -1, -1));

        lblValor2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 28)); // NOI18N
        lblValor2.setForeground(new java.awt.Color(74, 144, 226));
        lblValor2.setText("12");
        pnlCardFechados.add(lblValor2, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 40, -1, -1));

        pnlMain.add(pnlCardFechados, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, 200, 120));

        pnlCardPendentes.setBackground(new java.awt.Color(255, 255, 255));
        pnlCardPendentes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(200, 200, 200), 1, true));
        pnlCardPendentes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        lblTitulo3.setText("Chamados Pendentes");
        pnlCardPendentes.add(lblTitulo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 10, -1, -1));

        lblValor3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 28)); // NOI18N
        lblValor3.setForeground(new java.awt.Color(74, 144, 226));
        lblValor3.setText("12");
        pnlCardPendentes.add(lblValor3, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 40, -1, -1));

        pnlMain.add(pnlCardPendentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, 200, 120));

        pnlChart.setBackground(new java.awt.Color(255, 255, 255));
        pnlChart.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(200, 200, 200), 1, true));

        lblChartgrafico.setBackground(new java.awt.Color(153, 153, 153));
        lblChartgrafico.setText("Gráfico aparecerá aqui");

        javax.swing.GroupLayout pnlChartLayout = new javax.swing.GroupLayout(pnlChart);
        pnlChart.setLayout(pnlChartLayout);
        pnlChartLayout.setHorizontalGroup(
            pnlChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChartLayout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(lblChartgrafico)
                .addContainerGap(227, Short.MAX_VALUE))
        );
        pnlChartLayout.setVerticalGroup(
            pnlChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlChartLayout.createSequentialGroup()
                .addContainerGap(147, Short.MAX_VALUE)
                .addComponent(lblChartgrafico)
                .addGap(135, 135, 135))
        );

        pnlMain.add(pnlChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 550, 300));

        pnlTabela.setBackground(new java.awt.Color(255, 255, 255));
        pnlTabela.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)));
        pnlTabela.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulorecente.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTitulorecente.setText("jLabel1");
        pnlTabela.add(lblTitulorecente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jScrollPane1.setColumnHeaderView(null);
        jScrollPane1.setName(""); // NOI18N

        tblChamados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Titulo", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblChamados);

        pnlTabela.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 330, 240));

        pnlMain.add(pnlTabela, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 250, 350, 300));

        add(pnlMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 970, 580));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblChartgrafico;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTitulo1;
    private javax.swing.JLabel lblTitulo2;
    private javax.swing.JLabel lblTitulo3;
    private javax.swing.JLabel lblTitulorecente;
    private javax.swing.JLabel lblValor1;
    private javax.swing.JLabel lblValor2;
    private javax.swing.JLabel lblValor3;
    private javax.swing.JPanel pnlCardAbertos;
    private javax.swing.JPanel pnlCardFechados;
    private javax.swing.JPanel pnlCardPendentes;
    private javax.swing.JPanel pnlChart;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlTabela;
    private javax.swing.JTable tblChamados;
    // End of variables declaration//GEN-END:variables
}
