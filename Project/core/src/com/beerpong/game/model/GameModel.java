package com.beerpong.game.model;


import com.badlogic.gdx.scenes.scene2d.Stage;
import com.beerpong.game.model.entities.BallModel;
import com.beerpong.game.model.entities.LimitModel;


import java.util.ArrayList;

import static com.beerpong.game.view.GameView.VIEWPORT_HEIGHT;
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



        ball = new BallModel(VIEWPORT_HEIGHT * 0.03f , VIEWPORT_WIDTH*0.5f,0);

        ground = new LimitModel(0,0,0);
        leftWall = new LimitModel(0,0,0);
        roof = new LimitModel(0,VIEWPORT_WIDTH*2.5f -4f,0);
        rightWall = new LimitModel(VIEWPORT_HEIGHT/3.7f ,0,0);


    }

    public BallModel getBall(){
        return ball;
    }

    public LimitModel getGround(){return ground;}
    public LimitModel getRoof(){return roof;}
    public LimitModel getLeftWall(){return leftWall;}
    public LimitModel getRightWall(){return rightWall;}

}
