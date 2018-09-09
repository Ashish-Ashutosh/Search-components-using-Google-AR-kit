package com.example.asus.ar_application;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

public class HelpVideo extends AppCompatActivity {

    VideoView mVideoView;
    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_video);

        mVideoView = (VideoView)findViewById(R.id.videoView);

        mediaController = new MediaController(this);
        playvideo();
    }

    public void playvideo(){
        String uriPath = "android.resource://com.example.asus.ar_application/" + R.raw.help;
        Uri uri2 = Uri.parse(uriPath);
        mVideoView.setVideoURI(uri2);
        mVideoView.setMediaController(mediaController);
        mediaController.setAnchorView(mVideoView);
        mVideoView.start();
    }
}
