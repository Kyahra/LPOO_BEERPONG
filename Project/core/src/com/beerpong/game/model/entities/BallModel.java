package com.beerpong.game.model.entities;

/**
 * Created by Sofia on 5/27/2017.
 */

public class BallModel extends EntityModel {

    public BallModel(float x, float y,float rotation){
        super(x,y,rotation);
    }

    @Override
    public ModelType getType() {
        return ModelType.BALL;
    }
}
