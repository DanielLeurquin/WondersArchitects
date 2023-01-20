package com.isep.architects.wondersarchitects.controllers;

import com.isep.architects.wondersarchitects.GuiParser;
import com.isep.architects.wondersarchitects.Player;
import com.isep.architects.wondersarchitects.complex.Complex;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class BoardOverviewController extends Controller{

    @FXML
    private AnchorPane anchorPane;

    @Override
    public void init(GuiParser parser) {
        anchorPane.getTransforms().setAll(parser.getApp().getScale());
        Image img = new Image(getClass().getResourceAsStream(
                "/com/isep/architects/wondersarchitects/img/logos.png"));
        double size = img.getHeight();
        double viewSize = 100.0;
        Complex z;

        int nbPlayers = parser.getGame().getNumberPlayer();

        double angle = (2*Math.PI)/nbPlayers;
        int radius = 210;
        double spacing = 10.0;
        int compteur = 0;

        for(Player player : parser.getGame().getPlayerList()){


            int i = player.getWonder().getType().numFromWonder();

            VBox vBox = new VBox();

            Label label = new Label(player.getName());
            label.setFont(Font.font(24));

            StackPane sp = new StackPane();

            ImageView view = new ImageView(img);

            view.setFitHeight(viewSize);
            view.setFitWidth(viewSize);
            view.setViewport(new Rectangle2D(size*i,0,size,size));

            sp.getChildren().add(view);
            sp.getStyleClass().add("stack-pane");



            parser.getApp().getStage().show();

            vBox.setSpacing(spacing);
            vBox.alignmentProperty().setValue(Pos.CENTER);

            vBox.getChildren().add(label);
            vBox.getChildren().add(sp);
            double vBoxH = viewSize + label.getFont().getSize()*2 +spacing;
            double vBoxW = viewSize;

            anchorPane.getChildren().add(vBox);

            z = Complex.exp(radius,(Math.PI/2)-angle*compteur);

            vBox.setLayoutX(z.re - vBoxW/2 + 485);
            vBox.setLayoutY(z.im - vBoxH/2 + 300);
            compteur++;

            sp.setOnMouseClicked(mouseEvent -> {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    //change scene to apropriate wonder
                    parser.loadPlayerScene(player);
                }
            });

        }

    }
}
