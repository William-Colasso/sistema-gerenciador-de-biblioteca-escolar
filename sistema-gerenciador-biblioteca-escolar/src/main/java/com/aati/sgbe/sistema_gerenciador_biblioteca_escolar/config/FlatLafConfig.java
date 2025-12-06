package com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.config;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.springframework.context.annotation.Configuration;

import com.formdev.flatlaf.FlatLaf;

@Configuration
public class FlatLafConfig {

    private static final String PATH = "com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.view.themes";

    static {
        // registra onde estão seus .properties
         FlatLaf.registerCustomDefaultsSource(PATH);

    }

    public static void setLookAndFeel(FlatLaf laF) {
        try {
            UIManager.setLookAndFeel(laF);
            FlatLaf.updateUI();
        } catch (UnsupportedLookAndFeelException e) {
            System.out.println(e.getMessage());
        }
    }
}
