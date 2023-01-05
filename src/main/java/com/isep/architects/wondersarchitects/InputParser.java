package com.isep.architects.wondersarchitects;

import com.isep.architects.wondersarchitects.wonders.WonderStage;

public interface InputParser {
    void askPlayerName(int number);

    void chargeOverview();

    void loadPlayerScene(Player player);

    void animationStage(WonderStage stage);


}
