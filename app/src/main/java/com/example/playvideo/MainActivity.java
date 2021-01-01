package com.example.playvideo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {
    private SurfaceView surfaceView;
    private MediaPlayer mediaPlayer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this , R.raw.truth_untold);
        surfaceView = findViewById(R.id.surfaceView);

        Button play = findViewById(R.id.buttonPlay);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
            }
        });

        Button pause = findViewById(R.id.buttonPause);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
            }
        });

        Button skip = findViewById(R.id.buttonSkip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.seekTo(mediaPlayer.getDuration()/2);
            }
        });

        surfaceView.setKeepScreenOn(true);  //To keep the screen on till the video is completely played and finished.

        SurfaceHolder holder = surfaceView.getHolder();
        holder.addCallback(this);  // As we have implemented the MainActivity with appropriate interface
        holder.setFixedSize(400 , 300);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {

        mediaPlayer.setDisplay(holder);
        mediaPlayer.start(); // To start the video as soon as the app is opened.


    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mediaPlayer!=null){
            mediaPlayer.pause();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}