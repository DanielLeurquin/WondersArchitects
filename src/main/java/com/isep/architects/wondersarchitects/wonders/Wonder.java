package com.isep.architects.wondersarchitects.wonders;

import java.util.ArrayList;

public abstract class Wonder {

    protected ArrayList<WonderStage> stages = new ArrayList<WonderStage>();

    protected WonderType type;

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
}
