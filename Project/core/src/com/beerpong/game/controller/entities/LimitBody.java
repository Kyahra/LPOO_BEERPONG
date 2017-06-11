package com.beerpong.game.controller.entities;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.beerpong.game.model.entities.EntityModel;



/**
 * Created by Sofia on 5/27/2017.
 */


/**
 * Limit body class
 *
 */
public class LimitBody extends EntityBody {

    /**
     * Constructs a limit body representing a model in a certain world.
     *
     * @param world The world this body lives on.
     * @param model The model representing the body.
     * @param width The width of the body.
     * @param width The height of the body.
     */
    public LimitBody(World world, EntityModel model, float width, float height) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(model.getX(), model.getY());
        bodyDef.angle = model.getRotation();

        body = world.createBody(bodyDef);
        body.setUserData(model);

        PolygonShape rectangle = new PolygonShape();

        rectangle.setAsBox(width,height);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = rectangle;
        fixtureDef.density = .5f;      // how heavy is the ground
        fixtureDef.friction =  .5f;    // how slippery is the ground
        fixtureDef.restitution =  .5f; // how bouncy is the ground


        body.createFixture(fixtureDef);

        rectangle.dispose();

    }

}
