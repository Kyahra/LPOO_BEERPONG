package com.aor.bouncing;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Daniel on 18/05/2017.
 */

class Controller{

    public float power = 50f;
    public float angle = 0f;

}

class ProjectileEquation {

    public float gravity;
    public Vector2 startVelocity = new Vector2();
    public Vector2 startPoint = new Vector2();

    public float getX(float t) {
        return startVelocity.x * t + startPoint.x;
    }

    public float getY(float t) {
        return 0.5f * gravity * t * t + startVelocity.y * t + startPoint.y;
    }

}



class TrajectoryActor extends Actor {

        private Controller controller;
        private ProjectileEquation projectileEquation;
        private Sprite sprite;

        public int trajectoryPointCount = 30;
        public float timeSeparation = 1f;

        public TrajectoryActor(Controller controller, float gravity, Sprite trajectorySprite, BouncingBalls game) {
            this.controller = controller;
            this.sprite = trajectorySprite;
            this.projectileEquation = new ProjectileEquation();
            this.projectileEquation.gravity = gravity;

            Texture texture = game.getAssetManager().get("ball.png");
            sprite = new Sprite(texture);

            // Necessary so that inputs events are registered correctly
            setWidth(texture.getWidth());
            setHeight(texture.getHeight());

            // Necessary so that rotations are correctly processed
            setOrigin(getWidth() / 2, getHeight() / 2);
            sprite.setOrigin(getWidth() / 2, getHeight() / 2);
        }

        @Override
        public void act(float delta) {
            super.act(delta);
            projectileEquation.startVelocity.set(controller.power, 0f);
            projectileEquation.startVelocity.rotate(controller.angle);

        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            float t = 0f;
            float width = getWidth();
            float height = getHeight();

            float timeSeparation = this.timeSeparation;

            for (int i = 0; i < trajectoryPointCount; i++) {
                float x = getX() + projectileEquation.getX(t);
                float y = getY() + projectileEquation.getY(t);

                batch.setColor(getColor());
                batch.draw(sprite, x, y, width, height);

                t += timeSeparation;
            }
        }

        @Override
        public Actor hit(float x, float y,boolean touchable) {
            return null;
        }

    }


