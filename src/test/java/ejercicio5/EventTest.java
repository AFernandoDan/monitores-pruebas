package ejercicio5;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventTest {

    private Event event;
    private Thread subscriber1;
    private Thread subscriber2;
    private Thread suscriberRetrasado;

    // guarda las variables del out
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();


    @BeforeEach
    public void setup() {
        this.event = new Event();
        System.setOut(new PrintStream(outContent));

        this.subscriber1 = new Thread(() -> {
            System.out.print("S1 esperando ");
            String evento = (String) event.subscribe();
            System.out.print("S1 recibio el evento: " + evento);
        });

        this.subscriber2 = new Thread(() -> {
            System.out.print("S2 esperando ");
            String evento = (String) event.subscribe();
            System.out.print("S2 recibio el evento: " + evento);
        });

        this.suscriberRetrasado = new Thread(() -> {
            try {
                Thread.sleep(120);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("S3 esperando ");
            String evento = (String) event.subscribe();
            System.out.print("S3 recibio el evento: " + evento);
        });
    }
    @Test
    public void testEvent() throws InterruptedException {


        subscriber1.start();
        subscriber2.start();

        // Asegurarse de que los suscriptores estén esperando
        Thread.sleep(100);

        // Publicar evento
        event.publish("Evento Publicado");

        // Esperar a que los suscriptores reciban el evento
        subscriber1.join();
        subscriber2.join();

        System.setOut(standardOut);

        String output = outContent.toString();

        assertTrue(output.contains("S1 esperando"));
        assertTrue(output.contains("S2 esperando"));
        assertTrue(output.contains("S1 recibio el evento: Evento Publicado"));

    }

    @Test
    public void testEventConSuscriptorRetrasado() throws InterruptedException {
        subscriber1.start();
        subscriber2.start();
        suscriberRetrasado.start();

        // Asegurarse de que los suscriptores estén esperando
        Thread.sleep(100);

        // Publicar evento
        event.publish("Evento Publicado");

        // Esperar a que los suscriptores reciban el evento
        subscriber1.join();
        subscriber2.join();

        // Esperar unos segundos ya que la idea
        // es que el suscriptor retrasado no reciba el evento
        Thread.sleep(100);
        System.setOut(standardOut);

        String output = outContent.toString();

        assertTrue(output.contains("S1 esperando"));
        assertTrue(output.contains("S2 esperando"));
        assertTrue(output.contains("S1 recibio el evento: Evento Publicado"));
        assertTrue(output.contains("S2 recibio el evento: Evento Publicado"));
        assertTrue(output.contains("S3 esperando"));
    }

    @Test
    public void testEventPublicLuegoSuscribeNoRecibe() throws InterruptedException {
        event.publish("Evento Publicado 1");

        subscriber1.start();
        subscriber2.start();

        // Asegurarse de que los suscriptores estén esperando
        Thread.sleep(100);

        event.publish("Evento Publicado 2");

        // Esperar a que los suscriptores reciban el evento
        subscriber1.join();
        subscriber2.join();

        System.setOut(standardOut);

        String output = outContent.toString();

        assertTrue(output.contains("S1 esperando"));
        assertTrue(output.contains("S2 esperando"));
        assertTrue(output.contains("S1 recibio el evento: Evento Publicado 2"));
        assertTrue(output.contains("S2 recibio el evento: Evento Publicado 2"));
    }
}
