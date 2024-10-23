package ejercicio4;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BarreraTest {

    @Test
    public void testBarreraOutput() throws InterruptedException {
        // Redirect System.out to a ByteArrayOutputStream
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Create and start threads
        Barrera miBarrera = new Barrera(3);

        Thread t1 = new Thread(() -> {
            System.out.print("a");
            miBarrera.esperar();
            System.out.print(1);
        });

        Thread t2 = new Thread(() -> {
            System.out.print("b");
            miBarrera.esperar();
            System.out.print(2);
        });

        Thread t3 = new Thread(() -> {
            System.out.print("c");
            miBarrera.esperar();
            System.out.print(3);
        });

        t1.start();
        t2.start();
        t3.start();

        // Wait for threads to finish
        t1.join();
        t2.join();
        t3.join();

        // Restore original System.out
        System.setOut(originalOut);

        // Verify the output three first characters are a combination of "a", "b" and "c"
        String[] firstThreeChars = outContent.toString().substring(0, 3).split("");

        assertEquals(3, firstThreeChars.length);
        assert(firstThreeChars[0].matches("[abc]"));


        // Verify the output three last characters are a combination of "1", "2" and "3"
        String[] lastThreeChars = outContent.toString().substring(3).split("");

        assertEquals(3, lastThreeChars.length);
        assert(lastThreeChars[0].matches("[123]"));
    }

    @Test
    public void testBarreraOutputConOtrasEsperasLuegoDeLasPrimeras() throws InterruptedException {
        // Redirect System.out to a ByteArrayOutputStream
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Create and start threads
        Barrera miBarrera = new Barrera(3);

        Thread t1 = new Thread(() -> {
            System.out.print("a");
            miBarrera.esperar();
            System.out.print(1);
            miBarrera.esperar();
            System.out.print("x");
        });

        Thread t2 = new Thread(() -> {
            System.out.print("b");
            miBarrera.esperar();
            System.out.print(2);
            miBarrera.esperar();
            System.out.print(2);
        });

        Thread t3 = new Thread(() -> {
            System.out.print("c");
            miBarrera.esperar();
            System.out.print(3);
            miBarrera.esperar();
            System.out.print("z");
        });

        t1.start();
        t2.start();
        t3.start();

        // Wait for threads to finish
        t1.join();
        t2.join();
        t3.join();

        // Restore original System.out
        System.setOut(originalOut);

        // Verify the output three first characters are a combination of "a", "b" and "c"
        String[] firstThreeChars = outContent.toString().substring(0, 3).split("");
        assertEquals(3, firstThreeChars.length);
        assert(firstThreeChars[0].matches("[abc]"));

        // Verify the output the last six characters are a combination of "1", "2", "3", "x", "y" and "z"
        String[] lastSixChars = outContent.toString().substring(3).split("");
        assertEquals(6, lastSixChars.length);
        assert(lastSixChars[0].matches("[123xz]"));
    }
}