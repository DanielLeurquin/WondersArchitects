package com.isep.architects.wondersarchitects;

import com.isep.architects.wondersarchitects.Animation.WonderStageAnimation;
import com.isep.architects.wondersarchitects.cards.*;
import com.isep.architects.wondersarchitects.pile.Pile;
import com.isep.architects.wondersarchitects.tokens.TokenTypes;
import com.isep.architects.wondersarchitects.wonders.*;

import java.util.*;

public class Game {



    private InputParser inputParser;

    private Application app;

    private int numberPlayer;

    private Random random = new Random();

    private ArrayList<Wonder> availableWonders = new ArrayList<Wonder>();

    private ArrayList<Player> playerList = new ArrayList<Player>();

    private ArrayList<TokenTypes> militaryTokens = new ArrayList<>();

    private ArrayList<TokenTypes> progress = new ArrayList<>();

    private Pile centerPile = new Pile();

    private Player playerturn;

    private ArrayList<WonderStage> stageToBuild = new ArrayList<>();

    private double multiplier = 0.75;


    public Game(InputParser inputParser, Application app){
        this.inputParser = inputParser;
        this.app = app;
        availableWonders.add(new Alexandria());
        availableWonders.add(new Artemis());
        availableWonders.add(new Babylon());
        availableWonders.add(new Gizeh());
        availableWonders.add(new Zeus());
        availableWonders.add(new Rhodes());
        availableWonders.add(new Hallicarnas());
        fillCenterPile();
        Collections.shuffle(centerPile.getCards());

        for(int i =0; i<14;i++){
            progress.add(TokenTypes.values()[i]);
        }
        progress.add(TokenTypes.CULTURE);
        Collections.shuffle(progress);

    }

    public void createMilitaryToken(){

        militaryTokens.clear();

        militaryTokens.add(TokenTypes.PEACE);
        militaryTokens.add(TokenTypes.PEACE);
        militaryTokens.add(TokenTypes.PEACE);

        if(numberPlayer >=4){
            militaryTokens.add(TokenTypes.PEACE);
        }
        if(numberPlayer >=5){
            militaryTokens.add(TokenTypes.PEACE);
        }
        if(numberPlayer >=6){
            militaryTokens.add(TokenTypes.PEACE);
        }
    }

    public void evaluateWar(CardsTypes card){
        int horns = 1;
        if(card.equals(CardsTypes.RED2)){
            horns = 2;
        }
        int compteur = 0;
        for(TokenTypes token : militaryTokens){
            if(compteur<horns && token.equals(TokenTypes.PEACE)){
                militaryTokens.set(militaryTokens.indexOf(token), TokenTypes.MILITARY);
                compteur++;
            }
        }
        if(allWar()){
            inputParser.playHornSound();
            executeWar();
        }
    }

    public void executeWar(){

        for(Player player : playerList){
            int index = playerList.indexOf(player);
            Player previous;
            Player next;
            if(index+1==playerList.size()){
                next = playerList.get(0);
            }else {
                next = playerList.get(index+1);;
            }
            if(index-1==-1){
                previous = playerList.get(playerList.size()-1);
            }else {
                previous = playerList.get(index-1);;
            }
            if(numberPlayer>2){
                if(player.countRed()> previous.countRed()){
                    player.getConflict().add(TokenTypes.CONFLICT);
                }
                if(player.countRed()> next.countRed()){
                    player.getConflict().add(TokenTypes.CONFLICT);
                }
            }else {
                if(player.countRed()>previous.countRed()){
                    player.getConflict().add(TokenTypes.CONFLICT);
                }
                if(player.countRed()>=2*previous.countRed()){
                    player.getConflict().add(TokenTypes.CONFLICT);
                }
            }

        }

        for (Player player : playerList){
            player.removeHornRed();
        }

        createMilitaryToken();




    }

    public boolean allWar(){
        boolean value = true;
        for(TokenTypes token : militaryTokens){
            value = value && token.equals(TokenTypes.MILITARY);
        }
        return value;
    }

