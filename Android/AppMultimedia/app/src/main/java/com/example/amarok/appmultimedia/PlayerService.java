package com.example.amarok.appmultimedia;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;

public class PlayerService extends Service {
    private MediaPlayer mp;
    private int currentPos;
    private boolean isPlaying;
    private String TAG = "PlayerService";

    public PlayerService() {
        currentPos = 0;
        isPlaying = false;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mp = new MediaPlayer();
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        String url = "/sdcard/cantina.mp3";
        try {
            mp.setDataSource(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.w(TAG, "[ERROR]MediaPlayer:" + what);
                return false;
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mp.release();
        mp = null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getAction() == "ACTION_PLAY") {
            if(!isPlaying) {
                Log.i(TAG, "MediaPlayer:Play");
                Notification notification=new Notification();
                Intent i=new Intent(this, mainActivity.class);

                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                PendingIntent pi=PendingIntent.getActivity(this, 0, i, 0);
                notification.tickerText = "Now Playing: \"cantina.mp3\"";
                notification.flags|=Notification.FLAG_NO_CLEAR;
                this.startForeground(1412, notification);
                isPlaying = true;
                mp.prepareAsync();
                mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    public void onPrepared(MediaPlayer mp) {
                        mp.seekTo(currentPos);
                        mp.start();
                    }
                });
            }
        } else if (intent.getAction() == "ACTION_PAUSE") {
            Log.i(TAG, "MediaPlayer:Pause");
            mp.stop();
            isPlaying = false;
            currentPos = mp.getCurrentPosition();
        } else if (intent.getAction() == "ACTION_STOP") {
            Log.i(TAG, "MediaPlayer:Stop");
            mp.stop();
            isPlaying = false;
            currentPos = 0;
            stopForeground(true);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
