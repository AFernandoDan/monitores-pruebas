package ejercicio3;
public class Consumidor extends Thread {

    private final Buffer buffer;

    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
    }

    public void consumir() {
        int n = (int) buffer.read();
        System.out.println("Consumiendo concurrentemente: " + n);
        // parar cuando llegue a 100
        if (n == 100) {
            System.out.println("El consumidor ha llegado a 100, terminando...");
            System.exit(0);
        }
    }

    @Override
    public void run() {
        while (true) {
            consumir();
        }
    }
}
