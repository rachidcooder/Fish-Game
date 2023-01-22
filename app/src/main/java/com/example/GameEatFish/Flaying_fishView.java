package com.example.GameEatFish;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;

public class Flaying_fishView extends View {
private Bitmap fish[]=new Bitmap[2];
private Bitmap background;
private Paint paint=new Paint();
private Bitmap lifs[]=new Bitmap[2];

private boolean touch=false;
private int fishX=10;
private int fishY,fishSpeed;
    private int canvasWhith,canvasHight;
    private int yellowx,yellowy,yellowspeed=20;
    private Paint yellowpaint=new Paint();

    private int score,life_counter_fish;
    private int greenx,greeny,greenspeed=15;
    private Paint greenpaint=new Paint();

    private int redx,redy,redspeed=25;
    private Paint redpaint=new Paint();
    // constructer :

    public Flaying_fishView(Context context) {
        super(context);
        fish[0]= BitmapFactory.decodeResource(getResources(), R.drawable.fish1);
        fish[1]= BitmapFactory.decodeResource(getResources(),R.drawable.fish2);
        background=BitmapFactory.decodeResource(getResources(),R.drawable.background);

        yellowpaint.setColor(Color.YELLOW);
        yellowpaint.setAntiAlias(false);
        greenpaint.setColor(Color.GREEN);
        greenpaint.setAntiAlias(false);

       redpaint.setColor(Color.RED);
       redpaint.setAntiAlias(false);


        paint.setColor(Color.WHITE);
        paint.setTextSize(70);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setAntiAlias(true);



        //
        lifs[0]=BitmapFactory.decodeResource(getResources(),R.drawable.hearts);
        lifs[1]=BitmapFactory.decodeResource(getResources(), R.drawable.heart_grey);
        fishY=550;
        score=0;
        life_counter_fish=3;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvasWhith=canvas.getWidth();
        canvasHight=canvas.getHeight();

        canvas.drawBitmap(background,0,0,null);

        int minfishY=fish[0].getHeight();
        int maxfishY=canvasHight-fish[0].getHeight()*3;
        fishY=fishY+fishSpeed;
        if(fishY<minfishY){
            fishY=minfishY;
        }
        if(fishY>maxfishY){
            fishY=maxfishY;
        }
        fishSpeed=fishSpeed+2;
        if(touch){
    canvas.drawBitmap(fish[1],fishX,fishY,null);
    touch=false;
     }
        else{
            canvas.drawBitmap(fish[0],fishX,fishY,null);

        }
       yellowx=yellowx-yellowspeed;

    if(hitballchecker(yellowx,yellowy)){
        score=score+10;
            yellowx=-100;
        }

        if (yellowx<0){
            yellowx =canvasWhith-210;
            yellowy=(int) Math.floor(Math.random() * (maxfishY - minfishY))+minfishY;
        }
        canvas.drawCircle(yellowx,yellowy,20,yellowpaint);
        // after here :

        greenx=greenx-greenspeed;
        if(hitballchecker(greenx,greeny)){
            score=score+1;
            greenx=-50;
        }

        if (greenx<0){
            greenx =canvasWhith-21;
            greeny=(int) Math.floor(Math.random() * (maxfishY - minfishY))+minfishY;
        }
        canvas.drawCircle(greenx,greeny,20,greenpaint);
        // red ball :
        redx=redx-redspeed;
        if(hitballchecker(redx,redy)){
            redx=-50;
            life_counter_fish--;
            if(life_counter_fish==0){

                Intent gameoverIntent=new Intent(getContext(), GameOveractivity.class);
                gameoverIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                gameoverIntent.putExtra("score",score);
                getContext().startActivity(gameoverIntent);
            }
        }

        if (redx<0){
            redx =canvasWhith-21;
            redy=(int) Math.floor(Math.random() * (maxfishY - minfishY))+minfishY;
        }
        canvas.drawCircle(redx,redy,30,redpaint);
        for(int i=0;i<3;i++){
            int x= (int) (450+lifs[0].getWidth()*1.5*i);
            int y=30;

            if(i<life_counter_fish){
                canvas.drawBitmap(lifs[0],x,y,null);
            }else{
                canvas.drawBitmap(lifs[1],x,y,null);
            }
        }

        canvas.drawText("score : "+score,20,60,paint);
        //canvas.drawBitmap(lifs[0],650,10,null);
    }
    public boolean hitballchecker(int x,int y){
        if(fishX<x && x<(fishX+fish[0].getWidth())  && fishY<y  &&  y<(fishY+fish[0].getHeight())){
            return true;
        }
    return false;}

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            touch=true;
            fishSpeed=-25;
        }
        return  true;
    }
}
