package com.isep.architects.wondersarchitects.wonders;

import java.util.ArrayList;

public class Wonder {

    private ArrayList<WonderStage> stages = new ArrayList<WonderStage>();

    private WonderType type;

    public Wonder(WonderType type){
        this.type = type;
    }


    public WonderType getType() {
        return type;
    }
}
