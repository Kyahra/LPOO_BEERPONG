package com.beerpong.game.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.beerpong.game.BeerPong;
import com.beerpong.game.controller.GameController;
import com.beerpong.game.model.entities.EntityModel;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

import com.badlogic.gdx.utils.Array;
import com.beerpong.game.controller.entities.BallBody;
import com.beerpong.game.controller.entities.CupBody;
import com.beerpong.game.controller.entities.LimitBody;

import com.beerpong.game.controller.levels.LevelController;
import com.beerpong.game.model.GameModel;
import com.beerpong.game.model.entities.BallModel;
import com.beerpong.game.model.entities.CupModel;
import com.beerpong.game.model.entities.EntityModel;

import org.junit.Test;

import static com.beerpong.game.controller.GameController.*;
import static org.junit.Assert.assertEquals;


/**
 * Created by Daniel on 09/06/2017.
 */
public class GameControllerTest {

    BeerPong.AndroidAPIAdapter androidAPIAdapter = new BeerPong.AndroidAPIAdapter() {
        @Override
        public void setScore(int score) {

        }

    };

    int level = 1;

    BeerPong beerPong = new BeerPong(androidAPIAdapter, level);


    @Test
    public void reset() throws Exception {
        getInstance().reset();
    }


    @Test
    public void testUpdate() throws Exception {

        World world1 = GameController.getInstance().getWorld();

        world1.step(2,6,2);

        Array<Body> bodies = new Array<Body>();
        world1.getBodies(bodies);

        for(Body  body:bodies) {

            ((EntityModel) body.getUserData()).setPosition(body.getPosition().x, body.getPosition().y);

        }


        GameController.getInstance().update(2);

        World world2 = GameController.getInstance().getWorld();

        assertEquals(world1,world2);

    }




}