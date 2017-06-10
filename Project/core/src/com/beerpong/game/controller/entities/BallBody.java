package com.beerpong.game.controller.entities;


import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.beerpong.game.model.entities.EntityModel;
import com.beerpong.game.model.entities.SimpleModel;



/**
 * Created by Sofia on 5/27/2017.
 */

public class BallBody extends EntityBody {


    /**
     * Constructs a space ship body according to
     * a space ship model.
     *
     * @param world the physical world this space ship belongs to.
     * @param model the model representing this space ship.
     */
    public BallBody(World world, EntityModel model) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(model.getX(), model.getY());
        bodyDef.angle = model.getRotation();

        body = world.createBody(bodyDef);
        body.setUserData(model);


        CircleShape circle = new CircleShape();
        circle.setRadius(0.34f);

        // Create ball fixture
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = .04f;      // how heavy is the ball
        fixtureDef.friction =  .7f;    // how slippery is the ball
        fixtureDef.restitution =  .5f; // how bouncy is the ball
        fixtureDef.filter.categoryBits = BALL_BODY;
        fixtureDef.filter.maskBits = LIMIT_BODY | CUP_BODY;

        // Attach fixture to body
        body.createFixture(fixtureDef);

        // Dispose of circle shape
        circle.dispose();
    }

}
