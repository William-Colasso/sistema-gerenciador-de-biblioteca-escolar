package com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.view;

import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.controller.LivroController;
import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.model.Livro;

@Component
public class LivroPanel extends JPanel {

    @Autowired
    private LivroController livroController;

    private JTextField txtTitulo;
    private JTextField txtAutor;
    private JTextField txtQuantidade;
    private JTextField txtEditora;
    private JTextField txtGenero;
    private JTextField txtAno;

    private JTable tabelaLivros;
    private DefaultTableModel tableModel;

    private Long livroEditandoId = null;

    public LivroPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Painel de formulário
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Cadastro de Livro"));

        txtTitulo = new JTextField();
        txtAutor = new JTextField();
        txtQuantidade = new JTextField();
        txtEditora = new JTextField();
        txtGenero = new JTextField();
        txtAno = new JTextField();

        formPanel.add(new JLabel("Título:"));
        formPanel.add(txtTitulo);

        formPanel.add(new JLabel("Autor:"));
        formPanel.add(txtAutor);

        formPanel.add(new JLabel("Quantidade:"));
        formPanel.add(txtQuantidade);

        formPanel.add(new JLabel("Editora:"));
        formPanel.add(txtEditora);

        formPanel.add(new JLabel("Genero:"));
        formPanel.add(txtGenero);

        formPanel.add(new JLabel("Ano de Publicação:"));
        formPanel.add(txtAno);

        // Botões de ação
        JButton btnSalvar = new JButton("Salvar");
        JButton btnLimpar = new JButton("Limpar");
        JButton btnExcluir = new JButton("Excluir Selecionado");
        JButton btnAtualizar = new JButton("Atualizar Lista");

        btnSalvar.addActionListener(this::salvarOuAtualizarLivro);
        btnLimpar.addActionListener(e -> limparCampos());
        btnExcluir.addActionListener(e -> excluirLivroSelecionado());
        btnAtualizar.addActionListener(e -> carregarTabela());

        JPanel botoes = new JPanel();
        botoes.add(btnSalvar);
        botoes.add(btnLimpar);
        botoes.add(btnExcluir);
        botoes.add(btnAtualizar);

        // Tabela
        tableModel = new DefaultTableModel(
                new Object[]{"ID", "Título", "Quantidade", "Autor", "Genero", "Editora", "Ano"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabelaLivros = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabelaLivros);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Livros Cadastrados"));

        // Duplo clique para editar
        tabelaLivros.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && tabelaLivros.getSelectedRow() != -1) {
                    carregarLivroParaEdicao();
                }
            }
        });

        add(formPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(botoes, BorderLayout.SOUTH);

        SwingUtilities.invokeLater(this::carregarTabela);
    }

    private void salvarOuAtualizarLivro(ActionEvent e) {
        if (livroController == null) {
            JOptionPane.showMessageDialog(this, "Erro: controlador não injetado.");
            return;
        }

        try {
            Livro livro = new Livro();
            livro.setTitulo(txtTitulo.getText());
            livro.setAutor(txtAutor.getText());
            livro.setEditora(txtEditora.getText());
            livro.setGenero(txtGenero.getText());
            livro.setQuantidade(Integer.parseInt(txtQuantidade.getText()));

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate localDate = LocalDate.parse(txtAno.getText(), formatter);
                Date dataPublicacao = Date.valueOf(localDate);
                livro.setAno(dataPublicacao);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ano inválido. Digite um número inteiro.");
                return;
            }

            if (livroEditandoId == null) {
                livroController.create(livro);
                JOptionPane.showMessageDialog(this, "Livro cadastrado com sucesso!");
            }

            limparCampos();
            carregarTabela();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao salvar/atualizar livro: " + ex.getMessage());
        }
    }

    private void carregarTabela() {
        if (livroController == null) return;

        List<Livro> livros = livroController.getAll();
        tableModel.setRowCount(0);

        for (Livro l : livros) {
            tableModel.addRow(new Object[]{
                    l.getId(),
                    l.getTitulo(),
                    l.getQuantidade(),
                    l.getAutor(),
                    l.getGenero(),
                    l.getEditora(),
                    l.getAno()
            });
        }
    }

    private void carregarLivroParaEdicao() {
        int selectedRow = tabelaLivros.getSelectedRow();
        if (selectedRow == -1) return;

        Object idObj = tabelaLivros.getValueAt(selectedRow, 0);
        Long id = Long.valueOf(idObj.toString());
        Livro livro = livroController.getById(id);

        if (livro != null) {
            livroEditandoId = id;
            txtTitulo.setText(livro.getTitulo());
            txtAutor.setText(livro.getAutor());
            txtEditora.setText(livro.getEditora());
            txtGenero.setText(livro.getGenero());
            txtQuantidade.setText(String.valueOf(livro.getQuantidade()));
            txtAno.setText(String.valueOf(livro.getAno()));

            JOptionPane.showMessageDialog(this, "Modo edição ativado para o livro ID " + id);
        }
    }

    private void excluirLivroSelecionado() {
        int selectedRow = tabelaLivros.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um livro para excluir.");
            return;
        }

        Object idObj = tabelaLivros.getValueAt(selectedRow, 0);
        Long id = Long.valueOf(idObj.toString());
        int confirm = JOptionPane.showConfirmDialog(this, "Deseja excluir o livro ID " + id + "?", "Confirmação", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            livroController.deleteById(id);
        }
    }

    private void limparCampos() {
        txtTitulo.setText("");
        txtQuantidade.setText("");
        txtEditora.setText("");
        txtGenero.setText("");
        txtAno.setText("");
        livroEditandoId = null;
    }
}
