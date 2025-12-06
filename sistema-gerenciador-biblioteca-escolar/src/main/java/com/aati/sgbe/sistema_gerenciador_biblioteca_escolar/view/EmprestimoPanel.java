package com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.view;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.controller.EmprestimoController;
import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.controller.AlunoController;
import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.controller.LivroController;
import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.model.Emprestimo;
import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.model.Aluno;
import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.model.Livro;

@Component
public class EmprestimoPanel extends JPanel {

    @Autowired
    private EmprestimoController emprestimoController;

    @Autowired
    private AlunoController alunoController;

    @Autowired
    private LivroController livroController;

    private JComboBox<String> cbAluno;
    private JComboBox<String> cbLivro;

    private JTextField txtDataEmprestimo;
    private JTextField txtDataDevolucao;
    private JCheckBox checkDevolvido;

    private JTable tabelaEmprestimos;
    private DefaultTableModel tableModel;

    private Long emprestimoEditandoId = null;

    // ---------------------------------------------------
    // CONSTRUTOR - APENAS MONTA A INTERFACE (SEM LÓGICA)
    // ---------------------------------------------------
    public EmprestimoPanel() {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Painel do formulário
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Cadastro de Empréstimo"));

        cbAluno = new JComboBox<>();
        cbLivro = new JComboBox<>();

        txtDataEmprestimo = new JTextField();
        
        txtDataEmprestimo.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        txtDataDevolucao = new JTextField();
        checkDevolvido = new JCheckBox();

        formPanel.add(new JLabel("Aluno:"));
        formPanel.add(cbAluno);

        formPanel.add(new JLabel("Livro:"));
        formPanel.add(cbLivro);

        formPanel.add(new JLabel("Data Empréstimo (dd/MM/yyyy):"));
        formPanel.add(txtDataEmprestimo);

        formPanel.add(new JLabel("Data Devolução (dd/MM/yyyy):"));
        formPanel.add(txtDataDevolucao);

        formPanel.add(new JLabel("Devolvido: "));
        formPanel.add(checkDevolvido);

        // Botões
        JButton btnSalvar = new JButton("Salvar");
        JButton btnLimpar = new JButton("Limpar");
        JButton btnExcluir = new JButton("Excluir Selecionado");
        JButton btnAtualizar = new JButton("Atualizar Lista");

        btnSalvar.addActionListener(this::salvarOuAtualizar);
        btnLimpar.addActionListener(e -> limparCampos());
        btnExcluir.addActionListener(e -> excluirSelecionado());
        btnAtualizar.addActionListener(e -> carregarTabela());

        JPanel botoes = new JPanel();
        botoes.add(btnSalvar);
        botoes.add(btnLimpar);
        botoes.add(btnExcluir);
        botoes.add(btnAtualizar);

        // Tabela
        tableModel = new DefaultTableModel(
                new Object[] { "ID", "Aluno", "Livro", "Data Empréstimo", "Data Devolução", "Devolvido" }, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        tabelaEmprestimos = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabelaEmprestimos);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Empréstimos Registrados"));

        tabelaEmprestimos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && tabelaEmprestimos.getSelectedRow() != -1) {
                    carregarParaEdicao();
                }
            }
        });

        add(formPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(botoes, BorderLayout.SOUTH);
    }

    @jakarta.annotation.PostConstruct
    public void init() {
        carregarCombos();
        carregarTabela();
    }

    // -----------------------------------------------------
    // CARREGA ALUNOS E LIVROS NOS COMBOBOX
    // -----------------------------------------------------
    private void carregarCombos() {
        cbAluno.removeAllItems();
        cbLivro.removeAllItems();

        List<Aluno> alunos = alunoController.getAll();
        List<Livro> livros = livroController.getAll();

        for (Aluno a : alunos)
            cbAluno.addItem(a.getMatricula());
        for (Livro l : livros)
            cbLivro.addItem(l.getTitulo());
    }

    // -----------------------------------------------------
    // CARREGA A TABELA
    // -----------------------------------------------------
    private void carregarTabela() {
        tableModel.setRowCount(0);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        List<Emprestimo> lista = emprestimoController.getAll();

        for (Emprestimo emp : lista) {
            if (!emp.isDevolvido()) {
                tableModel.addRow(new Object[] {
                        emp.getId(),
                        emp.getAluno().getNome(),
                        emp.getLivro().getTitulo(),
                        sdf.format(emp.getDataEmprestimo()),
                        sdf.format(emp.getDataDevolucao()),
                        emp.isDevolvido() ? "Devolvido" : "Não Devolvido"
                });
            }
        }

        carregarCombos();
    }

    // -----------------------------------------------------
    // SALVAR OU ATUALIZAR
    // -----------------------------------------------------
    private void salvarOuAtualizar(ActionEvent e) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            Emprestimo emp = new Emprestimo();
            emp.setAluno(alunoController.getByMatricula(String.valueOf(cbAluno.getSelectedItem())));
            emp.setLivro(livroController.getByTitulo(String.valueOf(cbLivro.getSelectedItem())));
            emp.setDataEmprestimo(sdf.parse(txtDataEmprestimo.getText()));
            emp.setDataDevolucao(sdf.parse(txtDataDevolucao.getText()));
            emp.setDevolvido(checkDevolvido.isSelected());

            if (emprestimoEditandoId == null) {
                emprestimoController.create(emp);
                JOptionPane.showMessageDialog(this, "Empréstimo cadastrado com sucesso!");
            } else {
                emp.setId(emprestimoEditandoId);
                emprestimoController.update(emp);
                JOptionPane.showMessageDialog(this, "Empréstimo atualizado com sucesso!");
                emprestimoEditandoId = null;
            }

            limparCampos();
            carregarTabela();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao salvar/atualizar: " + ex.getMessage());
        }
    }

    // -----------------------------------------------------
    // MODO DE EDIÇÃO VIA DUPLO CLIQUE
    // -----------------------------------------------------
    private void carregarParaEdicao() {
        int row = tabelaEmprestimos.getSelectedRow();
        if (row == -1)
            return;

        Long id = (Long) tabelaEmprestimos.getValueAt(row, 0);

        Emprestimo emp = emprestimoController.getById(id);

        if (emp != null) {
            emprestimoEditandoId = id;

            cbAluno.setSelectedItem(emp.getAluno());
            cbLivro.setSelectedItem(emp.getLivro());

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            txtDataEmprestimo.setText(sdf.format(emp.getDataEmprestimo()));
            txtDataDevolucao.setText(sdf.format(emp.getDataDevolucao()));

            JOptionPane.showMessageDialog(this,
                    "Modo edição ativado para empréstimo ID " + id);
        }
    }

    // -----------------------------------------------------
    // EXCLUSÃO
    // -----------------------------------------------------
    private void excluirSelecionado() {
        int row = tabelaEmprestimos.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um registro para excluir.");
            return;
        }

        Long id = (Long) tabelaEmprestimos.getValueAt(row, 0);

        int confirm = JOptionPane.showConfirmDialog(this,
                "Deseja realmente excluir o empréstimo ID " + id + "?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            emprestimoController.deleteById(id);
            carregarTabela();
        }
    }

    // -----------------------------------------------------
    // LIMPAR CAMPOS
    // -----------------------------------------------------
    private void limparCampos() {
        txtDataEmprestimo.setText("");
        txtDataDevolucao.setText("");
        emprestimoEditandoId = null;
    }

}
