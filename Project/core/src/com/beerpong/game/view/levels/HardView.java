package com.beerpong.game.view.levels;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.beerpong.game.BeerPong;
import com.beerpong.game.model.GameModel;
import com.beerpong.game.model.entities.SimpleModel;
import com.beerpong.game.view.entities.EntityView;

/**
 * Created by Sofia on 6/8/2017.
 */


/**
 * The view that is responsible for the hard level
 *
 */
public class HardView implements LevelView {


    /**
     * Maximum value that the zoom is able to take
     */
    private float MAX_ZOOM = 1f;

    /**
     * Minimum value that the zoom is able to take
     */
    private float MIN_ZOOM =0.9f;

    /**
     * Maximum value that the x is able to take
     */
    private float MAX_X =1470;

    /**
     * Minimum value that the x is able to take
     */
    private float MIN_X = 1360;

    /**
     * Boolean that checks the zoom
     */
    private boolean zooming = false;

    /**
     * Boolean that checks if it is left moving
     */
    private boolean movingLeft = true;





    @Override
    public void updateCamera(OrthographicCamera camera) {

        Gdx.app.log("x:", String.valueOf(camera.position.x));
        Gdx.app.log("y:", String.valueOf(camera.position.y));


        if(zooming){
            camera.zoom+= 0.002;
            if(Math.abs(camera.zoom - MAX_ZOOM) <=0.01)
                zooming = false;
        }else{
            camera.zoom-=0.002;
            if(Math.abs(camera.zoom - MIN_ZOOM) <=0.01)
                zooming = true;

        }




        if(movingLeft){

            camera.position.x-=1;
            camera.position.y-=1;
            camera.rotate(0.025f);


            if(Math.abs(camera.position.x - MIN_X) <=0.5)
                movingLeft = false;

        }else{
            camera.position.x+= 1;
            camera.position.y+=1;

            camera.rotate(-0.025f);



            if(Math.abs(camera.position.x - MAX_X) <=0.5)
                movingLeft = true;


        }

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
