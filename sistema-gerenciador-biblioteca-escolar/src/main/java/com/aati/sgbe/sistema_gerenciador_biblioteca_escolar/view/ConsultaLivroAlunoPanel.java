package com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.controller.EmprestimoController;
import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.controller.LivroController;
import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.model.Emprestimo;

@Component
public class ConsultaLivroAlunoPanel extends JPanel {

    private JTextField txtTitulo;
    private JTextField txtMatricula;
    private JLabel lblResultadoLivro;

    private JTable tabelaPendencias;
    private DefaultTableModel modeloTabela;

    @Autowired
    private LivroController livroController;
    @Autowired
    private EmprestimoController emprestimoController;

    
    public ConsultaLivroAlunoPanel(LivroController livroController, EmprestimoController emprestimoController) {
  
        criarTela();
    }

    private void criarTela() {
        setLayout(new BorderLayout());

        JPanel painelTopo = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel lblTitulo = new JLabel("Título do Livro:");
        gbc.gridx = 0; gbc.gridy = 0;
        painelTopo.add(lblTitulo, gbc);

        txtTitulo = new JTextField(15);
        gbc.gridx = 1;
        painelTopo.add(txtTitulo, gbc);

        JButton btnBuscarLivro = new JButton("Buscar Disponibilidade");
        gbc.gridx = 2;
        painelTopo.add(btnBuscarLivro, gbc);

        lblResultadoLivro = new JLabel("Quantidade disponível:");
        gbc.gridx = 1; gbc.gridy = 1;
        painelTopo.add(lblResultadoLivro, gbc);

        JLabel lblMatricula = new JLabel("Matrícula do Aluno:");
        gbc.gridx = 0; gbc.gridy = 2;
        painelTopo.add(lblMatricula, gbc);

        txtMatricula = new JTextField(15);
        gbc.gridx = 1;
        painelTopo.add(txtMatricula, gbc);

        JButton btnBuscarAluno = new JButton("Verificar Pendências");
        gbc.gridx = 2;
        painelTopo.add(btnBuscarAluno, gbc);

        add(painelTopo, BorderLayout.NORTH);

        modeloTabela = new DefaultTableModel(
                new Object[]{"Título", "Data Empréstimo", "Data Devolução"}, 0);
        tabelaPendencias = new JTable(modeloTabela);

        add(new JScrollPane(tabelaPendencias), BorderLayout.CENTER);

        // EVENTOS
        btnBuscarLivro.addActionListener(e -> buscarLivro());
        btnBuscarAluno.addActionListener(e -> buscarPendenciasAluno());
    }

    private void buscarLivro() {
        String titulo = txtTitulo.getText();
        int quantidade = livroController.buscarDisponiveis(titulo);

        lblResultadoLivro.setText(quantidade <=0 ? "Livro não existente ou sem estoque" : "Quantidade disponível: " + quantidade);
    }

    private void buscarPendenciasAluno() {
        String matricula = txtMatricula.getText();
        List<Emprestimo> pendencias = emprestimoController.buscarPendenciasPorAluno(matricula);

        modeloTabela.setRowCount(0); // limpa a tabela

        for (Emprestimo e : pendencias) {
            modeloTabela.addRow(new Object[]{
                    e.getLivro().getTitulo(),
                    e.getDataEmprestimo(),
                    e.getDataDevolucao()
            });
        }
    }
}
