package com.enrico.client;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class SendCommand extends AppCompatActivity {
    String addr;
    String port;
    String response;
    Interface i = new Interface(6);
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
        String addr = intent.getStringExtra(MainActivity.ADDR_MESSAGE);
        String port = intent.getStringExtra(MainActivity.PORT_MESSAGE);

        TextView text_addr  = (TextView)findViewById(R.id.addr);
        text_addr.setText("IP: "+addr);

        TextView text_port = (TextView)findViewById(R.id.port);
        text_port.setText("PORT: "+port);

    }
    /*
    first = i.Motor(0).setPower(255);
    sec = i.Motor(0).setPower(255);


    Interface.seq([first, Interface.pause(100), sec]);
    interface.par([first,sec])
    */
    public void TurnOn(View view)
    {


        boolean checked = ((CheckBox)view).isChecked();

        switch(view.getId()) {
            case R.id.pin1:
                if (checked) {
                    Interface.exec(i.motor(0).setPower(255));
                    // myClient.execute();
                } else {
                    Interface.exec(i.motor(0).off());
                }
                break;

            case R.id.pin2:
                if (checked) {
                    Interface.exec(i.motor(1).setPower(255));
                    // myClient.execute();
                } else {
                    Interface.exec(i.motor(1).off());
                }
                break;

            case R.id.pin3:
                if (checked) {
                    Interface.exec(i.motor(2).setPower(255));
                    // myClient.execute();
                } else {
                    Interface.exec(i.motor(2).off());
                }
                break;

            case R.id.pin4:
                if (checked) {
                    Interface.exec(i.motor(3).setPower(255));
                    // myClient.execute();
                } else {
                    Interface.exec(i.motor(3).off());
                }
                break;

            case R.id.pin5:
                if (checked) {
                    Interface.exec(i.motor(4).setPower(255));
                    // myClient.execute();
                } else {
                    Interface.exec(i.motor(4).off());
                }
                break;

            case R.id.pin6:
                if (checked) {
                    Interface.exec(i.motor(5).setPower(255));
                    // myClient.execute();
                } else {
                    Interface.exec(i.motor(5).off());
                }
                break;
        }
    }
}

