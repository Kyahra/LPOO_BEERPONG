package com.beerpong.game.view.levels;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.beerpong.game.BeerPong;
import com.beerpong.game.model.GameModel;
import com.beerpong.game.model.entities.SimpleModel;
import com.beerpong.game.view.entities.EntityView;

/**
 * Created by Sofia on 6/6/2017.
 */

public class MediumView implements LevelView {
    @Override
    public void updateCamera(OrthographicCamera camera) {

    }

    @Override
    public void drawEntities(BeerPong game) {

        EntityView view;

        SimpleModel ball = GameModel.getInstance().getTable();
        view = new EntityView(game, "table.png");
        view.update(ball);
        view.draw(game.getSpriteBatch());

    }
}
