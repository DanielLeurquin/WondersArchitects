package com.isep.architects.wondersarchitects.controllers;

import com.isep.architects.wondersarchitects.Animation.WonderStageAnimation;
import com.isep.architects.wondersarchitects.GuiParser;
import com.isep.architects.wondersarchitects.Player;
import com.isep.architects.wondersarchitects.cards.*;
import com.isep.architects.wondersarchitects.pile.Pile;
import com.isep.architects.wondersarchitects.tokens.TokenTypes;
import com.isep.architects.wondersarchitects.wonders.Wonder;
import com.isep.architects.wondersarchitects.wonders.WonderStage;
import com.isep.architects.wondersarchitects.wonders.WonderType;
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

import java.util.ArrayList;
import java.util.HashMap;

public class WonderController extends Controller{

    @FXML
    private Button back;
    @FXML
    private ImageView wonderStage1, wonderStage2, wonderStage3, wonderStage4, wonderStage5;
    @FXML
    private ImageView centerPileView, leftPileView, rightPileView;
    @FXML
    private Label label,titleLab;
    @FXML
    private StackPane sp1,sp2,sp3, ptSp1,ptSp2,ptSp3,ptSp4, eyeSp,overlaySpl,overlaySpc,overlaySpr;
    @FXML
    private HBox hBoxRessource, hBoxProgress, hBoxMilitary, hBoxWar , hBoxPoints, hBoxToken;
    @FXML
    private ScrollPane scrollP;
    @FXML
    private VBox progressOverlay, overlayPile, alexOverlay;

    @FXML
    private ImageView pt1,pt2,pt3,pt4, progress1Img,progress2Img,progress3Img,progress4Img,
            catView, overlayPilel,overlayPilec,overlayPiler;

    @FXML
    private AnchorPane ap, overlayAp;
    private GuiParser parser;
    private Player player;
    private Pile centerPile,leftPile,rightPile;
    private double multiplier;

    private Image imageCard = new Image(getClass().getResourceAsStream(
            "/com/isep/architects/wondersarchitects/img/cards.png"));

    private Image imageIcons = new Image(getClass().getResourceAsStream(
            "/com/isep/architects/wondersarchitects/img/icons.png"));



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

