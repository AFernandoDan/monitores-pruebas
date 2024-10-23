package ejercicio3;

public class Productor extends Thread {

    private final Buffer buffer;
    private int n = 1;

    public Productor(Buffer buffer) {
        this.buffer = buffer;
    }

    public void producir() {
        this.buffer.write(n);
        n++;
    }

    @Override
    public void run() {
        while (n <= 100) {
            producir();
        }
        System.out.println("El productor ha llegado a 100, terminando...");
    }
}