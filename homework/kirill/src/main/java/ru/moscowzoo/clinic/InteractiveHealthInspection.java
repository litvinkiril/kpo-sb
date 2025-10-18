package ru.moscowzoo.clinic;

import java.util.Locale;
import ru.moscowzoo.domain.animal.Animal;
import ru.moscowzoo.io.ConsoleGateway;

/**
 * Interactive inspection that asks the operator about the health state.
 */
public final class InteractiveHealthInspection implements HealthInspection {
    private final ConsoleGateway console;

    public InteractiveHealthInspection(ConsoleGateway console) {
        this.console = console;
    }

    @Override
    public InspectionReport inspect(Animal candidate) {
        console.println("Проверка здоровья животного " + candidate.getInventoryName());
        console.println("Животное здорово? (y/n):");
        String answer = console.readLine();
        if (answer == null) {
            return InspectionReport.rejected("Нет ответа от ветеринара");
        }
        String normalized = answer.trim().toLowerCase(Locale.ROOT);
        if (normalized.startsWith("y") || normalized.startsWith("д")) {
            return InspectionReport.accepted("Осмотр подтвержден");
        }
        return InspectionReport.rejected("Осмотр не пройден");
    }
}
