package thelp.desktop;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class frmCartaoChamado extends JPanel {

    private JButton btnAbrir;
    private int idChamado;
    private Runnable onAbrirChamadoListener;

    public frmCartaoChamado(int id, String titulo, String prioridade, String status) {

        this.idChamado = id;

        setLayout(null);
        setPreferredSize(new Dimension(250, 140));
        setBackground(Color.WHITE);
        setBorder(new LineBorder(new Color(200,200,200), 1));
        
        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setBounds(10, 10, 230, 25);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        add(lblTitulo);

        JLabel lblPrioridade = new JLabel("Prioridade: " + prioridade);
        lblPrioridade.setBounds(10, 45, 200, 20);
        lblPrioridade.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        add(lblPrioridade);

        JLabel lblStatus = new JLabel("Status: " + status);
        lblStatus.setBounds(10, 70, 200, 20);
        lblStatus.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        add(lblStatus);

        btnAbrir = new JButton("Abrir Chat");
        btnAbrir.setBounds(10, 100, 120, 25);

        btnAbrir.addActionListener(e -> {
        if (onAbrirChamadoListener != null) {
            onAbrirChamadoListener.run();
        }
        });
        add(btnAbrir);
    }
    public void setOnAbrirChamadoListener(Runnable r) {
        this.onAbrirChamadoListener = r;
    }
    private void abrirChamado() {
        frmChat chat = new frmChat();
        chat.setVisible(true);
    }
}
