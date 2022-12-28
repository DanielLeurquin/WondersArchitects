package com.isep.architects.wondersarchitects.wonders;

import java.util.ArrayList;

public class Wonder {

    private ArrayList<WonderStage> stages = new ArrayList<WonderStage>();

    private WonderType type;

    public Wonder(WonderType type){
        this.type = type;
        buildStages();

    }

    private void buildStages() {
        if(this.type.equals(WonderType.ALEXANDRIA)){
            buildAlexandria();
        }else if(this.type.equals(WonderType.ARTEMIS)){
            buildArtemis();
        }else if(this.type.equals(WonderType.BABYLON)){
            buildBabylon();
        }else if(this.type.equals(WonderType.GIZEH)){
            buildGizeh();
        }else if(this.type.equals(WonderType.ZEUS)){
            buildZeus();
        }else if(this.type.equals(WonderType.HALLICARNAS)){
            buildHallicarnas();
        }else if(this.type.equals(WonderType.RHODES)){
            buildRhodes();
        }

    }

    private void buildRhodes() {
        this.stages.add(new WonderStage(2,false,1, new int[]{0}));
        this.stages.add(new WonderStage(2,true,2, new int[]{0}));
        this.stages.add(new WonderStage(3,false,3, new int[]{1,2}));
        this.stages.add(new WonderStage(3,true,4, new int[]{3}));
        this.stages.add(new WonderStage(4,false,5, new int[]{4}));

    }

    private void buildHallicarnas() {
        this.stages.add(new WonderStage(2,false,1, new int[]{0}));
        this.stages.add(new WonderStage(2,true,2, new int[]{1}));
        this.stages.add(new WonderStage(3,false,3, new int[]{2}));
        this.stages.add(new WonderStage(3,true,4, new int[]{3}));
        this.stages.add(new WonderStage(4,false,5, new int[]{3}));

    }

    private void buildZeus() {
        this.stages.add(new WonderStage(2,false,1, new int[]{0}));
        this.stages.add(new WonderStage(2,true,2, new int[]{1}));
        this.stages.add(new WonderStage(3,false,3, new int[]{1}));
        this.stages.add(new WonderStage(3,true,4, new int[]{2,3}));
        this.stages.add(new WonderStage(4,false,5, new int[]{4}));
    }

    private void buildGizeh() {
        this.stages.add(new WonderStage(2,false,1, new int[]{0}));
        this.stages.add(new WonderStage(2,true,2, new int[]{1}));
        this.stages.add(new WonderStage(3,false,3, new int[]{2}));
        this.stages.add(new WonderStage(3,true,4, new int[]{3}));
        this.stages.add(new WonderStage(4,false,5, new int[]{4}));

    }

    private void buildBabylon() {
        this.stages.add(new WonderStage(2,false,1, new int[]{0}));
        this.stages.add(new WonderStage(2,true,2, new int[]{1}));
        this.stages.add(new WonderStage(3,false,3, new int[]{2}));
        this.stages.add(new WonderStage(3,true,4, new int[]{2,3}));
        this.stages.add(new WonderStage(4,false,5, new int[]{2,3}));

    }

    private void buildArtemis() {
        this.stages.add(new WonderStage(2,false,1, new int[]{0}));
        this.stages.add(new WonderStage(2,true,2, new int[]{1}));
        this.stages.add(new WonderStage(3,false,3, new int[]{1}));
        this.stages.add(new WonderStage(3,true,4, new int[]{1}));
        this.stages.add(new WonderStage(4,false,5, new int[]{2,3,4}));

    }

    private void buildAlexandria() {
        this.stages.add(new WonderStage(2,false,1, new int[]{0}));
        this.stages.add(new WonderStage(2,true,2, new int[]{1}));
        this.stages.add(new WonderStage(3,false,3, new int[]{2}));
        this.stages.add(new WonderStage(3,true,4, new int[]{3}));
        this.stages.add(new WonderStage(4,false,5, new int[]{4}));


    }

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
