package Aplicaçao;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Entidades.Evento;

public class MenuEvento {
    private ArrayList<Evento> eventos;
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField txtCodigo;
    private JTextField txtNome;
    private JTextField txtData;
    private JTextField txtValorIngresso;
    private JTextField txtLugaresDisponiveis;

    public MenuEvento() {
        eventos = new ArrayList<>();

        JFrame frame = new JFrame("Gerenciador de Eventos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel panel = new JPanel(new BorderLayout());

        // Tabela
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Código");
        tableModel.addColumn("Nome");
        tableModel.addColumn("Data");
        tableModel.addColumn("Valor do Ingresso");
        tableModel.addColumn("Lugares Disponíveis");
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Painel de dados do evento
        JPanel dataPanel = new JPanel();
        dataPanel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(5, 1));
        labelPanel.add(new JLabel("Código:"));
        labelPanel.add(new JLabel("Nome:"));
        labelPanel.add(new JLabel("Data (dd/mm/aaaa):"));
        labelPanel.add(new JLabel("Valor do Ingresso:"));
        labelPanel.add(new JLabel("Lugares Disponíveis:"));

        JPanel textFieldPanel = new JPanel();
        textFieldPanel.setLayout(new GridLayout(5, 1));
        txtCodigo = new JTextField(10);
        txtNome = new JTextField(10);
        txtData = new JTextField(10);
        txtValorIngresso = new JTextField(10);
        txtLugaresDisponiveis = new JTextField(10);
        textFieldPanel.add(txtCodigo);
        textFieldPanel.add(txtNome);
        textFieldPanel.add(txtData);
        textFieldPanel.add(txtValorIngresso);
        textFieldPanel.add(txtLugaresDisponiveis);

        inputPanel.add(labelPanel, BorderLayout.WEST);
        inputPanel.add(textFieldPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnConsultar = new JButton("Consultar");
        JButton btnRemover = new JButton("Remover");
        buttonPanel.add(btnCadastrar);
        buttonPanel.add(btnConsultar);
        buttonPanel.add(btnRemover);

        dataPanel.add(inputPanel, BorderLayout.NORTH);
        dataPanel.add(buttonPanel, BorderLayout.CENTER);

        panel.add(dataPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(panel);

        // Ação do botão "Cadastrar"
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarEvento();
            }
        });

        // Ação do botão "Consultar"
        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarEvento();
            }
        });

        // Ação do botão "Remover"
        btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerEvento();
            }
        });

        frame.setVisible(true);
    }

    private void cadastrarEvento() {
        String codigo = txtCodigo.getText();
        String nome = txtNome.getText();
        String dataString = txtData.getText();
        String valorIngressoString = txtValorIngresso.getText();
        String lugaresDisponiveisString = txtLugaresDisponiveis.getText();

        if (codigo.isEmpty() || nome.isEmpty() || dataString.isEmpty() || valorIngressoString.isEmpty()
                || lugaresDisponiveisString.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos.");
            return;
        }

        Date data;
        double valorIngresso;
        int lugaresDisponiveis;

        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            data = dateFormat.parse(dataString);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Data inválida. Utilize o formato dd/mm/aaaa.");
            return;
        }

        try {
            valorIngresso = Double.parseDouble(valorIngressoString);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valor do ingresso inválido.");
            return;
        }

        try {
            lugaresDisponiveis = Integer.parseInt(lugaresDisponiveisString);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Quantidade de lugares disponíveis inválida.");
            return;
        }

        Evento evento = new Evento(codigo, nome, data, valorIngresso, lugaresDisponiveis);
        eventos.add(evento);

        Object[] rowData = { codigo, nome, dataString, valorIngressoString, lugaresDisponiveisString };
        tableModel.addRow(rowData);

        limparCampos();
    }

    private void consultarEvento() {
        String codigo = JOptionPane.showInputDialog("Digite o código do evento a ser consultado:");

        Evento eventoEncontrado = null;
        for (Evento evento : eventos) {
            if (evento.getCodigo().equals(codigo)) {
                eventoEncontrado = evento;
                break;
            }
        }

        if (eventoEncontrado != null) {
            String status = eventoEncontrado.isOcorrido() ? "Ocorrido" : "Não ocorrido";
            String mensagem = "Código: " + eventoEncontrado.getCodigo() +
                    "\nNome: " + eventoEncontrado.getNome() +
                    "\nData: " + eventoEncontrado.getData() +
                    "\nValor do Ingresso: " + eventoEncontrado.getValorIngresso() +
                    "\nStatus: " + status +
                    "\nLugares Disponíveis: " + eventoEncontrado.getLugaresDisponiveis();
            JOptionPane.showMessageDialog(null, mensagem);
        } else {
            JOptionPane.showMessageDialog(null, "Evento não encontrado.");
        }
    }

    private void removerEvento() {
        String codigo = JOptionPane.showInputDialog("Digite o código do evento a ser removido:");

        Evento eventoEncontrado = null;
        for (Evento evento : eventos) {
            if (evento.getCodigo().equals(codigo)) {
                eventoEncontrado = evento;
                break;
            }
        }

        if (eventoEncontrado != null) {
            int resposta = JOptionPane.showConfirmDialog(null,
                    "Deseja remover o evento?\nCódigo: " + eventoEncontrado.getCodigo() + "\nNome: "
                            + eventoEncontrado.getNome(),
                    "Remover Evento", JOptionPane.YES_NO_OPTION);

            if (resposta == JOptionPane.YES_OPTION) {
                eventos.remove(eventoEncontrado);
                int rowIndex = table.getSelectedRow();
                if (rowIndex != -1) {
                    tableModel.removeRow(rowIndex);
                }
                JOptionPane.showMessageDialog(null, "Evento removido com sucesso.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Evento não encontrado.");
        }
    }

    private void limparCampos() {
        txtCodigo.setText("");
        txtNome.setText("");
        txtData.setText("");
        txtValorIngresso.setText("");
        txtLugaresDisponiveis.setText("");
    }

    public static void main(String[] args) {
        new MenuEvento();
    }
}
