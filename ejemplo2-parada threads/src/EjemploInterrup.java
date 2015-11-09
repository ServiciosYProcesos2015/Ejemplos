import java.util.Random;

public class EjemploInterrup {

    static class ThreadInterrumpible extends Thread{

        public void run(){

            final int INTERACCIONES = 10000000; // Ajustalo si tu máquina es muy rápida o muy lenta


            System.out.println("THREAD INTERRUMPIBLE INICIADO");

            while(true){
                for(int i=0;i<INTERACCIONES;i++){
                    int a = (new Random()).nextInt(100)+1;
                    a = a * a + a / a;
                }

                // Salir si nos han interrumpido
                System.out.println("CHEQUEANDO INTERRUPCION");
                if(isInterrupted()) break;
            }

            System.out.println("FIN DEL THREAD INTERRUMPIBLE");
        }
    }


    public static void main(String args[]){

        // Arrancar el thread interrumpible
        ThreadInterrumpible interrumpible = new ThreadInterrumpible();
        System.out.println("Iniciando Thread");
        interrumpible.start();


        // Esperar un poco
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {Thread.currentThread().interrupt();}

        // Interrumpir
        System.out.println("Interrumpiendo thread");
        interrumpible.interrupt();
    }

}
