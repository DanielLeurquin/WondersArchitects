package com.isep.architects.wondersarchitects.wonders;

public class WonderStage {

    private boolean built = false;

    private int value;

    private boolean same;

    private int stagenum;

    private int[] neededNumBuild;

    private int victoryPoints;

    private Wonder wonder;

    private double axis;

    public WonderStage(int value, boolean same, int stagenum, int[] neededNumBuild, int victoryPoints,Wonder wonder, double axis){
        this.value = value;
        this.same = same;
        this.stagenum = stagenum;
        this.neededNumBuild = neededNumBuild;
        this.victoryPoints = victoryPoints;
        this.wonder = wonder;
        this.axis = axis;
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

    public Wonder getWonder() {
        return wonder;
    }

    public double getAxis() {
        return axis;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }
}
