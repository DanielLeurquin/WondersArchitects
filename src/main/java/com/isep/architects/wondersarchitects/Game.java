package com.isep.architects.wondersarchitects;

import java.util.ArrayList;

public class Game {

    private InputParser inputParser;

    private Application app;

    private int numberPlayer;


    private ArrayList<Player> playerList = new ArrayList<Player>();


    public Game(InputParser inputParser, Application app){
        this.inputParser = inputParser;
        this.app = app;
    }

    public void createPlayer(String name){
        playerList.add(new Player(name));
        if(playerList.size()<numberPlayer){
            this.inputParser.askPlayerName(playerList.size()+1);
        }else {
            //next node
            System.out.println("done");
            System.out.println(playerList);
        }
    }

    public void setNumberPlayer(int numberPlayer) {
        this.numberPlayer = numberPlayer;
    }

    public Application getApp() {
        return app;
    }

}
