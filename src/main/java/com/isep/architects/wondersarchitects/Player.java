package com.isep.architects.wondersarchitects;

import com.isep.architects.wondersarchitects.wonders.Wonder;

public class Player {

    private String name;

    private Wonder wonder;

    public Player(String name){
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setWonder(Wonder wonder) {
        this.wonder = wonder;
    }

    public Wonder getWonder() {
        return wonder;
    }
}
