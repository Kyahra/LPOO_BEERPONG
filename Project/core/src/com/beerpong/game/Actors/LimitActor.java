package com.beerpong.game.Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.beerpong.game.BeerPong;


import static com.beerpong.game.Stages.GameStage.VIEWPORT_WIDTH;

/**
 * Created by Sofia on 5/25/2017.
 */

public class LimitActor extends Actor{
    float width;
    float height;
    float x;
    float y;


    public LimitActor(float width, float height, float x, float y){
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;



    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

            super.draw(batch,parentAlpha);

    }


    public Body createBody(World world) {
        // Create the ball body definition
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x,y);

        // Create the ball body
        Body body = world.createBody(bodyDef);
     //   body.setTransform(0, 0, 0); // Bottom left corner
        body.setTransform(x, y, 0);

        // Create rectangular shape
        PolygonShape rectangle = new PolygonShape();
      ///  rectangle.setAsBox(VIEWPORT_WIDTH, 0.1f); // Viewport width and 50cm height
        rectangle.setAsBox(width,height);


        // Create ground fixture
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = rectangle;
        fixtureDef.density = .5f;      // how heavy is the ground
        fixtureDef.friction =  .5f;    // how slippery is the ground
        fixtureDef.restitution =  .5f; // how bouncy is the ground

        // Attach fixture to body
        body.createFixture(fixtureDef);

        // Dispose of circle shape
        rectangle.dispose();

        return body;
    }

}