    private ArrayList<TokenTypes> executedProgress = new ArrayList<>();

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
        back.setOnAction(event -> {
            if(!ap.isDisable()){
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


                    if(card.equals(CardsTypes.RED1) || card.equals(CardsTypes.RED2)){
                        parser.getGame().evaluateWar(card);
                        loadWar(parser.getGame().getMilitaryTokens());
                    }else if(card.equals(CardsTypes.BLUE2)){
                        catView.setVisible(true);
                        parser.getGame().catMove(player);
                    }
                    disableOverlay();
                    drawn = card;
                    checkFinish(card);
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
                        if (lastprogress.equals(TokenTypes.SCIENCE)) {
                            checkFinish(null);
                        }else {
                            checkFinish(drawn);
                        }

                    }

                }
            });
        }

        //key strokes
        parser.getApp().getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(!ap.isDisable()){
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

    public void loadPile(){

        double width = 255;
        double height = 378;

        Image image = imageCard;

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

        for(int i = 0; i<list.length;i++){

            int finalI = i;
            list[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {

                    if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                        if(player.equals(parser.getGame().getPlayerturn())){
                            CardsTypes card = piles[finalI].drawCard(player);
                            if(card.equals(CardsTypes.GOLD) &&
                                    player.getProgress().contains(TokenTypes.ECONOMY)){
                                player.getCards().add(CardsTypes.GOLD);
                            }

                            drawn = card;
                            if(card.equals(CardsTypes.RED1) || card.equals(CardsTypes.RED2)){
                                parser.getGame().evaluateWar(card);
                                loadWar(parser.getGame().getMilitaryTokens());
                            }else if(card.equals(CardsTypes.BLUE2)){
                                catView.setVisible(true);
                                parser.getGame().catMove(player);
                            }

                            checkFinish(card);



                        }

                    }
                }
            });

        }



    }

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

    public void loadRessources(Player player){

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
    }

    public void loadConflict(){
        label.setText(String.valueOf(player.getConflict().size()));
        label.getStyleClass().add("num-lab");
    }

    public void initWonder(Player player){
        this.player = player;
        loadAll();
        drawCard();
        catView.setVisible(player.isCat());
        buildAlexandriaOverlay();

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

    public void chooseProgress(){
        ap.setDisable(true);
        overlayAp.setDisable(false);
        overlayAp.setVisible(true);
        progressOverlay.setDisable(false);
        progressOverlay.setVisible(true);

        loadProgressToken(new ImageView[]{progress1Img,progress2Img,progress3Img,progress4Img});
    }

    public void chargeAlexOverlay(){
        ap.setDisable(true);
        overlayAp.setVisible(true);
        overlayAp.setDisable(false);
        alexOverlay.setVisible(true);
        alexOverlay.setDisable(false);
        int compteur = 0;
        for(Pile pile : alexMap.keySet()){
            ImageView img = alexMap.get(pile);

            if(compteur==(alexMap.size()-1) && !parser.getGame().getPlayerturn().isCat()){
                img.setViewport(new Rectangle2D(0,0,255,378));
            }else {
                loadCardImage(pile,img);
            }
            compteur++;
        }
    }

    public void checkFinish(CardsTypes card){
        boolean pileOver = false;

        Player playerturn = parser.getGame().getPlayerturn();

        if(playerturn.getProgress().contains(TokenTypes.ARCHITECTURE) &&
                !executedProgress.contains(TokenTypes.ARCHITECTURE)){
            archiCount+= parser.getGame().buildStage();
            if(archiCount>0 && alexOverlay.isDisable()){
                pileOver = true;
                titleLab.setText("ARCHITECTURE");
                archiCount--;
                executedProgress.add(TokenTypes.ARCHITECTURE);
            }
        }

        if(card!=null){
            for(TokenTypes token : playerturn.getProgress()){
                if(token.pileEffect(card) && !executedProgress.contains(token)){
                    pileOver = true;
                    titleLab.setText(token.toString());
                    executedProgress.add(token);
                    break;
                }
            }
        }


        if(playerturn.sameGreen() || playerturn.differentGreen()|| progressPower){
            chooseProgress();
            if(progressPower){
                progressPower = false;
            }


        }else if(pileOver){
            ap.setDisable(true);
            overlayAp.setDisable(false);
            overlayAp.setVisible(true);
            overlayPile.setDisable(false);
            overlayPile.setVisible(true);

            loadCardImage(leftPile,overlayPilel);
            loadCardImage(rightPile,overlayPiler);
            if(playerturn.isCat()){
                loadCardImage(centerPile,overlayPilec);
            }else {
                overlayPilec.setViewport(new Rectangle2D(0,0,255,378));
            }


        }else {
            parser.getGame().buildStage();
            parser.getGame().endTurn();
            loadRessources(player);
            loadPile();
            loadConflict();
            loadProgressToken(new ImageView[]{pt1,pt2,pt3,pt4});
            eyeSp.setDisable(true);
            eyeSp.setVisible(false);

        }



    }

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

    public void setProgressPower(boolean progressPower){
        this.progressPower = progressPower;
    }

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


                    if(card.equals(CardsTypes.RED1) || card.equals(CardsTypes.RED2)){
                        parser.getGame().evaluateWar(card);
                        loadWar(parser.getGame().getMilitaryTokens());
                    }else if(card.equals(CardsTypes.BLUE2)){
                        catView.setVisible(true);
                        parser.getGame().catMove(player);
                    }
                    disableOverlay();
                    drawn = card;
                    checkFinish(card);
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



}
