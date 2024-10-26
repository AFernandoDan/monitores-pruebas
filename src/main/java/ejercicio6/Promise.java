package ejercicio6;

public class Promise implements Future {

    private Object result;
    Promise() {
        this.result = null;
    }

    public synchronized void set(Object result) {
        this.result = result;
        notifyAll();
    }

    @Override
    public synchronized Object get() throws InterruptedException {
        while (result == null) {
            wait();
        }
        return result;
    }
}
