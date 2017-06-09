package com.beerpong.game.controller.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.beerpong.game.model.entities.EntityModel;


/**
 * Created by Sofia on 6/1/2017.
 */

public class CupBody extends EntityBody {


    /**
     * Constructs a body representing a model in a certain world.
     *
     * @param world The world this body lives on.
     * @param model The model representing the body.
     */
    public CupBody(World world, EntityModel model) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(model.getX(), model.getY());
        bodyDef.angle = model.getRotation();

        body = world.createBody(bodyDef);
        body.setUserData(model);


        float density = 2f, friction = 1f, restitution = 0f;
        int width = 234, height = 330;


        // left side
        createFixture(body, new float[]{
                0,20, 40,330,65,330,25,20
        }, width, height, density, friction, restitution, CUP_BODY, (short) (BALL_BODY | LIMIT_BODY));

        // bottom
        createFixture(body, new float[]{
                40,290,40,330,196,330,196,290
        }, width, height, 3f, friction, restitution, CUP_BODY, (short) (BALL_BODY | LIMIT_BODY));

       // right side
        createFixture(body, new float[]{
                215,20,234,20,175,330,193,330
        }, width, height, density, friction, restitution, CUP_BODY, (short) (BALL_BODY | LIMIT_BODY));



    }
}
