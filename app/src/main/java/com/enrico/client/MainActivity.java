package com.enrico.client;

/**
 * Created by Enrico on 12/04/2016.
 */
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    public final static String ADDR_MESSAGE ="com.enrico.client.MESSAGE";
    public final static String PORT_MESSAGE ="com.enrico.client.MESSAGE1";
    public final static Client MY_CLIENT = null;
    TextView response;
    EditText editTextAddress, editTextPort;
    Button buttonConnect, buttonClear, buttonOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextAddress = (EditText) findViewById(R.id.addressEditText);
        editTextPort = (EditText) findViewById(R.id.portEditText);
        buttonConnect = (Button) findViewById(R.id.connectButton);
        buttonClear = (Button) findViewById(R.id.clearButton);
        response = (TextView) findViewById(R.id.responseTextView);


        /*
        buttonConnect.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

            }
        });
*/
        buttonClear.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                response.setText("");
            }
        });
    }
    public void sendMessage(View view)
    {
        Intent intent = new Intent(this,SendCommand.class);
        String addr = editTextAddress.getText().toString();
        String port = editTextPort.getText().toString();
        /*
        Client myClient = new Client(editTextAddress.getText()
                .toString(), Integer.parseInt(editTextPort
                .getText().toString()));//,"^R;");//, response);
                */
        intent.putExtra(ADDR_MESSAGE,addr);
        intent.putExtra(PORT_MESSAGE,port);
/*
        myClient.execute();
        myClient.setMsg("^R;");
        */

//
        startActivity(intent);
    }


}
