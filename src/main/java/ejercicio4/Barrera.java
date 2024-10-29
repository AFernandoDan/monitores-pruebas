package ejercicio4;

public class Barrera {

    private final int tamanio;
    private int listos = 0;

    public Barrera(int tamanio) {
        this.tamanio = tamanio;
    }

    public synchronized void esperar() {
        if (listos < tamanio)
            listos++;
        while (listos < tamanio) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        notifyAll();
    }
}
