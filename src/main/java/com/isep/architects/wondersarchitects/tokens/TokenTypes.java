package com.isep.architects.wondersarchitects.tokens;

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

    CAT,
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


}
