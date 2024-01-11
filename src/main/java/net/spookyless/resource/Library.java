package net.spookyless.resource;

import net.spookyless.actors.Entity;
import net.spookyless.actors.Writer;

import java.util.concurrent.Semaphore;

public class Library {
    private final int readerCapacity;
    private final Semaphore lock;

    private final Integer[] counters = {0, 0, 0, 0};

    public Library(int readerCapacity) {
        this.readerCapacity = readerCapacity;
        this.lock = new Semaphore(readerCapacity, true);
    }

    public void enter(Entity guest) {
        int permitCount = getPermitCount(guest);

        try {
            counters[getOffset(guest, false)] += 1;
            lock.acquire(permitCount);

            counters[getOffset(guest, false)] -= 1;
            counters[getOffset(guest, true)] += 1;
        } catch (InterruptedException e) {
            System.out.printf(e.getMessage());
        }

        postAction();
    }

    public void leave(Entity guest) {
        lock.release(getPermitCount(guest));

        counters[getOffset(guest, true)] -= 1;

        postAction();
    }

    private void postAction() {
        printStats();
    }

    private static boolean isWriter(Entity guest) {
        return guest instanceof Writer;
    }

    private static int getOffset(Entity guest, boolean isInside) {
        return getOffset(isWriter(guest), isInside);
    }

    private static int getOffset(boolean isWriter, boolean isInside) {
        return (isWriter ? 1 : 0) << 1 | (isInside ? 1 : 0);
    }

    private int getPermitCount(Entity guest) {
        return guest instanceof Writer ? this.readerCapacity : 1;
    }

    public void printStats() {
        System.out.println("[Library] Readers: " + counters[getOffset(false, false)] + " in queue / " + counters[getOffset(false, true)] + " inside");
        System.out.println("[Library] Writers: " + counters[getOffset(true, false)] + " in queue / " + counters[getOffset(true, true)] + " inside");
    }
}
