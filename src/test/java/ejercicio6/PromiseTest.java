package ejercicio6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.StandardOutTest;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PromiseTest extends StandardOutTest {
    private Thread threadGenerator;
    private Thread threadReceiver;
    private Promise promise; // Declarar Promise como un campo de clase
    private String recieveData;

    @BeforeEach
    public void setup() {
        promise = new Promise(); // Inicializar Promise aquí

        threadGenerator = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            promise.set("data");
        });

        threadReceiver = new Thread(() -> {
            try {
                recieveData = (String) promise.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    // test
    @Test
    public void testPromise() {
        threadGenerator.start();
        threadReceiver.start();

        try {
            threadGenerator.join();
            threadReceiver.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals("data", recieveData);
    }
}
