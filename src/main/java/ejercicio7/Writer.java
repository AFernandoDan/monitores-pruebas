package ejercicio7;

public class Writer extends Thread {
    private RW rw;
    private int id;

    public Writer(RW rw, int id) {
        this.rw = rw;
        this.id = id;
    }

    public void run() {
        int i = 0;
        while (true) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            rw.beginWrite();
            // espera 300ms simulando escritura
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            rw.value = "Valor " + i + " escrito por el escritor " + id;
            rw.endWrite();
            i++;
        }
    }
}
