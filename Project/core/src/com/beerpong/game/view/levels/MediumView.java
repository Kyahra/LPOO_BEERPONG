package com.beerpong.game.view.levels;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.beerpong.game.BeerPong;
import com.beerpong.game.model.GameModel;
import com.beerpong.game.model.entities.SimpleModel;
import com.beerpong.game.view.entities.EntityView;

/**
 * Created by Sofia on 6/6/2017.
 */


/**
 * The view that is responsible for the medium level
 *
 */
public class MediumView implements LevelView {

    /**
     * Updates the camera
     *
     * @param camera the camera
     */
    @Override
    public void updateCamera(OrthographicCamera camera) {

    }

    /**
     * Draws entities
     *
     * @param game the game
     */
    @Override
    public void drawEntities(BeerPong game) {

        EntityView view;

        SimpleModel ball = GameModel.getInstance().getTable();
        view = new EntityView(game, "table.png");
        view.update(ball);
        view.draw(game.getSpriteBatch());

    }
}
