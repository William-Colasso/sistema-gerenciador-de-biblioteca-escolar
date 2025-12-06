package com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.controller.EmprestimoController;
import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.controller.LivroController;
import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.view.logical.abstracts.AbstractFrame;

@Component
public class MainFrame extends AbstractFrame {

    @Autowired
    private LivroController livroController;

    @Autowired
    private EmprestimoController emprestimoController;



    @Autowired
    public MainFrame(AlunoPanel alunoPanel, LivroPanel livroPanel, EmprestimoPanel emprestimoPanel, ConsultaLivroAlunoPanel consultaLivroAlunoPanel) {
        super("Sistema de biblioteca");
        setLayout(new GridLayout(1,1));
        

        

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Alunos", alunoPanel);
        tabs.addTab("Livro", livroPanel);
        tabs.addTab("Emprestimo", emprestimoPanel);
        tabs.addTab("Relatorio",consultaLivroAlunoPanel);
        add(tabs, BorderLayout.CENTER);
    }


    

}
