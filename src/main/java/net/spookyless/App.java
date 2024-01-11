package net.spookyless;

import net.spookyless.actors.Reader;
import net.spookyless.actors.Writer;
import net.spookyless.resource.Library;

public class App {
    public static void main(String[] args) {
        int readerCount = args.length > 0 ? Integer.parseInt(args[0]) : 10;
        int writerCount = args.length > 1 ? Integer.parseInt(args[1]) : 3;
        int readerCapacity = args.length > 2 ? Integer.parseInt(args[2]) : 5;

        Library library = new Library(readerCapacity);

        for(int i = 0; i < readerCount; i++) {
            Reader r = new Reader(library);
            r.start();
        }

        for(int i = 0; i < writerCount; i++) {
            Writer w = new Writer(library);
            w.start();
        }
    }
}
