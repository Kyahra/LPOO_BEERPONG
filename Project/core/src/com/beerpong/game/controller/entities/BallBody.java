package com.beerpong.game.controller.entities;

import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.beerpong.game.model.entities.BallModel;



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
    public BallBody(World world, BallModel model) {
        super(world, model);


        CircleShape circle = new CircleShape();
        circle.setRadius(0.05f); // 22cm / 2

        // Create ball fixture
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = .5f;      // how heavy is the ball
        fixtureDef.friction =  .5f;    // how slippery is the ball
        fixtureDef.restitution =  .5f; // how bouncy is the ball

        // Attach fixture to body
        body.createFixture(fixtureDef);

        // Dispose of circle shape
        circle.dispose();
    }
}
