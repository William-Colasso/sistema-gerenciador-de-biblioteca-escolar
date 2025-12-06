package com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.model.Livro;
import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.service.LivroService;

@Controller
public class LivroController {
    @Autowired
    private LivroService livroService;

    

    public void create(Livro livro) {
        livroService.create(livro);
    }

    public void delete(Livro livro) {
        livroService.delete(livro);
    }

    public void deleteById(Long id) {
        livroService.deleteById(id);
    }

    public void update(Livro livro) {
        livroService.update(livro);
    }

    public Livro getById(Long id) {
        return livroService.getById(id);
    }

    public Livro getByTitulo(String titulo) {
        return livroService.getByTitulo(titulo);
    }

    public List<Livro> getAll() {
        return livroService.getAll();
    }

    public int buscarDisponiveis(String titulo) {
        return livroService.countByTituloAndDisponivelTrue(titulo);
    }
}
