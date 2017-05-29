package com.beerpong.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.beerpong.game.view.GameView;

public class BeerPong extends Game {

	private AssetManager assetManager;
	private SpriteBatch batch;


	@Override
	public void create () {
		assetManager = new AssetManager();
		batch = new SpriteBatch();


		startGame();
	}

	public void startGame(){
		setScreen(new GameView(this));

	}

	@Override
	public void dispose(){
		batch.dispose();
		assetManager.dispose();
	}

	public AssetManager getAssetManager(){
		return  assetManager;
	}

	public SpriteBatch getSpriteBatch(){return batch;}
}
