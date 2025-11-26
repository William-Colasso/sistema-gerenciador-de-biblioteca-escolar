package com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.model.Emprestimo;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long>{
    
}
