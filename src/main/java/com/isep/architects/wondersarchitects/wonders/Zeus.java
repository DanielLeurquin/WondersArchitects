package com.isep.architects.wondersarchitects.wonders;

import com.isep.architects.wondersarchitects.cards.*;

import java.util.Collections;

public class Zeus extends Wonder{

    public Zeus(){
        this.type = WonderType.ZEUS;
        buildStage();
        fillPile();
    }

    @Override
    public void buildStage() {
        this.stages.add(new WonderStage(2,false,1, new int[]{0},3,this,233));
        this.stages.add(new WonderStage(2,true,2, new int[]{1},2,this,127));
        this.stages.add(new WonderStage(3,false,3, new int[]{1},5,this,324));
        this.stages.add(new WonderStage(3,true,4, new int[]{2,3},5,this,233));
        this.stages.add(new WonderStage(4,false,5, new int[]{4},7,this,233));

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
