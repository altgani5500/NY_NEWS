package com.example.ienovo.list_ho;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends Activity {
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash);
            new android.os.Handler().postDelayed(new Runnable()


                                                 {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

                                                     @Override
                                                     public void run () {


    Intent i = new Intent(Splash.this,Voice_Recnization.class);
    startActivity(i);

     finish();

                                                     }
                                                 }

                    ,SPLASH_TIME_OUT);



        }



    }

