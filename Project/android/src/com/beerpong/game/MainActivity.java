package com.beerpong.game;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.login.widget.LoginButton;

/**
 * Created by Daniel on 31/05/2017.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    LoginButton loginButton;
    CallbackManager callBackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());

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

        loginButton = (LoginButton)findViewById(R.id.fb_login_bn);



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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
