public class EjemploRunnable {

    static class RunnableEjemplo implements Runnable{

        public void run(){
            for(int i=0;i<10;i++){
                try {
                    System.out.println("Hola runnable "+i);
                    Thread.sleep(1000); // Esperar 1 segundo
                } catch (InterruptedException ex) {Thread.currentThread().interrupt();}
            }
        } // run
    } // T1


    public static void main(String args[]){

        RunnableEjemplo runnableEjemplo;
        Thread thread;

        runnableEjemplo = new RunnableEjemplo();
        thread = new Thread(runnableEjemplo);
        thread.start();

        //(new Thread(new RunnableEjemplo())).start();

    }
}
