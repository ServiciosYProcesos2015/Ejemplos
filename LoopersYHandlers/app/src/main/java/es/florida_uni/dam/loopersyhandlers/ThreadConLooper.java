package es.florida_uni.dam.loopersyhandlers;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class ThreadConLooper implements Runnable{

    private final String TAG = "SYP-Handlers";

    public Handler handler;

    public void run() {
        Looper.prepare();

        // OJO: Hay que crearlo tras hacer un Looper.prepare
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Log.i(TAG, "Tratando mensaje " + msg.what);
            }
        };

        Looper.loop();
    }

}
