package com.beerpong.game.view;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
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
    public static final float PIXEL_TO_METER =  0.007f;
    public static  int VIEWPORT_WIDTH =20;
    private static final boolean DEBUG_PHYSICS = false;

    private final BeerPong game;


    private GestureDetector gestureDetecture;
    Texture background;

    private final OrthographicCamera camera;
    private Box2DDebugRenderer debugRenderer;
    private Matrix4 debugCamera;

    public GameView(BeerPong game){
        this.game = game;

        loadAssets();

        camera = createCamera();



        gestureDetecture = new GestureDetector(this);
        Gdx.input.setInputProcessor(gestureDetecture);

        background = game.getAssetManager().get("background.png",Texture.class);

    }

    private OrthographicCamera createCamera() {
        OrthographicCamera camera = new OrthographicCamera(VIEWPORT_WIDTH / PIXEL_TO_METER, VIEWPORT_WIDTH / PIXEL_TO_METER * ((float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth()));

        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();

        if (DEBUG_PHYSICS) {
            debugRenderer = new Box2DDebugRenderer();
            debugCamera = camera.combined.cpy();
            debugCamera.scl(1 / PIXEL_TO_METER);
        }

        return camera;
    }

    private void loadAssets() {

        game.getAssetManager().load("background.png", Texture.class);
        game.getAssetManager().load("ball.png",Texture.class);
        game.getAssetManager().finishLoading();
    }

    @Override
    public void render(float delta){

        GameController.getInstance().update(delta);


        camera.update();
        game.getSpriteBatch().setProjectionMatrix(camera.combined);
        
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        
        game.getSpriteBatch().begin();

        drawBackground();
        drawEntities();
        game.getSpriteBatch().end();

        if (DEBUG_PHYSICS) {
            debugCamera = camera.combined.cpy();
            debugCamera.scl(1 / PIXEL_TO_METER);
            debugRenderer.render(GameController.getInstance().getWorld(), debugCamera);
        }
        

    }

    private void drawBackground() {
        game.getSpriteBatch().draw(background,0,0,camera.viewportWidth,camera.viewportHeight);
    }

    private void drawEntities(){
        BallModel ball = GameModel.getInstance().getBall();
        EntityView view = ViewFactory.makeView(game,ball);
        view.update(ball);
        view.draw(game.getSpriteBatch());
    }

/*
    @Override
    public void resize(int width, int height){
        viewport.update(width,height);


    }

*/
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
