package ejercicio6;

public class PruebaDirecta {

    public static void main(String[] args) {
        Promise promise = new Promise();
        Thread threadGenerator = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            promise.set("data");
        });

        Thread threadReciver = new Thread(() -> {
            try {
                System.out.println(promise.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadGenerator.start();
        threadReciver.start();

        try {
            threadGenerator.join();
            threadReciver.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
