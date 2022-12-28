package com.isep.architects.wondersarchitects;

import com.isep.architects.wondersarchitects.controllers.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    private FXMLLoader fxmlLoader;
    private Stage stage;

    private Game game;


    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        Font.loadFont(getClass().getResourceAsStream(
                "/com/isep/architects/wondersarchitects/font/PossumSaltareNF.otf"), 10);
        this.fxmlLoader = new FXMLLoader(Application.class.getResource(
                "/com/isep/architects/wondersarchitects/welcomeMenu.fxml"));

        Scene scene = new Scene(this.fxmlLoader.load(), 970, 600);

        GuiParser parser = new GuiParser();

        this.game = new Game(parser,this);

        parser.setGame(this.game);

        Controller controller = this.fxmlLoader.getController();
        controller.init(parser);
        this.stage.setTitle("7 Wonders Architects");
        this.stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/isep/architects/wondersarchitects/img/GameIcon.png")));
        this.stage.setResizable(false);
        this.stage.setScene(scene);
        this.stage.show();
    }


    public Controller changeScene(String fxmlFile, GuiParser parser) throws IOException {
        this.fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = this.fxmlLoader.load();
        Controller controller = this.fxmlLoader.getController();

        controller.init(parser);

        Scene scene = new Scene(root, 970, 600);
        this.stage.setScene(scene);
        this.stage.show();
        return controller;

    }


    public static void main(String[] args) {
        launch();
    }
}