/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POO
 * Prof. Fausto Maranhão Ayres
 **********************************/

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Evento;
import regras_negocio.Fachada;

public class TelaEventos {
    private JFrame frame;
    private JTable table;
    private JScrollPane scrollPane;
    private JLabel label;
    private JLabel label_2;
    private JLabel label_3;
    private JTextField textField;
    private JButton button_1;
    private JButton button;
    private JButton button_2;

    public TelaEventos() {
        initialize();
        frame.setVisible(true);
    }

    private void initialize() {
        frame = new JFrame();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                listagem();
            }
        });
        frame.setTitle("Eventos"); // Alteração: Prateleiras para Eventos
        frame.setBounds(100, 100, 659, 362);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(21, 11, 431, 207);
        frame.getContentPane().add(scrollPane);

        table = new JTable() {
            public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            }
        };
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (table.getSelectedRow() >= 0)
                    label_2.setText("selecionado=" + table.getValueAt(table.getSelectedRow(), 0));
            }
        });
        table.setGridColor(Color.BLACK);
        table.setRequestFocusEnabled(false);
        table.setFocusable(false);
        table.setBackground(Color.WHITE);
        table.setFillsViewportHeight(true);
        table.setRowSelectionAllowed(true);
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        scrollPane.setViewportView(table);
        table.setBorder(new LineBorder(new Color(0, 0, 0)));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setShowGrid(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        label = new JLabel("");
        label.setForeground(Color.RED);
        label.setBounds(21, 296, 587, 14);
        frame.getContentPane().add(label);

        label_2 = new JLabel("selecione");
        label_2.setBounds(21, 216, 394, 14);
        frame.getContentPane().add(label_2);

        label_3 = new JLabel("tamanho:");
        label_3.setHorizontalAlignment(SwingConstants.RIGHT);
        label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
        label_3.setBounds(21, 257, 71, 14);
        frame.getContentPane().add(label_3);

        textField = new JTextField();
        textField.setFont(new Font("Dialog", Font.PLAIN, 12));
        textField.setColumns(10);
        textField.setBackground(Color.WHITE);
        textField.setBounds(102, 254, 134, 20);
        frame.getContentPane().add(textField);

        button_1 = new JButton("Criar");
        button_1.setToolTipText("");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (textField.getText().isEmpty()) {
                        label.setText("campo vazio");
                        return;
                    }
                    String data = textField.getText().trim(); // Alteração: Tamanho para Data
                    Fachada.criarEvento(data, "Descrição Padrão", 100, 0.0); // Alteração: criarPrateleira para criarEvento
                    label.setText("evento criado: "); // Alteração: prateleira criada para evento criado
                    listagem();
                } catch (NumberFormatException ex) {
                    label.setText("formato numerico invalido");
                } catch (Exception ex) {
                    label.setText(ex.getMessage());
                }
            }
        });
        button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        button_1.setBounds(258, 253, 86, 23);
        frame.getContentPane().add(button_1);

        button = new JButton("Apagar Selecionado");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (table.getSelectedRow() >= 0) {
                        Integer id = (Integer) table.getValueAt(table.getSelectedRow(), 0);
                        // confirmação
                        Object[] options = { "Confirmar", "Cancelar" };
                        int escolha = JOptionPane.showOptionDialog(null, "Confirma exclusão: " + id, "Alerta",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
                        if (escolha == 0) {
                            Fachada.apagarEvento(id); // Alteração: apagarPrateleira para apagarEvento
                            label.setText("exclusão realizada");
                            listagem();
                        } else
                            label.setText("exclusao cancelada");
                    } else
                        label.setText("selecione uma linha");
                } catch (Exception erro) {
                    label.setText(erro.getMessage());
                }
            }
        });
        button.setFont(new Font("Tahoma", Font.PLAIN, 12));
        button.setBounds(462, 98, 160, 23);
        frame.getContentPane().add(button);

        button_2 = new JButton("Listar");
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listagem();
            }
        });
        button_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
        button_2.setBounds(462, 57, 160, 23);
        frame.getContentPane().add(button_2);
    }

    public void listagem() {
        try {
            List<Evento> lista = Fachada.listarEventos(); // Alteração: listarPrateleiras para listarEventos

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Id");
            model.addColumn("Data"); // Alteração: Tamanho para Data
            model.addColumn("Descrição");
            model.addColumn("Capacidade");
            model.addColumn("Preço");

            for (Evento evento : lista) {
                model.addRow(new Object[] { evento.getId(), evento.getData(), evento.getDescricao(),
                        evento.getCapacidade(), evento.getPreco() });
            }
            table.setModel(model);
            label_2.setText("resultados: " + lista.size() + " linhas   - selecione uma linha");

            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            table.getColumnModel().getColumn(0).setMaxWidth(50);
            table.getColumnModel().getColumn(1).setMaxWidth(100);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        } catch (Exception erro) {
            label.setText(erro.getMessage());
        }
    }
}
