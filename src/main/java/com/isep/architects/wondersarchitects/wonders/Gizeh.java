package com.isep.architects.wondersarchitects.wonders;

import com.isep.architects.wondersarchitects.cards.*;

import java.util.Collections;

public class Gizeh extends Wonder{

    public Gizeh(){
        this.type = WonderType.GIZEH;
        buildStage();
        fillPile();
    }

    @Override
    public void buildStage() {
        this.stages.add(new WonderStage(2,false,1, new int[]{0},4));
        this.stages.add(new WonderStage(2,true,2, new int[]{1},5));
        this.stages.add(new WonderStage(3,false,3, new int[]{2},6));
        this.stages.add(new WonderStage(3,true,4, new int[]{3},7));
        this.stages.add(new WonderStage(4,false,5, new int[]{4},8));
    }

    public void fillPile(){
        for(int i = 0; i<3;i++){
            pile.getCards().add(new GreyCards(RessourceType.WOOD));
            pile.getCards().add(new GreyCards(RessourceType.STONE));
            pile.getCards().add(new GreyCards(RessourceType.BRICK));
            pile.getCards().add(new GreyCards(RessourceType.PAPER));
            pile.getCards().add(new GreyCards(RessourceType.GLASS));
            pile.getCards().add(new YellowCards());
            pile.getCards().add(new BlueCards(true));
            pile.getCards().add(new BlueCards(false));
            pile.getCards().add(new GreenCards(ScienceType.WHEEL));
            pile.getCards().add(new GreenCards(ScienceType.TABLET));
            pile.getCards().add(new GreenCards(ScienceType.COMPASS));
            pile.getCards().add(new RedCards(0));
            pile.getCards().add(new RedCards(1));
            pile.getCards().add(new RedCards(2));

        }
        Collections.shuffle(pile.getCards());
    }
}
