package com.example.GameEatFish;

import androidx.appcompat.app.AppCompatActivity;
import com.example.GameEatFish.R;
import android.content.Intent;
import android.os.Bundle;
public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Thread thread=new Thread(){
            @Override
            public void run() {
               try {
                   sleep(3000);
               }catch (Exception e){

               }
               finally {
                   startActivity(new Intent(Activity2.this, MainActivity.class));
               }
            }
        };
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}