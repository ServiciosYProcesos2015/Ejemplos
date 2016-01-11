package es.florida_uni.dam.loopersyhandlers;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "SYP-Handlers";
    ThreadConLooper threadConLooper;
    private int numMsj = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread.currentThread().setName("MainActivity");
        instalarManejadores();
        creaLooperThread();
    }


    private void creaLooperThread(){
        threadConLooper = new ThreadConLooper();
        Thread t = new Thread(threadConLooper);
        // Asignamos un nombre para indentificarlo en los logs
        t.setName("ThreadConLooper");
        t.start();
    }

    private void ejecutarEnEsteThread(){
        Runnable runnable = crearRunnable();
        runnable.run();
    }

    private void enviarMensaje(){
        Message message = new Message();
        message.what = numMsj++;
        threadConLooper.handler.sendMessage(message);
    }

    private void enviarRunnable(){
        threadConLooper.handler.post(crearRunnable());
    }

    private Runnable crearRunnable(){
        return new Runnable() {
            @Override
            public void run() {
                Log.i(TAG,"Ejecutando "+(numMsj++)+" en thread "+Thread.currentThread().getName()+"...");
                try{Thread.sleep(2000);}catch(InterruptedException e){}
                Log.i(TAG,"Fin ejecución en thread "+Thread.currentThread().getName());
            }
        };
    }



    private void instalarManejadores(){

        Button btnEjecutarEnEsteThread = (Button) findViewById(R.id.btnEjecutarEnEsteThread);
        Button btnEnviarRunnable = (Button) findViewById(R.id.btnEnviarRunnable);
        Button btnEnviarMensaje = (Button) findViewById(R.id.btnEnviarMensaje);
        Button btnSaludar = (Button) findViewById(R.id.btnSaludar);

        // Manejador del botón de saludar
        btnSaludar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Log.i(TAG, "Saludando");
                Toast.makeText(
                        MainActivity.this, "Hola", Toast.LENGTH_SHORT
                ).show();
            }
        });

        // Manejador del botón que ejecuta la tarea costosa
        btnEjecutarEnEsteThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                ejecutarEnEsteThread();
            }
        });


        // Manejador del botón que detiene la tarea costosa
        btnEnviarRunnable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                enviarRunnable();
            }
        });

        // Manejador del botón que detiene la tarea costosa
        btnEnviarMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                enviarMensaje();
            }
        });
    }

}


