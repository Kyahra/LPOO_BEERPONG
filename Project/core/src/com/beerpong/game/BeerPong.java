package com.beerpong.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.beerpong.game.controller.GameController;
import com.beerpong.game.model.GameModel;
import com.beerpong.game.view.GameView;


public class BeerPong extends Game  {




	public interface AndroidAPIAdapter{
		public void showScore();
	}

	AndroidAPIAdapter androidAPIAdapter;

	public BeerPong(AndroidAPIAdapter androidAPIAdapter){
		super();
		this.androidAPIAdapter = androidAPIAdapter;
	}

	private AssetManager assetManager;
	private SpriteBatch batch;


	@Override
	public void create () {
		assetManager = new AssetManager();
		batch = new SpriteBatch();

		//assetManager.load("audio/music/whiplash.mp3", Music.class);
		//assetManager.finishLoading();

		GameController.reset();
		GameModel.reset();

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

	public void showScore(){

		if(androidAPIAdapter!=null)
			androidAPIAdapter.showScore();
	}
}
