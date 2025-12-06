package com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.view;

import java.awt.BorderLayout;

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



  
    public MainFrame() {
        super("Sistema de Biblioteca");
        
        
        
    }

    @Override
    protected void initComponents() {
        
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Alunos", new AlunoPanel());
        tabs.addTab("Livro", new LivroPanel());
        tabs.addTab("Emprestimo", new EmprestimoPanel());
        tabs.addTab("Relatorio",new ConsultaLivroAlunoPanel(livroController,emprestimoController));
        add(tabs, BorderLayout.CENTER);
    }

}
