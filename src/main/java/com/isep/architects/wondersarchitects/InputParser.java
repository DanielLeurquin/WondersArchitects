package com.isep.architects.wondersarchitects;

import com.isep.architects.wondersarchitects.Animation.WonderStageAnimation;
import com.isep.architects.wondersarchitects.cards.CardsTypes;
import com.isep.architects.wondersarchitects.wonders.WonderStage;

public interface InputParser {
    void askPlayerName(int number);

    void chargeOverview();

    void loadPlayerScene(Player player);

    WonderStageAnimation animationStage(WonderStage stage);


    void chooseProgress();

    void drawCard(CardsTypes drawCard);

    void chargeAlexOverlay();

    void setWonderPower(boolean value);

    boolean isWonderPower();

    void enableHaliOverlay();

    void setAnimation(boolean b);

    void checkFinish(CardsTypes card);

    void loadScoreBoard();
}
