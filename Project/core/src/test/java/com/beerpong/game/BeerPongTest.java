package com.beerpong.game;

import static org.junit.Assert.*;

/**
 * Created by Daniel on 09/06/2017.
 */
public class BeerPongTest {
    @org.junit.Test
    public void startGame() throws Exception {

        BeerPong.AndroidAPIAdapter androidAPIAdapter = new BeerPong.AndroidAPIAdapter() {
            @Override
            public void setScore() {
            }
        };

        int level = 1;

       BeerPong beerPong = new BeerPong(androidAPIAdapter, level);

        BeerPong.setExited(true);

        boolean exited = BeerPong.isExited();

        assertEquals(exited, false);


    }

}