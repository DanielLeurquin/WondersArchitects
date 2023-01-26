package com.isep.architects.wondersarchitects.wonders;

import com.isep.architects.wondersarchitects.Player;
import com.isep.architects.wondersarchitects.pile.Pile;


import java.util.ArrayList;

public abstract class Wonder {

    protected ArrayList<WonderStage> stages = new ArrayList<WonderStage>();

    protected WonderType type;

    protected Pile pile = new Pile();

    protected Player player;

    public abstract void buildStage();


    public WonderStage getStageFromNum(int num){
        for (WonderStage stage : stages){
            if(stage.getStagenum() == num){
                return stage;
            }
        }
        return null;
    }


    public abstract void fillPile();


    public ArrayList<WonderStage> getStages() {
        return stages;
    }

    public WonderType getType() {
        return type;
    }

    public Pile getPile() {
        return pile;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
