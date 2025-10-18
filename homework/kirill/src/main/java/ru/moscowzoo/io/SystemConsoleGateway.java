package ru.moscowzoo.io;

import java.io.PrintStream;
import java.util.Objects;
import java.util.Scanner;

/**
 * Console implementation backed by {@link System#in} and {@link System#out}.
 */
public final class SystemConsoleGateway implements ConsoleGateway {
    private final Scanner scanner;
    private final PrintStream out;

    public SystemConsoleGateway() {
        this(new Scanner(System.in), System.out);
    }

    public SystemConsoleGateway(Scanner scanner, PrintStream out) {
        this.scanner = Objects.requireNonNull(scanner, "scanner");
        this.out = Objects.requireNonNull(out, "out");
    }

    @Override
    public void println(String message) {
        out.println(message);
    }

    @Override
    public String readLine() {
        return scanner.nextLine();
    }

    @Override
    public void close() {
        scanner.close();
    }
}
