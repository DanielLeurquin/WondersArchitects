package com.isep.architects.wondersarchitects.pile;

import com.isep.architects.wondersarchitects.Player;
import com.isep.architects.wondersarchitects.cards.*;

import java.util.ArrayList;

public class Pile {

    protected ArrayList<Cards> cards = new ArrayList<Cards>();

    public Cards drawCard(Player player){
        Cards card = cards.get(0);
        if(card instanceof GreyCards){
            player.getRessources().add((GreyCards) card);
        }else if(card instanceof YellowCards){
            player.getGold().add((YellowCards) card);
        }else if(card instanceof BlueCards){
            player.getBlue().add((BlueCards) card);
        }else if(card instanceof RedCards){
            player.getRed().add((RedCards) card);
        }else if(card instanceof GreenCards){
            player.getGreen().add((GreenCards) card);
        }

        cards.remove(card);
        return card;
    }

    public ArrayList<Cards> getCards() {
        return cards;
    }
}
