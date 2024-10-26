package ejercicio7;

public class RWExample {
    public static void main(String[] args) {
        RW rw = new RW();
        Writer w1 = new Writer(rw, 1);
        Writer w2 = new Writer(rw, 2);
        Reader r1 = new Reader(rw, 1);
        Reader r2 = new Reader(rw, 2);
        Reader r3 = new Reader(rw, 3);
        Reader r4 = new Reader(rw, 4);
        w1.start();
        w2.start();
        r1.start();
        r2.start();
        r3.start();
        r4.start();
    }
}
