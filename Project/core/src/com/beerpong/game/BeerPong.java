package com.beerpong.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.beerpong.game.controller.GameController;
import com.beerpong.game.controller.levels.EasyController;
import com.beerpong.game.controller.levels.MediumController;
import com.beerpong.game.model.GameModel;
import com.beerpong.game.view.GameView;
import com.beerpong.game.view.levels.EasyView;
import com.beerpong.game.view.levels.HardView;
import com.beerpong.game.view.levels.MediumView;


public class BeerPong extends Game  {


	public interface AndroidAPIAdapter{
		public void setScore(int score);
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
	private	static boolean exited = false;


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
				GameController.setLevelController((new EasyController()));
				setScreen(new GameView(this, new EasyView()));
				break;
			case 2:
				GameController.setLevelController(((new MediumController())));
				setScreen(new GameView(this, new MediumView()));
				break;
			case 3:
				GameController.setLevelController(((new MediumController())));
				setScreen(new GameView(this,new HardView()));
				break;
			default:
				break;


		}
	}

	@Override
	public void dispose(){
		batch.dispose();
		assetManager.dispose();
		exited = true;
	}

	public static boolean isExited() {
		return exited;
	}

	public static void setExited(boolean exited) {
		exited = exited;
	}

	public AssetManager getAssetManager(){
		return  assetManager;
	}

	public SpriteBatch getSpriteBatch(){return batch;}

	public void setScore(int score){

		if(androidAPIAdapter!=null)
			androidAPIAdapter.setScore(score);

		Gdx.app.exit();



	}
}
