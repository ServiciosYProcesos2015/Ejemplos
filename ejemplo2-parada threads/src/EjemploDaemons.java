public class EjemploDaemons {

    public static void main(String args[]){

        Thread daemon = new Thread(new Runnable(){
                @Override
                public void run() {
                    for(int i=1;i<=999;i++){
                        try {
                            System.out.println("DAEMON "+i);
                            Thread.sleep(200); // Esperar 1 segundo
                        } catch (InterruptedException ex) {Thread.currentThread().interrupt();}
                    }
                }
            });

        Thread normal = new Thread(new Runnable(){
                @Override
                public void run() {
                    for(int i=1;i<=5;i++){
                        try {
                            System.out.println("Soy un thread normal "+i);
                            Thread.sleep(1000); // Esperar 1 segundo
                        } catch (InterruptedException ex) {Thread.currentThread().interrupt();}
                    }
                }
            });

        daemon.setDaemon(true);
        daemon.start();

        normal.start();
    }
}
