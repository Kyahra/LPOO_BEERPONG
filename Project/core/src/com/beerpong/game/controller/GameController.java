package com.beerpong.game.controller;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

import com.badlogic.gdx.utils.Array;
import com.beerpong.game.controller.entities.BallBody;
import com.beerpong.game.controller.entities.LimitBody;
import com.beerpong.game.model.GameModel;
import com.beerpong.game.model.entities.EntityModel;
import com.beerpong.game.model.entities.LimitModel;

import java.util.ArrayList;

import static com.beerpong.game.view.GameView.VIEWPORT_HEIGHT;
import static com.beerpong.game.view.GameView.VIEWPORT_WIDTH;


/**
 * Created by Sofia on 5/27/2017.
 */

public class GameController implements ContactListener {

    private static GameController instance;




    private final World world;

    private final BallBody ballBody;


    private GameController(){
        world = new World(new Vector2(0,-10),true);

        ballBody = new BallBody(world, GameModel.getInstance().getBall());


        new LimitBody(world,GameModel.getInstance().getGround(),VIEWPORT_HEIGHT,VIEWPORT_WIDTH*0.25f);
        new LimitBody(world,GameModel.getInstance().getLeftWall(),4f,VIEWPORT_HEIGHT);
        new LimitBody(world,GameModel.getInstance().getRoof(),VIEWPORT_HEIGHT,VIEWPORT_WIDTH*0.25f);
       new LimitBody(world,GameModel.getInstance().getRightWall(),2f,VIEWPORT_HEIGHT);


        world.setContactListener(this);

    }


    public static GameController getInstance() {
        if(instance == null)
            instance = new GameController();
        return instance;

    }

    public void update(float delta) {

        // Step the world
        world.step(delta, 6, 2);

        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);

        for(Body  body:bodies) {

            ((EntityModel) body.getUserData()).setPosition(body.getPosition().x, body.getPosition().y);

        }

    }

    public void shootBall(float delta_X, float delta_Y) {
        Vector2 vector = new Vector2(delta_X,delta_Y);
        vector.rotateRad(ballBody.getAngle());
        ballBody.applyForceToCenter(delta_X,delta_Y, true);

    }



    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }



}
