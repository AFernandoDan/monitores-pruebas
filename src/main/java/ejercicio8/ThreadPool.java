package ejercicio8;

public class ThreadPool {
    Buffer tasks;

    ThreadPool(int n) {
        this.tasks = new Buffer(n);
        for (int i = 0; i < n; i++) {
            new Worker(tasks).start();
        }
    }

    public void launch(Runnable task) {
        tasks.write(task);
    }
}
