package com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.view;

import javax.swing.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class MainFrame extends JFrame {

    @Autowired
    public MainFrame(AlunoPanel alunoPanel, LivroPanel livroPanel, EmprestimoPanel emprestimoPanel, ConsultaLivroAlunoPanel consultaLivroAlunoPanel) {
        setTitle("Sistema de Biblioteca");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Alunos", alunoPanel);
        tabs.addTab("Livro", livroPanel);
        tabs.addTab("Emprestimo", emprestimoPanel);
        tabs.addTab("Relatorio",consultaLivroAlunoPanel);
        add(tabs, BorderLayout.CENTER);
    }

}
