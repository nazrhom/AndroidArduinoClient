package com.enrico.client;


        import java.io.ByteArrayOutputStream;
        import java.io.IOException;
        import java.io.InputStream;
        import java.net.DatagramPacket;
        import java.net.DatagramSocket;
        import java.net.InetAddress;
        import java.net.Socket;
        import java.net.UnknownHostException;
        import java.util.concurrent.ExecutionException;

        import android.os.AsyncTask;
        import android.util.Log;
        import android.widget.TextView;

public final class Client {
    final static String ID = "CLIENT";

    private static Client instance = null;
    private Client() { }

    String         dstAddress;
    int            dstPort;
    DatagramSocket socket;
    InetAddress    IpAddress;

    public void setData(String addr, int port) {
        this.dstAddress = addr;
        this.dstPort = port;
    }

    public Boolean connect() {
        UDPconnect con = new UDPconnect();
        try {
            return con.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public AsyncTask scheduleSend(String d) {
        return new SendPacket(d);
    }

    protected class SendPacket extends AsyncTask<Void, Void, Void> {
        String data;

        public SendPacket(String d) {
            this.data = d;
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            Client c  = Client.getInstance();
            byte[] sendData = data.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, c.IpAddress, c.dstPort);

            try {
                c.socket.send(sendPacket);
            }
            catch(Exception e){
                e.printStackTrace();
                Log.v(ID, e.toString());
            }

            return null;
        }
    }

    private class UDPconnect extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... arg0) {
            Client c = Client.getInstance();

            try {
                c.socket = new DatagramSocket();
                c.IpAddress = InetAddress.getByName(dstAddress);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                Log.v(ID, e.toString());
                return false;
            }
        }
    }


    public static synchronized Client getInstance() {
        if (instance == null) {
            instance = new Client();
        }

        return instance;
    }

}
