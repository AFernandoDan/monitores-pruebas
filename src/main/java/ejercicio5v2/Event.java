package ejercicio5v2;

public class Event {
    boolean wasPublished = false;
    public Event() {
        wasPublished = false;
    }

    public synchronized void subscribe() {
        while (!wasPublished) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public synchronized void publish() {
        wasPublished = true;
        notifyAll();
    }
}