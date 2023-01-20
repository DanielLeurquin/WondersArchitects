package com.isep.architects.wondersarchitects;

import com.isep.architects.wondersarchitects.cards.*;
import com.isep.architects.wondersarchitects.tokens.TokenTypes;
import com.isep.architects.wondersarchitects.wonders.Wonder;
import com.isep.architects.wondersarchitects.wonders.WonderStage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Player {

    private String name;

    private boolean cat = false;

    private Wonder wonder;

    private ArrayList<CardsTypes> cards = new ArrayList<>();

    private ArrayList<TokenTypes> conflict = new ArrayList<>();

    private ArrayList<TokenTypes> progress = new ArrayList<>();

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
                        value = value &&
                                (wonder.getStageFromNum(stage.getNeededNumBuild()[i]).isBuilt() ||
                                        stageToBuild.contains(wonder.getStageFromNum(stage.getNeededNumBuild()[i])));
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
        if(progress.contains(TokenTypes.ENGINEERING)){
            ArrayList<CardsTypes> ressources = new ArrayList<>();
            for(CardsTypes c : cards){
                if(c.equals(CardsTypes.WOOD) || c.equals(CardsTypes.STONE) || c.equals(CardsTypes.BRICK) ||
                        c.equals(CardsTypes.PAPER) || c.equals(CardsTypes.GLASS) || c.equals(CardsTypes.GOLD)){
                    ressources.add(c);
                }
            }

            if(ressources.size()>= stage.getValue()){
                cards.removeAll(ressources);
                return true;
            }else {
                return false;
            }
        } else if(stage.isSame()){
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

    public boolean sameGreen(){
        CardsTypes type = null;
        if(Collections.frequency(cards,CardsTypes.WHEEL)>=2){
            type = CardsTypes.WHEEL;
        }else if(Collections.frequency(cards,CardsTypes.TABLET)>=2){
            type = CardsTypes.TABLET;
        }else if(Collections.frequency(cards,CardsTypes.COMPASS)>=2){
            type = CardsTypes.COMPASS;
        }
        if(type!=null){
            for(int i = 0; i<2;i++){
                cards.remove(type);
            }
            return true;
        }else {
            return false;
        }

    }

    public boolean differentGreen(){
        if(cards.contains(CardsTypes.WHEEL) &&
                cards.contains(CardsTypes.TABLET) && cards.contains(CardsTypes.COMPASS)){
            cards.remove(CardsTypes.WHEEL);
            cards.remove(CardsTypes.TABLET);
            cards.remove(CardsTypes.COMPASS);
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

    public int countRed(){
        int compteur = 0;
        for(CardsTypes card : cards){
            if(card.equals(CardsTypes.RED0) || card.equals(CardsTypes.RED1) || card.equals(CardsTypes.RED2)){
                compteur++;
            }
        }
        return compteur;
    }

    public void removeHornRed(){
        int horn1 = 0;
        int horn2 = 0;
        for(CardsTypes card : cards){
            if(card.equals(CardsTypes.RED1)){
                horn1++;
            }else if(card.equals(CardsTypes.RED2)){
                horn2++;
            }
        }
        for(int i = 0; i<horn1;i++){
            cards.remove(CardsTypes.RED1);
        }
        for(int i = 0; i<horn2;i++){
            cards.remove(CardsTypes.RED2);
        }


    }
    public void removeGold(){
        int lim = countGold();
        for(int i = 0;i<lim;i++){
            cards.remove(CardsTypes.GOLD);
        }
    }

    public boolean finish(){
        boolean value = true;
        for(WonderStage stage : getWonder().getStages()){
            value = value && stage.isBuilt();
        }
        return value;
    }

    public int getStagePoints(){
        int points = 0;
        for(WonderStage stage : wonder.getStages()){
            if(stage.isBuilt()){
                points += stage.getVictoryPoints();
            }
        }
        return points;
    }

    public int getBluePoints(){
        int value = 0;
        for(CardsTypes card : cards){
            if(card.equals(CardsTypes.BLUE2)){
                value += 2;
            }else if(card.equals(CardsTypes.BLUE3)){
                value += 3;
            }
        }
        return value;
    }

    public int getCatPoints(){
        if(isCat()){
            return 2;
        }else {
            return 0;
        }
    }

    public int getMilitaryPoints(){
        int value = 0;
        for(TokenTypes token : getConflict()){
            value += 3;
        }
        return value;
    }

    public int getProgressPoints(){
        int value = 0;
        for(TokenTypes token : progress){
            if(token.getIndex()>8){
                if(token.equals(TokenTypes.DECOR)){
                    if(finish()){
                        value+=6;
                    }else {
                        value+=4;
                    }
                }else if(token.equals(TokenTypes.POLITICS)){
                    for(CardsTypes card : cards){
                        if(card.equals(CardsTypes.BLUE2)){
                            value += 1;
                        }
                    }
                }else if(token.equals(TokenTypes.STRATEGY)){
                    value += conflict.size();
                }else if(token.equals(TokenTypes.EDUCATION)){
                    value += 2*progress.size();
                }else if(token.equals(TokenTypes.CULTURE)){
                    value += 4;
                }
            }
        }
        if(Collections.frequency(progress,TokenTypes.CULTURE)==2){
            value += 4;
        }

        return value;
    }


    public ArrayList<TokenTypes> getConflict() {
        return conflict;
    }

    public ArrayList<TokenTypes> getProgress() {
        return progress;
    }

    public boolean isCat() {
        return cat;
    }

    public void setCat(boolean cat) {
        this.cat = cat;
    }
}
