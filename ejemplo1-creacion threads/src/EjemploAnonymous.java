/**
 * Created by alvaro on 2/11/15.
 */
public class EjemploAnonymous {

    public static void main(String args[]){

        new Thread(new Runnable(){
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    try {
                        System.out.println("Hola anonymous "+i);
                        Thread.sleep(1000); // Esperar 1 segundo
                    } catch (InterruptedException ex) {Thread.currentThread().interrupt();}
                }
            }
        }).start();

        new Thread(() -> {
                    for(int i=0;i<10;i++){
                        try {
                            System.out.println("Hola lambda "+i);
                            Thread.sleep(1000); // Esperar 1 segundo
                        } catch (InterruptedException ex) {Thread.currentThread().interrupt();}
                    }
                }
            ).start();
    }
}
