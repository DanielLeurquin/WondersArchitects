package com.isep.architects.wondersarchitects;

import com.isep.architects.wondersarchitects.cards.CardsTypes;
import com.isep.architects.wondersarchitects.controllers.Controller;
import com.isep.architects.wondersarchitects.controllers.PlayerCreationController;
import com.isep.architects.wondersarchitects.controllers.WonderController;
import com.isep.architects.wondersarchitects.wonders.WonderStage;

import java.io.IOException;

public class GuiParser implements InputParser{

    private Controller controller;

    private Game game;
    @Override
    public void askPlayerName(int number){
        try {
            PlayerCreationController controller = (PlayerCreationController) getApp().changeScene(
                    "/com/isep/architects/wondersarchitects/playerCreation.fxml",this);
            controller.numberInit(number);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void chargeOverview() {
        try {
            getApp().changeScene("/com/isep/architects/wondersarchitects/boardOverview.fxml",this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void loadPlayerScene(Player player) {
        try {
            WonderController controller = (WonderController) getApp().changeScene(
                    "/com/isep/architects/wondersarchitects/wonder.fxml",this);
            controller.initWonder(player);
            this.controller =controller;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void animationStage(WonderStage stage) {
        WonderController cont = (WonderController) this.controller;
        cont.startAnimation(stage);

    }

    @Override
    public void chooseProgress() {
        WonderController cont = (WonderController) this.controller;
        cont.setProgressPower(true);
        cont.checkFinish(null);
        game.endTurn();
    }

    @Override
    public void checkFinish(CardsTypes drawCard) {
        WonderController cont = (WonderController) this.controller;
        cont.checkFinish(drawCard);
    }

    @Override
    public void chargeAlexOverlay() {
        WonderController cont = (WonderController) this.controller;
        cont.chargeAlexOverlay();
    }


    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public Application getApp(){
        return this.game.getApp();
    }

}
