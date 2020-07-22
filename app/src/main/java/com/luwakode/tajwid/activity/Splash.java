package com.luwakode.tajwid.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.luwakode.tajwid.MainActivity;
import com.luwakode.tajwid.R;
import com.luwakode.tajwid.util.SetUI;

public class Splash extends Activity {

    SetUI setUI = new SetUI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setUI.StatusBarTransparent(getWindow());

        redirect();
    }

    private void redirect(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //setelah loading maka akan langsung berpindah ke home activity
                Intent i = new Intent(Splash.this, MainActivity.class);
                startActivity(i);
                finish();

            }
        },1000);
    }
}