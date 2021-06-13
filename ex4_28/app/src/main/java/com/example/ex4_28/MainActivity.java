package com.example.ex4_28;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        setTitle("애완동물 사진 보기");

        CheckBox cbStart = findViewById(R.id.cbStart);
        TextView tv = findViewById(R.id.tv1);
        RadioGroup rg = findViewById(R.id.rg);
        RadioButton rbDog = findViewById(R.id.rbDog);
        RadioButton rbCat = findViewById(R.id.rbCat);
        RadioButton rbRabbit = findViewById(R.id.rbRabbit);
        Button btn = findViewById(R.id.btn);
        ImageView iv = findViewById(R.id.iv);

        cbStart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    tv.setVisibility(View.VISIBLE);
                    rg.setVisibility(View.VISIBLE);
                    btn.setVisibility(View.VISIBLE);
                    iv.setVisibility(View.VISIBLE);
                } else {
                    tv.setVisibility(View.INVISIBLE);
                    rg.setVisibility(View.INVISIBLE);
                    btn.setVisibility(View.INVISIBLE);
                    iv.setVisibility(View.INVISIBLE);
                }
            }
        });       // 체크박스의 리스너


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(rg.getCheckedRadioButtonId()){       // 체크된 라디오버튼의 아이디값을 가져오는 코드

                    case R.id.rbCat : iv.setImageResource(R.drawable.cat);
                    break;

                    case R.id.rbDog : iv.setImageResource(R.drawable.dog2);
                    break;

                    case R.id.rbRabbit : iv.setImageResource(R.drawable.rabbit);
                    break;
                }
            }
        });

    }
}