    public void createPlayer(String name){
        Player player = new Player(name);
        playerList.add(player);
        int index = random.nextInt(availableWonders.size());
        Wonder wonder = availableWonders.get(index);
        player.setWonder(wonder);
        wonder.setPlayer(player);
        availableWonders.remove(wonder);

        if(playerList.size()<numberPlayer){
            this.inputParser.askPlayerName(playerList.size()+1);
        }else {
            //next node
            this.playerturn = playerList.get(0);
            this.inputParser.chargeOverview();
        }
    }

    public void fillCenterPile(){
        centerPile.addMultiple(CardsTypes.GOLD,6);
        centerPile.addMultiple(CardsTypes.STONE,4);
        centerPile.addMultiple(CardsTypes.BRICK,4);
        centerPile.addMultiple(CardsTypes.WOOD,4);
        centerPile.addMultiple(CardsTypes.GLASS,4);
        centerPile.addMultiple(CardsTypes.PAPER,4);
        centerPile.addMultiple(CardsTypes.WHEEL,4);
        centerPile.addMultiple(CardsTypes.COMPASS,4);
        centerPile.addMultiple(CardsTypes.TABLET,4);
        centerPile.addMultiple(CardsTypes.BLUE3,4);
        centerPile.addMultiple(CardsTypes.BLUE2,8);
        centerPile.addMultiple(CardsTypes.RED0,4);
        centerPile.addMultiple(CardsTypes.RED2,2);
        centerPile.addMultiple(CardsTypes.RED1,4);

        Collections.shuffle(centerPile.getCards());

    }

    public void catMove(Player player){
        for(Player p : playerList){
            if(p.equals(player)){
                p.setCat(true);
            }else {
                p.setCat(false);
            }
        }

    }


    public void buildStage(){
        ArrayList<WonderStage> stages = playerturn.buildStage();
        stageToBuild.addAll(stages);

        if(stageToBuild.size()>0){
            WonderStage stage = stageToBuild.get(0);
            stage.setBuilt(true);
            WonderStageAnimation anim = inputParser.animationStage(stage);

            anim.setOnFinished(event -> {
                stageToBuild.remove(stage);
                inputParser.setAnimation(false);
                if(stage.isPower()){
                    if(stage.getWonder().getType().equals(WonderType.BABYLON)){
                        inputParser.chooseProgress();
                    }else if(stage.getWonder().getType().equals(WonderType.RHODES)){

                        playerturn.getCards().add(CardsTypes.RED0);
                        inputParser.checkFinish(null);
                    }else if(stage.getWonder().getType().equals(WonderType.ARTEMIS)){
                        inputParser.drawCard(centerPile.drawCard(playerturn));
                    }else if(stage.getWonder().getType().equals(WonderType.ZEUS)){
                        inputParser.setWonderPower(true);
                        inputParser.drawCard(playerturn.getWonder().getPile().drawCard(playerturn));
                        inputParser.drawCard(playerList.get(1).getWonder().getPile().drawCard(playerturn));

                    }else if(stage.getWonder().getType().equals(WonderType.ALEXANDRIA)){
                        inputParser.chargeAlexOverlay();
                    }else if(stage.getWonder().getType().equals(WonderType.HALLICARNAS)){
                        inputParser.enableHaliOverlay();
                    }
                }else {
                    inputParser.checkFinish(null);
                }
            });
            inputParser.setAnimation(true);
            anim.play();



        }


    }



    public void endTurn(){
        //System.out.println("end turn\n.\n.");
        if(playerList.get(0).finish()){
            inputParser.loadScoreBoard();
            return;
        }
        for(int i = 1; i<playerList.size();i++){

            playerList.set(i-1,playerList.get(i));

        }
        playerList.set(playerList.size()-1,playerturn);
        playerturn = playerList.get(0);


    }

    public void setNumberPlayer(int numberPlayer) {
        this.numberPlayer = numberPlayer;
    }

    public Application getApp() {
        return app;
    }

    public int getNumberPlayer() {
        return numberPlayer;
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public ArrayList<TokenTypes> getMilitaryTokens() {
        return militaryTokens;
    }

    public ArrayList<WonderStage> getStageToBuild() {
        return stageToBuild;
    }

    public Pile getCenterPile() {
        return centerPile;
    }

    public Player getPlayerturn() {
        return playerturn;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public ArrayList<TokenTypes> getProgress() {
        return progress;
    }
}
