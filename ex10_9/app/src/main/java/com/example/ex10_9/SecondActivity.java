package com.example.ex10_9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity{

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.second_main);
            setTitle("투표 결과");

            Intent intent = getIntent();
            int[] voteResult = intent.getIntArrayExtra("voteCount");
            String[] imgName = intent.getStringArrayExtra("imgName");

            TextView tv[] = new TextView[imgName.length];
            RatingBar rBar[] = new RatingBar[9];

            Integer tvId[] = {R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5,
                    R.id.tv6, R.id.tv7, R.id.tv8, R.id.tv9};
            Integer rBarId[] = {R.id.rbar1, R.id.rbar2, R.id.rbar3, R.id.rbar4, R.id.rbar5,
                    R.id.rbar6, R.id.rbar7, R.id.rbar8, R.id.rbar9};

            for(int i=0; i<tvId.length; i++){
                tv[i] = findViewById(tvId[i]);
                tv[i].setText(imgName[i]);
            }

            for(int i=0; i<rBar.length; i++){
                rBar[i] = findViewById(rBarId[i]);
                rBar[i].setStepSize(0.5f);
                rBar[i].setRating(voteResult[i] / 2.0f);
            }

            Button btnReturn = findViewById(R.id.btnReturn);
            btnReturn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

