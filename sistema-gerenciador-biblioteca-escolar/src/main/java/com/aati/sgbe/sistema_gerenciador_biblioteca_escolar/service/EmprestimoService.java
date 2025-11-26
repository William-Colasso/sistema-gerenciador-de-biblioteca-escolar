package com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.model.Emprestimo;
import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.repository.AlunoRepository;
import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.repository.EmprestimoRepository;

@Service
public class EmprestimoService {

    @Autowired
    AlunoRepository alunoRepository;
    @Autowired
    EmprestimoRepository emprestimoRepository;

    public void create(Emprestimo emprestimo){
        emprestimoRepository.save(emprestimo);
    }

    public void delete(Emprestimo emprestimo){
        emprestimoRepository.delete(emprestimo);
    }

    public void deleteById(Long id){
        emprestimoRepository.deleteById(id);
    }

    public void update(Emprestimo emprestimo){
        emprestimoRepository.save(emprestimo);
    }

    public Emprestimo getById(Long id){
        return emprestimoRepository.findById(id).get();
    }

    public List<Emprestimo> getAll(){
        return emprestimoRepository.findAll();
    }

    public List<Emprestimo> alunoTemPendencias(String matricula){
        return emprestimoRepository.findByAlunoAndIsDevolvidoFalse(alunoRepository.findByMatricula(matricula));
    }
}
