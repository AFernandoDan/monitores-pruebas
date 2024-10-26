package utils;

import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class StandardOutTest {
    protected final PrintStream standardOut = System.out;
    protected final ByteArrayOutputStream outContent = new ByteArrayOutputStream();


    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outContent));
        System.setOut(new PrintStream(outContent));
    }
}
