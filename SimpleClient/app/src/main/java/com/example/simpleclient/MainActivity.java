package com.example.simpleclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    EditText et;

    Socket s1;
    DataInputStream din;
    DataOutputStream dout;

    boolean stop = false;

    String host = "192.168.5.10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        et = findViewById(R.id.et);

        Button btn = findViewById(R.id.btn);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                service();
            }
        });

        t.start();

        btn.setOnClickListener(new View.OnClickListener(){
             @Override
                public void onClick (View v){
                 new Thread(new Runnable() {
                     @Override
                     public void run() {

                    try {
                        String msg = et.getText().toString();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tv.append("me : " + msg + "\n");
                            }
                        });


                        dout.writeUTF(msg);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                et.setText("");
                            }
                        });

                        if (msg.equals("exit")) {
                            //ta.append("me : exit...\n");
                            stop = true;
                            dout.close();
                            s1.close();
//                        System.exit(0);
                        } else {
                        }

                    } catch (Exception e1) {
                        tv.append(e1.getMessage());
                    }

                }
            }).start();
    }
    });
}


    public void service(){
            try {
                // 포트번호
                s1 = new Socket(host,12345);
                din = new DataInputStream(s1.getInputStream());
                dout = new DataOutputStream(s1.getOutputStream());

                tv.append(host+" connect complete ...\n");

                Thread t1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            while(!stop){
                                String msg = din.readUTF();

                                // RunOnUiThread 는
                                // append 를 쓰기 전에, mainActivity 의 TextView 를 쓸 수 있게 도와주는 것
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv.append("Server :"+msg+"\n");
                                    }
                                });
                                if(msg.equals("exit")) break;
                            }
                            din.close();
                            s1.close();
//                            System.exit(0);

                        }catch(IOException e){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tv.append(e.getMessage());
                                }
                            });
                        }
                    }
                });

                t1.start();

            }catch(Exception e){
                Log.i("mytag","disconnect....");
                System.exit(0);
            }
        }
    }
