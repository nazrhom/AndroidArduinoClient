package com.enrico.client;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class SendCommand extends AppCompatActivity {
    String addr;
    String port;
    String response;
    Client myClient;
    int sw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_command);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        addr = intent.getStringExtra(MainActivity.ADDR_MESSAGE);
        port = intent.getStringExtra(MainActivity.PORT_MESSAGE);
        TextView text_addr  = (TextView)findViewById(R.id.addr);
        text_addr.setText("IP: "+addr);
        TextView text_port = (TextView)findViewById(R.id.port);
        text_port.setText("PORT: "+port);
        myClient= new Client(addr,Integer.parseInt(port));
        myClient.execute();


    }

    public void TurnOn(View view)
    {


        boolean checked = ((CheckBox)view).isChecked();
        switch(view.getId()) {

            case R.id.pin1:
                if(checked){

                    myClient.setMsg("^W0;255;");
                   // myClient.execute();
                }

                    else
                {
                    myClient.setMsg("^W0;0;");
                   // myClient.execute();
;
                }
                break;
            case R.id.pin2:
                if(checked){
                    myClient.setMsg("^W1;255;");
                   // myClient.execute();
                }
                else
                {
                    myClient.setMsg("^W1;0;");
                  //  myClient.execute();
                }
                break;
            case R.id.pin3:
                if(checked){
                    myClient.setMsg("^W2;255;");
                 //   myClient.execute();
                }
                else
                {
                    myClient.setMsg("^W2;0;");
                 //   myClient.execute();
                }
                break;
            case R.id.pin4:
                if(checked){
                    myClient.setMsg("^W3;255;");
                  //  myClient.execute();
                }
                else
                {
                    myClient.setMsg("^W3;0;");
                  //  myClient.execute();
                }
                break;
            case R.id.pin5:
                if(checked){
                    myClient.setMsg("^W4;255;");
                 //   myClient.execute();
                }

                else
                {
                    myClient.setMsg("^W4;0;");
                 //   myClient.execute();
                }
                break;

            case R.id.pin6:
                if(checked){
                    myClient.setMsg("^W5;255;");
                 //   myClient.execute();
                }

                else
                {
                    myClient.setMsg("^W5;0;");
                  //  myClient.execute();
                }
                break;
            case R.id.reset:
                if(checked){
                    myClient.setMsg("^R;");
                  //  myClient.execute();
                }
                break;
            case R.id.Ramp1:
                if(checked)
                {
                    final Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            int tmp = 1000;
                            for (int i = 0; i < 6; i++) {
                                for (int j = 0; j < 6; j++) {
                                    myClient.setMsg("^F" + j + ";" + "255;" + "0;" + tmp + ";");
                                    try {
                                        Thread.sleep(tmp / 2, 0);
                                    }
                                    catch(InterruptedException e)
                                    {
                                        Log.v("SendCommand",e.toString());
                                    }
                                }
                                myClient.setMsg("^F" + i + ";" + "0;" + "255;" + tmp + ";");
                            }
                        }
                    });
                    thread.start();
                }

        }
    }
}

