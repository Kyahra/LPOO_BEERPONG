package com.beerpong.game;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;

import android.view.KeyEvent;
import android.view.View;
import android.media.MediaPlayer;
import android.widget.CheckBox;
import android.widget.TextView;


import com.facebook.FacebookSdk;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareButton;


import java.io.ByteArrayOutputStream;
import java.util.Stack;

/**
 * Created by Daniel on 31/05/2017.
 */


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static MainActivity instance;
    private int score =0;

    LoginButton loginButton;
    MediaPlayer music;
    MediaPlayer sound;

    boolean soundAvailable;

    Stack<Integer> viewStack = new Stack<>();



    public void configMainLayout(){

        viewStack.push(R.layout.mainlayout);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.mainlayout);
        findViewById(R.id.startGame).setOnClickListener(this);
        findViewById(R.id.exitButton).setOnClickListener(this);
        findViewById(R.id.settingsButton).setOnClickListener(this);
        findViewById(R.id.helpButton).setOnClickListener(this);

        loginButton = (LoginButton)findViewById(R.id.fb_login_bn);
/*
        ShareLinkContent content =  new ShareLinkContent.Builder().setContentUrl(Uri.parse("https://developers.facebook.com")).build();
        ShareButton shareButton = (ShareButton)findViewById(R.id.fb_share_button);
        shareButton.setShareContent(content);
*/
    }

    public static MainActivity getInstance() {
        if(instance == null)
            instance = new MainActivity();
        return instance;

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
        soundAvailable = true;

        instance = this;

    }

    public static Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null)
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        else
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }


    @Override
    public void onResume (){
        super.onResume();
        if(BeerPong.isExited()) {

            System.out.println("SCORE:" + score);

            if( score != 0) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                setContentView(R.layout.scorelayout);
                findViewById(R.id.menuButton).setOnClickListener(this);

                TextView textScore = (TextView)findViewById(R.id.textView);
                Typeface type = Typeface.createFromAsset(getAssets(),"fonts/Capture_it.ttf");

                textScore.setTypeface(type);
                textScore.setText(Integer.toString(score));


               // Bitmap image = getBitmapFromView(findViewById(R.id.textView));

               // Drawable myDrawable = getResources().getDrawable(R.drawable.bitmapback);
               // Bitmap myLogo = ((BitmapDrawable) myDrawable).getBitmap();


                Bitmap myLogo = BitmapFactory.decodeResource(getResources(), R.drawable.cup);

                SharePhoto photo = new SharePhoto.Builder().setBitmap(myLogo).build();
               // SharePhotoContent content = new SharePhotoContent.Builder().addPhoto(photo).build();

                ShareLinkContent content = new ShareLinkContent.Builder().setContentUrl(Uri.parse("https://developers.facebook.com")).build();
                ShareButton shareButton = (ShareButton) findViewById(R.id.fb_share_button);
                shareButton.setShareContent(content);


                BeerPong.setExited(false);
            }
        }

    }


    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.startGame:
                playSound();
                setContentView(R.layout.levelayout);
                findViewById(R.id.easyButton).setOnClickListener(this);
                findViewById(R.id.normalButton).setOnClickListener(this);
                findViewById(R.id.difficultButton).setOnClickListener(this);
                viewStack.push(R.layout.levelayout);
                break;
            case R.id.easyButton:
                playSound();
                AndroidLauncher.setLevel(1);
                startActivity(new Intent(this, AndroidLauncher.class));
                break;
            case R.id.normalButton:
                playSound();
                AndroidLauncher.setLevel(2);
                startActivity(new Intent(this, AndroidLauncher.class));
                break;
            case R.id.difficultButton:
                playSound();
                AndroidLauncher.setLevel(3);
                startActivity(new Intent(this, AndroidLauncher.class));
                break;
            case R.id.helpButton:
                playSound();
                setContentView(R.layout.helplayout);
                viewStack.push(R.layout.helplayout);
                break;
            case R.id.settingsButton:
                playSound();
                enableSettings();
                findViewById(R.id.checkBoxMusic).setOnClickListener(this);
                findViewById(R.id.checkBoxSound).setOnClickListener(this);
                viewStack.push(R.layout.settingslayout);
                break;
            case R.id.checkBoxMusic:
                playSound();
                if(((CheckBox)v).isChecked()) music.start();
                else music.pause();
                break;
            case R.id.checkBoxSound:
                playSound();
                if(((CheckBox)v).isChecked()) soundAvailable = true;
                else soundAvailable = false;
                break;
            case R.id.menuButton:
                playSound();
                configMainLayout();
                break;
            case R.id.exitButton:
                playSound();
                System.exit(0);
        }

    }


    public void playSound(){

        if(soundAvailable)
            sound.start();
    }


    public void enableSettings(){

        if(music.isPlaying() && soundAvailable){

            setContentView(R.layout.settings3);
        }
        if(music.isPlaying() && !soundAvailable){

            setContentView(R.layout.settings2);
        }
        if(!music.isPlaying() && soundAvailable){

            setContentView(R.layout.settings1);
        }
        if(!music.isPlaying() && !soundAvailable){

            setContentView(R.layout.settings4);
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if(viewStack.peek() == R.layout.mainlayout){
                music.pause();
                return super.onKeyDown(keyCode, event);

            }
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
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    public void setScore(int score) {
        this.score = score;
    }

}
