package com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.view.logical.custom.util.color;

import java.awt.Color;

public class LightBlueTheme extends ColorTheme {

    public LightBlueTheme() {
        super(
            new Color(4, 70, 179),          // contrast
            new Color(30, 27, 24),           // dark
            new Color(250, 250, 250),        // white
            new Color(221, 236, 250),        // primary
            new Color(173, 198, 232),        // secondary
            new Color(145, 168, 214),        // tertiary
            new Color(Color.TRANSLUCENT),     // transparent
            new Color(0,0,0,20),          // shadowSoft
            new Color(0, 0, 0, 46),           // shadowStrong
            new Color(92, 116, 255)         //Accent
        );
    }

}
