package com.isep.architects.wondersarchitects.controllers;

import com.isep.architects.wondersarchitects.Animation.WonderStageAnimation;
import com.isep.architects.wondersarchitects.GuiParser;
import com.isep.architects.wondersarchitects.Player;
import com.isep.architects.wondersarchitects.cards.*;
import com.isep.architects.wondersarchitects.pile.Pile;
import com.isep.architects.wondersarchitects.pile.SidePile;
import com.isep.architects.wondersarchitects.tokens.TokenTypes;
import com.isep.architects.wondersarchitects.wonders.Wonder;
import com.isep.architects.wondersarchitects.wonders.WonderStage;
import com.isep.architects.wondersarchitects.wonders.WonderType;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;

public class WonderController extends Controller{

    @FXML
    private Button back, closeButton;
    @FXML
    private ImageView wonderStage1, wonderStage2, wonderStage3, wonderStage4, wonderStage5;
    @FXML
    private ImageView centerPileView, leftPileView, rightPileView;
    @FXML
    private Label label;
    @FXML
    private StackPane sp1,sp2,sp3;
    @FXML
    private HBox hBoxRessource, hBoxProgress, hBoxMilitary, hBoxWar , hBoxPoints;
    @FXML
    private ScrollPane scrollP;
    @FXML
    private VBox vBoxLabel, progressOverlay;

    @FXML
    private ImageView pt1,pt2,pt3,pt4;

    @FXML
    private AnchorPane ap, overlayAp;
    private GuiParser parser;
    private Player player;
    private Pile centerPile,leftPile,rightPile;
    private double multiplier;



    //symbol Rect
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
    private Rectangle2D militaryHornRect = new Rectangle2D(0*150,1*150,150,150);
    private Rectangle2D militaryRect = new Rectangle2D(1*150,1*150,150,150);
    private Rectangle2D wheelRect = new Rectangle2D(4*150,1*150,150,150);
    private Rectangle2D compassRect = new Rectangle2D(3*150,1*150,150,150);
    private Rectangle2D tabletRect = new Rectangle2D(5*150,1*150,150,150);


    //Card Rect

    private Rectangle2D woodCard = new Rectangle2D(255*0,378*1,255,378);
    private Rectangle2D stoneCard = new Rectangle2D(255*1,378*1,255,378);
    private Rectangle2D brickCard = new Rectangle2D(255*2,378*1,255,378);
    private Rectangle2D paperCard = new Rectangle2D(255*3,378*1,255,378);
    private Rectangle2D glassCard = new Rectangle2D(255*4,378*1,255,378);
    private Rectangle2D goldCard = new Rectangle2D(255*5,378*1,255,378);
    private Rectangle2D blue2Card = new Rectangle2D(255*6,378*1,255,378);
    private Rectangle2D blue3Card = new Rectangle2D(255*7,378*1,255,378);
    private Rectangle2D war0Card = new Rectangle2D(255*0,378*2,255,378);
    private Rectangle2D war1Card = new Rectangle2D(255*1,378*2,255,378);
    private Rectangle2D war2Card = new Rectangle2D(255*2,378*2,255,378);
    private Rectangle2D wheelCard = new Rectangle2D(255*4,378*2,255,378);
    private Rectangle2D tabletCard = new Rectangle2D(255*5,378*2,255,378);
    private Rectangle2D compassCard = new Rectangle2D(255*3,378*2,255,378);

    private WonderStageAnimation anim;


    public void loadAll(){
        loadRessources(player);
        loadWar(parser.getGame().getMilitaryTokens());
        loadWonder(player);
        loadPile();
        loadConflict();
        loadProgressToken();
        Pile[] piles = {centerPile,leftPile,rightPile};
        ImageView[] views = {centerPileView,leftPileView,rightPileView};
        for(int i = 0; i<piles.length;i++){
            loadCardImage(piles[i],views[i]);
        }

    }

