package com.isep.architects.wondersarchitects.controllers;

import com.isep.architects.wondersarchitects.GuiParser;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

public class PlayerCreationController extends Controller{

    @FXML
    private Label label;

    @FXML
    private ImageView imageView;

    @FXML
    private TextField textField;

    @FXML
    private Button button;

    private String[] list = {"chat.png","gold.png","lance.png","papyrus.png",
            "pierre.png","roue.png","tablette.png"};

    @Override
    public void init(GuiParser parser) {
        button.setOnAction(event -> {
            if(!textField.getText().equals("")){
                String name = textField.getText();
                parser.getGame().createPlayer(name);
            }
        });

        parser.getApp().getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()){
                    case ENTER:
                        if(!textField.getText().equals("")){
                            String name = textField.getText();
                            parser.getGame().createPlayer(name);
                        }
                        break;
                }
            }
        });



    }

    public void numberInit(int number){
        this.label.setText("Please chose a name for player number "+number);
        String path = "/com/isep/architects/wondersarchitects/img/";
        Image image = new Image(getClass().getResourceAsStream(
                path+list[number-1]));
        imageView.setImage(image);

    }

}
