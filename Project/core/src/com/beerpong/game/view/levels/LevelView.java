package com.beerpong.game.view.levels;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.beerpong.game.BeerPong;

/**
 * Created by Sofia on 6/6/2017.
 */

/**
 * Level interface, implements all levels
 *
 */
public interface LevelView {

    /**
     * Updates the camera
     *
     * @param camera the camera
     */
    public void updateCamera(OrthographicCamera camera);

    /**
     * Draws entities
     *
     * @param game the game
     */
    public void drawEntities(BeerPong game);


}
