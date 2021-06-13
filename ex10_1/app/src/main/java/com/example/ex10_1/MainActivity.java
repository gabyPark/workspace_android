package com.example.ex10_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 새로운 액티비티 화면을 만들어서 이동할 수 있도록 ⭐⭐⭐
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(intent);
            }
        });

        Log.i("mytag","oncreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("mytag", "onstart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("mytag", "onstart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("mytag", "onstart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("mytag", "onstart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("mytag", "onstart");
    }
}