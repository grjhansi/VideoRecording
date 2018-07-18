package com.nurture.nurture_user.videorecording;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    VideoView vvSample;
    Button btnCaptureVideo, btnFullScreen;
    static final int REQUEST_VIDEO_CAPTURE = 1;
    String filePath;
    private MediaController mediaControls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vvSample = findViewById(R.id.vvSample);
        btnCaptureVideo = findViewById(R.id.btnCaptureVideo);
        //btnFullScreen = findViewById(R.id.btnFullScreen);
        if (mediaControls == null)
        {
            mediaControls = new MediaController(MainActivity.this);
        }

//        vvSample.setVideoPath("/storage/emulated/0/WhatsApp/Media/WhatsApp Video/VID-20180625-WA0001.mp4");
//        vvSample.setMediaController(mediaControls);
//        vvSample.start();

        btnCaptureVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            final Uri videoUri = intent.getData();


            /*DisplayMetrics metrics = new DisplayMetrics(); getWindowManager().getDefaultDisplay().getMetrics(metrics);
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) vvSample.getLayoutParams();
            params.width =  metrics.widthPixels;
            params.height = metrics.heightPixels;
            params.leftMargin = 0;
            vvSample.setLayoutParams(params);*/
            vvSample.setMediaController(mediaControls);
            vvSample.setVideoURI(videoUri);
            vvSample.start();

            /*  https://stackoverflow.com/questions/11310764/videoview-full-screen-in-android-application*/
            /*btnFullScreen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DisplayMetrics metrics = new DisplayMetrics(); getWindowManager().getDefaultDisplay().getMetrics(metrics);
                    ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) vvSample.getLayoutParams();
                    params.width =  metrics.widthPixels;
                    params.height = metrics.heightPixels;
                    params.leftMargin = 0;
                    vvSample.setLayoutParams(params);
                    vvSample.setMediaController(mediaControls);
                    vvSample.setVideoURI(videoUri);
                    vvSample.start();
                }
            });*/
        }
    }

}
