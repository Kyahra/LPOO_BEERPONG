package com.beerpong.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.beerpong.game.BeerPong;
import com.beerpong.game.model.entities.EntityModel;

/**
 * Created by Sofia on 5/27/2017.
 */

public class BallView extends EntityView {


    public BallView(BeerPong game) {
        super(game);
    }

    @Override
    public Sprite createSprite(BeerPong game) {
        Texture texture = game.getAssetManager().get("ball.png");


        return new Sprite(texture,texture.getWidth(), texture.getHeight());

    }


}