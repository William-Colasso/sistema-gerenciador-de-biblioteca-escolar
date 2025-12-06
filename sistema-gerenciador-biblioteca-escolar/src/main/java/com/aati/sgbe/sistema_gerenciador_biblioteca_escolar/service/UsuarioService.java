package com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.model.Usuario;
import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario validarLogin(String nome, String senha) {
        Usuario user = usuarioRepository.findByNome(nome);
        if (user != null && user.getSenha().equals(senha)) {
            return user;
        }
        return null;
    }

    public void create(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public Usuario getById(Long id) {
        return usuarioRepository.findById(id).get();
    }

    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }
}
