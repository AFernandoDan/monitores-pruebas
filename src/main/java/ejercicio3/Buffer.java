package ejercicio3;

public class Buffer {

    private final Object[] buffer;
    private int inicio = 0;
    private int fin = 0;
    final int dimension;

    public Buffer(int dimension) {
        this.dimension = dimension;
        this.buffer = new Object[dimension + 1];
    }

    public synchronized void write(Object o) {
        while (next(inicio) == fin) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        this.buffer[inicio] = o;
        inicio = next(inicio);
        notifyAll();
    }

    public synchronized Object read() {
        while (inicio == fin) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        Object result = buffer[fin];
        fin = next(fin);
        notifyAll();
        return result;
    }

    private int next(int i) {
        return (i + 1) % (dimension + 1);
    }
}