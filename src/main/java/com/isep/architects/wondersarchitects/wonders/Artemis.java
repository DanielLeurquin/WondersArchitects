package com.isep.architects.wondersarchitects.wonders;

public class Artemis extends Wonder{

    public Artemis(){
        this.type = WonderType.ARTEMIS;
        buildStage();
    }

    @Override
    public void buildStage() {
        this.stages.add(new WonderStage(2,false,1, new int[]{0},3));
        this.stages.add(new WonderStage(2,true,2, new int[]{1},3));
        this.stages.add(new WonderStage(3,false,3, new int[]{1},4));
        this.stages.add(new WonderStage(3,true,4, new int[]{1},5));
        this.stages.add(new WonderStage(4,false,5, new int[]{2,3,4},7));

    }
}
