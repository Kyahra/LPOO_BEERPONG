package com.beerpong.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.beerpong.game.BeerPong;

public class AndroidLauncher extends AndroidApplication implements BeerPong.AndroidAPIAdapter{
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new BeerPong(this), config);
	}


	@Override
	public void showScore() {
	}
}
