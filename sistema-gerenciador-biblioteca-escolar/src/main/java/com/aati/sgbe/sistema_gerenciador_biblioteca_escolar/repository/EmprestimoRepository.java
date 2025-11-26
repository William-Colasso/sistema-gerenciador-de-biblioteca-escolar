package com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.model.Aluno;
import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.model.Emprestimo;
import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.model.Livro;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long>{
    Long countByLivroAndIsDevolvidoFalse(Livro livro);
    List<Emprestimo> findByAlunoAndIsDevolvidoFalse(Aluno aluno);
}
