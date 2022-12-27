package com.isep.architects.wondersarchitects.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class WelcomeMenuController extends Controller{

    @FXML
    private Button button;

    @FXML
    private ChoiceBox<Integer> choiceBox;

    @Override
    public void init() {
        ObservableList<Integer> list = FXCollections.observableArrayList(2,3,4,5,6,7);
        choiceBox.setItems(list);
        choiceBox.setValue(2);

    }
}
