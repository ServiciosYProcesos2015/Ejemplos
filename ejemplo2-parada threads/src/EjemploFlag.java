public class EjemploFlag {


    static class ThreadInterrumpible extends Thread{

        private volatile boolean meHanInterrumpido = false;
            // Cosas MUY MALAS pueden pasar si no añades volatile

        public void detener(){
            meHanInterrumpido = true;
        }

        public void run(){
            while(true){

                // Salir si nos han interrumpido
                if(meHanInterrumpido) break;

                try {
                    System.out.println("Thread interrumpible ejecutándose ");
                    Thread.sleep(2100); // Esperar 1 segundo
                } catch (InterruptedException ex) {Thread.currentThread().interrupt();}
            }

            System.out.println("Fin del thread interrumplible");
        }
    }


    public static void main(String args[]){

        // Arrancar el thread interrumpible
        ThreadInterrumpible interrumpible = new ThreadInterrumpible();
        interrumpible.start();

        // Esperar un poco
        try {
            Thread.sleep(4500);
        } catch (InterruptedException ex) {Thread.currentThread().interrupt();}

        // Interrumpir
        interrumpible.detener();

        // El thread principal ha terminado
        System.out.println("El thread principal ha finalizado");
    }

}
