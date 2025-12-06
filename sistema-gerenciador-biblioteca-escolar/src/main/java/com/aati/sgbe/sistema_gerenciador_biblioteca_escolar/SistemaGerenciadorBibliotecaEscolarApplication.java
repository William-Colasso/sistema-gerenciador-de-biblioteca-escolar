package com.aati.sgbe.sistema_gerenciador_biblioteca_escolar;



import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.view.AuthPanel;
import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.view.MainFrame;
import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.view.frames.LoginFrame;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

@SpringBootApplication
public class SistemaGerenciadorBibliotecaEscolarApplication {

    @Autowired
    private AuthPanel authPanel;

    @Autowired
    private MainFrame mainFrame;

    public static void main(String[] args) throws UnsupportedLookAndFeelException {

        UIManager.setLookAndFeel(new FlatMacLightLaf());

        System.setProperty("java.awt.headless", "false");

        SpringApplication.run(SistemaGerenciadorBibliotecaEscolarApplication.class, args);
    	
	}

    // Executa automaticamente quando o Spring terminar de iniciar
    @EventListener(ApplicationReadyEvent.class)
    public void start() {
        SwingUtilities.invokeLater(() -> {

            JFrame loginFrame = new LoginFrame();
            loginFrame.add(authPanel);
            loginFrame.setVisible(true);
            loginFrame.setLocationRelativeTo(null);

            // Ouvir evento de login do painel
            authPanel.addPropertyChangeListener("loginSuccess", evt -> {
                loginFrame.dispose();
                mainFrame.setVisible(true);
            });
        });
    }

}
