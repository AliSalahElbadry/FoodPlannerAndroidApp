package com.app.our.foodplanner.app_vp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.app.our.foodplanner.R;

import java.util.Timer;
import java.util.TimerTask;

public class Splash_fScreen extends AppCompatActivity {

    LottieAnimationView lottieAnimationView;
    Animation animationImage;
    ImageView imageViewSplash;
    Handler mainHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        animationImage= AnimationUtils.loadAnimation(this,R.anim.splash_image_anim);
        imageViewSplash=findViewById(R.id.imageViewMealSplash);
        imageViewSplash.setAnimation(animationImage);
        animationImage.start();
        lottieAnimationView=findViewById(R.id.splash_anim);
         mainHandler = new Handler(new Handler.Callback() {
             @Override
             public boolean handleMessage(@NonNull Message msg) {
                 if(msg.arg1==0)
                    lottieAnimationView.playAnimation();
                 else {
                     Intent intent=new Intent(Splash_fScreen.this,MainActivityContainer.class);
                     startActivity(intent);
                     finish();
                 }
                 return true;
             }
         });
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Message message=new Message();
                message.arg1=0;
               mainHandler.sendMessage(message);
            }
        },2000);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                Message message=new Message();
                message.arg1=1;
                mainHandler.sendMessage(message);
            }
        },5000);

    }
}