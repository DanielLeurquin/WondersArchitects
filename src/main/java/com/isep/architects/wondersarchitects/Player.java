package com.isep.architects.wondersarchitects;

import com.isep.architects.wondersarchitects.cards.*;
import com.isep.architects.wondersarchitects.wonders.Wonder;
import com.isep.architects.wondersarchitects.wonders.WonderStage;

import java.util.ArrayList;

public class Player {

    private String name;

    private Wonder wonder;

    private ArrayList<GreyCards> ressources = new ArrayList<GreyCards>();

    private ArrayList<YellowCards> gold = new ArrayList<YellowCards>();

    private ArrayList<BlueCards> blue = new ArrayList<>();

    private ArrayList<RedCards> red = new ArrayList<>();

    private ArrayList<GreenCards> green = new ArrayList<>();

    public Player(String name){
        this.name = name;
    }

    public void buildStage(){
        for(WonderStage stage : wonder.getStages()){
            if(stage.getNeededNumBuild()[0] != 0){
                boolean value = true;
                for(int i = 0; i<stage.getNeededNumBuild().length;i++){
                    value = value && wonder.getStageFromNum(stage.getNeededNumBuild()[i]).isBuilt();
                }
                if(value){
                    checkCondition(stage);
                }
            }else {
                checkCondition(stage);
            }

        }

    }

    public void checkCondition(WonderStage stage){
        if(stage.isSame()){
            if(sameRessource(stage.getValue())){
                stage.setBuilt(true);
            }
        }else {
            if(differentRessource(stage.getValue())){
                stage.setBuilt(true);
            }
        }
    }

    public boolean sameRessource(int value){
        ArrayList<GreyCards> wood = new ArrayList<>();
        ArrayList<GreyCards> stone = new ArrayList<>();
        ArrayList<GreyCards> brick = new ArrayList<>();
        ArrayList<GreyCards> paper = new ArrayList<>();
        ArrayList<GreyCards> glass = new ArrayList<>();
        ArrayList<GreyCards> list = new ArrayList<>();

        for(GreyCards card : this.ressources){
            if(card.getType().equals(RessourceType.WOOD)){
                wood.add(card);
            }else if(card.getType().equals(RessourceType.STONE)){
                stone.add(card);
            }else if(card.getType().equals(RessourceType.BRICK)){
                brick.add(card);
            }else if(card.getType().equals(RessourceType.PAPER)){
                paper.add(card);
            }else if(card.getType().equals(RessourceType.GLASS)){
                glass.add(card);
            }
        }
        if(wood.size()>=value){
            list = wood;
        }else if(stone.size()>= value){
            list = stone;
        }else if(brick.size()>= value){
            list = brick;
        }else if(paper.size()>= value){
            list = paper;
        }else if(glass.size()>= value){
            list = glass;
        }


        if(list.size()>0){
            for(GreyCards card : list){
                this.ressources.remove(card);
            }
            return true;
        }else {
            return false;
        }



    }

    public boolean differentRessource(int value){
        ArrayList<GreyCards> list = new ArrayList<GreyCards>();
        ArrayList<RessourceType> listType = new ArrayList<RessourceType>();
        for(GreyCards card : this.ressources){
            if(!listType.contains(card.getType())){
                listType.add(card.getType());
                list.add(card);
            }
        }
        if(list.size()>=value){
            for(GreyCards cards : list){
                this.ressources.remove(cards);
            }
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

    public ArrayList<GreyCards> getRessources() {
        return ressources;
    }

    public ArrayList<YellowCards> getGold() {
        return gold;
    }

    public ArrayList<BlueCards> getBlue() {
        return blue;
    }

    public ArrayList<GreenCards> getGreen() {
        return green;
    }

    public ArrayList<RedCards> getRed() {
        return red;
    }
}
