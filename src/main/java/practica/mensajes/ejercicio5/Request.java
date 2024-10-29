package practica.mensajes.ejercicio5;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Request {
    BlockingQueue<Boolean> resultado;
    BlockingQueue<Integer> intento;

    public Request() {
        this.resultado = new ArrayBlockingQueue<>(1);
        this.intento = new ArrayBlockingQueue<>(1);
    }
}
