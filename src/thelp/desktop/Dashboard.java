package thelp.desktop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import thelp.desktop.controller.DashboardController;
import thelp.desktop.model.ChamadoModel;

public class Dashboard extends javax.swing.JPanel 
{
   private DashboardController controller;
   
   public Dashboard() {
        //createDashboard();
        initComponents();
        controller = new DashboardController();
        
        LayoutOrg();
        
        carregarGrafico();
        carregarDadosDashboard();
        
    }
   private void carregarDadosDashboard() {
        lblValor1.setText(String.valueOf(controller.getAbertos()));
        lblValor2.setText(String.valueOf(controller.getFechados()));
        lblValor3.setText(String.valueOf(controller.getPendentes()));

        carregarTabelaChamados();
    }
   private void carregarTabelaChamados() 
   {
        List<ChamadoModel> lista = controller.getChamadosRecentes();

        DefaultTableModel model = (DefaultTableModel) tblChamados.getModel();
        model.setRowCount(0);

        for (ChamadoModel c : lista) {
            model.addRow(new Object[]{ c.getId(), c.getTitulo(), c.getStatus() });
        }
    }
   private void carregarGrafico() 
   {
        int abertos = controller.getAbertos();
        int fechados = controller.getFechados();
        int pendentes = controller.getPendentes();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(abertos, "Abertos", "Ponto 1");
        dataset.addValue(abertos, "Abertos", "Ponto 2");

        dataset.addValue(fechados, "Fechados", "Ponto 1");
        dataset.addValue(fechados, "Fechados", "Ponto 2");

        dataset.addValue(pendentes, "Pendentes", "Ponto 1");
        dataset.addValue(pendentes, "Pendentes", "Ponto 2");

        JFreeChart chart = ChartFactory.createLineChart(
                "Status dos Chamados",
                "Categoria",
                "Quantidade",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
             false
        );

        CategoryPlot plot = chart.getCategoryPlot();
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();

        renderer.setDefaultShapesVisible(true);
        renderer.setDefaultLinesVisible(true);

        plot.setBackgroundPaint(Color.WHITE);

        ChartPanel chartPanel = new ChartPanel(chart);

        pnlChart.removeAll();
        pnlChart.setLayout(new BorderLayout());
        pnlChart.add(chartPanel, BorderLayout.CENTER);
        pnlChart.revalidate();
        pnlChart.repaint();
    }

   private void LayoutOrg() 
   {

        pnlMain.removeAll();
        pnlMain.setLayout(new BorderLayout());

        JPanel topo = new JPanel(new BorderLayout());

        JPanel header = new JPanel();
        header.setLayout(new BorderLayout());
        header.add(lblTitulo, BorderLayout.NORTH);
        header.add(jSeparator1, BorderLayout.SOUTH);

        JPanel cards = new JPanel();
        cards.setLayout(new java.awt.FlowLayout(FlowLayout.LEFT, 20, 10));
        cards.add(pnlCardAbertos);
        cards.add(pnlCardFechados);
        cards.add(pnlCardPendentes);

        topo.add(header, BorderLayout.NORTH);
        topo.add(cards, BorderLayout.CENTER);

        pnlMain.add(topo, BorderLayout.NORTH);
        pnlMain.add(pnlChart, BorderLayout.CENTER);
        pnlMain.add(pnlTabela, BorderLayout.EAST);

        pnlMain.revalidate();
        pnlMain.repaint();
    }
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
        setRequestFocusEnabled(false);
        setLayout(new java.awt.BorderLayout());

        pnlMain.setBackground(new java.awt.Color(245, 245, 245));
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
        pnlCardAbertos.add(lblTitulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 10, 140, -1));

        lblValor1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 28)); // NOI18N
        lblValor1.setForeground(new java.awt.Color(74, 144, 226));
        lblValor1.setText("12");
        pnlCardAbertos.add(lblValor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, -1, -1));

        pnlMain.add(pnlCardAbertos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 200, 120));

        pnlCardFechados.setBackground(new java.awt.Color(255, 255, 255));
        pnlCardFechados.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(200, 200, 200), 1, true));
        pnlCardFechados.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        lblTitulo2.setText("Chamados Fechados");
        pnlCardFechados.add(lblTitulo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 10, 150, -1));

        lblValor2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 28)); // NOI18N
        lblValor2.setForeground(new java.awt.Color(74, 144, 226));
        lblValor2.setText("12");
        pnlCardFechados.add(lblValor2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, -1, -1));

        pnlMain.add(pnlCardFechados, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, 200, 120));

        pnlCardPendentes.setBackground(new java.awt.Color(255, 255, 255));
        pnlCardPendentes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(200, 200, 200), 1, true));
        pnlCardPendentes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        lblTitulo3.setText("Chamados Pendentes");
        pnlCardPendentes.add(lblTitulo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 10, 160, -1));

        lblValor3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 28)); // NOI18N
        lblValor3.setForeground(new java.awt.Color(74, 144, 226));
        lblValor3.setText("12");
        pnlCardPendentes.add(lblValor3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, -1, -1));

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
        lblTitulorecente.setText("Chamados Recentes");
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
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblChamados);

        pnlTabela.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 330, 240));

        pnlMain.add(pnlTabela, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 250, 350, 300));

        add(pnlMain, java.awt.BorderLayout.CENTER);
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
