package com.beerpong.game.model.entities;

/**
 * Created by Sofia on 5/27/2017.
 */

public class SimpleModel extends EntityModel {

    ModelType type;

    public SimpleModel(float x, float y,float rotation, ModelType type){
        super(x,y,rotation);
        this.type = type;
    }

    public ModelType getType(){
        return type;
    }


}
