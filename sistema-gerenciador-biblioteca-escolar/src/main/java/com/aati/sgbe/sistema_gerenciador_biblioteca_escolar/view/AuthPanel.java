package com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.springframework.stereotype.Component;

import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.controller.UsuarioController;
import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.model.Usuario;


@Component
public class AuthPanel extends JPanel {

    private final UsuarioController usuarioController;

    private CardLayout cardLayout;
    private JPanel cards;

    
    public AuthPanel(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        JPanel loginPanel = createLoginPanel();
        JPanel cadastroPanel = createCadastroPanel();

        cards.add(loginPanel, "login");
        cards.add(cadastroPanel, "cadastro");

        setLayout(new BorderLayout());
        add(cards, BorderLayout.CENTER);
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblTitle = new JLabel("Login");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel lblNome = new JLabel("Nome:");
        JTextField txtNome = new JTextField(15);

        JLabel lblSenha = new JLabel("Senha:");
        JPasswordField txtSenha = new JPasswordField(15);

        JButton btnEntrar = new JButton("Entrar");
        JButton btnTelaCadastro = new JButton("Não possui conta? Cadastrar");

        btnEntrar.addActionListener(e -> {
            Usuario usuario = usuarioController.validarLogin(
                txtNome.getText(), new String(txtSenha.getPassword())
            );

            if (usuario != null) {
                JOptionPane.showMessageDialog(this, "Login OK!");
                firePropertyChange("loginSuccess", false, true); //evento customizado
            } else {
                JOptionPane.showMessageDialog(this, "Credenciais inválidas!");
            }
        });

        btnTelaCadastro.addActionListener(e -> cardLayout.show(cards, "cadastro"));

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(lblTitle, gbc);

        gbc.gridwidth = 1; gbc.gridy++;
        panel.add(lblNome, gbc);
        gbc.gridx = 1;
        panel.add(txtNome, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(lblSenha, gbc);
        gbc.gridx = 1;
        panel.add(txtSenha, gbc);

        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2;
        panel.add(btnEntrar, gbc);

        gbc.gridy++;
        panel.add(btnTelaCadastro, gbc);

        return panel;
    }

    private JPanel createCadastroPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblTitle = new JLabel("Cadastro de Usuário");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel lblNome = new JLabel("Nome:");
        JTextField txtNome = new JTextField(15);

        JLabel lblSenha = new JLabel("Senha:");
        JPasswordField txtSenha = new JPasswordField(15);

        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnVoltarLogin = new JButton("Voltar para Login");

        btnCadastrar.addActionListener(e -> {
            Usuario usuario = new Usuario();
            usuario.setNome(txtNome.getText());
            usuario.setSenha(new String(txtSenha.getPassword()));
        
            usuarioController.create(usuario);
            JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
            cardLayout.show(cards, "login");
        });
        btnVoltarLogin.addActionListener(e -> cardLayout.show(cards, "login"));

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(lblTitle, gbc);

        gbc.gridwidth = 1; gbc.gridy++;
        panel.add(lblNome, gbc);
        gbc.gridx = 1;
        panel.add(txtNome, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(lblSenha, gbc);
        gbc.gridx = 1;
        panel.add(txtSenha, gbc);

        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2;
        panel.add(btnCadastrar, gbc);

        gbc.gridy++;
        panel.add(btnVoltarLogin, gbc);

        return panel;
    }
}