package com.isep.architects.wondersarchitects.wonders;

public enum WonderType {
    ALEXANDRIA,
    ARTEMIS,
    BABYLON,
    GIZEH,
    HALLICARNAS,
    RHODES,
    ZEUS;

    public int numFromWonder(){
        if(this.equals(WonderType.RHODES)){
            return 0;
        } else if (this.equals(WonderType.BABYLON)) {
            return 1;
        }else if (this.equals(WonderType.HALLICARNAS)) {
            return 2;
        }else if (this.equals(WonderType.ALEXANDRIA)) {
            return 3;
        }else if (this.equals(WonderType.GIZEH)) {
            return 4;
        }else if (this.equals(WonderType.ZEUS)) {
            return 5;
        }else {
            return 6;
        }
    }


}
