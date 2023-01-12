package com.isep.architects.wondersarchitects.cards;

public enum CardsTypes {
    WOOD,
    STONE,
    BRICK,
    PAPER,
    GLASS,
    GOLD,
    RED0,
    RED1,
    RED2,
    BLUE2,
    BLUE3,
    WHEEL,
    TABLET,
    COMPASS;

    public int indice(){
        if(this.equals(CardsTypes.WOOD)){
            return 0;
        }else if(this.equals(CardsTypes.STONE)){
            return 1;
        }else if(this.equals(CardsTypes.BRICK)) {
            return 2;
        }else if(this.equals(CardsTypes.PAPER)) {
            return 3;
        }else if(this.equals(CardsTypes.GLASS)) {
            return 4;
        }else if(this.equals(CardsTypes.GOLD)) {
            return 5;
        }else if(this.equals(CardsTypes.RED0)) {
            return 6;
        }else if(this.equals(CardsTypes.RED1)) {
            return 7;
        }else if(this.equals(CardsTypes.RED2)) {
            return 8;
        }else if(this.equals(CardsTypes.BLUE2)) {
            return 9;
        }else if(this.equals(CardsTypes.BLUE3)) {
            return 10;
        }else if(this.equals(CardsTypes.WHEEL)) {
            return 11;
        }else if(this.equals(CardsTypes.TABLET)) {
            return 12;
        }else if(this.equals(CardsTypes.COMPASS)) {
            return 13;
        }
        return -1;
    }

}
