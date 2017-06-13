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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.beerpong.game.BeerPong;
import com.beerpong.game.controller.GameController;
import com.beerpong.game.model.GameModel;
import com.beerpong.game.model.entities.BallModel;
import com.beerpong.game.model.entities.CupModel;
import com.beerpong.game.view.entities.EntityView;
import com.beerpong.game.view.levels.EasyView;
import com.beerpong.game.view.levels.HardView;
import com.beerpong.game.view.levels.LevelView;
import com.beerpong.game.view.levels.MediumView;


/**
 * Created by Sofia on 5/24/2017.
 */

/**
 * The game view class. Everything that is related to the view is implemented here.
 *
 */
public class GameView extends ScreenAdapter implements GestureDetector.GestureListener {

    /**
     * The pixel to meter value
     *
     */
    public static final float PIXEL_TO_METER =  0.007f;

    /**
     * Viewport width
     *
     */
    public static  int VIEWPORT_WIDTH =20;

    /**
     * A boolean that sets the debug physics state
     *
     */
    private static final boolean DEBUG_PHYSICS = false;

    /**
     * The game
     *
     */
    private final BeerPong game;

    /**
     * The level view
     *
     */
    private static LevelView level;

    /**
     * The gesture detecture
     *
     */
    private GestureDetector gestureDetecture;

    /**
     * The Orthographic Camera
     *
     */
    private final OrthographicCamera camera;

    /**
     * The debug renderer
     *
     */
    private Box2DDebugRenderer debugRenderer;

    /**
     * The debug camera
     *
     */
    private Matrix4 debugCamera;

    /**
     * The music
     *
     */
    private Music music;

    /**
     * The background texture
     *
     */
    Texture background;

    /**
     * The stage
     *
     */
    private Stage stage;

    /**
     * The game view contructor
     *
     * @param game the game
     * @param level the level
     */
    public GameView(BeerPong game, int level){


        this.game = game;

        setLevel(level);

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


        GameModel.setTest();


    }

    /**
     * The Orthographic Camera contructor
     *
     * @return the camera
     */
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

    /**
     * Creates a rewind button
     *
     */
    private void createRewindButton(){

        Texture rewindButton = game.getAssetManager().get("rewind.png",Texture.class);
        TextureRegion myTextureRegion = new TextureRegion(rewindButton);
        TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        ImageButton button = new ImageButton(myTexRegionDrawable);

        button.setPosition(Gdx.graphics.getWidth() -250,Gdx.graphics.getHeight()-200);

        button.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                GameController.reset();
                GameModel.reset();
                return true;
            }
        });

        stage.addActor(button);

    }

    /**
     * Loads the assets
     *
     */
    private void loadAssets() {


        game.getAssetManager().load("background.png", Texture.class);
        game.getAssetManager().load("ball.png",Texture.class);
        game.getAssetManager().load("cup.png",Texture.class);
        game.getAssetManager().load("table.png", Texture.class);
        game.getAssetManager().load("rewind.png",Texture.class);

        game.getAssetManager().finishLoading();
    }


    /**
     * Renders the game view
     *
     * @param delta the delta
     */
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

        int score;
        score = GameController.getInstance().isOver();
        if(score !=0)

        game.setScore(score);

    }


    /**
     * Draws Entities
     *
     */
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

    /**
     * Draws the bacjground
     *
     */
    private void drawBackground() {
        game.getSpriteBatch().draw(background,0,0,camera.viewportWidth,camera.viewportHeight);
    }


    /**
     * Checks if a touchDown movement is performed
     *
     * @param x the x value
     * @param y the y value
     * @param pointer the pointer value
     * @param button the button value
     * @return true if the movement is performed, false otherwise
     */
    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    /**
     * Checks if a tap movement is performed
     *
     * @param x the x value
     * @param y the y value
     * @param count the count value
     * @param button the button value
     * @return true if the movement is performed, false otherwise
     */
    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    /**
     * Checks if a longPress movement is performed
     *
     * @param x the x value
     * @param y the y value
     * @return true if the movement is performed, false otherwise
     */
    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    /**
     * Checks if a fling movement is performed
     *
     * @param velocityX the x velocity value
     * @param velocityY the y velocity value
     * @param button the button value
     * @return true if the movement is performed, false otherwise
     */
    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
       GameController.getInstance().shootBall(velocityX,velocityY);
        return false;

    }

    /**
     * Checks if a pan movement is performed
     *
     * @param x the x value
     * @param y the y value
     * @param deltaX the delta x value
     * @param deltaY the delta y value
     * @return true if the movement is performed, false otherwise
     */
    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {

        return true;
    }

    /**
     * Checks if a panStop movement is performed
     *
     * @param x the x value
     * @param y tha y value
     * @param pointer the pointer value
     * @param button the button value
     * @return true if the movement is performed, false otherwise
     */
    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    /**
     * Checks if a zoom movement is performed
     *
     * @param initialDistance the inicial distance
     * @param distance the distance
     * @return true if the movement is performed, false otherwise
     */
    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    /**
     * Checks if a pinch movement is performed
     *
     * @param initialPointer1 the inicial pointer 1
     * @param initialPointer2 the inicial pointer 2
     * @param pointer1 the pointer 1
     * @param pointer2 the pointer 2
     * @return true if the movement is performed, false otherwise
     */
    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    /**
     * Checks if a pinchStop movement is performed
     *
     */
    @Override
    public void pinchStop() {

    }

    public void setLevel(int level) {
        switch(level){
            case 1:
                this.level = new EasyView();
                break;
            case 2:
                this.level = new MediumView();
                break;
            case 3:
                this.level = new HardView();
                break;
            default:
                break;
        }
    }
}
