package com.beerpong.game.view.levels;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.beerpong.game.BeerPong;
import com.beerpong.game.view.levels.LevelView;

/**
 * Created by Sofia on 6/8/2017.
 */

public class HardView implements LevelView {

    private float max_zoom = 1.5f;
    private float min_zoom =0.5f;

    //ANDA TER A FLORESTA SUA SLUT, bjinho do teu cunhazinho ;)

    private boolean zooming = true;


    @Override
    public void updateCamera(OrthographicCamera camera) {

        Gdx.app.log("MyTag", String.valueOf(camera.zoom));

        if(zooming){
            camera.zoom+= 0.01;

            if(Math.abs(camera.zoom - max_zoom) <=0.05)
                zooming = false;
        }else{
            camera.zoom-=0.01;

            if(Math.abs(camera.zoom - min_zoom) <=0.05)
                zooming = true;

        }

    }

    @Override
    public void drawEntities(BeerPong game) {

    }
}
