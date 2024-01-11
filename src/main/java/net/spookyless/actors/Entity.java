package net.spookyless.actors;

import net.spookyless.resource.Library;

public class Entity extends Thread {
    protected String name;
    protected final Library library;

    public Entity(Library library) {
        this.library = library;
    }

    @Override
    public void run() {
        while(true) {
            try {
                logIncoming();

                library.enter(this);
                logEntry();

                Thread.sleep(getRandomWaitingTime());

                library.leave(this);
                logLeave();

                Thread.sleep(getRandomWaitingTime());
            } catch (InterruptedException e) {
                System.out.println(getThreadIdentifier() + " broke");
            }
        }
    }

    private static long getRandomWaitingTime() {
        return Math.round((1 + Math.random() * 2) * 1000);
    }

    private String getThreadIdentifier() {
        return "[" + name + "]" + " Thread #" + getId();
    }

    private void logIncoming() {
        System.out.println(getThreadIdentifier() + " wants to enter");
    }

    private void logEntry() {
        System.out.println(getThreadIdentifier() + " entered");
    }

    private void logLeave() {
        System.out.println(getThreadIdentifier() + " left");
    }
}
