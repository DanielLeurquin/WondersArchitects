package com.isep.architects.wondersarchitects.controllers;

import com.isep.architects.wondersarchitects.GuiParser;
import com.isep.architects.wondersarchitects.Player;
import com.isep.architects.wondersarchitects.wonders.Wonder;
import com.isep.architects.wondersarchitects.wonders.WonderType;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WonderController extends Controller{

    @FXML
    private Button back;

    @FXML
    private ImageView wonderStage1, wonderStage2, wonderStage3, wonderStage4, wonderStage5;


    @Override
    public void init(GuiParser parser) {
        back.setOnAction(event -> {
            parser.chargeOverview();
        });
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



    }

}
