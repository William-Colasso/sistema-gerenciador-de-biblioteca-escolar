package com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.view.logical.custom.panel;




import java.awt.Dimension;

import javax.swing.JPanel;


public class CardImagePanel extends MigPanel {

    public CardImagePanel() {
        super("wrap, insets 0", "[grow]", "[80%][20%]");
    setRoundedBorder(14);
    setPreferredSize(new Dimension(250, 260));
    }

    public void setContent(ImagePanel imagePanel, JPanel bellowPanel) {
        add(imagePanel, "grow, push");
        add(bellowPanel, "growx");
    }
}
