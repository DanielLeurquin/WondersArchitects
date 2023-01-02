package com.isep.architects.wondersarchitects;

import com.isep.architects.wondersarchitects.cards.*;
import com.isep.architects.wondersarchitects.pile.Pile;
import com.isep.architects.wondersarchitects.tokens.MilitaryToken;
import com.isep.architects.wondersarchitects.wonders.*;

import java.util.*;

public class Game {

    private InputParser inputParser;

    private Application app;

    private int numberPlayer;

    private Random random = new Random();

    private ArrayList<Wonder> availableWonders = new ArrayList<Wonder>();

    private ArrayList<Player> playerList = new ArrayList<Player>();

    private ArrayList<MilitaryToken> militaryTokens = new ArrayList<>();

    private Pile centerPile = new Pile();

    private Player playerturn;


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



    }

    public void createMilitaryToken(){


        militaryTokens.add(new MilitaryToken());
        militaryTokens.add(new MilitaryToken());
        militaryTokens.add(new MilitaryToken());

        if(numberPlayer >=4){
            militaryTokens.add(new MilitaryToken());
        }
        if(numberPlayer >=5){
            militaryTokens.add(new MilitaryToken());
        }
        if(numberPlayer >=6){
            militaryTokens.add(new MilitaryToken());
        }
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
        for(int i = 0; i<3;i++){
            centerPile.getCards().add(new GreyCards(RessourceType.WOOD));
            centerPile.getCards().add(new GreyCards(RessourceType.STONE));
            centerPile.getCards().add(new GreyCards(RessourceType.BRICK));
            centerPile.getCards().add(new GreyCards(RessourceType.PAPER));
            centerPile.getCards().add(new GreyCards(RessourceType.GLASS));
            centerPile.getCards().add(new YellowCards());
            centerPile.getCards().add(new BlueCards(true));
            centerPile.getCards().add(new BlueCards(false));
            centerPile.getCards().add(new GreenCards(ScienceType.WHEEL));
            centerPile.getCards().add(new GreenCards(ScienceType.TABLET));
            centerPile.getCards().add(new GreenCards(ScienceType.COMPASS));
            centerPile.getCards().add(new RedCards(0));
            centerPile.getCards().add(new RedCards(1));
            centerPile.getCards().add(new RedCards(2));

        }

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

    public ArrayList<MilitaryToken> getMilitaryTokens() {
        return militaryTokens;
    }

    public Pile getCenterPile() {
        return centerPile;
    }

    public Player getPlayerturn() {
        return playerturn;
    }
}
