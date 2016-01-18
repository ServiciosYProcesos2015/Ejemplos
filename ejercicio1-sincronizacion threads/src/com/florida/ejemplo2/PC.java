package com.florida.ejemplo2;

class Utils {
    static void sleep(long milis){
        try{
            Thread.sleep(milis);
        }catch(InterruptedException e){}
    }
}

class Q {
    int n;
    synchronized int get() {
        System.out.println("Obtengo: " + n);
        return n;
    }
    synchronized void put(int n) {
        this.n = n;
        System.out.println("Pongo: " + n);
    }
}
class Producer implements Runnable {
    Q q;
    Producer(Q q) {
        this.q = q;
        new Thread(this, "Productor").start();
    }
    public void run() {
        int i = 0;
        while(true) {
            Utils.sleep(300);
            q.put(i++);
        }
    }
}
class Consumer implements Runnable {
    Q q;
    Consumer(Q q) {
        this.q = q;
        new Thread(this, "Consumidor").start();
    }
    public void run() {
        while(true) {
            Utils.sleep(500);
            q.get();
        }
    }
}
public class PC {
    public static void main(String args[]) {
        Q q = new Q();
        new Producer(q);
        new Consumer(q);
        System.out.println("Pulsa Control-C para parar.");
    }
}













