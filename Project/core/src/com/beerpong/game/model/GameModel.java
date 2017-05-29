package com.beerpong.game.model;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.beerpong.game.model.entities.BallModel;
import com.beerpong.game.model.entities.LimitModel;

import static com.beerpong.game.view.GameView.VIEWPORT_WIDTH;


/**
 * Created by Sofia on 5/27/2017.
 */

public class GameModel extends Stage {
    private static GameModel instance;

    private BallModel ball;

    private LimitModel ground;
    private LimitModel roof;
    private LimitModel leftWall;
    private LimitModel rightWall;
    // private CupActor cup;

    public static GameModel getInstance() {
        if (instance == null)
            instance = new GameModel();
        return instance;
    }

    private GameModel() {


        float ratio = ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());
        ball = new BallModel(VIEWPORT_WIDTH/2,  VIEWPORT_WIDTH *ratio /2,0);

        ground = new LimitModel(0,0,0);
        leftWall = new LimitModel(0,0,0);
        roof = new LimitModel(0,VIEWPORT_WIDTH *ratio,0);
        rightWall = new LimitModel(VIEWPORT_WIDTH ,0,0);


    }

    public BallModel getBall(){
        return ball;
    }

    public LimitModel getGround(){return ground;}
    public LimitModel getRoof(){return roof;}
    public LimitModel getLeftWall(){return leftWall;}
    public LimitModel getRightWall(){return rightWall;}

}
