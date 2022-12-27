package com.isep.architects.wondersarchitects;

import com.isep.architects.wondersarchitects.controllers.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    private FXMLLoader fxmlLoader;


    @Override
    public void start(Stage stage) throws IOException {
        Font.loadFont(getClass().getResourceAsStream("/com/isep/architects/wondersarchitects/font/PossumSaltareNF.otf"), 10);
        this.fxmlLoader = new FXMLLoader(Application.class.getResource("/com/isep/architects/wondersarchitects/welcomeMenu.fxml"));

        Scene scene = new Scene(this.fxmlLoader.load(), 970, 600);
        Controller controller = this.fxmlLoader.getController();
        controller.init();
        stage.setTitle("7 Wonders Architects");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/isep/architects/wondersarchitects/img/GameIcon.png")));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}