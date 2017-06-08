package com.beerpong.game.view.levels;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.beerpong.game.BeerPong;

/**
 * Created by Sofia on 6/6/2017.
 */

public interface LevelView {

    public void updateCamera(OrthographicCamera camera);
    public void drawEntities(BeerPong game);


}
