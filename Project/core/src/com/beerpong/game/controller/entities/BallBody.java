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

/**
 * Ball body class
 *
 */
public class BallBody extends EntityBody {


    /**
     * Constructs a ball body according to
     * a ball model.
     *
     * @param world the physical world this  ball belongs to.
     * @param model the model representing this ball.
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

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = .04f;
        fixtureDef.friction =  .7f;
        fixtureDef.restitution =  .5f;


        body.createFixture(fixtureDef);

        circle.dispose();

    }

}
