package com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.controller.AlunoController;
import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.model.Aluno;

@Component
public class AlunoPanel extends JPanel {

    @Autowired
    private AlunoController alunoController;

    private JTextField txtNome;
    private JTextField txtMatricula;
    private JTextField txtTelefone;
    private JTextField txtTurma;

    private JTable tabelaAlunos;
    private final DefaultTableModel tableModel;

    private Long alunoEditandoId = null;

    public AlunoPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Painel de formulário
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Cadastro de Aluno"));

        txtNome = new JTextField();
        txtMatricula = new JTextField();
        txtTelefone = new JTextField();
        txtTurma = new JTextField();

        formPanel.add(new JLabel("Nome:"));
        formPanel.add(txtNome);

        formPanel.add(new JLabel("Matrícula:"));
        formPanel.add(txtMatricula);

        formPanel.add(new JLabel("Telefone:"));
        formPanel.add(txtTelefone);

        formPanel.add(new JLabel("Turma:"));
        formPanel.add(txtTurma);


        // Botões de ação
        JButton btnSalvar = new JButton("Salvar");
        JButton btnLimpar = new JButton("Limpar");
        JButton btnExcluir = new JButton("Excluir Selecionado");
        JButton btnAtualizar = new JButton("Atualizar Lista");

        btnSalvar.addActionListener(this::salvarOuAtualizarAluno);
        btnLimpar.addActionListener(e -> limparCampos());
        btnExcluir.addActionListener(e -> excluirAlunoSelecionado());
        btnAtualizar.addActionListener(e -> carregarTabela());

        JPanel botoes = new JPanel();
        botoes.add(btnSalvar);
        botoes.add(btnLimpar);
        botoes.add(btnExcluir);
        botoes.add(btnAtualizar);

        // Tabela
        tableModel = new DefaultTableModel(
                new Object[]{"ID", "Nome", "Matrícula", "Telefone", "Turma", "Bibliotecário"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // impede edição direta
            }
        };

        tabelaAlunos = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabelaAlunos);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Alunos Cadastrados"));

        // Duplo clique para editar
        tabelaAlunos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && tabelaAlunos.getSelectedRow() != -1) {
                    carregarAlunoParaEdicao();
                }
            }
        });

        add(formPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(botoes, BorderLayout.SOUTH);

        // Carrega tabela ao iniciar
        SwingUtilities.invokeLater(this::carregarTabela);
    }

    private void salvarOuAtualizarAluno(ActionEvent e) {
        if (alunoController == null) {
            JOptionPane.showMessageDialog(this, "Erro: controlador não injetado.");
            return;
        }

        try {
            Aluno aluno = new Aluno();
            aluno.setNome(txtNome.getText());
            aluno.setMatricula(txtMatricula.getText());
            aluno.setTelefone(txtTelefone.getText());
            aluno.setTurma(txtTurma.getText());

            if (alunoEditandoId == null) {
                // Criar novo
                alunoController.create(aluno);
                JOptionPane.showMessageDialog(this, "Aluno cadastrado com sucesso!");
            } else {
                // Atualizar existente
                aluno.setId(alunoEditandoId);
                alunoController.update(aluno);
                JOptionPane.showMessageDialog(this, "Aluno atualizado com sucesso!");
                alunoEditandoId = null;
            }

            limparCampos();
            carregarTabela();

        } catch (HeadlessException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao salvar/atualizar aluno: " + ex.getMessage());
        }
    }

    private void carregarTabela() {
        if (alunoController == null) return;

        List<Aluno> alunos = alunoController.getAll();
        tableModel.setRowCount(0);

        for (Aluno a : alunos) {
            tableModel.addRow(new Object[]{
                    a.getId(),
                    a.getNome(),
                    a.getMatricula(),
                    a.getTelefone(),
                    a.getTurma(),
            });
        }
    }

    private void carregarAlunoParaEdicao() {
        int selectedRow = tabelaAlunos.getSelectedRow();
        if (selectedRow == -1) return;

        Long id = (Long) tabelaAlunos.getValueAt(selectedRow, 0);
        Aluno aluno = alunoController.getById(id);

        if (aluno != null) {
            alunoEditandoId = id;
            txtNome.setText(aluno.getNome());
            txtMatricula.setText(aluno.getMatricula());
            txtTelefone.setText(aluno.getTelefone());
            txtTurma.setText(aluno.getTurma());

            JOptionPane.showMessageDialog(this, "Modo edição ativado para o aluno ID " + id);
        }
    }

    private void excluirAlunoSelecionado() {
        int selectedRow = tabelaAlunos.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um aluno para excluir.");
            return;
        }

        Long id = (Long) tabelaAlunos.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir o aluno ID " + id + "?", "Confirmação", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            alunoController.deleteById(id);
            carregarTabela();
        }
    }

    private void limparCampos() {
        txtNome.setText("");
        txtMatricula.setText("");
        txtTelefone.setText("");
        txtTurma.setText("");
        alunoEditandoId = null;
    }
}
