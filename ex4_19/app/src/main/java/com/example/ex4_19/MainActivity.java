package com.example.ex4_19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText edit1 = findViewById(R.id.et1); // R.java 안에 id값이 et1 인 소스를 불러와라
        // EditText 타입인 ~~~ 를 edit1 변수에 저장
        EditText edit2 = findViewById(R.id.et2);

        Button btn1 = findViewById(R.id.BtnAdd);
        Button btn2 = findViewById(R.id.BtnMinus);
        Button btn3 = findViewById(R.id.BtnX);
        Button btn4 = findViewById(R.id.BtnDivide);

        TextView txt1 = findViewById(R.id.TextResult);



        // 익명객체로 인터페이스 구현
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num1 = edit1.getText().toString();
                String num2 = edit2.getText().toString();

                int result1 = Integer.parseInt(num1)+Integer.parseInt(num2);
                txt1.setText(result1+"");  // setText 는 String 값만 들어갈 수 있기 때문에
            }
        });
    }
}