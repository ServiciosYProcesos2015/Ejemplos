public class EjemploInterruptBloqueo {


    static class ThreadInterrumpible extends Thread{

        public void run(){

            System.out.println("THREAD INTERRUMPIBLE INICIADO");

            while(!isInterrupted()){ // Es importante hacer la comprobación, ya que si no el bucle no pararía

                System.out.println("THREAD INTERRUMPIBLE EJECUTANDOSE");
                try {


                    Thread.sleep(2000); // Esperar

                } catch (InterruptedException ex) {
                    System.out.println("THREAD INTERRUMPIBLE INTERRUMPIDO");
                    // Poner de nuevo el flag de interrupted
                    Thread.currentThread().interrupt();
                }
            }

            System.out.println("THREAD INTERRUMPIBLE FINALIZADO");
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
