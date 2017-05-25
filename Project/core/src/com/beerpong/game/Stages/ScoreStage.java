package com.beerpong.game.Stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.beerpong.game.BeerPong;


/**
 * Created by Sofia on 5/24/2017.
 */

public class ScoreStage extends Stage{

    static final int VIEWPORT_WIDTH = 400;

    private final Label scoreLabel;

    public ScoreStage(BeerPong game){
        float ratio = ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());
        setViewport(new FitViewport(VIEWPORT_WIDTH,VIEWPORT_WIDTH *ratio));

        scoreLabel = new Label("0", new Label.LabelStyle(new BitmapFont(), null));
        scoreLabel.setPosition(10,VIEWPORT_WIDTH*ratio -30);
        scoreLabel.setColor(Color.WHITE);
        addActor(scoreLabel);

    }


    public void setScore(int score){
        scoreLabel.setText("" +score);
    }



}
