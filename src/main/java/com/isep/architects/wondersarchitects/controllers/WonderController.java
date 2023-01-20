package com.isep.architects.wondersarchitects.controllers;

import com.isep.architects.wondersarchitects.Animation.SpritePulseAnimation;
import com.isep.architects.wondersarchitects.Animation.WonderStageAnimation;
import com.isep.architects.wondersarchitects.GuiParser;
import com.isep.architects.wondersarchitects.Player;
import com.isep.architects.wondersarchitects.cards.*;
import com.isep.architects.wondersarchitects.pile.Pile;
import com.isep.architects.wondersarchitects.tokens.TokenTypes;
import com.isep.architects.wondersarchitects.wonders.Wonder;
import com.isep.architects.wondersarchitects.wonders.WonderStage;
import com.isep.architects.wondersarchitects.wonders.WonderType;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
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
import javafx.util.Duration;

import java.util.*;

public class WonderController extends Controller{

    @FXML
    private Button back, leftPileButton, rightPileButton;
    @FXML
    private ImageView wonderStage1, wonderStage2, wonderStage3, wonderStage4, wonderStage5;
    @FXML
    private ImageView centerPileView, leftPileView, rightPileView;
    @FXML
    private Label label,titleLab;
    @FXML
    private StackPane sp1,sp2,sp3, ptSp1,ptSp2,ptSp3,ptSp4, eyeSp,overlaySpl,overlaySpc,overlaySpr,
            haliOverSp1,haliOverSp2,haliOverSp3,haliOverSp4,haliOverSp5;
    @FXML
    private HBox hBoxRessource, hBoxProgress, hBoxMilitary, hBoxWar , hBoxPoints, hBoxToken, hBoxHaliPile;
    @FXML
    private ScrollPane scrollP;
    @FXML
    private VBox progressOverlay, overlayPile, alexOverlay, haliOverlay;


    @FXML
    private ImageView pt1,pt2,pt3,pt4, progress1Img,progress2Img,progress3Img,progress4Img,
            catView, overlayPilel,overlayPilec,overlayPiler,
            haliImg1,haliImg2,haliImg3,haliImg4,haliImg5;

    @FXML
    private AnchorPane ap, overlayAp, parentAp;
    private GuiParser parser;
    private Player player;
    private Pile centerPile,leftPile,rightPile;
    private double multiplier;

    private Image imageCard = new Image(getClass().getResourceAsStream(
            "/com/isep/architects/wondersarchitects/img/cards.png"));

    private Image imageIcons = new Image(getClass().getResourceAsStream(
            "/com/isep/architects/wondersarchitects/img/icons.png"));

    private boolean animation = false;



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

    private HashMap<TokenTypes,int[]> coordToken = new HashMap<>();

    private CardsTypes drawn = null;

    int archiCount = 0;

    private boolean progressPower = false;

    private boolean wonderPower = false;

    private ArrayList<TokenTypes> executedProgress = new ArrayList<>();

    private ArrayList<TokenTypes> pendingProgress = new ArrayList<>();

    private HashMap<Pile,ImageView> alexMap = new HashMap<>();

    public void loadAll(){
        loadRessources(player);
        loadWar(parser.getGame().getMilitaryTokens());
        loadWonder(player);
        loadPile();
        loadConflict();
        loadProgressToken(new ImageView[]{pt1,pt2,pt3,pt4});


    }

