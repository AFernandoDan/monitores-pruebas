package ejercicio5;

public class Event {
    private Object eventObject;
    private int listeners;

    public Event() {
        this.eventObject = null;
        this.listeners = 0;
    }

    public synchronized Object subscribe() {
        while (eventObject != null) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        listeners++;
        while (eventObject == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        listeners--;
        Object aux = eventObject;
        if (listeners == 0) {
            eventObject = null;
        }
        return aux;
    }

    public synchronized void publish(Object eventObject) {
        while (this.eventObject != null) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        if (listeners > 0) this.eventObject = eventObject;
        notifyAll();
    }
}