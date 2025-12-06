package com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.controller.UsuarioController;
import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.model.Usuario;
import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.view.logical.custom.panel.MigPanel;

import net.miginfocom.swing.MigLayout;

@Component
public class AuthPanel extends MigPanel {

    @Autowired
    private UsuarioController usuarioController;

    private CardLayout cardLayout;
    private JPanel cards;

    public AuthPanel() {
        super("fill, insets 20");

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        MigPanel loginPanel = createLoginPanel();
        MigPanel cadastroPanel = createCadastroPanel();

        cards.add(loginPanel, "login");
        cards.add(cadastroPanel, "cadastro");

        setLayout(new MigLayout("fill"));
        add(cards, "grow, push");
    }

    private MigPanel createLoginPanel() {
        MigPanel panel = new MigPanel(
            "wrap 2, align center, gapy 15",
            "[right][250!]",
            ""
        );

        JLabel lblTitle = new JLabel("Login");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 28));

        JLabel lblNome = new JLabel("Nome:");
        JTextField txtNome = new JTextField();

        JLabel lblSenha = new JLabel("Senha:");
        JPasswordField txtSenha = new JPasswordField();

        JButton btnEntrar = new JButton("Entrar");
        JButton btnTelaCadastro = new JButton("Não possui conta? Cadastrar");

        btnEntrar.addActionListener(e -> {
            Usuario usuario = usuarioController.validarLogin(
                txtNome.getText(), new String(txtSenha.getPassword())
            );

            if (usuario != null) {
                JOptionPane.showMessageDialog(this, "Login OK!");
                firePropertyChange("loginSuccess", false, true);
            } else {
                JOptionPane.showMessageDialog(this, "Credenciais inválidas!");
            }
        });

        btnTelaCadastro.addActionListener(e -> cardLayout.show(cards, "cadastro"));

        panel.add(lblTitle, "span 2, align center");
        panel.add(lblNome);
        panel.add(txtNome, "growx");
        panel.add(lblSenha);
        panel.add(txtSenha, "growx");
        panel.add(btnEntrar, "span 2, growx");
        panel.add(btnTelaCadastro, "span 2, growx");

        return wrapCentered(panel);
    }

    private MigPanel createCadastroPanel() {
        MigPanel panel = new MigPanel(
            "wrap 2, align center, gapy 15",
            "[right][250!]",
            ""
        );

        JLabel lblTitle = new JLabel("Cadastro de Usuário");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 28));

        JLabel lblNome = new JLabel("Nome:");
        JTextField txtNome = new JTextField();

        JLabel lblSenha = new JLabel("Senha:");
        JPasswordField txtSenha = new JPasswordField();

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

        panel.add(lblTitle, "span 2, align center");
        panel.add(lblNome);
        panel.add(txtNome, "growx");
        panel.add(lblSenha);
        panel.add(txtSenha, "growx");
        panel.add(btnCadastrar, "span 2, growx");
        panel.add(btnVoltarLogin, "span 2, growx");

        return wrapCentered(panel);
    }

    private MigPanel wrapCentered(MigPanel form) {
        MigPanel wrapper = new MigPanel(
            "fill, insets 0",
            "",
            ""
        );
        wrapper.setLayout(new MigLayout("fill"));
        wrapper.add(form, "center, growx, pushx, pushy");
        return wrapper;
    }
}