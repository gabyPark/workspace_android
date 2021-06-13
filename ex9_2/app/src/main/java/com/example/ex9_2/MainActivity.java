package com.example.ex9_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    final static int LINE = 1;
    final static int CIRCLE = 2;
    static int curShap = LINE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater mf = getMenuInflater();
        mf.inflate(R.menu.my_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.line :
                curShap = LINE;
                break;

            case R.id.circle :
                curShap = CIRCLE;
        }
        return false;
    }

    public static class MyGraphicView extends View {

        int startX, startY, stopX, stopY;

        public MyGraphicView(Context c){
            super(c);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            super.onTouchEvent(event);

            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN :
                    startX = (int) event.getX();
                    startY = (int) event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                case MotionEvent.ACTION_UP :
                    stopX = (int) event.getX();
                    stopY = (int) event.getY();

                    this.invalidate();        // onDraw() 메서드를 호출한다

                    break;
            }

            return true;
        }

        @Override
        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);
            Paint p = new Paint();
            p.setAntiAlias(true);
            p.setStrokeWidth(5);
            p.setStyle(Paint.Style.STROKE);
            p.setColor(Color.RED);

            switch (curShap){
                case LINE :
                    canvas.drawLine(startX, startY, stopX, stopY, p);
                    break;

                case CIRCLE:
                    int radius = (int) Math.sqrt(Math.pow(stopX - startX, 2) + Math.pow(stopY - startY, 2));
                    canvas.drawCircle(startX, startY, radius, p);
                    break;
            }
        }
    }
}