package com.isep.architects.wondersarchitects;

import com.isep.architects.wondersarchitects.controllers.Controller;
import com.isep.architects.wondersarchitects.controllers.PlayerCreationController;

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
