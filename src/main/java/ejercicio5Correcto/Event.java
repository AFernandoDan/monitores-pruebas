package ejercicio5Correcto;

public class Event {
    private int i;

    public Event() {
        this.i = 0;
    }

    public synchronized void subscribe() {
        int localI = this.i;
        while(localI == i) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public synchronized void publish() {
        i++;
        notifyAll();
    }
}