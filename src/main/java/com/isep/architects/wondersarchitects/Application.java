package com.isep.architects.wondersarchitects;

import com.isep.architects.wondersarchitects.controllers.BoardOverviewController;
import com.isep.architects.wondersarchitects.controllers.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
    private MediaPlayer player;


    @Override
    public void start(Stage stage) throws IOException {

        Media mainTheme = new Media(getClass().getResource(
                "/com/isep/architects/wondersarchitects/sound/main_Theme.mp3").toExternalForm());
        player = new MediaPlayer(mainTheme);

        player.play();

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
        scHeight = Screen.getPrimary().getBounds().getHeight();

        if(scWidth/970 < scHeight/600){
            ratio = scWidth/970;
        }else {
            ratio = scHeight/600;
        }

        scale = new Scale(ratio,ratio);
        scale.setPivotX(-(scWidth-970*ratio)/2);
        scale.setPivotY(-(scHeight-600*ratio)/2);


        this.scene = new Scene(this.fxmlLoader.load(),scWidth,scHeight);

        GuiParser parser = new GuiParser();

        this.game = new Game(parser,this);

        parser.setGame(this.game);

        Controller controller = this.fxmlLoader.getController();

        this.stage.setTitle("7 Wonders Architects");
        this.stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/isep/architects/wondersarchitects/img/GameIcon.png")));
        this.stage.setScene(this.scene);
        this.stage.show();

        controller.init(parser);
    }


    public Controller changeScene(String fxmlFile, GuiParser parser) throws IOException {

        this.fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = this.fxmlLoader.load();
        Controller controller = this.fxmlLoader.getController();

        this.scene = new Scene(root,scWidth,scHeight);

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

    public Scale getScale() {
        return scale;
    }

    public static void main(String[] args) {
        launch();
    }
}