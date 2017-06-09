package com.beerpong.game;

import android.content.Intent;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;



public class AndroidLauncher extends AndroidApplication implements BeerPong.AndroidAPIAdapter{


	private static int level;


	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new BeerPong(this, level), config);

	}



	public  static void setLevel(int level){
		AndroidLauncher.level = level;

	}

	@Override
	public void setScore(int score) {
		MainActivity.getInstance().setScore(score);

	}
}

