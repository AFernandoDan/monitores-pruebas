package ejercicio8;

public class Worker extends Thread {
    private Buffer tasks;

    public Worker(Buffer tasks) {
        this.tasks = tasks;
    }

    public void run() {
        while (true) {
            Runnable task = (Runnable) tasks.read();
            task.run();
        }
    }
}
