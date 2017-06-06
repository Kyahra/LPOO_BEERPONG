package com.beerpong.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.beerpong.game.controller.GameController;
import com.beerpong.game.controller.levels.EasyController;
import com.beerpong.game.model.GameModel;
import com.beerpong.game.view.GameView;
import com.beerpong.game.view.levels.EasyView;



public class BeerPong extends Game  {


	public interface AndroidAPIAdapter{
		public void showScore();
	}

	AndroidAPIAdapter androidAPIAdapter;
	int level;

	public BeerPong(AndroidAPIAdapter androidAPIAdapter, int level){
		super();
		this.androidAPIAdapter = androidAPIAdapter;
		this.level = level;
	}

	private AssetManager assetManager;
	private SpriteBatch batch;


	@Override
	public void create () {
		assetManager = new AssetManager();
		batch = new SpriteBatch();

		GameController.reset();
		GameModel.reset();

		startGame();

	}



	public void startGame(){


		switch(level){
			case 1:
				setScreen(new GameView(this, new EasyView()));
				GameController.getInstance().setLevel((new EasyController()));
				break;


		}


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
