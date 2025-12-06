package com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.model.Aluno;
import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.service.AlunoService;

@Controller
public class AlunoController {
    @Autowired
    private AlunoService alunoService;

    public void create(Aluno aluno){
        alunoService.create(aluno);
    }

    public void delete(Aluno aluno){
        alunoService.delete(aluno);
    }

    public void deleteById(Long id){
        alunoService.deleteById(id);
    }

    public void update(Aluno aluno){
        alunoService.update(aluno);
    }

    public Aluno getById(Long id){
        return alunoService.getById(id);
    }

    public Aluno getByMatricula(String matricula){
        return alunoService.getByMatricula(matricula);
    }

    public List<Aluno> getAll(){
        return alunoService.getAll();
    }
}