    @Override
    public void init(GuiParser parser) {
        this.parser = parser;
        this.multiplier = parser.getGame().getMultiplier();
        back.setOnAction(event -> {
            parser.chargeOverview();
        });

        closeButton.setOnAction(event -> {
            ap.setDisable(false);
            overlayAp.setVisible(false);
            overlayAp.setDisable(true);
            progressOverlay.setVisible(false);
            progressOverlay.setDisable(true);
        });

        parser.getApp().getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {

                switch (keyEvent.getCode()){

                    case ESCAPE:
                        parser.chargeOverview();
                        break;
                    case LEFT:
                        loadPrevious();
                        break;
                    case RIGHT:
                        loadNext();
                        break;
                }
            }
        });

    }

    public void loadPile(){

        double width = 255;
        double height = 378;

        Image image = new Image(getClass().getResourceAsStream(
                "/com/isep/architects/wondersarchitects/img/cards.png"));

        sp1.getStyleClass().add("stack-pane");
        sp2.getStyleClass().add("stack-pane");
        sp3.getStyleClass().add("stack-pane");

        this.leftPile = this.player.getWonder().getPile();
        this.centerPile = this.parser.getGame().getCenterPile();
        int index = this.parser.getGame().getPlayerList().indexOf(this.player);
        if(index!=this.parser.getGame().getPlayerList().size()-1){
            index++;
        }else {
            index = 0;
        }

        this.rightPile = this.parser.getGame().getPlayerList().get(index).getWonder().getPile();

        centerPileView.setImage(image);
        leftPileView.setImage(image);
        rightPileView.setImage(image);

        centerPileView.setViewport(new Rectangle2D(0,0,width,height));


        loadCardImage(this.leftPile, leftPileView);
        loadCardImage(this.rightPile, rightPileView);

    }

    public void drawCard(){

        StackPane[] list = {sp1,sp2,sp3};
        Pile[] piles = {centerPile,leftPile,rightPile};
        ImageView[] views = {centerPileView,leftPileView,rightPileView};

        for(int i = 0; i<list.length;i++){

            int finalI = i;
            list[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {

                    if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                        if(player.equals(parser.getGame().getPlayerturn())){
                            CardsTypes card = piles[finalI].drawCard(player);
                            if(card.equals(CardsTypes.RED1) || card.equals(CardsTypes.RED2)){
                                parser.getGame().evaluateWar(card);
                                loadWar(parser.getGame().getMilitaryTokens());
                            }
                            parser.getGame().endTurn();
                            loadRessources(player);
                            loadPile();
                            loadConflict();


                        }

                    }
                }
            });

        }



    }

    public void loadWar(ArrayList<TokenTypes> warTokens){
        Image img = new Image(getClass().getResourceAsStream(
                "/com/isep/architects/wondersarchitects/img/icons.png"));

        hBoxWar.getChildren().clear();

        for (TokenTypes token : warTokens){
            ImageView imageView = new ImageView();
            imageView.setImage(img);

            imageView.setFitWidth(35);
            imageView.setFitHeight(35);

            if(token.equals(TokenTypes.MILITARY)){
                imageView.setViewport(warRect);
            }else {
                imageView.setViewport(peaceRect);
            }
            hBoxWar.getChildren().add(imageView);

        }



    }

    public void loadProgressToken(){
        HashMap<TokenTypes,int[]> coord = new HashMap<>();
        coord.put(TokenTypes.SCIENCE, new int[]{0, 1});
        coord.put(TokenTypes.POLITICS, new int[]{0, 2});
        coord.put(TokenTypes.URBANISM, new int[]{0, 3});
        coord.put(TokenTypes.JEWELLERY, new int[]{1, 0});
        coord.put(TokenTypes.ECONOMY, new int[]{1, 1});
        coord.put(TokenTypes.DECOR, new int[]{1, 2});
        coord.put(TokenTypes.ARCHITECTURE, new int[]{1, 3});
        coord.put(TokenTypes.STRATEGY, new int[]{2, 0});
        coord.put(TokenTypes.TACTICS, new int[]{2, 1});
        coord.put(TokenTypes.PROPAGANDA, new int[]{2, 2});
        coord.put(TokenTypes.EDUCATION, new int[]{3, 0});
        coord.put(TokenTypes.CULTURE, new int[]{3, 1});
        coord.put(TokenTypes.ENGINEERING, new int[]{3, 2});
        coord.put(TokenTypes.CRAFTS, new int[]{3, 3});

        Image image = new Image(getClass().getResourceAsStream(
                "/com/isep/architects/wondersarchitects/img/progress.png"));
        ArrayList<TokenTypes> progress = parser.getGame().getProgress();

        pt1.setImage(image);
        pt2.setImage(image);
        pt3.setImage(image);
        pt4.setImage(image);

        pt1.setViewport(new Rectangle2D(208*coord.get(progress.get(0))[0],
                213*coord.get(progress.get(0))[1],208,213));
        pt2.setViewport(new Rectangle2D(208*coord.get(progress.get(1))[0],
                213*coord.get(progress.get(1))[1],208,213));
        pt3.setViewport(new Rectangle2D(208*coord.get(progress.get(2))[0],
                213*coord.get(progress.get(2))[1],208,213));

        pt4.setViewport(new Rectangle2D(0,0,208,213));
    }

    public void loadRessources(Player player){
        Image img = new Image(getClass().getResourceAsStream(
                "/com/isep/architects/wondersarchitects/img/icons.png"));

        hBoxProgress.getChildren().clear();
        hBoxRessource.getChildren().clear();
        hBoxPoints.getChildren().clear();
        hBoxMilitary.getChildren().clear();
        Rectangle2D[] listRect = {woodRect,stoneRect,brickRect,paperRect,glassRect,goldRect,militaryRect,militaryHornRect,
        militaryHornRect,blue2Rect,blue3Rect,wheelRect,tabletRect,compassRect};

        for(CardsTypes card : player.getCards()){
            ImageView imageView = new ImageView();
            imageView.setImage(img);

            imageView.setFitWidth(50);
            imageView.setFitHeight(50);
            imageView.setViewport(listRect[card.indice()]);
            if(card.indice()<6){
                hBoxRessource.getChildren().add(imageView);
            }else if(card.indice()<9){
                hBoxMilitary.getChildren().add(imageView);
            }else if(card.indice()<11){
                hBoxPoints.getChildren().add(imageView);
            }else {
                hBoxProgress.getChildren().add(imageView);
            }

        }

        scrollP.getStyleClass().add("scroll-pane");
    }

    public void loadConflict(){
        label.setText(String.valueOf(player.getConflict().size()));
        label.getStyleClass().add("num-lab");
    }

    public void initWonder(Player player){
        this.player = player;
        loadAll();
        drawCard();


    }

    public void loadWonder(Player player){
        Image image = null;
        double width = 0;
        double height = 0;



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
            height = 518;
        }
        else if(wonder.getType().equals(WonderType.ZEUS)){
            image = new Image(getClass().getResourceAsStream(
                    "/com/isep/architects/wondersarchitects/img/wonder_zeus.png"));
            width = 467;
            height = 589;
        }

        for(int i = 1; i<6;i++){
            list[i-1].setImage(image);
            list[i-1].setFitWidth(width*this.multiplier);
            list[i-1].setFitHeight(height*multiplier);

            if(wonder.getStageFromNum(i).isBuilt()){
                list[i-1].setViewport(new Rectangle2D(width*i,height,width,height));
            }else{
                list[i-1].setViewport(new Rectangle2D(width*i,0,width,height));
            }
        }
    }

    public void loadCardImage(Pile pile, ImageView imgView){
        if(!(pile instanceof SidePile)){
            return;
        }
        Rectangle2D listRect[] = {woodCard,stoneCard,brickCard,paperCard,glassCard,goldCard,war0Card,war1Card,war2Card,
        blue2Card,blue3Card,wheelCard,tabletCard,compassCard};

        imgView.setViewport(listRect[pile.getCards().get(0).indice()]);

    }

    public void loadNext(){
        ArrayList<Player> list = this.parser.getGame().getPlayerList();
        int index = list.indexOf(this.player);
        if(index!=list.size()-1){
            index++;
        }else {
            index = 0;
        }
        parser.loadPlayerScene(list.get(index));

    }

    public void loadPrevious(){
        ArrayList<Player> list = this.parser.getGame().getPlayerList();
        int index = list.indexOf(this.player);
        if(index!=0){
            index--;
        }else {
            index = list.size()-1;
        }
        parser.loadPlayerScene(list.get(index));

    }

    public void startAnimation(WonderStage stage){

        ImageView[] list = {wonderStage1, wonderStage2, wonderStage3, wonderStage4, wonderStage5};
        int i = stage.getStagenum()-1;

        double width = list[i].getFitWidth()/multiplier;

        double height = list[i].getFitHeight()/multiplier;

        Rectangle2D rect = new Rectangle2D(width*(i+1),height,width,height);
        anim = new WonderStageAnimation(list[i],rect,stage,multiplier);
        anim.play();

    }

}
