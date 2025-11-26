package com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.model.Livro;
import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.repository.LivroRepository;

@Service
public class LivroService {
    @Autowired
    LivroRepository livroRepository;

    public void create(Livro livro){
        livroRepository.save(livro);
    }

    public void delete(Livro livro){
        livroRepository.delete(livro);
    }

    public void deleteById(Long id){
        livroRepository.deleteById(id);
    }

    public void update(Livro livro){
        livro.setId(livroRepository.findByTitulo(livro.getTitulo()).getId());
        livroRepository.save(livro);
    }

    public Livro getById(Long id){
        return livroRepository.findById(id).get();
    }

    public Livro getByTitulo(String titulo){
        return livroRepository.findByTitulo(titulo);
    }

    public List<Livro> getAll(){
        return livroRepository.findAll();
    }
}
