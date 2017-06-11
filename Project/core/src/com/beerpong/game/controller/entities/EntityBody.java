package com.beerpong.game.controller.entities;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.beerpong.game.controller.GameController;
import com.beerpong.game.model.entities.EntityModel;

import static com.beerpong.game.view.GameView.PIXEL_TO_METER;

/**
 * Created by Sofia on 5/27/2017.
 */


/**
 * Entity body class
 *
 */
public abstract class EntityBody {



    Body body;
   private float[] vertexes;

    /**
     * Helper method to create a polygon fixture represented by a set of vertexes.
     *
     * @param body        The body the fixture is to be attached to.

     * @param density     The density of the fixture. How heavy it is in relation to its area.
     * @param friction    The friction of the fixture. How slippery it is.
     * @param restitution The restitution of the fixture. How much it bounces.
     *
     */
    final void createFixture(Body body,float density, float friction, float restitution) {


        PolygonShape polygon = new PolygonShape();
        polygon.set(vertexes);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygon;

        fixtureDef.density = density;
        fixtureDef.friction = friction;
        fixtureDef.restitution = restitution;


        body.createFixture(fixtureDef);

        polygon.dispose();
    }

    public void setShape( float[] vertexes, int width, int height){
        for (int i = 0; i < vertexes.length; i++) {
            if (i % 2 == 0) vertexes[i] -= width / 2;
            if (i % 2 != 0) vertexes[i] -= height / 2;

            if (i % 2 != 0) vertexes[i] *= -1;

            vertexes[i] *= PIXEL_TO_METER;
        }

        this.vertexes = vertexes;

    }





    /**
     * Wraps the getAngle method from the Box2D body class.
     *
     * @return the angle of rotation of this body.
     */
    public float getAngle() {
        return body.getAngle();
    }



    /**
     * Wraps the applyForceToCenter method from the Box2D body class.
     *
     * @param forceX the x-component of the force to be applied
     * @param forceY the y-component of the force to be applied
     * @param awake  should the body be awaken
     */
    public void applyForceToCenter(float forceX, float forceY, boolean awake) {
        body.applyForceToCenter(forceX, forceY, awake);
    }



    public Vector2 getLinearVelocity(){
        return body.getLinearVelocity();
    }

}