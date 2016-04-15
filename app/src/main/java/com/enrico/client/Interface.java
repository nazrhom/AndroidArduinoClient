package com.enrico.client;

import android.os.AsyncTask;

import java.util.ArrayList;

public class Interface {
    final static String ID = "INTERFACE";

    ArrayList<Motor> motors;

    public Interface(int motors) {
        for (int i = 0; i <= motors; i++) {
            this.motors.add(new Motor(i));
        }
    }

    public Motor motor(int i) {
        return motors.get(i);
    }

    static AsyncTask exec(AsyncTask a) {
        return a.execute();
    }

    static void seq(ArrayList<AsyncTask> ps) {
        final ArrayList<AsyncTask> packets = ps;

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (AsyncTask p : packets) {
                    try {
                        p.execute().get();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }

    static void par(ArrayList<AsyncTask> ps) {
        String parMsg = "";
        for (AsyncTask packet : ps) {
            if (packet instanceof Client.SendPacket) {
                parMsg = parMsg.concat(((Client.SendPacket) packet).data);
            }
        }
        Client c = Client.getInstance();
        Interface.exec(c.scheduleSend(parMsg));
    }

    static AsyncTask pause(int i) {
        return new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... i) {
                int t = i[0];
                try {
                    Thread.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
    }

    public class Motor {
        int id;

        private Motor (int id) {
            this.id = id;
        }

        public AsyncTask setPower(int p) {
            Client c = Client.getInstance();
            String msg = "^W" + this.id + ";" + p;
            return c.scheduleSend(msg);
        }

        public AsyncTask off() {
            return this.setPower(0);
        }

    }


}
