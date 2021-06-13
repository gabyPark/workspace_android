package com.example.ex9_6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
    }

    public static class MyGraphicView extends View {
        public MyGraphicView(Context c){
            super(c);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Bitmap pic = BitmapFactory.decodeResource(getResources(), R.drawable.small);

            int picX = (this.getWidth() - pic.getWidth()) / 2;
            int picY = (this.getHeight() - pic.getHeight()) / 2;

            Paint p = new Paint();
            float[] array = {2, 0, 0, 0, -25,
                             0, 2, 0, 0, -25,
                             0, 0, 2, 0, -25,
                             0, 0, 0 , 1, 0};

            ColorMatrix cm = new ColorMatrix(array);
            p.setColorFilter(new ColorMatrixColorFilter(cm));
            canvas.drawBitmap(pic, picX, picY, p);

            pic.recycle();
        }
    }
}