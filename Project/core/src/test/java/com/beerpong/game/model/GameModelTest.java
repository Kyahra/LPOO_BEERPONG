package com.beerpong.game.model;

import com.beerpong.game.BeerPong;
import com.beerpong.game.model.entities.BallModel;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Daniel on 09/06/2017.
 */
public class GameModelTest {

    BeerPong.AndroidAPIAdapter androidAPIAdapter = new BeerPong.AndroidAPIAdapter() {
        @Override
        public void setScore() {
        }
    };

    int level = 1;

    BeerPong beerPong = new BeerPong(androidAPIAdapter, level);



    @Test
    public void getBall() throws Exception {

        BallModel ballModel =  new BallModel(0.5f, 0.5f,0);
    }

    @Test
    public void getCup() throws Exception {

    }

    @Test
    public void getGround() throws Exception {

    }

    @Test
    public void getRoof() throws Exception {

    }

    @Test
    public void getLeftWall() throws Exception {

    }

    @Test
    public void getRightWall() throws Exception {

    }

    @Test
    public void getTable() throws Exception {

    }

    @Test
    public void reset() throws Exception {

    }

}