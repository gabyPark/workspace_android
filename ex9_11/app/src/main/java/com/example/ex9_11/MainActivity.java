package com.example.ex9_11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    static float sx = 1;
    static float sy = 1;
    static int angle = 0;
    static float color = 1;
    static float satur = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout pictureLayout = (LinearLayout) findViewById(R.id.pictureLayout);
        MyGraphicView myView = new MyGraphicView(this);
        pictureLayout.addView(myView);

        ImageButton zoomIn, zoomOut, rotate, bright, dark, gray;
        zoomIn = findViewById(R.id.zoomIn);
        zoomOut = findViewById(R.id.zoomOut);
        rotate = findViewById(R.id.rotate);
        bright = findViewById(R.id.bright);
        dark = findViewById(R.id.dark);
        gray = findViewById(R.id.gray);

        // 리스너를 익명클래스를 통해 구현
        zoomIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sx = sx+0.2f;
                sy = sy+0.2f;
                if (sx < 0 && sy < 0){ sx = 0; sy = 0;}
                myView.invalidate();
            }
        });

        zoomOut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                sx = sx-0.2f;
                sy = sy-0.2f;
                if (sx < 0 && sy < 0){ sx = 0; sy = 0;}
                myView.invalidate();
            }
        });

        rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            angle-=90;
            myView.invalidate();
            }
        });

        bright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = color + 0.2f;
                myView.invalidate();
            }
        });

        dark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = color - 0.2f;
                myView.invalidate();
            }
        });

        gray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (satur == 0) satur = 1;
                else satur = 0;
                myView.invalidate();
            }
        });
    }


    public static class MyGraphicView extends View {
        public MyGraphicView(Context c){
            super(c);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.small);

            int picX = (this.getWidth() - bitmap.getWidth()) / 2;
            int picY = (this.getHeight() - bitmap.getHeight()) / 2;

            Paint p = new Paint();

          canvas.scale(sx, sy, (float)this.getWidth()/2, (float)this.getHeight()/2);
            canvas.rotate(angle, this.getWidth()/2, this.getHeight()/2);

            float[] array = {color, 0, 0, 0, 0,
                             0, color, 0, 0, 0,
                             0, 0, color, 0, 0,
                             0, 0, 0 , 1, 0};
            ColorMatrix cm = new ColorMatrix(array);
            if(satur == 0) cm.setSaturation(satur);
            p.setColorFilter(new ColorMatrixColorFilter(cm));

            canvas.drawBitmap(bitmap, picX, picY, p);

            bitmap.recycle();
        }
    }

}