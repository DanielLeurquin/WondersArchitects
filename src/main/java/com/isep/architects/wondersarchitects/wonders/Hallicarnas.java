package com.isep.architects.wondersarchitects.wonders;

import com.isep.architects.wondersarchitects.cards.*;

import java.util.Collections;

public class Hallicarnas extends Wonder{

    public Hallicarnas(){
        this.type = WonderType.HALLICARNAS;
        buildStage();
        fillPile();
    }

    @Override
    public void buildStage() {
        this.stages.add(new WonderStage(2,false,1, new int[]{0},3,this,233));
        this.stages.add(new WonderStage(2,true,2, new int[]{1},3,this,233));
        this.stages.add(new WonderStage(3,false,3, new int[]{2},6,this,124));
        this.stages.add(new WonderStage(3,true,4, new int[]{2},5,this,355));
        this.stages.add(new WonderStage(4,false,5, new int[]{3,4},7,this,233));
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
        pile.addMultiple(CardsTypes.PAPER,1);
        pile.addMultiple(CardsTypes.WHEEL,1);
        pile.addMultiple(CardsTypes.COMPASS,1);
        pile.addMultiple(CardsTypes.TABLET,2);
        pile.addMultiple(CardsTypes.BLUE3,2);
        pile.addMultiple(CardsTypes.BLUE2,2);
        pile.addMultiple(CardsTypes.RED0,2);
        pile.addMultiple(CardsTypes.RED2,1);
        pile.addMultiple(CardsTypes.RED1,2);


        Collections.shuffle(pile.getCards());
    }


}
