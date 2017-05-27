package com.beerpong.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.beerpong.game.BeerPong;
import com.beerpong.game.Stages.GameStage;
import com.beerpong.game.Stages.ScoreStage;

/**
 * Created by Sofia on 5/24/2017.
 */

public class PlayScreen extends ScreenAdapter {

    private final GameStage gameStage;
    //private final ScoreStage scoreStage;

    public PlayScreen(BeerPong game){
        this.gameStage = new GameStage(game);
       // this.scoreStage = new ScoreStage(game);


        Gdx.input.setInputProcessor(gameStage);
    }

    @Override
    public void render(float delta){
        super.render(delta);

        Gdx.gl.glClearColor( 0, 0, 0, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        // Steps the stage
        gameStage.act(delta);
        //scoreStage.setScore((int)gameStage.getMaximumHeight());

        // Draws the stage
        gameStage.draw();
     //   scoreStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gameStage.getViewport().update(width, height, true);
      //  scoreStage.getViewport().update(width, height, true);
    }
}
