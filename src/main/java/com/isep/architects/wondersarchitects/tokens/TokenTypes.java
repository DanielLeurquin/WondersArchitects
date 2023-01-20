package com.isep.architects.wondersarchitects.tokens;

import com.isep.architects.wondersarchitects.cards.CardsTypes;

import java.util.ArrayList;

public enum TokenTypes {
    //progress
    URBANISM,
    CRAFTS,
    JEWELLERY,
    SCIENCE,
    PROPAGANDA,
    ARCHITECTURE,
    ECONOMY,
    ENGINEERING,
    TACTICS,
    DECOR,
    POLITICS,
    STRATEGY,
    EDUCATION,
    CULTURE,
    CONFLICT,
    MILITARY,
    PEACE;


    public int getIndex(){
        for(int i = 0; i<TokenTypes.values().length;i++){
            if(TokenTypes.values()[i].equals(this)){
                return i;
            }
        }
        return 0;
    }

    public boolean pileEffect(CardsTypes card){
        if(this.getIndex()==0){
            return card.equals(CardsTypes.WOOD) || card.equals(CardsTypes.BRICK);
        }else if(this.getIndex()==1){
            return card.equals(CardsTypes.PAPER) || card.equals(CardsTypes.GLASS);
        }else if(this.getIndex()==2){
            return card.equals(CardsTypes.STONE) || card.equals(CardsTypes.GOLD);
        }else if(this.getIndex()==3){
            return card.equals(CardsTypes.WHEEL) || card.equals(CardsTypes.TABLET) ||
                    card.equals(CardsTypes.COMPASS);
        }else if(this.getIndex()==4){
            return card.equals(CardsTypes.RED0) || card.equals(CardsTypes.RED1) ||
                    card.equals(CardsTypes.RED2);
        }else {
            return false;
        }
    }




}
