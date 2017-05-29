package com.beerpong.game.view.entities;

import com.beerpong.game.BeerPong;
import com.beerpong.game.model.entities.EntityModel;

import java.util.HashMap;
import java.util.Map;

import static com.beerpong.game.model.entities.EntityModel.ModelType.BALL;

/**
 * Created by Sofia on 5/27/2017.
 */



public class ViewFactory {
    private static Map<EntityModel.ModelType, EntityView> cache =
            new HashMap<EntityModel.ModelType, EntityView>();

    public static EntityView makeView(BeerPong game, EntityModel model) {
        if (!cache.containsKey(model.getType())) {
            if (model.getType() == BALL)
                cache.put(model.getType(), new BallView(game));

        }
        return cache.get(model.getType());
    }
}