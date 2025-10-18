package ru.moscowzoo.app;

import ru.moscowzoo.clinic.HealthInspection;
import ru.moscowzoo.clinic.InteractiveHealthInspection;
import ru.moscowzoo.clinic.VeterinaryClinic;
import ru.moscowzoo.di.ServiceRegistry;
import ru.moscowzoo.io.ConsoleGateway;
import ru.moscowzoo.io.SystemConsoleGateway;
import ru.moscowzoo.zoo.Zoo;

/**
 * Application entry point configuring dependencies.
 */
public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        ServiceRegistry registry = new ServiceRegistry();
        registry.registerSingleton(ConsoleGateway.class, SystemConsoleGateway::new);
        registry.registerSingleton(HealthInspection.class, () -> new InteractiveHealthInspection(registry.resolve(ConsoleGateway.class)));
        registry.registerSingleton(VeterinaryClinic.class, () -> new VeterinaryClinic(registry.resolve(HealthInspection.class)));
        registry.registerSingleton(Zoo.class, () -> new Zoo(registry.resolve(VeterinaryClinic.class)));
        registry.registerSingleton(ZooConsole.class, () -> new ZooConsole(registry.resolve(ConsoleGateway.class), registry.resolve(Zoo.class)));

        ConsoleGateway gateway = registry.resolve(ConsoleGateway.class);
        try {
            registry.resolve(ZooConsole.class).run();
        } finally {
            try {
                gateway.close();
            } catch (Exception ignored) {
                // suppress console closing issues
            }
        }
    }
}
