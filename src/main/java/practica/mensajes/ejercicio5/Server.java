package practica.mensajes.ejercicio5;

import java.util.concurrent.BlockingQueue;

class Server implements Runnable {
    private final BlockingQueue<Request> canal;

    public Server(BlockingQueue<Request> canal) {
        this.canal = canal;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Request req = canal.take();
                new Thread(() -> {
                    int n = (int) (Math.random() * 10);
                    System.out.println("Servidor: Número a adivinar: " + n);
                    try {
                        while (true) {
                            int intento = req.intento.take();
                            if (intento == n) {
                                req.resultado.put(true);
                                break;
                            } else {
                                req.resultado.put(false);
                            }
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }).start();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}