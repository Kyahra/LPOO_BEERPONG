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
        super(world, model);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(model.getX(), model.getY());
        bodyDef.angle = model.getRotation();


        float density = 2f, friction = 1f, restitution = 0f;
        int width = 234, height = 330;


        createFixture(body, new float[]{
                0,20, 40,330,55,330,15,20
        }, width, height, density, friction, restitution, CUP_BODY, (short) (BALL_BODY | LIMIT_BODY));

        createFixture(body, new float[]{
                30,300,30,330,204,330,204,300
        }, width, height, density, friction, restitution, CUP_BODY, (short) (BALL_BODY | LIMIT_BODY));

        createFixture(body, new float[]{
                218,17,234,17,178,330,193,330
        }, width, height, density, friction, restitution, CUP_BODY, (short) (BALL_BODY | LIMIT_BODY));



    }
}
