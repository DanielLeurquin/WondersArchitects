package com.isep.architects.wondersarchitects.wonders;

public class Babylon extends Wonder{

    public Babylon(){
        this.type = WonderType.BABYLON;
        buildStage();
    }


    @Override
    public void buildStage() {
        this.stages.add(new WonderStage(2,false,1, new int[]{0},3));
        this.stages.add(new WonderStage(2,true,2, new int[]{1},0));
        this.stages.add(new WonderStage(3,false,3, new int[]{2},5));
        this.stages.add(new WonderStage(3,true,4, new int[]{2,3},5));
        this.stages.add(new WonderStage(4,false,5, new int[]{2,3},7));

    }
}
