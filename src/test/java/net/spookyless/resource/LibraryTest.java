package net.spookyless.resource;

import net.spookyless.actors.Reader;
import net.spookyless.actors.Writer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    @Test
    void testLibraryWithReadersAndWriters() {
        Library library = new Library(3);

        Reader reader = new Reader(library);
        Writer writer = new Writer(library);

        assertDoesNotThrow(() -> {
            library.enter(reader);

            Thread.sleep(1000);

            library.leave(reader);
        });

        assertDoesNotThrow(() -> {
            library.enter(writer);

            Thread.sleep(1000);

            library.leave(writer);
        });
    }
}
