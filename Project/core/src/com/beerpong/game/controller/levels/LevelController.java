package com.beerpong.game.controller.levels;

import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Sofia on 6/6/2017.
 */

/**
 * Level controller Class.
 *
 * The interface that is responsible for the level implementation
 *
 */
public interface LevelController {

    /**
     * Initialize the elements in this level
     *
     * @param world the world the elements are being initialized
     */
    public void initializeElements(World world);
}
