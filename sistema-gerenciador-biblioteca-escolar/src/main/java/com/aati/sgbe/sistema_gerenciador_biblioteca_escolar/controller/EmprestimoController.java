package com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.model.Emprestimo;
import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.service.EmprestimoService;

@Controller
public class EmprestimoController {
    @Autowired
    EmprestimoService emprestimoService;

    public void create(Emprestimo livro){
        emprestimoService.create(livro);
    }

    public void delete(Emprestimo livro){
        emprestimoService.delete(livro);
    }

    public void deleteById(Long id){
        emprestimoService.deleteById(id);
    }

    public void update(Emprestimo livro){
        emprestimoService.update(livro);
    }

    public Emprestimo getById(Long id){
        return emprestimoService.getById(id);
    }

    public List<Emprestimo> getAll(){
        return emprestimoService.getAll();
    }

    public List<Emprestimo> buscarPendenciasPorAluno(String matricula) {
        return emprestimoService.alunoTemPendencias(matricula);
    }
}
