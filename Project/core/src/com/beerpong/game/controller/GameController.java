package com.beerpong.game.controller;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

import com.badlogic.gdx.utils.Array;
import com.beerpong.game.controller.entities.BallBody;
import com.beerpong.game.controller.entities.CupBody;
import com.beerpong.game.controller.entities.LimitBody;

import com.beerpong.game.controller.levels.LevelController;
import com.beerpong.game.model.GameModel;
import com.beerpong.game.model.entities.BallModel;
import com.beerpong.game.model.entities.CupModel;
import com.beerpong.game.model.entities.EntityModel;

import static com.beerpong.game.view.GameView.VIEWPORT_WIDTH;


/**
 * Created by Sofia on 5/27/2017.
 */


/**
 * The game controller class
 *
 */
public class GameController implements ContactListener {

    /**
     * The Cup floor id
     *
     */
    public static final float CUP_FLOOR_ID =  3f;

    /**
     * The game controller instance
     *
     */
    private static GameController instance;

    /**
     * The Game controller world
     *
     */
    private final World world;

    /**
     * The ball body
     *
     */
    private final BallBody ballBody;

    /**
     * The level
     *
     */
    private static LevelController level;

    /**
     * The score
     */
    private int score =0;

    /**
     * Boolean, true if you won the game, false otherwise
     *
     */
    private boolean gameWon = false;

    /**
     * Boolean, true if the ball is moving, false otherwise
     *
     */
    private boolean isBallMoving = false;

    /*
    * Initializes the game controller
    *
    * */
    private GameController(){

        world = new World(new Vector2(0,-10),true);

        ballBody = new BallBody(world, GameModel.getInstance().getBall());

        float ratio = ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());
        new CupBody(world, GameModel.getInstance().getCup());

        new LimitBody(world,GameModel.getInstance().getGround(), VIEWPORT_WIDTH*ratio*2, VIEWPORT_WIDTH*0.05f );
        new LimitBody(world,GameModel.getInstance().getLeftWall(),VIEWPORT_WIDTH*ratio*0.1f,VIEWPORT_WIDTH);
        new LimitBody(world,GameModel.getInstance().getRoof(),VIEWPORT_WIDTH*ratio*2,VIEWPORT_WIDTH*0.01f);
        new LimitBody(world,GameModel.getInstance().getRightWall(),VIEWPORT_WIDTH*ratio*0.1f,VIEWPORT_WIDTH);

        level.initializeElements(world);


        world.setContactListener(this);

    }

    /*
    * Accesses the game controller.
    * Gets game controller instance
    *
    */
    public static GameController getInstance() {
        if(instance == null)
            instance = new GameController();
        return instance;

    }


    /**
     * Updates the game controller
     *
     * @param delta the delta needed to be updated
     */
    public void update(float delta) {

        world.step(delta, 6, 2);

        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);

        for(Body  body:bodies) {

            ((EntityModel) body.getUserData()).setPosition(body.getPosition().x, body.getPosition().y);

        }
    }

    /**
     * Shoots the ball. Turns the delta-X and delta-Y values into a specific movement.
     *
     * @param delta_X the delta-X input
     * @param delta_Y the delta-Y input
     */
    public void shootBall(float delta_X, float delta_Y) {

        if(!isBallMoving) {

            Vector2 vector = new Vector2(delta_X / 400, -delta_Y / 400);
            vector.rotateRad(ballBody.getAngle());
            ballBody.applyForceToCenter(delta_X / 400, -delta_Y / 400, true);
            isBallMoving = true;

        }
    }

    /**
     * Begins the contact between two bodies
     *
     * @param contact the contact
     */
    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        checkBallInsideCup(fixtureA, fixtureB);
        checkBallInsideCup(fixtureB, fixtureA);

        score += 55;

    }

    /**
     * Checks whether the ball is inside the cup or not
     *
     * @param ball the ball fixture
     * @param cup the cup fixture
     *
     */
    private void checkBallInsideCup(Fixture ball, Fixture cup) {

        if(ball.getBody().getUserData() instanceof BallModel)
            if(cup.getBody().getUserData() instanceof CupModel)
                if(cup.getDensity() == CUP_FLOOR_ID)
                gameWon = true;

    }

    /**
     * Ends  the contact
     *
     * @param contact the contact
     */
    @Override
    public void endContact(Contact contact) {

    }

    /**
     * Pre solves the interactions
     *
     * @param contact the contact
     * @param oldManifold  the oldManifold
     */
    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    /**
     * Post solves interactions
     *
     * @param contact the contact
     * @param impulse the impulse
     */
    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }


    /**
     * Gets the world
     *
     * @return world the game controller world
     */
    public World getWorld() {
        return world;
    }

    /**
     * Resets the game controller
     */
    public static void reset(){

        instance = null;

    }

    /**
     * Checks whether the play is over or not
     *
     * @return the score of the play
     */
    public int isOver(){

        if(isBallMoving && ballBody.getLinearVelocity().isZero(0.5f))
            return score;

        if(gameWon)
            return score;


        return 0;

    }

    /**
     * Sets Level Controller
     *
     * @param level the level wanted
     */
    public static void setLevelController(LevelController level) {

        GameController.level = level;
    }

    /**
     * Gets the ball body
     *
     * @return the ball body
     */
    public BallBody getBallBody(){

        return ballBody;
    }

    /**
     * Checks whether the ball is moving or not
     *
     * @return  true if the ball is moving, false otherwise
     */
    public boolean isBallMoving(){
        return isBallMoving;
    }

}
