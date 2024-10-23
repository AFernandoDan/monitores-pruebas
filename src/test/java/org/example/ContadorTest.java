package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContadorTest {

    @Test
    public void testIncrementar2ContadoresApuntandoAlMismoObjeto() {
        Contador contador = new Contador();
        Contador otroContador = contador;
        contador.incrementar();
        assertEquals(1, otroContador.valor);
    }

    @Test
    public void testDecrementar() {
        Contador contador = new Contador();
        contador.decrementar();
        assertEquals(-1, contador.valor);
    }
}
