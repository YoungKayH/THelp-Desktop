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
        //initComponents();
        
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

        pnlChamadosAbertos = new javax.swing.JPanel();
        lblValor = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        pnlSla = new javax.swing.JPanel();
        lblValor1 = new javax.swing.JLabel();
        lblTitulo1 = new javax.swing.JLabel();
        pnlResolvidos = new javax.swing.JPanel();
        lblValor2 = new javax.swing.JLabel();
        lblTitulo2 = new javax.swing.JLabel();
        pnlPendentes = new javax.swing.JPanel();
        lblValor3 = new javax.swing.JLabel();
        lblTitulo3 = new javax.swing.JLabel();
        pnlChamadosMes = new javax.swing.JPanel();
        pnlDepartamento = new javax.swing.JPanel();
        lblTituloDashboard = new javax.swing.JLabel();

        setBackground(new java.awt.Color(15, 23, 42));

        lblValor.setText("jLabel1");

        lblTitulo.setText("jLabel1");

        javax.swing.GroupLayout pnlChamadosAbertosLayout = new javax.swing.GroupLayout(pnlChamadosAbertos);
        pnlChamadosAbertos.setLayout(pnlChamadosAbertosLayout);
        pnlChamadosAbertosLayout.setHorizontalGroup(
            pnlChamadosAbertosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChamadosAbertosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlChamadosAbertosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblValor)
                    .addComponent(lblTitulo))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        pnlChamadosAbertosLayout.setVerticalGroup(
            pnlChamadosAbertosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChamadosAbertosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblValor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTitulo)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        lblValor1.setText("jLabel1");

        lblTitulo1.setText("jLabel2");

        javax.swing.GroupLayout pnlSlaLayout = new javax.swing.GroupLayout(pnlSla);
        pnlSla.setLayout(pnlSlaLayout);
        pnlSlaLayout.setHorizontalGroup(
            pnlSlaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSlaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSlaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblValor1)
                    .addComponent(lblTitulo1))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        pnlSlaLayout.setVerticalGroup(
            pnlSlaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSlaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblValor1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTitulo1)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        lblValor2.setText("jLabel1");

        lblTitulo2.setText("jLabel2");

        javax.swing.GroupLayout pnlResolvidosLayout = new javax.swing.GroupLayout(pnlResolvidos);
        pnlResolvidos.setLayout(pnlResolvidosLayout);
        pnlResolvidosLayout.setHorizontalGroup(
            pnlResolvidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResolvidosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlResolvidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblValor2)
                    .addComponent(lblTitulo2))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        pnlResolvidosLayout.setVerticalGroup(
            pnlResolvidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResolvidosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblValor2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTitulo2)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        lblValor3.setText("jLabel1");

        lblTitulo3.setText("jLabel2");

        javax.swing.GroupLayout pnlPendentesLayout = new javax.swing.GroupLayout(pnlPendentes);
        pnlPendentes.setLayout(pnlPendentesLayout);
        pnlPendentesLayout.setHorizontalGroup(
            pnlPendentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPendentesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPendentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPendentesLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblTitulo3))
                    .addComponent(lblValor3))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        pnlPendentesLayout.setVerticalGroup(
            pnlPendentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPendentesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblValor3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTitulo3)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlChamadosMesLayout = new javax.swing.GroupLayout(pnlChamadosMes);
        pnlChamadosMes.setLayout(pnlChamadosMesLayout);
        pnlChamadosMesLayout.setHorizontalGroup(
            pnlChamadosMesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        pnlChamadosMesLayout.setVerticalGroup(
            pnlChamadosMesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlDepartamentoLayout = new javax.swing.GroupLayout(pnlDepartamento);
        pnlDepartamento.setLayout(pnlDepartamentoLayout);
        pnlDepartamentoLayout.setHorizontalGroup(
            pnlDepartamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        pnlDepartamentoLayout.setVerticalGroup(
            pnlDepartamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        lblTituloDashboard.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlChamadosAbertos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(pnlSla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnlResolvidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlPendentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(137, 137, 137)
                        .addComponent(pnlChamadosMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 191, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addComponent(lblTituloDashboard)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlChamadosAbertos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlResolvidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlSla, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(pnlPendentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(lblTituloDashboard)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlChamadosMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTitulo1;
    private javax.swing.JLabel lblTitulo2;
    private javax.swing.JLabel lblTitulo3;
    private javax.swing.JLabel lblTituloDashboard;
    private javax.swing.JLabel lblValor;
    private javax.swing.JLabel lblValor1;
    private javax.swing.JLabel lblValor2;
    private javax.swing.JLabel lblValor3;
    private javax.swing.JPanel pnlChamadosAbertos;
    private javax.swing.JPanel pnlChamadosMes;
    private javax.swing.JPanel pnlDepartamento;
    private javax.swing.JPanel pnlPendentes;
    private javax.swing.JPanel pnlResolvidos;
    private javax.swing.JPanel pnlSla;
    // End of variables declaration//GEN-END:variables
}
