package practica.mensajes.ejercicio5;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<Request> canal = new ArrayBlockingQueue<>(10);

        Thread serverThread = new Thread(new Server(canal));
        Thread c1 = new Thread(new Cliente(canal, 1));
        Thread c2 = new Thread(new Cliente(canal, 2));


        serverThread.start();
        c1.start();
        c2.start();
    }
}