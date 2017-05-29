package com.beerpong.game.view;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.beerpong.game.BeerPong;
import com.beerpong.game.controller.GameController;
import com.beerpong.game.model.GameModel;
import com.beerpong.game.model.entities.BallModel;
import com.beerpong.game.view.entities.EntityView;
import com.beerpong.game.view.entities.ViewFactory;




/**
 * Created by Sofia on 5/24/2017.
 */

public class GameView extends ScreenAdapter implements GestureDetector.GestureListener {
    public static final float PIXEL_TO_METER = 0.04f;
    public static  int VIEWPORT_WIDTH =20;
    public static  float VIEWPORT_HEIGHT;

    private final BeerPong game;
    private Viewport viewport;

    private GestureDetector gestureDetecture;
    Texture background;

    public GameView(BeerPong game){
        this.game = game;

        loadAssets();

        float ratio = ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());
        VIEWPORT_HEIGHT = VIEWPORT_WIDTH /PIXEL_TO_METER * ratio;
        viewport =  new FitViewport(VIEWPORT_WIDTH/PIXEL_TO_METER, VIEWPORT_HEIGHT);



        gestureDetecture = new GestureDetector(this);
        Gdx.input.setInputProcessor(gestureDetecture);

        background = game.getAssetManager().get("background.png",Texture.class);

    }

    private void loadAssets() {

        game.getAssetManager().load("background.png", Texture.class);
        game.getAssetManager().load("ball.png",Texture.class);
        game.getAssetManager().finishLoading();
    }

    @Override
    public void render(float delta){

        GameController.getInstance().update(delta);
        
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        
        game.getSpriteBatch().begin();

        drawBackground();
        drawEntities();
        game.getSpriteBatch().end();
        
        

    }

    private void drawBackground() {
        game.getSpriteBatch().draw(background,0,0,viewport.getScreenWidth(),viewport.getScreenHeight());
    }

    private void drawEntities(){
        BallModel ball = GameModel.getInstance().getBall();
        EntityView view = ViewFactory.makeView(game,ball);
        view.update(ball);
        view.draw(game.getSpriteBatch());
    }


    @Override
    public void resize(int width, int height){
        viewport.update(width,height);


    }


    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
       GameController.getInstance().shootBall(velocityX,velocityY);
        return false;

    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {

        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
