package com.isep.architects.wondersarchitects.wonders;

public class WonderStage {

    private boolean built = false;

    private int value;

    private boolean same;

    private int stagenum;

    private int[] neededNumBuild;

    public WonderStage(int value, boolean same, int stagenum, int[] neededNumBuild){//stagePower
        this.value = value;
        this.same = same;
        this.stagenum = stagenum;
        this.neededNumBuild = neededNumBuild;
    }

    public void setBuilt(boolean built) {
        this.built = built;
    }

    public boolean isBuilt() {
        return built;
    }

    public int getStagenum() {
        return stagenum;
    }

    public int[] getNeededNumBuild() {
        return neededNumBuild;
    }

    public int getValue() {
        return value;
    }

    public boolean isSame() {
        return same;
    }
}
