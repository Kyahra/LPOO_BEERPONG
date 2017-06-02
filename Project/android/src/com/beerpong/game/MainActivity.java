package com.beerpong.game;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Daniel on 31/05/2017.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                                                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                                                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
        setContentView(R.layout.mainlayout);
        findViewById(R.id.startGame).setOnClickListener(this);
        findViewById(R.id.exitButton).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.startGame:
                startActivity(new Intent(this, AndroidLauncher.class));
                break;
            case R.id.exitButton:
                System.exit(0);
        }
    }
}
