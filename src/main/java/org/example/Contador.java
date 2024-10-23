package org.example;

public class Contador extends Thread {
    public int valor;

    public Contador() {
        valor = 0;
    }

    public void incrementar() {
        valor++;
    }

    public void decrementar() {
        valor--;
    }
}
