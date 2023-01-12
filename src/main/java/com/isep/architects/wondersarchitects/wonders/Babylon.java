package com.isep.architects.wondersarchitects.wonders;

import com.isep.architects.wondersarchitects.cards.*;

import java.util.Collections;

public class Babylon extends Wonder{

    public Babylon(){
        this.type = WonderType.BABYLON;
        buildStage();
        fillPile();
    }


    @Override
    public void buildStage() {
        this.stages.add(new WonderStage(2,false,1, new int[]{0},3,this,233));
        this.stages.add(new WonderStage(2,true,2, new int[]{1},0,this,233));
        this.stages.add(new WonderStage(3,false,3, new int[]{2},5,this,233));
        this.stages.add(new WonderStage(3,true,4, new int[]{2,3},5,this,129));
        this.stages.add(new WonderStage(4,false,5, new int[]{2,3},7,this,307));

    }

    @Override
    public void specialEffect() {

    }

    public void fillPile(){
        for(int i = 0; i<3;i++){
            pile.getCards().add(CardsTypes.WOOD);
            pile.getCards().add(CardsTypes.STONE);
            pile.getCards().add(CardsTypes.BLUE2);
            pile.getCards().add(CardsTypes.PAPER);
            pile.getCards().add(CardsTypes.GLASS);
            pile.getCards().add(CardsTypes.GOLD);
            pile.getCards().add(CardsTypes.BLUE2);
            pile.getCards().add(CardsTypes.BLUE3);
            pile.getCards().add(CardsTypes.WHEEL);
            pile.getCards().add(CardsTypes.TABLET);
            pile.getCards().add(CardsTypes.COMPASS);
            pile.getCards().add(CardsTypes.RED0);
            pile.getCards().add(CardsTypes.RED1);
            pile.getCards().add(CardsTypes.RED2);
        }
        Collections.shuffle(pile.getCards());
    }
}
