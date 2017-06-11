package com.beerpong.game.model;


import com.badlogic.gdx.Gdx;
import com.beerpong.game.model.entities.BallModel;
import com.beerpong.game.model.entities.CupModel;
import com.beerpong.game.model.entities.SimpleModel;

import static com.beerpong.game.view.GameView.VIEWPORT_WIDTH;


/**
 * Created by Sofia on 5/27/2017.
 */

/**
 * The game model class
 *
 */
public class GameModel {

    /**
     * The game model instance
     *
     */
    private static GameModel instance;

    /**
     * The ball model
     *
     */
    private  BallModel ball;

    /**
     * The cup model
     *
     */
    private  CupModel cup;

    /**
     * The table model
     *
     */
    private SimpleModel table;

    /**
     * The ground model
     *
     */
    private SimpleModel ground;

    /**
     * The roof model
     *
     */
    private SimpleModel roof;

    /**
     * The letf wall model
     *
     */
    private SimpleModel leftWall;


    /**
     * The right wall model
     *
     */
    private SimpleModel rightWall;

    private static boolean test = true;


    /**
     * Gets the instance of the game model
     *
     * @return the game model instance
     */
    public static GameModel getInstance() {
        if (instance == null)
            instance = new GameModel();
        return instance;
    }

    /**
     * Game Model constructor
     */
    private GameModel() {

        
        float ratio = ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());


        ball = new BallModel(  VIEWPORT_WIDTH  *0.1f,VIEWPORT_WIDTH/2 ,0);

        if(test) ratio =1;

        ball = new BallModel(  VIEWPORT_WIDTH *ratio*0.2f,VIEWPORT_WIDTH/2 ,0);
        cup = new CupModel( VIEWPORT_WIDTH *0.8f, VIEWPORT_WIDTH *0.1f , 0);
        table = new SimpleModel(VIEWPORT_WIDTH/2,VIEWPORT_WIDTH*ratio/3.6f,0);


        ground = new SimpleModel(0,0,0);
        leftWall = new SimpleModel(0,0,0);

        roof = new SimpleModel(0,0+VIEWPORT_WIDTH*ratio,0);
        rightWall = new SimpleModel(VIEWPORT_WIDTH ,0,0);


    }

    /**
     * Gets the ball
     *
     * @return the ball
     */
    public BallModel getBall(){
        return ball;
    }

    /**
     * Gets the cup
     *
     * @return the cup
     */
    public CupModel getCup() {return cup;}

    /**
     * Gets the ground
     *
     * @return the ground
     */
    public SimpleModel getGround(){return ground;}

    /**
     * Gets the roof
     *
     * @return the roof
     */
    public SimpleModel getRoof(){return roof;}

    /**
     * Gets the left wall
     *
     * @return the left wall
     */
    public SimpleModel getLeftWall(){return leftWall;}

    /**
     * Gets the right wall
     *
     * @return the rigth wall
     */
    public SimpleModel getRightWall(){return rightWall;}

    /**
     * Gets the table
     *
     * @return the table
     */
    public SimpleModel getTable(){ return table; }

    /**
     * Resets the game model instance
     *
     */
    public static void reset(){
        instance = null;
    }


    public static void setTest(){

        test = false;
    }


}
