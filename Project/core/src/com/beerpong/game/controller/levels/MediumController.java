package com.beerpong.game.controller.levels;

import com.badlogic.gdx.physics.box2d.World;
import com.beerpong.game.controller.entities.TableModel;
import com.beerpong.game.model.GameModel;

/**
 * Created by Sofia on 6/6/2017.
 */

public class MediumController implements LevelController {

    @Override
    public void initializeElements(World world) {
        new TableModel(world, GameModel.getInstance().getTable());

    }
}
