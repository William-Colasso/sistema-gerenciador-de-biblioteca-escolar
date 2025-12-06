package com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.view.frames;

import java.awt.GridLayout;

import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.view.logical.abstracts.AbstractFrame;

public class LoginFrame extends AbstractFrame {

    public LoginFrame() {
        super("Login");
        setLayout(new GridLayout());
        setSize(400, 300);
    }

    @Override
    protected void initComponents() {
        
    }

}
