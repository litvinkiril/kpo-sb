package ru.moscowzoo.io;

/**
 * Abstraction for console IO used by the CLI application.
 */
public interface ConsoleGateway extends AutoCloseable {
    void println(String message);

    String readLine();

    @Override
    default void close() {
        // no-op by default
    }
}
