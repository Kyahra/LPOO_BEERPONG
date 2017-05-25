package com.beerpong.game.Stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.beerpong.game.Actors.BallActor;
import com.beerpong.game.Actors.GroundActor;
import com.beerpong.game.BeerPong;

/**
 * Created by Sofia on 5/24/2017.
 */

public class GameStage extends Stage {
    public static final int VIEWPORT_WIDTH =4;
    static final float PIXEL_TO_METER = 0.22f/200;

    float viewport_height;

    BeerPong game;
    Texture background;

    private final World world;
    private final BallActor ballActor;
    private  Body ballBody;
    // private final Sound splashSound;
    // private final Sound backgroundSound;

    public GameStage(BeerPong game){

        this.game = game;

        // Setting Viewport
        float ratio = ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());
        viewport_height = VIEWPORT_WIDTH /PIXEL_TO_METER * ratio;

        setViewport(new FitViewport(VIEWPORT_WIDTH/PIXEL_TO_METER,viewport_height));

        game.getAssetManager().load("background.png", Texture.class);
        game.getAssetManager().load("ball.png",Texture.class);
        game.getAssetManager().finishLoading();

        ballActor = new BallActor(game);
       // ballActor.setPosition((VIEWPORT_WIDTH)/PIXEL_TO_METER,(VIEWPORT_WIDTH*ratio)/PIXEL_TO_METER);
    //    ballActor.setPosition(0,0);
        addActor(ballActor);

        world =new World(new Vector2(0,-3),true);
        ballBody = ballActor.createBody(world);

        addLimitsActors();

        ballActor.addListener(new ActorGestureListener() {
            @Override
            public void pan (InputEvent event, float x, float y, float deltaX, float deltaY) {
                Vector2 vector = new Vector2(-deltaX/10, -deltaY/10);
                vector.rotateRad(ballBody.getAngle());
                ballBody.applyForceToCenter(vector, true);
            }
        });


        background = game.getAssetManager().get("background.png");


    }

    @Override
    public void draw(){

        game.getSpriteBatch().begin();
        game.getSpriteBatch().draw(background,0,0,getViewport().getScreenWidth(),getViewport().getScreenHeight());
        game.getSpriteBatch().end();

        super.draw();
    }

    @Override
    public void act(float delta) {
        super.act(delta);



        // Step the world
        world.step(delta, 6, 2);

     // Update the ball actor position
        ballActor.setRotation((float) Math.toDegrees(ballBody.getAngle()));
        ballActor.setPosition(ballBody.getPosition().x / PIXEL_TO_METER, ballBody.getPosition().y / PIXEL_TO_METER);


    }

    public void addLimitsActors(){

        float ratio = ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());

        GroundActor groundActor = new GroundActor(VIEWPORT_WIDTH,0.1f,0,0);
        addActor(groundActor);

        GroundActor roofActor = new GroundActor(VIEWPORT_WIDTH,0.1f,0,VIEWPORT_WIDTH *ratio);
        addActor(roofActor);

        GroundActor leftWallActor = new GroundActor(0.1f,VIEWPORT_WIDTH * ratio,0,0);
        addActor(leftWallActor);

        GroundActor rightWallActor = new GroundActor(0.1f,VIEWPORT_WIDTH * ratio,VIEWPORT_WIDTH,0);
        addActor(rightWallActor);


        groundActor.createBody(world);
        roofActor.createBody(world);
        leftWallActor.createBody(world);
        rightWallActor.createBody(world);


    }




}
