package com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.view.logical.abstracts.card;

import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.view.logical.custom.LayeredOverlayPane;
import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.view.logical.interfaces.Card;
import com.aati.sgbe.sistema_gerenciador_biblioteca_escolar.view.logical.interfaces.CardLayoutable;

public class LayeredOverlayCard extends  LayeredOverlayPane implements Card {

    

    private  final CardLayoutable cardLayoutbleFrame;
    private  final String cardName;



    
    public LayeredOverlayCard(CardLayoutable cardLayoutbleFrame, String cardName){
        super();
        this.cardLayoutbleFrame = cardLayoutbleFrame;
        this.cardName = cardName;
    }

    @Override
    public CardLayoutable getCardLayoutbleFrame() {
        return this.cardLayoutbleFrame;
    }

    @Override
    public String getCardName() {
        return  this.cardName;
    }



}
