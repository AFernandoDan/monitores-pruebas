package ejercicio3;

public class ProductorConsumidorTest {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(2);
        Productor productor = new Productor(buffer);
        Consumidor consumidor = new Consumidor(buffer);
        productor.start();
        consumidor.start();
    }
}
