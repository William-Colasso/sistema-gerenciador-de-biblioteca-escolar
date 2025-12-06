package com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.model.Usuario;
import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.service.UsuarioService;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    public void create(Usuario usuario){
        usuarioService.create(usuario);
    }

    public Usuario validarLogin(String nome, String senha){
        return usuarioService.validarLogin(nome, senha);
    }

    public Usuario getById(Long id){
        return usuarioService.getById(id);
    }

    public List<Usuario> getAll(){
        return usuarioService.getAll();
    }
}
