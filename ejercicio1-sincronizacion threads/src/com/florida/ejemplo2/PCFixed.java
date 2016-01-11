package com.florida.ejemplo2;

class UtilsFixed {
    static void sleep(long milis){
        try{
            Thread.sleep(milis);
        }catch(InterruptedException e){}
    }
}

class QFixed {
    int n;
    boolean valueSet = false;
    synchronized int get() {
        if(!valueSet)
            try {
                wait();
            } catch(InterruptedException e) {
                System.out.println("InterruptedException capturada");
            }
        System.out.println("Obtengo (F): " + n);
        valueSet = false;
        notify();
        return n;
    }
    synchronized void put(int n) {
        if(valueSet)
            try {
                wait();
            } catch(InterruptedException e) {
                System.out.println("InterruptedException capturada");
            }
        this.n = n;
        valueSet = true;
        System.out.println("Pongo (F): " + n);
        notify();
    }
}
class ProducerFixed implements Runnable {
    QFixed q;
    ProducerFixed(QFixed q) {
        this.q = q;
        new Thread(this, "Productor").start();
    }
    public void run() {
        int i = 0;
        while(true) {
            UtilsFixed.sleep(300);
            q.put(i++);
        }
    }
}
class ConsumerFixed implements Runnable {
    QFixed q;
    ConsumerFixed(QFixed q) {
        this.q = q;
        new Thread(this, "Consumidor").start();
    }
    public void run() {
        while(true) {
            UtilsFixed.sleep(500);
            q.get();
        }
    }
}
class PCFixed {
    public static void main(String args[]) {
        QFixed q = new QFixed();
        new ProducerFixed(q);
        new ConsumerFixed(q);
        System.out.println("Pulsa Control-C para parar.");
    }
}