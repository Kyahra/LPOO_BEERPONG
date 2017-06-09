package com.beerpong.game.view;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.beerpong.game.BeerPong;
import com.beerpong.game.controller.GameController;
import com.beerpong.game.model.GameModel;
import com.beerpong.game.model.entities.BallModel;
import com.beerpong.game.model.entities.CupModel;
import com.beerpong.game.view.entities.EntityView;
import com.beerpong.game.view.levels.LevelView;

import javax.swing.text.View;


/**
 * Created by Sofia on 5/24/2017.
 */

public class GameView extends ScreenAdapter implements GestureDetector.GestureListener {
    public static final float PIXEL_TO_METER =  0.007f;
    public static  int VIEWPORT_WIDTH =20;
    private static final boolean DEBUG_PHYSICS = false;

    private final BeerPong game;
    private final LevelView level;

    private GestureDetector gestureDetecture;


    private final OrthographicCamera camera;
    private Box2DDebugRenderer debugRenderer;
    private Matrix4 debugCamera;

    private Music music;
    Texture background;

    private Stage stage;



    public GameView(BeerPong game, LevelView level){
        this.game = game;
        this.level = level;

        loadAssets();

        camera = createCamera();

        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        createRewindButton();

        background = game.getAssetManager().get("background.png",Texture.class);

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        gestureDetecture = new GestureDetector(this);

        inputMultiplexer.addProcessor(gestureDetecture);
        inputMultiplexer.addProcessor(stage);

        Gdx.input.setInputProcessor(inputMultiplexer);



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

    private void createRewindButton(){

        Texture rewindButton = game.getAssetManager().get("rewind.png",Texture.class);
        TextureRegion myTextureRegion = new TextureRegion(rewindButton);
        TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        ImageButton button = new ImageButton(myTexRegionDrawable);

        button.setPosition(Gdx.graphics.getWidth() -250,Gdx.graphics.getHeight()-200);

        button.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed"); //** Usually used to start Game, etc. **//
                GameController.reset();
                GameModel.reset();
                return true;
            }
        });

        stage.addActor(button);





    }


    private void loadAssets() {


        game.getAssetManager().load("background.png", Texture.class);
        game.getAssetManager().load("ball.png",Texture.class);
        game.getAssetManager().load("cup.png",Texture.class);
        game.getAssetManager().load("table.png", Texture.class);
        game.getAssetManager().load("rewind.png",Texture.class);

        game.getAssetManager().finishLoading();
    }



    @Override
    public void render(float delta){

        GameController.getInstance().update(delta);



        camera.update();
        this.level.updateCamera(camera);
        game.getSpriteBatch().setProjectionMatrix(camera.combined);
        
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        
        game.getSpriteBatch().begin();
        drawBackground();
        drawEntities();
        level.drawEntities(game);
        game.getSpriteBatch().end();

        if (DEBUG_PHYSICS) {
            debugCamera = camera.combined.cpy();
            debugCamera.scl(1 / PIXEL_TO_METER);
            debugRenderer.render(GameController.getInstance().getWorld(), debugCamera);


        }

        stage.act();
        stage.draw();

        if(GameController.getInstance().isOver()){
            game.showScore();

        }

        

    }

    public void drawEntities() {
        EntityView view;

        BallModel ball = GameModel.getInstance().getBall();
        view = new EntityView(game, "ball.png");
        view.update(ball);
        view.draw(game.getSpriteBatch());

        CupModel cup = GameModel.getInstance().getCup();
        view = new EntityView(game, "cup.png");
        view.update(cup);
        view.draw(game.getSpriteBatch());

    }

    private void drawBackground() {
        game.getSpriteBatch().draw(background,0,0,camera.viewportWidth,camera.viewportHeight);
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
