package com.enrico.client;


        import java.io.ByteArrayOutputStream;
        import java.io.IOException;
        import java.io.InputStream;
        import java.net.DatagramPacket;
        import java.net.DatagramSocket;
        import java.net.InetAddress;
        import java.net.Socket;
        import java.net.UnknownHostException;
        import android.os.AsyncTask;
        import android.util.Log;
        import android.widget.TextView;

public class Client extends AsyncTask<Void, Void, Void> {
    final static String id = "CLIENT";
    String dstAddress;
    int dstPort;
    String response = "";
    String sentence="";
    boolean changed =false;
    DatagramSocket clientSocket = null;
    DatagramPacket sendPacket;
    InetAddress IpAddress;

  public  Client(String addr, int port){//,String msg){//}, TextView textResponse) {
        this.dstAddress = addr;
        this.dstPort = port;
        //sentence = msg;

    }

    @Override
    protected Void doInBackground(Void... arg0) {

        //Socket socket = null;

            try {

                this.clientSocket = new DatagramSocket();
                this.IpAddress = InetAddress.getByName(dstAddress);

            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                response = "UnknownHostException: " + e.toString();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                response = "IOException: " + e.toString();
            }

            return null;

    }
    public void setMsg(final String m)
    {

        final InetAddress IP = this.IpAddress;
        final int port = this.dstPort;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // this.changed = true;
                byte[] sendData;
                sendData = m.getBytes();
                sendPacket = new DatagramPacket(sendData, sendData.length, IP, port);
                //  sendPacket = new DatagramPacket(sendData,sendData.length);
                Log.v(id , clientSocket.getLocalAddress().toString());

                try {
                    clientSocket.send(sendPacket);
                }
                catch(Exception e){
                    e.printStackTrace();
                    Log.v(id , e.toString());
                }
            }
        });
        thread.start();

    }

}
