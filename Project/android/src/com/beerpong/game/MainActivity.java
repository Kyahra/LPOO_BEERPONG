package com.beerpong.game;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.media.MediaPlayer;
import android.widget.CheckBox;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;


import java.util.Stack;

/**
 * Created by Daniel on 31/05/2017.
 */


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    LoginButton loginButton;
    CallbackManager callBackManager;
    MediaPlayer music;
    MediaPlayer sound;
    ShareButton shareButton;


    Stack<Integer> viewStack = new Stack<>();



    public void configMainLayout(){

        viewStack.push(R.layout.mainlayout);

        setContentView(R.layout.mainlayout);
        findViewById(R.id.startGame).setOnClickListener(this);
        findViewById(R.id.exitButton).setOnClickListener(this);
        findViewById(R.id.settingsButton).setOnClickListener(this);
        findViewById(R.id.helpButton).setOnClickListener(this);

        loginButton = (LoginButton)findViewById(R.id.fb_login_bn);

        ShareLinkContent content =  new ShareLinkContent.Builder().setContentUrl(Uri.parse("https://developers.facebook.com")).build();
        ShareButton shareButton = (ShareButton)findViewById(R.id.fb_share_button);
        shareButton.setShareContent(content);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());

        configMainLayout();

        music = MediaPlayer.create(MainActivity.this, R.raw.whiplash);
        music.setLooping(true);
        music.start();

        sound = MediaPlayer.create(MainActivity.this, R.raw.button);
    }

/*
    @Override
    protected void onPause(){
        super.onPause();
        music.release();
        //finish();

    }
*/


    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.startGame:
                sound.start();
                setContentView(R.layout.levelayout);
                findViewById(R.id.easyButton).setOnClickListener(this);
                findViewById(R.id.normalButton).setOnClickListener(this);
                findViewById(R.id.difficultButton).setOnClickListener(this);
                viewStack.push(R.layout.levelayout);
                break;
            case R.id.easyButton:
                startActivity(new Intent(this, AndroidLauncher.class));
                viewStack.push(R.layout.levelayout);
                break;
            case R.id.normalButton:
                startActivity(new Intent(this, AndroidLauncher.class));
                viewStack.push(R.layout.levelayout);
                break;
            case R.id.difficultButton:
                startActivity(new Intent(this, AndroidLauncher.class));
                viewStack.push(R.layout.levelayout);
                break;
            case R.id.helpButton:
                setContentView(R.layout.helplayout);
                music.stop();
                viewStack.push(R.layout.helplayout);
                break;
            case R.id.settingsButton:
                setContentView(R.layout.settingslayout);
                findViewById(R.id.checkBoxMusic).setOnClickListener(this);
                viewStack.push(R.layout.settingslayout);
                break;
            case R.id.checkBoxMusic:
                if(((CheckBox)v).isChecked()){
                    music.start();
                }
                else {
                    music.pause();
                }

                break;
            case R.id.exitButton:
                System.exit(0);
        }
    }




    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if((viewStack.peek() == R.layout.levelayout) ||(viewStack.peek() == R.layout.settingslayout) || (viewStack.peek() == R.layout.helplayout) ) {
                viewStack.pop();
                configMainLayout();

            }
            else {
                viewStack.pop();
                setContentView(viewStack.peek());
            }
            return true;
        }

        return false;
       // return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }


}
