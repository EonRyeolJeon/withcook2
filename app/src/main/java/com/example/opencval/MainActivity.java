package com.example.opencval;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity{

    ImageView mImageView;
    Animation an1;
    Button Login;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);




            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                    WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


            setContentView(R.layout.login);

            Login = (Button) findViewById(R.id.button_Login);
            Login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this,MainActivity_sdj.class);
                    startActivity(intent);
                }
            });


            an1 = AnimationUtils.loadAnimation(this,R.anim.rotate);
            mImageView = (ImageView) findViewById(R.id.mark);
            mImageView.startAnimation(an1);



        }


}