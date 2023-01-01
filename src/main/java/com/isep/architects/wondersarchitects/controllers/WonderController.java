package com.isep.architects.wondersarchitects.controllers;

import com.isep.architects.wondersarchitects.GuiParser;
import com.isep.architects.wondersarchitects.Player;
import com.isep.architects.wondersarchitects.cards.BlueCards;
import com.isep.architects.wondersarchitects.cards.GreyCards;
import com.isep.architects.wondersarchitects.cards.RessourceType;
import com.isep.architects.wondersarchitects.cards.YellowCards;
import com.isep.architects.wondersarchitects.tokens.MilitaryToken;
import com.isep.architects.wondersarchitects.wonders.Wonder;
import com.isep.architects.wondersarchitects.wonders.WonderType;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class WonderController extends Controller{

    @FXML
    private Button back;

    @FXML
    private ImageView wonderStage1, wonderStage2, wonderStage3, wonderStage4, wonderStage5;

    @FXML
    private ImageView centerPile, leftPile, rightPile;

    @FXML
    private StackPane sp1,sp2,sp3;

    @FXML
    private HBox hBoxRessource, hBoxProgress, hBoxMilitary, hBoxWar , hBoxPoints;

    @FXML
    private ScrollPane scrollP;

    @FXML
    private VBox vBoxLabel;

    private Rectangle2D woodRect = new Rectangle2D(0,0,150,150);
    private Rectangle2D stoneRect = new Rectangle2D(150,0,150,150);
    private Rectangle2D brickRect = new Rectangle2D(300,0,150,150);
    private Rectangle2D paperRect = new Rectangle2D(450,0,150,150);
    private Rectangle2D glassRect = new Rectangle2D(600,0,150,150);
    private Rectangle2D goldRect = new Rectangle2D(750,0,150,150);

    private Rectangle2D peaceRect = new Rectangle2D(1350,150,150,150);

    private Rectangle2D warRect = new Rectangle2D(1050,150,150,150);

    private Rectangle2D blue2Rect = new Rectangle2D(6*150,0,150,150);

    private Rectangle2D blue3Rect = new Rectangle2D(7*150,0,150,150);


    @Override
    public void init(GuiParser parser) {
        back.setOnAction(event -> {
            parser.chargeOverview();
        });

        parser.getApp().getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()){
                    case ESCAPE:
                        parser.chargeOverview();
                        break;
                }
            }
        });

        loadPile();
        loadWar(parser.getGame().getMilitaryTokens());

    }

    public void loadPile(){

        double width = 255;
        double height = 378;


        Image image = new Image(getClass().getResourceAsStream(
                "/com/isep/architects/wondersarchitects/img/cards.png"));

        sp1.getStyleClass().add("stack-pane");
        sp2.getStyleClass().add("stack-pane");
        sp3.getStyleClass().add("stack-pane");

        centerPile.setImage(image);
        leftPile.setImage(image);
        rightPile.setImage(image);

        centerPile.setViewport(new Rectangle2D(0,0,width,height));
        leftPile.setViewport(new Rectangle2D(0,0,width,height));
        rightPile.setViewport(new Rectangle2D(0,0,width,height));

    }

    public void loadWar(ArrayList<MilitaryToken> warTokens){
        Image img = new Image(getClass().getResourceAsStream(
                "/com/isep/architects/wondersarchitects/img/icons.png"));

        for (MilitaryToken token : warTokens){
            ImageView imageView = new ImageView();
            imageView.setImage(img);

            imageView.setFitWidth(35);
            imageView.setFitHeight(35);

            if(token.isSideWar()){
                imageView.setViewport(warRect);
            }else {
                imageView.setViewport(peaceRect);
            }
            hBoxWar.getChildren().add(imageView);

        }



    }

    public void loadRessources(Player player){
        Image img = new Image(getClass().getResourceAsStream(
                "/com/isep/architects/wondersarchitects/img/icons.png"));

        /*
        //tests

        player.getRessources().add(new GreyCards(RessourceType.WOOD));
        player.getRessources().add(new GreyCards(RessourceType.STONE));
        player.getRessources().add(new GreyCards(RessourceType.BRICK));
        player.getRessources().add(new GreyCards(RessourceType.PAPER));
        player.getRessources().add(new GreyCards(RessourceType.GLASS));

        player.getGold().add(new YellowCards());

        player.getBlue().add(new BlueCards(true));
        player.getBlue().add(new BlueCards(false));
        player.getBlue().add(new BlueCards(false));
        player.getBlue().add(new BlueCards(true));


         */


        for(GreyCards cards : player.getRessources()){
            ImageView imageView = new ImageView();
            imageView.setImage(img);

            imageView.setFitWidth(65);
            imageView.setFitHeight(65);
            if(cards.getType().equals(RessourceType.WOOD)){
                imageView.setViewport(woodRect);
            }else if(cards.getType().equals(RessourceType.STONE)){
                imageView.setViewport(stoneRect);
            }else if(cards.getType().equals(RessourceType.BRICK)){
                imageView.setViewport(brickRect);
            }else if(cards.getType().equals(RessourceType.PAPER)){
                imageView.setViewport(paperRect);
            }else if(cards.getType().equals(RessourceType.GLASS)){
                imageView.setViewport(glassRect);
            }
            hBoxRessource.getChildren().add(imageView);
        }


        for(YellowCards cards : player.getGold()){
            ImageView imageView = new ImageView();
            imageView.setImage(img);
            imageView.setViewport(goldRect);
            imageView.setFitWidth(65);
            imageView.setFitHeight(65);
            hBoxRessource.getChildren().add(imageView);
        }



        for(BlueCards cards : player.getBlue()){

            ImageView imageView = new ImageView();
            imageView.setImage(img);

            imageView.setFitWidth(45);
            imageView.setFitHeight(45);

            if(cards.isTwoPoint()){
                imageView.setViewport(blue2Rect);
            }else {
                imageView.setViewport(blue3Rect);
            }
            hBoxPoints.getChildren().add(imageView);

        }


        scrollP.getStyleClass().add("scroll-pane");

    }

    public void initWonder(Player player){
        Image image = null;
        double width = 0;
        double height = 0;

        double multiplier = 0.75;

        ImageView[] list = {wonderStage1,wonderStage2,wonderStage3,wonderStage4,wonderStage5};

        Wonder wonder = player.getWonder();
        if(wonder.getType().equals(WonderType.ALEXANDRIA)){
            image = new Image(getClass().getResourceAsStream(
                    "/com/isep/architects/wondersarchitects/img/wonder_alexandria.png"));
            width = 466;
            height = 631;
        }else if(wonder.getType().equals(WonderType.ARTEMIS)){
            image = new Image(getClass().getResourceAsStream(
                    "/com/isep/architects/wondersarchitects/img/wonder_artemis.png"));
            width = 467;
            height = 486;
        }
        else if(wonder.getType().equals(WonderType.BABYLON)){
            image = new Image(getClass().getResourceAsStream(
                    "/com/isep/architects/wondersarchitects/img/wonder_babylon.png"));
            width = 467;
            height = 420;
        }
        else if(wonder.getType().equals(WonderType.GIZEH)){
            image = new Image(getClass().getResourceAsStream(
                    "/com/isep/architects/wondersarchitects/img/wonder_gizeh.png"));
            width = 467;
            height = 422;
        }
        else if(wonder.getType().equals(WonderType.HALLICARNAS)){
            image = new Image(getClass().getResourceAsStream(
                    "/com/isep/architects/wondersarchitects/img/wonder_hallicarnas.png"));
            width = 465;
            height = 660;
        }
        else if(wonder.getType().equals(WonderType.RHODES)){
            image = new Image(getClass().getResourceAsStream(
                    "/com/isep/architects/wondersarchitects/img/wonder_rhodes.png"));
            width = 466;
            height = 515;
        }
        else if(wonder.getType().equals(WonderType.ZEUS)){
            image = new Image(getClass().getResourceAsStream(
                    "/com/isep/architects/wondersarchitects/img/wonder_zeus.png"));
            width = 467;
            height = 589;
        }

        for(int i = 1; i<6;i++){
            list[i-1].setImage(image);
            list[i-1].setFitWidth(width*multiplier);
            list[i-1].setFitHeight(height*multiplier);
            if(wonder.getStageFromNum(i).isBuilt()){
                list[i-1].setViewport(new Rectangle2D(width*i,height,width,height));
            }else{
                list[i-1].setViewport(new Rectangle2D(width*i,0,width,height));
            }
        }

        loadRessources(player);



    }

}
