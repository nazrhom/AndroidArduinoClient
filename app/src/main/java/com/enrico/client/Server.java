package com.enrico.client;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by Enrico on 14/04/2016.
 */
public class Server extends AsyncTask<Void, Void, Void> {
    String messageStr = "Hello Android!";
    int server_port = 3333;
    String response;

    @Override
    protected Void doInBackground(Void... arg0) {
        try

        {
            DatagramSocket s = new DatagramSocket();
            InetAddress local = InetAddress.getByName("127.0.0.1");
            int msg_length = messageStr.length();
            byte[] message = messageStr.getBytes();
            DatagramPacket p = new DatagramPacket(message, msg_length, local, server_port);
           s.send(p);
        }
        catch (SocketException e) {
            e.printStackTrace();
            response = "SocketException " + e.toString();
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
            response = "UnknownHostException " + e.toString();
        }
        catch(IOException e){
            e.printStackTrace();
            response = "IOException " + e.toString();
        }
    return null;
    }
}
