package com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.model.Livro;


@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{
    Livro findByTitulo(String titulo);
}
