package com.isep.architects.wondersarchitects;

import com.isep.architects.wondersarchitects.cards.*;
import com.isep.architects.wondersarchitects.wonders.Wonder;
import com.isep.architects.wondersarchitects.wonders.WonderStage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Player {

    private String name;

    private Wonder wonder;

    private ArrayList<CardsTypes> cards = new ArrayList<>();

    public Player(String name){
        this.name = name;
    }

    public ArrayList<WonderStage> buildStage(){
        ArrayList<WonderStage> stageToBuild = new ArrayList<>();
        for(WonderStage stage : wonder.getStages()){
            if(!stage.isBuilt()){
                if(stage.getNeededNumBuild()[0] != 0){
                    boolean value = true;
                    for(int i = 0; i<stage.getNeededNumBuild().length;i++){
                        value = value && wonder.getStageFromNum(stage.getNeededNumBuild()[i]).isBuilt();
                    }
                    if(value){
                        if(checkCondition(stage)){
                            stageToBuild.add(stage);
                        };
                    }
                }else {
                    if(checkCondition(stage)){
                        stageToBuild.add(stage);
                    };
                }
            }


        }
        return stageToBuild;
    }

    public boolean checkCondition(WonderStage stage){
        if(stage.isSame()){
            return sameRessource(stage.getValue());
        }else {
            return differentRessource(stage.getValue());
        }
    }

    public boolean sameRessource(int value){

        value = value-countGold();


        CardsTypes type;

        if(Collections.frequency(cards,CardsTypes.WOOD)>=value){
            type = CardsTypes.WOOD;
        }else if(Collections.frequency(cards,CardsTypes.STONE)>=value){
            type = CardsTypes.STONE;
        }else if(Collections.frequency(cards,CardsTypes.BRICK)>=value){
            type = CardsTypes.BRICK;
        }else if(Collections.frequency(cards,CardsTypes.PAPER)>=value){
            type = CardsTypes.PAPER;
        }else if(Collections.frequency(cards,CardsTypes.GLASS)>=value){
            type = CardsTypes.GLASS;
        }else {
            if(value!=0){
                return false;
            }else {

                removeGold();
                return true;
            }

        }

        for(int i = 0; i<value;i++){
            cards.remove(type);

        }

        removeGold();
        return true;

    }

    public boolean differentRessource(int value) {
        value = value - countGold();


        List<CardsTypes> allowed = Arrays.asList(CardsTypes.WOOD, CardsTypes.STONE,
               CardsTypes.BRICK,CardsTypes.PAPER,CardsTypes.GLASS);

        ArrayList<CardsTypes> possessed = new ArrayList<CardsTypes>();
        for (CardsTypes card : cards) {
            if(allowed.contains(card) && !possessed.contains(card)){
                possessed.add(card);
            }
        }

        if(value == 0) {
            removeGold();
            return true;
        }

        if (possessed.size() >= value) {

            for (CardsTypes cards : possessed) {
                this.cards.remove(cards);
            }
            removeGold();
            return true;
        }else {
            return false;
        }

    }


    public String getName() {
        return name;
    }

    public void setWonder(Wonder wonder) {
        this.wonder = wonder;
    }

    public Wonder getWonder() {
        return wonder;
    }


    public ArrayList<CardsTypes> getCards() {
        return cards;
    }

    public int countGold(){
        return Collections.frequency(cards,CardsTypes.GOLD);
    }

    public void removeGold(){
        int lim = countGold();
        for(int i = 0;i<lim;i++){
            cards.remove(CardsTypes.GOLD);
        }
    }

}
