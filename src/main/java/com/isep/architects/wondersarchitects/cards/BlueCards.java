package com.isep.architects.wondersarchitects.cards;

public class BlueCards extends Cards{

    private boolean twoPoint;

    public BlueCards(boolean twoPoint){
        this.twoPoint = twoPoint;
    }

    public boolean isTwoPoint() {
        return twoPoint;
    }
}
