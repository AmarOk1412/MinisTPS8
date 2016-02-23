package com.example.amarok.appmultimedia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class mainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void playBtnClicked(View view) {
        Intent playIntent = new Intent(this, PlayerService.class);
        playIntent.setAction("ACTION_PLAY");
        startService(playIntent);
    }

    public void stopBtnClicked(View view) {
        Intent stopIntent = new Intent(this, PlayerService.class);
        stopIntent.setAction("ACTION_STOP");
        startService(stopIntent);
    }

    public void pauseBtnClicked(View view) {
        Intent stopIntent = new Intent(this, PlayerService.class);
        stopIntent.setAction("ACTION_PAUSE");
        startService(stopIntent);
    }
}
