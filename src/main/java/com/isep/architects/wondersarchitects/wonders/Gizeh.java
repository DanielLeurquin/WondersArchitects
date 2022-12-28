package com.isep.architects.wondersarchitects.wonders;

public class Gizeh extends Wonder{

    public Gizeh(){
        this.type = WonderType.GIZEH;
        buildStage();
    }

    @Override
    public void buildStage() {
        this.stages.add(new WonderStage(2,false,1, new int[]{0},4));
        this.stages.add(new WonderStage(2,true,2, new int[]{1},5));
        this.stages.add(new WonderStage(3,false,3, new int[]{2},6));
        this.stages.add(new WonderStage(3,true,4, new int[]{3},7));
        this.stages.add(new WonderStage(4,false,5, new int[]{4},8));
    }
}
