package ejercicio7;

public class Reader extends Thread {
    private RW rw;
    private int id;

    public Reader(RW rw, int id) {
        this.rw = rw;
        this.id = id;
    }

    public void run() {
        while (true) {
            // simula espera
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            rw.beginRead();
            System.out.println("Lector " + id + " lee: " + rw.value);
            rw.endRead();
        }
    }
}
