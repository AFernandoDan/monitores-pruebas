package ejercicio8;

public class Main {

    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool(4);
        for (int i = 0; i < 10; i++) {
            final int j = i;
            pool.launch(() -> {
                System.out.println("Task " + j + " started");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Task " + j + " finished");
            });
        }
    }
}
