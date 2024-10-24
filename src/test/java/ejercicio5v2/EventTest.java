package ejercicio5v2;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;

public class EventTest {
    @Test
    public void testEvent() {
        AtomicBoolean wasPublished = new AtomicBoolean(false);
        AtomicBoolean received = new AtomicBoolean(false);

        Event event = new Event();
        Thread subscriber = new Thread(() -> {
            event.subscribe();
            received.set(true);
        });

        Thread publisher = new Thread(() -> {
            event.publish();
            wasPublished.set(true);
            System.out.println("Event published");
        });

        subscriber.start();
        publisher.start();

        try {
            subscriber.join();
            publisher.join();

            assert(received.get());
            assert(wasPublished.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
