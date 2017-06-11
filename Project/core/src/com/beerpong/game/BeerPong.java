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

/**
 * The game class. This is responsible for everythings that happens.
 *
 * Takes care of all MCV entities
 *
 */
public class BeerPong extends Game  {

	/**
	 * The API Adapter implementation
	 *
	 */
	public interface AndroidAPIAdapter{
		public void setScore(int score);
	}

	/**
	 * Android API Adapter
	 *
	 */
	AndroidAPIAdapter androidAPIAdapter;

	/**
	 * The game level
	 *
	 */
	int level;

	/**
	 * Starts the game
	 *
	 * @param androidAPIAdapter
	 * @param level
	 */
	public BeerPong(AndroidAPIAdapter androidAPIAdapter, int level){
		super();
		this.androidAPIAdapter = androidAPIAdapter;
		this.level = level;


	}

	/**
	 * The asset manager
	 *
	 */
	private AssetManager assetManager;

	/**
	 * The sprite batch
	 *
	 */
	private SpriteBatch batch;

	/**
	 * Boolean, true if the game has exited, false otherwise
	 *
	 */
	private	static boolean exited = false;

	/**
	 * Creates the game
	 *
	 */
	@Override
	public void create () {
		assetManager = new AssetManager();
		batch = new SpriteBatch();


		GameController.reset();
		GameModel.reset();

		startGame();

	}


	/**
	 * Starts the game
	 *
	 */
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

	/**
	 * Disposes
	 *
	 */
	@Override
	public void dispose(){
		batch.dispose();
		assetManager.dispose();
		exited = true;
	}

	/**
	 * Checks whether the game has exited
	 *
	 * @return true if the game has exited, false otherwise
	 */
	public static boolean isExited() {
		return exited;
	}

	/**
	 * Exit setter
	 *
	 * @param exited the exited state
	 */
	public static void setExited(boolean exited) {
		exited = exited;
	}

	/**
	 * Gets the asset manager
	 *
	 * @return the asset manager
	 */
	public AssetManager getAssetManager(){
		return  assetManager;
	}

	/**
	 * Gets the sprite batch
	 *
	 * @return the sprite batch
	 */
	public SpriteBatch getSpriteBatch(){return batch;}

	/**
	 * Score setter
	 *
	 * @param score the score
	 */
	public void setScore(int score){

		if(androidAPIAdapter!=null)
			androidAPIAdapter.setScore(score);

		Gdx.app.exit();

	}
}
