package practica.mensajes.ejercicio5;

import java.util.concurrent.BlockingQueue;

public class Cliente implements Runnable {
    private final BlockingQueue<Request> canal;
    private final int id;

    public Cliente(BlockingQueue<Request> canal, int id) {
        this.canal = canal;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            Request req = new Request();
            canal.put(req);
            req.intento.put(intento());
            // Simula la entrada del usuario
            while (true) {
                boolean resultado = req.resultado.take();
                if (resultado) {
                    System.out.println("Cliente " + id + ": Adiviné el número");
                    break;
                } else {
                    System.out.println("Cliente " + id + ": No adiviné el número");
                    req.intento.put(intento());
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private int intento() {
        return (int) (Math.random() * 10);
    }
}
