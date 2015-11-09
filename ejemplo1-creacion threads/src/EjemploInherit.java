public class EjemploInherit {

    static class ThreadEjemplo extends Thread{

        public void run(){
            for(int i=0;i<10;i++){
                try {
                    System.out.println("Hola "+i);
                    Thread.sleep(1000); // Esperar 1 segundo
                } catch (InterruptedException ex) {Thread.currentThread().interrupt();}
            }
        } // run
    } // T1


    public static void main(String args[]){

        ThreadEjemplo ejemplo;

        ejemplo = new ThreadEjemplo();
        ejemplo.start();

        //(new ThreadEjemplo()).start();
    }
}
