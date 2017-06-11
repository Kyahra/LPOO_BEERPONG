package com.beerpong.game.controller.levels;

import com.badlogic.gdx.physics.box2d.World;
import com.beerpong.game.controller.entities.TableModel;
import com.beerpong.game.model.GameModel;

/**
 * Created by Sofia on 6/6/2017.
 */

/**
 * Medium controller class
 *
 */
public class MediumController implements LevelController {

    /**
     * Initialize the elements in this level
     *
     * @param world the world the elements are being initialized
     */
    @Override
    public void initializeElements(World world) {
        new TableModel(world, GameModel.getInstance().getTable());

    }
}
