package com.isep.architects.wondersarchitects;

import com.isep.architects.wondersarchitects.wonders.Wonder;
import com.isep.architects.wondersarchitects.wonders.WonderType;

import java.util.ArrayList;
import java.util.Random;

public class Game {

    private InputParser inputParser;

    private Application app;

    private int numberPlayer;

    private Random random = new Random();

    private ArrayList<Wonder> availableWonders = new ArrayList<Wonder>();


    private ArrayList<Player> playerList = new ArrayList<Player>();


    public Game(InputParser inputParser, Application app){
        this.inputParser = inputParser;
        this.app = app;
        availableWonders.add(new Wonder(WonderType.ALEXANDRIA));
        availableWonders.add(new Wonder(WonderType.ARTEMIS));
        availableWonders.add(new Wonder(WonderType.BABYLON));
        availableWonders.add(new Wonder(WonderType.GIZEH));
        availableWonders.add(new Wonder(WonderType.ZEUS));
        availableWonders.add(new Wonder(WonderType.RHODES));
        availableWonders.add(new Wonder(WonderType.HALLICARNAS));


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
            this.inputParser.chargeOverview();
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
}
