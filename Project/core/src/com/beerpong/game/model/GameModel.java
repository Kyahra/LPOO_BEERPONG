package com.beerpong.game.model;


import com.badlogic.gdx.Gdx;
import com.beerpong.game.model.entities.BallModel;
import com.beerpong.game.model.entities.CupModel;
import com.beerpong.game.model.entities.SimpleModel;

import static com.beerpong.game.view.GameView.VIEWPORT_WIDTH;


/**
 * Created by Sofia on 5/27/2017.
 */

public class GameModel {
    private static GameModel instance;

    private  BallModel ball;
    private  CupModel cup;
    private SimpleModel table;

    private SimpleModel ground;
    private SimpleModel roof;
    private SimpleModel leftWall;
    private SimpleModel rightWall;

    private static boolean test = true;


    public static GameModel getInstance() {
        if (instance == null)
            instance = new GameModel();
        return instance;
    }

    private GameModel() {

        Gdx.app.log("cria-se game model","o");
        
        float ratio = ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());

        if(test) ratio =1;

        ball = new BallModel(  VIEWPORT_WIDTH  *0.2f,VIEWPORT_WIDTH/2 ,0);
        cup = new CupModel( VIEWPORT_WIDTH *0.8f, VIEWPORT_WIDTH *0.1f , 0);

        table = new SimpleModel(VIEWPORT_WIDTH/2,VIEWPORT_WIDTH*ratio/3.6f,0);


        ground = new SimpleModel(0,0,0);
        leftWall = new SimpleModel(0,0,0);
        roof = new SimpleModel(0,0+VIEWPORT_WIDTH*ratio,0);
        rightWall = new SimpleModel(VIEWPORT_WIDTH ,0,0);


    }

    public BallModel getBall(){
        return ball;
    }

    public CupModel getCup() {return cup;}

    public SimpleModel getGround(){return ground;}

    public SimpleModel getRoof(){return roof;}

    public SimpleModel getLeftWall(){return leftWall;}

    public SimpleModel getRightWall(){return rightWall;}

    public SimpleModel getTable(){ return table; }

    public static void reset(){
        instance = null;
    }

    public static void setTest(){

        test = false;
    }



}
