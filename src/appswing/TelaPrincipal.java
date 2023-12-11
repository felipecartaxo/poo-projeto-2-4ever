package appswing;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class TelaPrincipal {
    private JFrame frame;
    private JMenu mnEvento; // Alteração: mnPrateleira para mnEvento
    private JMenu mnIngressos; // Adição: mnIngressos
    private JMenu mnParticipantes; // Adição: mnParticipantes
    private JLabel label;
    private JMenu menu;
    private JMenu menu_1;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaPrincipal window = new TelaPrincipal();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TelaPrincipal() {
        initialize();
        frame.setVisible(true);
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Sistema 4Ever"); // Alteração: Sistema Almoxarifado para Sistema 4Ever
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        label = new JLabel("");
        label.setFont(new Font("Tahoma", Font.PLAIN, 26));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setText("Inicializando...");
        label.setBounds(0, 0, 450, 249);
        ImageIcon imagem = new ImageIcon(getClass().getResource("/arquivos/imagem.png"));
        imagem = new ImageIcon(imagem.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
        label.setIcon(imagem);
        frame.getContentPane().add(label);
        frame.setResizable(false);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        mnEvento = new JMenu("Evento"); // Adição: mnEvento
        mnEvento.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	TelaEventos tela = new TelaEventos();            }
        });
        menuBar.add(mnEvento);

        mnIngressos = new JMenu("Ingressos"); // Adição: mnIngressos
        mnIngressos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Adicione a lógica desejada para lidar com o menu de Ingressos
            }
        });
        menuBar.add(mnIngressos);

        mnParticipantes = new JMenu("Participantes"); // Adição: mnParticipantes
        mnParticipantes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Adicione a lógica desejada para lidar com o menu de Participantes
            }
        });
        menuBar.add(mnParticipantes);

        menu = new JMenu("Sobre");
        menu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(frame, "IFPB - TSI \nProgramação Orientada a Objetos \nGraduando em Sistemas para Internet - Felipe Cartaxo de Freitas", "Sobre o autor", 1);
            }
        });
        menuBar.add(menu);

        menu_1 = new JMenu("Sair");
        menu_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
            }
        });
        menuBar.add(menu_1);
    }
}
