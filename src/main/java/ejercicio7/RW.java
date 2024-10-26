package ejercicio7;

import java.io.Serializable;

public class RW {

    private int writers = 0;
    private int readers = 0;
    public Serializable value;

    public synchronized void beginWrite() {
        while (!canWrite()) {
            try { wait(); }
            catch (InterruptedException e) { return; }
        }
        writers = 1;
    }

    public synchronized void endWrite() {
        writers = 0;
        notifyAll();
    }

    public synchronized void beginRead() {
        while (!canRead()) {
            try { wait(); }
            catch (InterruptedException e) { return; }
        }
        readers++;
    }

    public synchronized void endRead() {
        readers--;
        if (readers == 0)
            notify();
    }

    private boolean canRead() {
        return writers == 0;
    }

    private boolean canWrite() {
        return writers == 0 && readers == 0;
    }
}