    @Override
    public void init(GuiParser parser) {
        this.parser = parser;
        this.multiplier = parser.getGame().getMultiplier();
        parentAp.getTransforms().setAll(parser.getApp().getScale());
        back.setOnAction(event -> {
            if(!ap.isDisable() && !animation){
                parser.chargeOverview();
            }

        });

        coordToken.put(TokenTypes.SCIENCE, new int[]{1, 0});
        coordToken.put(TokenTypes.POLITICS, new int[]{2, 0});
        coordToken.put(TokenTypes.URBANISM, new int[]{3, 0});
        coordToken.put(TokenTypes.JEWELLERY, new int[]{0, 1});
        coordToken.put(TokenTypes.ECONOMY, new int[]{1, 1});
        coordToken.put(TokenTypes.DECOR, new int[]{2, 1});
        coordToken.put(TokenTypes.ARCHITECTURE, new int[]{3, 1});
        coordToken.put(TokenTypes.STRATEGY, new int[]{0, 2});
        coordToken.put(TokenTypes.TACTICS, new int[]{1, 2});
        coordToken.put(TokenTypes.PROPAGANDA, new int[]{2, 2});
        coordToken.put(TokenTypes.EDUCATION, new int[]{0, 3});
        coordToken.put(TokenTypes.CULTURE, new int[]{1, 3});
        coordToken.put(TokenTypes.ENGINEERING, new int[]{2, 3});
        coordToken.put(TokenTypes.CRAFTS, new int[]{3, 3});

        //eye click
        eyeSp.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    loadCardImage(centerPile,centerPileView);
                }
            }
        });

        eyeSp.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                centerPileView.setViewport(new Rectangle2D(0,0,255,378));
            }
        });

        StackPane[] listSpPile = {overlaySpl,overlaySpc,overlaySpr};
        //pile overlay click
        for(int j = 0;j<listSpPile.length;j++){
            int finalJ = j;
            listSpPile[j].setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Pile[] listPile = {leftPile,centerPile,rightPile};
                    CardsTypes card = listPile[finalJ].drawCard(parser.getGame().getPlayerturn());



                    disableOverlay();
                    drawCard(card);

                }
            });
        }


        //draw progress token click
        StackPane[] listSpPt = {ptSp1,ptSp2,ptSp3,ptSp4};
        for(int i = 0; i<4;i++){
            int finalI = i;
            listSpPt[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                        player.getProgress().add(parser.getGame().getProgress().get(finalI));
                        parser.getGame().getProgress().remove(finalI);
                        disableOverlay();
                        TokenTypes lastprogress = player.getProgress().get(player.getProgress().size()-1);
                        if(lastprogress.equals(TokenTypes.TACTICS)){
                            player.getCards().add(CardsTypes.RED0);
                            player.getCards().add(CardsTypes.RED0);
                        }


                        executedProgress.add(lastprogress);
                        checkFinish(drawn);


                    }

                }
            });
        }

        //hali overlay Button action

        leftPileButton.setOnAction(event -> {
            Pile pile = parser.getGame().getPlayerturn().getWonder().getPile();
            loadHaliOverlayCards(pile);
            leftPileButton.setDisable(true);
            rightPileButton.setDisable(true);
        });

        rightPileButton.setOnAction(event -> {
            Pile pile = parser.getGame().getPlayerList().get(1).getWonder().getPile();
            loadHaliOverlayCards(pile);
            leftPileButton.setDisable(true);
            rightPileButton.setDisable(true);
        });



        //key strokes
        parser.getApp().getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(!ap.isDisable() && !animation){
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

            }
        });

    }

    public void loadHaliOverlayCards(Pile pile){
        hBoxHaliPile.setDisable(false);
        StackPane[] spList = {haliOverSp1,haliOverSp2,haliOverSp3,haliOverSp4,haliOverSp5};
        ImageView[]  imgList = {haliImg1,haliImg2,haliImg3,haliImg4,haliImg5};
        for(int i =0; i<5;i++){
            imgList[i].setImage(imageCard);
            loadCardImage(pile,imgList[i],i);
            int finalI = i;
            spList[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    leftPileButton.setDisable(false);
                    rightPileButton.setDisable(false);
                    Player player = parser.getGame().getPlayerturn();
                    CardsTypes card = pile.drawCard(player,finalI);

                    disableOverlay();

                    hBoxHaliPile.setDisable(true);
                    Collections.shuffle(pile.getCards());
                    drawCard(card);


                }
            });

        }


    }

    //load the three pile of the scene
    public void loadPile(){
        //set image to imageview
        double width = 255;
        double height = 378;

        Image image = imageCard;

        sp1.getStyleClass().add("stack-pane");
        sp2.getStyleClass().add("stack-pane");
        sp3.getStyleClass().add("stack-pane");

        this.leftPile = this.player.getWonder().getPile();
        if(leftPile.getCards().size()==0){
            this.player.getWonder().fillPile();
        }

        this.centerPile = this.parser.getGame().getCenterPile();
        if(this.centerPile.getCards().size()==0){
            this.parser.getGame().fillCenterPile();
        }
        int index = this.parser.getGame().getPlayerList().indexOf(this.player);
        if(index!=this.parser.getGame().getPlayerList().size()-1){
            index++;
        }else {
            index = 0;
        }

        this.rightPile = this.parser.getGame().getPlayerList().get(index).getWonder().getPile();
        if(this.rightPile.getCards().size()==0){
            this.parser.getGame().getPlayerList().get(index).getWonder().fillPile();
        }

        centerPileView.setImage(image);
        leftPileView.setImage(image);
        rightPileView.setImage(image);

        centerPileView.setViewport(new Rectangle2D(0,0,width,height));



        loadCardImage(this.leftPile, leftPileView);
        loadCardImage(this.rightPile, rightPileView);

    }

    //init Stack pane pile commands
    public void initPileStackPane(){

        StackPane[] list = {sp1,sp2,sp3};
        Pile[] piles = {centerPile,leftPile,rightPile};

        //initialize command for each Stack Pane pile click
        for(int i = 0; i<list.length;i++){

            int finalI = i;
            list[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {

                    if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                        if(player.equals(parser.getGame().getPlayerturn()) && !animation){
                            CardsTypes card = piles[finalI].drawCard(player);
                            drawCard(card);

                        }

                    }
                }
            });

        }



    }


    public void drawCard(CardsTypes card){
        if(card.equals(CardsTypes.GOLD) &&
                player.getProgress().contains(TokenTypes.ECONOMY) &&
                !executedProgress.contains(TokenTypes.ECONOMY)){
            //handle economy token
            player.getCards().add(CardsTypes.GOLD);
            executedProgress.add(TokenTypes.ECONOMY);
        }
        ImageView img = loadRessources(player);
        loadPile();
        SpritePulseAnimation anim = new SpritePulseAnimation(img,1.3);


        anim.setOnFinished(event -> {


            if(card.equals(CardsTypes.RED1) || card.equals(CardsTypes.RED2)){
                //handle war
                parser.getGame().evaluateWar(card);
                loadWar(parser.getGame().getMilitaryTokens());
            }else if(card.equals(CardsTypes.BLUE2)){
                //handle cat
                catView.setVisible(true);
                parser.getGame().catMove(player);
            }
            animation = false;
            drawn = card;

            checkFinish(card);


        });
        animation = true;
        anim.play();

    }

    //Create images for war token on the right side (peace/war)
    public void loadWar(ArrayList<TokenTypes> warTokens){
        Image img = imageIcons;

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

    //load images for progress token
    public void loadProgressToken(ImageView[] imgList){

        Image image = new Image(getClass().getResourceAsStream(
                "/com/isep/architects/wondersarchitects/img/progress.png"));
        ArrayList<TokenTypes> progress = parser.getGame().getProgress();

        for (int i = 0; i<imgList.length;i++) {
            imgList[i].setImage(image);
            imgList[i].setViewport(new Rectangle2D(208*coordToken.get(progress.get(i))[0],
                    213*coordToken.get(progress.get(i))[1],208,213));
        }

        imgList[imgList.length-1].setViewport(new Rectangle2D(0,0,208,213));
    }

    //load all the cards and progress token owned by a player
    public ImageView loadRessources(Player player){
        //enable the eye if the player has the cat
        ImageView lastRessource = null;
        if(parser.getGame().getPlayerturn().equals(player)){
            if(player.isCat()){
                eyeSp.setVisible(true);
                eyeSp.setDisable(false);
            }
        }

        Image img = imageIcons;

        hBoxProgress.getChildren().clear();
        hBoxRessource.getChildren().clear();
        hBoxPoints.getChildren().clear();
        hBoxMilitary.getChildren().clear();
        hBoxToken.getChildren().clear();
        Rectangle2D[] listRect = {woodRect,stoneRect,brickRect,paperRect,glassRect,goldRect,militaryRect,militaryHornRect,
        militaryHornRect,blue2Rect,blue3Rect,wheelRect,tabletRect,compassRect};

        //load all the cards et put them in the right Hbox
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
            lastRessource = imageView;

        }

        //load progress token owed by the player to the right Hbox
        img = new Image(getClass().getResourceAsStream(
                "/com/isep/architects/wondersarchitects/img/progress.png"));
        for(TokenTypes token : player.getProgress()){
            ImageView imageView = new ImageView();
            imageView.setImage(img);
            imageView.setFitWidth(50);
            imageView.setFitHeight(50);
            imageView.setViewport(new Rectangle2D(208*coordToken.get(token)[0],
                    213*coordToken.get(token)[1],208,213));
            hBoxToken.getChildren().add(imageView);

        }


        scrollP.getStyleClass().add("scroll-pane");
        return lastRessource;
    }

    //load number of conflict victory
    public void loadConflict(){
        label.setText(String.valueOf(player.getConflict().size()));
        label.getStyleClass().add("num-lab");
    }

    //first load
    public void initWonder(Player player){
        this.player = player;
        loadAll();
        initPileStackPane();
        catView.setVisible(player.isCat());
        buildAlexandriaOverlay();

    }

    //load the right wonder and set the right width and height for each sprite
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

    //load the right image to an Imageview given the pile
    public void loadCardImage(Pile pile, ImageView imgView){
        Rectangle2D listRect[] = {woodCard,stoneCard,brickCard,paperCard,glassCard,goldCard,war0Card,war1Card,war2Card,
        blue2Card,blue3Card,wheelCard,tabletCard,compassCard};

        imgView.setViewport(listRect[pile.getCards().get(0).indice()]);

    }

    //overload with the index
    public void loadCardImage(Pile pile, ImageView imgView, int index){
        Rectangle2D listRect[] = {woodCard,stoneCard,brickCard,paperCard,glassCard,goldCard,war0Card,war1Card,war2Card,
                blue2Card,blue3Card,wheelCard,tabletCard,compassCard};
        imgView.setViewport(listRect[pile.getCards().get(index).indice()]);

    }

    //load next player scene
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

    //load previous player scene
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

    //enable the overlay for a progress token choice
    public void chooseProgress(){
        ap.setDisable(true);
        overlayAp.setDisable(false);
        overlayAp.setVisible(true);
        progressOverlay.setDisable(false);
        progressOverlay.setVisible(true);

        loadProgressToken(new ImageView[]{progress1Img,progress2Img,progress3Img,progress4Img});
    }

    public void loadHaliOverlay(){
        ap.setDisable(true);
        overlayAp.setDisable(false);
        overlayAp.setVisible(true);
        haliOverlay.setDisable(false);
        haliOverlay.setVisible(true);
    }

    //enable Alexandria power overlay
    public void chargeAlexOverlay(){
        ap.setDisable(true);
        overlayAp.setVisible(true);
        overlayAp.setDisable(false);
        alexOverlay.setVisible(true);
        alexOverlay.setDisable(false);
        ArrayList<Pile> piles = new ArrayList<>();
        for(Player p : parser.getGame().getPlayerList()){
            piles.add(p.getWonder().getPile());
        }
        piles.add(parser.getGame().getCenterPile());

        //load the right image to each pile
        int compteur = 0;
        for(Pile pile : piles){
            ImageView img = alexMap.get(pile);


            if(compteur==(alexMap.size()-1) && !parser.getGame().getPlayerturn().isCat()){
                img.setViewport(new Rectangle2D(0,0,255,378));
            }else {
                loadCardImage(pile,img);
            }
            compteur++;
        }
    }


    //check if all the progress actions and wonders power has been executed before finishing turn
    public void checkFinish(CardsTypes card){
        Player player = parser.getGame().getPlayerturn();
        parser.getGame().buildStage();
        //add architecture to the pending list and execute the wonder effect
        if(parser.getGame().getStageToBuild().size()>0 &&
                !executedProgress.contains(TokenTypes.ARCHITECTURE)
                && player.getProgress().contains(TokenTypes.ARCHITECTURE)){
            pendingProgress.add(TokenTypes.ARCHITECTURE);
        }

        //if progress has to be executed and has not already been, add it to queue
        if(card==null) {
            card = drawn;
        }
        for(TokenTypes token : player.getProgress()){
            if(token.pileEffect(card) && !executedProgress.contains(token) &&
                    !pendingProgress.contains(token)){
                pendingProgress.add(token);
            }
        }


        if(player.sameGreen() || player.differentGreen()|| progressPower){
            chooseProgress();

            if(progressPower){
                progressPower = false;
            }


        }else if(pendingProgress.size()>0 && !ap.isDisable()){
            if(parser.getGame().getStageToBuild().size()!=0){
                return;
            }
            TokenTypes progress = pendingProgress.get(0);
            pendingProgress.remove(progress);
            executedProgress.add(progress);
            titleLab.setText(progress.toString());

            ap.setDisable(true);
            overlayAp.setDisable(false);
            overlayAp.setVisible(true);
            overlayPile.setDisable(false);
            overlayPile.setVisible(true);

            loadCardImage(leftPile,overlayPilel);
            loadCardImage(rightPile,overlayPiler);
            if(player.isCat()){
                loadCardImage(centerPile,overlayPilec);
            }else {
                overlayPilec.setViewport(new Rectangle2D(0,0,255,378));
            }

        }else if(!ap.isDisable() && parser.getGame().getStageToBuild().size()==0){
            if(!wonderPower){
                loadRessources(player);
                loadPile();
                loadConflict();
                loadProgressToken(new ImageView[]{pt1,pt2,pt3,pt4});
                eyeSp.setDisable(true);
                eyeSp.setVisible(false);
                parser.getGame().endTurn();
                Timeline tl = new Timeline(new KeyFrame(Duration.millis(400),event -> {
                    if(!parser.getGame().getPlayerturn().finish()){
                        parser.loadPlayerScene(parser.getGame().getPlayerturn());
                    }

                }));

                tl.play();
            }else {
                wonderPower = false;
            }

        }

    }


    //disable all overlays
    public void disableOverlay(){
        ap.setDisable(false);
        overlayAp.setVisible(false);
        overlayAp.setDisable(true);
        progressOverlay.setVisible(false);
        progressOverlay.setDisable(true);
        overlayPile.setVisible(false);
        overlayPile.setDisable(true);
        alexOverlay.setVisible(false);
        alexOverlay.setDisable(true);
        haliOverlay.setVisible(false);
        haliOverlay.setDisable(true);

    }


    //start the wonder turning animation
    public WonderStageAnimation startAnimation(WonderStage stage){

        ImageView[] list = {wonderStage1, wonderStage2, wonderStage3, wonderStage4, wonderStage5};
        int i = stage.getStagenum()-1;

        double width = list[i].getFitWidth()/multiplier;

        double height = list[i].getFitHeight()/multiplier;

        Rectangle2D rect = new Rectangle2D(width*(i+1),height,width,height);
        anim = new WonderStageAnimation(list[i],rect,stage,multiplier);
        //anim.play();
        return anim;

    }


    public void setProgressPower(boolean progressPower){
        this.progressPower = progressPower;
    }

    public void setWonderPower(boolean wonderPower){
        this.wonderPower = wonderPower;
    }

    public boolean isWonderPower(){
        return wonderPower;
    }

    //build the overlay for alexandria power depending on the number of players
    public void buildAlexandriaOverlay(){
        int numberPlayer = parser.getGame().getNumberPlayer();

        HBox box = new HBox();
        box.setAlignment(Pos.CENTER);
        box.setSpacing(15);

        ArrayList<String> nomList = new ArrayList<>();
        ArrayList<Pile> pileList = new ArrayList<>();
        for(Player p : parser.getGame().getPlayerList()){
            nomList.add(p.getWonder().getType().toString());
            pileList.add(p.getWonder().getPile());
        }

        nomList.add("CENTER");
        pileList.add(parser.getGame().getCenterPile());

        for(int j = 0; j<(numberPlayer+1);j++){
            if(j==4){
                alexOverlay.getChildren().add(box);
                box = new HBox();
                box.setAlignment(Pos.CENTER);
                box.setSpacing(15);
            }
            VBox vbox = new VBox();
            vbox.setAlignment(Pos.CENTER);
            Label label = new Label(nomList.get(j));
            StackPane sp = new StackPane();
            sp.getStyleClass().add("stack-pane");
            sp.setPrefWidth(100);
            sp.setMinHeight(148);
            int finalJ = j;
            sp.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {

                    CardsTypes card = pileList.get(finalJ).drawCard(parser.getGame().getPlayerturn());
                    disableOverlay();

                    drawCard(card);

                }
            });
            ImageView img = new ImageView();
            img.setImage(imageCard);
            img.setFitWidth(100);
            img.setFitHeight(142);
            alexMap.put(pileList.get(j),img);

            sp.getChildren().add(img);
            vbox.getChildren().add(label);
            vbox.getChildren().add(sp);
            box.getChildren().add(vbox);

        }
        alexOverlay.getChildren().add(box);

    }

    public void setAnimation(boolean animation){
        this.animation = animation;
    }



}
