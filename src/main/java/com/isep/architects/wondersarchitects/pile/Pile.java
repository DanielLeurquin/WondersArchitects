package com.isep.architects.wondersarchitects.pile;

import com.isep.architects.wondersarchitects.Player;
import com.isep.architects.wondersarchitects.cards.*;

import java.util.ArrayList;

public class Pile {

    protected ArrayList<CardsTypes> cards = new ArrayList<CardsTypes>();

    public CardsTypes drawCard(Player player){

        CardsTypes card = cards.get(0);
        player.getCards().add(card);
        cards.remove(card);
        return card;
    }

    public ArrayList<CardsTypes> getCards() {
        return cards;
    }
}
