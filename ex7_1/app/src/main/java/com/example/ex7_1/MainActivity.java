package com.example.ex7_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    LinearLayout baseLayout;  // 아이디 가져오기
    TextView tv;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 아이디에 변수 입력하기
        baseLayout = findViewById(R.id.baseLayout);
        tv = findViewById(R.id.tv);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "박가빈", Toast.LENGTH_SHORT).show();
            }
        });

        registerForContextMenu(tv);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu1, menu);
    }

    @Override  // 컨텍스트 메뉴를 클릭했을 때 작동될 수 있도록 하는 메서드
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        switch(item.getItemId()){
            case R.id.itemRed :
                baseLayout.setBackgroundColor(Color.RED);
                return true;

            case R.id.itemGreen :
                baseLayout.setBackgroundColor(Color.GREEN);
                return true;

            case R.id.itemBlue :
                baseLayout.setBackgroundColor(Color.BLUE);
                return true;

            case R.id.subRotate :
                btn.setRotation(15 + btn.getRotation());
                return true;

            case R.id.subSize :
                btn.setScaleX(2);
                return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater mInflater = getMenuInflater();
        mInflater.inflate(R.menu.menu1, menu);

        return true;
    }

    @Override  // 메뉴의 항목들을 클릭했을때 실행하는 메서드
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        super.onOptionsItemSelected(item);

        switch(item.getItemId()){
            case R.id.itemRed :
                baseLayout.setBackgroundColor(Color.RED);
                return true;

            case R.id.itemGreen :
                baseLayout.setBackgroundColor(Color.GREEN);
                return true;

            case R.id.itemBlue :
                baseLayout.setBackgroundColor(Color.BLUE);
                return true;

            case R.id.subRotate :
                btn.setRotation(15 + btn.getRotation());
                return true;

            case R.id.subSize :
                btn.setScaleX(2);
                return true;
        }


        return false;
    }
}