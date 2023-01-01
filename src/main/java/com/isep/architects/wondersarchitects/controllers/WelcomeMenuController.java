package com.isep.architects.wondersarchitects.controllers;

import com.isep.architects.wondersarchitects.GuiParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class WelcomeMenuController extends Controller{

    @FXML
    private Button button;

    @FXML
    private ChoiceBox<Integer> choiceBox;

    @Override
    public void init(GuiParser parser) {
        ObservableList<Integer> list = FXCollections.observableArrayList(2,3,4,5,6,7);
        choiceBox.setItems(list);
        choiceBox.setValue(2);
        button.setOnAction(event -> {
            int num = choiceBox.getValue();
            parser.getGame().setNumberPlayer(num);
            parser.getGame().createMilitaryToken();
            parser.askPlayerName(1);

        });

        parser.getApp().getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()){
                    case ENTER:
                        int num = choiceBox.getValue();
                        parser.getGame().setNumberPlayer(num);
                        parser.getGame().createMilitaryToken();
                        parser.askPlayerName(1);
                        break;
                    case DIGIT2:
                        choiceBox.setValue(2);
                        break;
                    case DIGIT3:
                        choiceBox.setValue(3);
                        break;
                    case DIGIT4:
                        choiceBox.setValue(4);
                        break;
                    case DIGIT5:
                        choiceBox.setValue(5);
                        break;
                    case DIGIT6:
                        choiceBox.setValue(6);
                        break;
                    case DIGIT7:
                        choiceBox.setValue(7);
                        break;
                }
            }
        });


    }
}
