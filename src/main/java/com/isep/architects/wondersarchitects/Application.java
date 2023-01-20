package com.isep.architects.wondersarchitects;

import com.isep.architects.wondersarchitects.controllers.BoardOverviewController;
import com.isep.architects.wondersarchitects.controllers.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    private FXMLLoader fxmlLoader;
    private Stage stage;

    private Game game;

    private Scene scene;

    private double ratio;

    private double scWidth;

    private double scHeight;

    private Scale scale;


    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        stage.setX(0);
        stage.setY(0);
        stage.setResizable(false);
        this.stage.setMaximized(true);
        Font.loadFont(getClass().getResourceAsStream(
                "/com/isep/architects/wondersarchitects/font/PossumSaltareNF.otf"), 10);
        this.fxmlLoader = new FXMLLoader(Application.class.getResource(
                "/com/isep/architects/wondersarchitects/welcomeMenu.fxml"));



        scWidth = Screen.getPrimary().getBounds().getWidth();
        scHeight = Screen.getPrimary().getBounds().getWidth();
        ratio = 1;
        if(scWidth/970 < scHeight/600){
            ratio = scWidth/970;
        }else {
            ratio = scHeight/600;
        }
        System.out.println(ratio);

        scale = new Scale(ratio,ratio);
        scale.setPivotX(0);
        scale.setPivotY(0);

        this.scene = new Scene(this.fxmlLoader.load(),scWidth,scHeight);
        this.scene.getRoot().getTransforms().setAll(scale);


        GuiParser parser = new GuiParser();

        this.game = new Game(parser,this);

        parser.setGame(this.game);

        Controller controller = this.fxmlLoader.getController();

        this.stage.setTitle("7 Wonders Architects");
        this.stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/isep/architects/wondersarchitects/img/GameIcon.png")));
        //this.stage.setResizable(false);
        this.stage.setScene(this.scene);
        this.stage.show();

        controller.init(parser);
    }


    public Controller changeScene(String fxmlFile, GuiParser parser) throws IOException {

        this.fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = this.fxmlLoader.load();
        Controller controller = this.fxmlLoader.getController();




        this.scene = new Scene(root,scWidth,scHeight);
        this.scene.getRoot().getTransforms().setAll(scale);


        controller.init(parser);

        this.stage.setScene(this.scene);
        this.stage.setScene(scene);
        this.stage.show();
        return controller;

    }

    public Stage getStage() {
        return stage;
    }

    public Scene getScene() {
        return scene;
    }

    public static void main(String[] args) {


        launch();
    }
}