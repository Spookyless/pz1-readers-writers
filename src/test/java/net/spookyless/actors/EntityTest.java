package net.spookyless.actors;

import net.spookyless.resource.Library;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EntityTest {
    private Library library;

    @BeforeEach
    void beforeEach() {
        library = new Library(5);
    }

    @Test
    void testReaderLifeCycle() {
        Entity entity = new Reader(library);

        assertDoesNotThrow(() -> entity.start());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertTrue(entity.isAlive());

        entity.interrupt();

        assertDoesNotThrow(() -> entity.join());
        assertFalse(entity.isAlive());
    }

    @Test
    void testWriterLifeCycle() {
        Entity entity = new Writer(library);

        assertDoesNotThrow(() -> entity.start());

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertTrue(entity.isAlive());

        entity.interrupt();

        assertDoesNotThrow(() -> entity.join());
        assertFalse(entity.isAlive());
    }
}
