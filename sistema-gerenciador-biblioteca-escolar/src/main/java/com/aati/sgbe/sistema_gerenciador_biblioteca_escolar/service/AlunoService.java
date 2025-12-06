package com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.model.Aluno;
import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.repository.AlunoRepository;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;

    public void create(Aluno aluno){
        alunoRepository.save(aluno);
    }

    public void delete(Aluno aluno){
        alunoRepository.delete(aluno);
    }

    public void deleteById(Long id){
        alunoRepository.deleteById(id);
    }

    public void update(Aluno aluno){
        aluno.setId(alunoRepository.findByMatricula(aluno.getMatricula()).getId());
        alunoRepository.save(aluno);
    }

    public Aluno getById(Long id){
        return alunoRepository.findById(id).get();
    }

    public Aluno getByMatricula(String matricula){
        return alunoRepository.findByMatricula(matricula);
    }

    public List<Aluno> getAll(){
        return alunoRepository.findAll();
    }
}
