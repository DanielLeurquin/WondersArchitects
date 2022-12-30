package com.isep.architects.wondersarchitects.tokens;

public class MilitaryToken extends Token{

    private boolean sideWar = false;


    public void setSideWar(boolean sideWar) {
        this.sideWar = sideWar;
    }

    public boolean isSideWar() {
        return sideWar;
    }
}
