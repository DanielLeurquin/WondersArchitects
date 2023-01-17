package com.isep.architects.wondersarchitects;

import com.isep.architects.wondersarchitects.Animation.WonderStageAnimation;
import com.isep.architects.wondersarchitects.cards.*;
import com.isep.architects.wondersarchitects.pile.Pile;
import com.isep.architects.wondersarchitects.tokens.TokenTypes;
import com.isep.architects.wondersarchitects.wonders.*;
import javafx.scene.control.Tab;

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

    public void startTurn(){
        int value = 0;
    }

    public void endTurn(){
        for(int i = 1; i<playerList.size();i++){
            playerList.set(i-1,playerList.get(i));
        }
        ArrayList<WonderStage> stageToBuild = playerturn.buildStage();
        for(WonderStage stage : stageToBuild){
            inputParser.animationStage(stage);
            stage.setBuilt(true);
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
