package appswing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Ingresso;
import regras_negocio.Fachada;

public class TelaIngressos {
    private JDialog frame;
    private JTable tabela;
    private JScrollPane scrollPane;
    private JButton btnCriarIngresso;
    private JButton btnApagarIngresso;
    private JLabel label_1;
    private JLabel label_2;
    private JLabel log;
    private JTextField textFieldCodigo;
    private JTextField textFieldCpf;
    private JTextField textFieldTelefone;
    private JButton btnListarIngressos;

    public TelaIngressos() {
        initialize();
    }

    private void initialize() {
        frame = new JDialog();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                listagem();
            }

            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });
        frame.setTitle("Ingressos");
        frame.setBounds(100, 100, 575, 340);
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(26, 11, 315, 172);
        frame.getContentPane().add(scrollPane);

        tabela = new JTable() {
            public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            }
        };
        tabela.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (tabela.getSelectedRow() >= 0)
                    label_2.setText("selecionado=" + tabela.getValueAt(tabela.getSelectedRow(), 0));
            }
        });
        tabela.setGridColor(Color.BLACK);
        tabela.setRequestFocusEnabled(false);
        tabela.setFocusable(false);
        tabela.setBackground(Color.WHITE);
        tabela.setFillsViewportHeight(true);
        tabela.setRowSelectionAllowed(true);
        tabela.setFont(new Font("Tahoma", Font.PLAIN, 12));
        scrollPane.setViewportView(tabela);
        tabela.setBorder(new LineBorder(new Color(0, 0, 0)));
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.setShowGrid(true);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        btnApagarIngresso = new JButton("Apagar ingresso");
        btnApagarIngresso.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnApagarIngresso.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (tabela.getSelectedRow() >= 0) {
                        String codigo = (String) tabela.getValueAt(tabela.getSelectedRow(), 0);
                        // confirmação
                        Object[] options = {"Confirmar", "Cancelar"};
                        int escolha = JOptionPane.showOptionDialog(null, "Confirma exclusão " + codigo, "Alerta",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
                        if (escolha == 0) {
                            Fachada.apagarIngresso(codigo);
                            log.setText("exclusão realizada");
                            listagem();
                        } else
                            log.setText("exclusão cancelada");
                    } else
                        log.setText("selecione uma linha");
                } catch (Exception erro) {
                    log.setText(erro.getMessage());
                }
            }
        });
        btnApagarIngresso.setBounds(380, 82, 160, 23);
        frame.getContentPane().add(btnApagarIngresso);

        label_1 = new JLabel("Código:");
        label_1.setHorizontalAlignment(SwingConstants.LEFT);
        label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        label_1.setBounds(26, 220, 71, 14);
        frame.getContentPane().add(label_1);

        label_2 = new JLabel("Telefone:");
        label_2.setHorizontalAlignment(SwingConstants.LEFT);
        label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
        label_2.setBounds(26, 272, 71, 14);
        frame.getContentPane().add(label_2);

        log = new JLabel("selecione uma linha");
        log.setBounds(26, 181, 315, 14);
        frame.getContentPane().add(log);

        textFieldTelefone = new JTextField();
        textFieldTelefone.setColumns(10);
        textFieldTelefone.setBounds(92, 270, 105, 20);
        frame.getContentPane().add(textFieldTelefone);

        btnCriarIngresso = new JButton("Criar ingresso");
        btnCriarIngresso.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (textFieldCodigo.getText().isEmpty() || textFieldTelefone.getText().isEmpty()
                            || textFieldCpf.getText().isEmpty()) {
                        log.setText("campo vazio");
                        return;
                    }
                    int idEvento = Integer.parseInt(textFieldCodigo.getText());
                    String cpfParticipante = textFieldCpf.getText();
                    String telefone = textFieldTelefone.getText();
                    Fachada.criarIngresso(idEvento, cpfParticipante, telefone);
                    log.setText("ingresso criado");
                    listagem();
                } catch (Exception ex) {
                    log.setText(ex.getMessage());
                }
            }
        });
        btnCriarIngresso.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnCriarIngresso.setBounds(380, 48, 160, 23);
        frame.getContentPane().add(btnCriarIngresso);

        textFieldCodigo = new JTextField();
        textFieldCodigo.setColumns(10);
        textFieldCodigo.setBounds(92, 218, 105, 20);
        frame.getContentPane().add(textFieldCodigo);

        btnListarIngressos = new JButton("Listar ingressos");
        btnListarIngressos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listagem();
            }
        });
        btnListarIngressos.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnListarIngressos.setBounds(380, 14, 160, 23);
        frame.getContentPane().add(btnListarIngressos);

        JLabel lblNewLabel = new JLabel("CPF:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel.setBounds(26, 245, 46, 14);
        frame.getContentPane().add(lblNewLabel);

        textFieldCpf = new JTextField();
        textFieldCpf.setBounds(92, 245, 105, 20);
        frame.getContentPane().add(textFieldCpf);
        textFieldCpf.setColumns(10);

        frame.setModal(true);
        frame.setVisible(true);
    }

    public void listagem() {
        try {
            List<Ingresso> lista = Fachada.listarIngressos();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Código");
            model.addColumn("Telefone");
            model.addColumn("Evento");

            for (Ingresso i : lista) {
                model.addRow(new Object[] { i.getCodigo(), i.getTelefone(), i.getEvento().getId() });
            }

            tabela.setModel(model);
            log.setText("resultados: " + lista.size() + " linhas  - selecione uma linha");

            tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            tabela.getColumnModel().getColumn(1).setMaxWidth(150);
            tabela.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        } catch (Exception erro) {
            log.setText(erro.getMessage());
        }
    }
}
