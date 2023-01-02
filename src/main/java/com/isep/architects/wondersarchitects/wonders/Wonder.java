package com.isep.architects.wondersarchitects.wonders;

import com.isep.architects.wondersarchitects.pile.Pile;
import com.isep.architects.wondersarchitects.pile.SidePile;

import java.util.ArrayList;

public abstract class Wonder {

    protected ArrayList<WonderStage> stages = new ArrayList<WonderStage>();

    protected WonderType type;

    protected Pile pile = new SidePile();

    public abstract void buildStage();


    public WonderStage getStageFromNum(int num){
        for (WonderStage stage : stages){
            if(stage.getStagenum() == num){
                return stage;
            }
        }
        return null;
    }


    public ArrayList<WonderStage> getStages() {
        return stages;
    }

    public WonderType getType() {
        return type;
    }

    public Pile getPile() {
        return pile;
    }
}
