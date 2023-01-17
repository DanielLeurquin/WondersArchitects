package com.isep.architects.wondersarchitects.wonders;

import com.isep.architects.wondersarchitects.cards.*;

import java.util.Collections;

public class Artemis extends Wonder{

    public Artemis(){
        this.type = WonderType.ARTEMIS;
        buildStage();
        fillPile();
    }

    @Override
    public void buildStage() {
        this.stages.add(new WonderStage(2,false,1, new int[]{0},3,this,233));
        this.stages.add(new WonderStage(2,true,2, new int[]{1},3,this,99));
        this.stages.add(new WonderStage(3,false,3, new int[]{1},4,this,233));
        this.stages.add(new WonderStage(3,true,4, new int[]{1},5,this,367));
        this.stages.add(new WonderStage(4,false,5, new int[]{2,3,4},7,this,233));

    }

    @Override
    public void specialEffect() {

    }

    public void fillPile(){
        pile.addMultiple(CardsTypes.GOLD,3);
        pile.addMultiple(CardsTypes.STONE,2);
        pile.addMultiple(CardsTypes.BRICK,2);
        pile.addMultiple(CardsTypes.WOOD,2);
        pile.addMultiple(CardsTypes.GLASS,2);
        pile.addMultiple(CardsTypes.PAPER,2);
        pile.addMultiple(CardsTypes.WHEEL,1);
        pile.addMultiple(CardsTypes.COMPASS,2);
        pile.addMultiple(CardsTypes.TABLET,1);
        pile.addMultiple(CardsTypes.BLUE3,1);
        pile.addMultiple(CardsTypes.BLUE2,2);
        pile.addMultiple(CardsTypes.RED0,2);
        pile.addMultiple(CardsTypes.RED2,1);
        pile.addMultiple(CardsTypes.RED1,1);


        Collections.shuffle(pile.getCards());
    }
}
