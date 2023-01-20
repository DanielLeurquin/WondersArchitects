package com.isep.architects.wondersarchitects.controllers;

import com.isep.architects.wondersarchitects.GuiParser;
import com.isep.architects.wondersarchitects.Player;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class ScoreBoardController extends Controller{

    @FXML
    private GridPane gridPane;
    @Override
    public void init(GuiParser parser) {
        double width = gridPane.getPrefWidth();
        System.out.println(width);
        width -= gridPane.getColumnConstraints().get(0).getPrefWidth();
        int nbplayer = parser.getGame().getNumberPlayer();

        for(int i = 0;i<nbplayer;i++){
            Player player = parser.getGame().getPlayerList().get(i);
            ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(width/nbplayer);
            gridPane.getColumnConstraints().add(col);
            ArrayList<HBox> boxList = new ArrayList<>();
            for(int j = 0;j<7;j++){
                HBox box = new HBox();
                box.setAlignment(Pos.CENTER);
                boxList.add(box);
            }

            Label name = new Label(player.getName());
            boxList.get(0).getChildren().add(name);
            Label stagePoints = new Label(String.valueOf(player.getStagePoints()));
            boxList.get(1).getChildren().add(stagePoints);
            Label catPoints = new Label(String.valueOf(player.getCatPoints()));
            boxList.get(2).getChildren().add(catPoints);
            Label bluePoints = new Label(String.valueOf(player.getBluePoints()));
            boxList.get(3).getChildren().add(bluePoints);
            Label militaryPonts = new Label(String.valueOf(player.getMilitaryPoints()));
            boxList.get(4).getChildren().add(militaryPonts);
            Label progressPoints = new Label(String.valueOf(player.getProgressPoints()));
            boxList.get(5).getChildren().add(progressPoints);

            int total = player.getStagePoints() + player.getCatPoints() + player.getBluePoints() +
                    player.getMilitaryPoints() + player.getProgressPoints();
            Label totalPoints = new Label(String.valueOf(total));
            boxList.get(6).getChildren().add(totalPoints);
            for(int j = 0;j<7;j++){
                gridPane.add(boxList.get(j),i+1,j);
            }

        }




    }
}
